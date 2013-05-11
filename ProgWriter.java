import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
		/* answer node */
		//		if (node.getData().getCls() != null) {

		try{

			FileWriter fstream = new FileWriter("OutputTree.java",true);
			BufferedWriter fbw = new BufferedWriter(fstream);
			fbw.write("append txt...");
			fbw.newLine();
			fbw.close();
		}catch (Exception e) {
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
		// TODO Auto-generated method stub

	}

}
