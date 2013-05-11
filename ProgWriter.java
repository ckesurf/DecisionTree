import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

/**
 * 
 */

/**
 * @author Chris Erlendson
 *
 */
public class ProgWriter {


	public void writeRec(Node<Attribute> node) throws IOException
	{
		
		try{

			FileWriter fstream = new FileWriter("OutputTree.java",true);
			BufferedWriter fbw = new BufferedWriter(fstream);
			/* answer node */
			if (node.getData().getCls() != null) {
			fbw.write("\tSystem.out.println(\"" + node.getData().getCls()
					+ "\");\n\treturn;");
			} else {
				int attr = node.getData().getCol();
				for (Iterator<Node<Attribute>> it = node.children.iterator(); it.hasNext();) {
					Node<Attribute> child = it.next();
					fbw.write("if (input.get(" + node.getData().getCls() + ").equals(\"" +
							child.getData().getValue() + "\") {\n");
					writeRec(child);
					fbw.write("}");
					if (it.hasNext())
						fbw.write(" else ");
				}
				fbw.newLine();
			}
			fbw.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void write(DecisionTree dt) throws IOException
	{
		/* Basic setup for our program */
		PrintWriter writer = new PrintWriter("OutputTree.java", "UTF-8");
		writer.println("import java.io.*;");
		writer.println("import java.util.*;");
		writer.println("import javax.swing.JTree");
		writer.println("import javax.swing.tree.DefaultMutableTreeNode;\n");
		writer.println("public class OutputTree {");
		writer.println("public static void main(String[] args) {");
		
		
		writer.println("\tSystem.out.println(\"Hello World!\");");
		writeRec(dt.root);
		
		writer.println("}\n}");
		writer.close();
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DecisionTree dlt = new DecisionTree();
		dlt.parser(args[0]);
		ProgWriter progWriter = new ProgWriter();
		try {
			progWriter.write(dlt);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
