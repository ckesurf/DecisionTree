import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.io.*;

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
						+ "\");\n");
			} else {
				/* question node */
				int attr = node.getData().getCol();
				for (Iterator<Node<Attribute>> it = node.children.iterator(); it.hasNext();) {
					Node<Attribute> child = it.next();
					fbw.write("if (input.get(" + attr + ").getValue().equals(\"" +
							child.getData().getValue() + "\")) {\n");


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
		FileWriter fstream = new FileWriter("OutputTree.java",true);
		BufferedWriter fbw = new BufferedWriter(fstream);
		fbw.write("\n");
		
		writeRec(dt.root, fbw);
		
		fbw.write("\n}\n}\n}");
		fbw.close();

	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DecisionTree dlt = new DecisionTree();
		dlt.parser(args[0]);
		
		
		InputStream inStream = null;
		OutputStream outStream = null;
	 
	    	try{
	 
	    	    File afile =new File("template.txt");
	    	    File bfile =new File("OutputTree.java");
	 
	    	    inStream = new FileInputStream(afile);
	    	    outStream = new FileOutputStream(bfile);
	 
	    	    byte[] buffer = new byte[1024];
	 
	    	    int length;
	    	    //copy the file content in bytes 
	    	    while ((length = inStream.read(buffer)) > 0){
	 
	    	    	outStream.write(buffer, 0, length);
	 
	    	    }
	 
	    	    inStream.close();
	    	    outStream.close();
	 
	    	}catch(IOException e){
	    		e.printStackTrace();
	    	}
		
		
		ProgWriter progWriter = new ProgWriter();
		try {
			progWriter.write(dlt);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
