import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Vector;

/**
 * 
 */

/**
 * @author Chris Erlendson
 *
 */
public class ProgWriter {


	public void writeRec(Node<Attribute> node, BufferedWriter fbw) throws IOException
	{

		try{

			/* answer node */
			if (node.getData().getCls() != null) {
				fbw.write("\tSystem.out.println(\"" + node.getData().getCls()
						+ "\");\n\treturn;\n");
			} else {
				/* question node */
				int attr = node.getData().getCol();
				for (Iterator<Node<Attribute>> it = node.children.iterator(); it.hasNext();) {
					Node<Attribute> child = it.next();
					fbw.write("if (input.get(" + attr + ").equals(\"" +
							child.getData().getValue() + "\") {\n");


					writeRec(child, fbw);

					fbw.write("}");
					if (it.hasNext())
						fbw.write(" else ");
				}
				fbw.newLine();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void write(DecisionTree dt) throws IOException
	{
		/* Basic setup for our program */
		PrintWriter writer = new PrintWriter("OutputTree.java");
		writer.println("import java.io.*;");
		writer.println("import java.util.*;");
		writer.println("import javax.swing.JTree");
		writer.println("import javax.swing.tree.DefaultMutableTreeNode;\n");
		writer.println("public class OutputTree {");
		writer.println("public static void main(String[] args) {");
		writer.println("\tSystem.out.println(\"Hello World!\");");
		writer.close();


		FileWriter fstream = new FileWriter("OutputTree.java",true);
		BufferedWriter fbw = new BufferedWriter(fstream);
		writeRec(dt.root, fbw);
		
		fbw.write("\n}\n}");
		fbw.close();

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
