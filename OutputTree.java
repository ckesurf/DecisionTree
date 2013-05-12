import java.io.*;
import java.util.*;


public class OutputTree {
	public static void main(String[] args) {

		Vector<Vector<Attribute>> inputs = new Vector<Vector<Attribute>>();
		Vector<Attribute> dataRow;

		try {

			//csv file containing data

			//create BufferedReader to read csv file
			BufferedReader br = new BufferedReader( new FileReader(args[0]));
			String strLine = "";
			StringTokenizer st = null;

			//read comma separated file line by line
			while( (strLine = br.readLine()) != null) {
				dataRow = new Vector<Attribute>(11);
				//break comma separated line using ","
				st = new StringTokenizer(strLine, ", ");

				while(st.hasMoreTokens()) {
					//display csv values
					String value = st.nextToken();
					dataRow.add(new Attribute(value));
				}

				inputs.add(dataRow);

			}


		} catch(Exception e) {
			System.out.println("Exception while reading csv file: " + e);
			return;
		}		




		for (Vector<Attribute> input : inputs) {
if (input.get(4).getValue().equals("Some")) {
	System.out.println("Yes");
} else if (input.get(4).getValue().equals("Full")) {
if (input.get(3).getValue().equals("Yes")) {
if (input.get(8).getValue().equals("Burger")) {
	System.out.println("Yes");
} else if (input.get(8).getValue().equals("French")) {
	System.out.println("No");
} else if (input.get(8).getValue().equals("Thai")) {
if (input.get(2).getValue().equals("Yes")) {
	System.out.println("Yes");
} else if (input.get(2).getValue().equals("No")) {
	System.out.println("No");
}
} else if (input.get(8).getValue().equals("Italian")) {
	System.out.println("No");
}
} else if (input.get(3).getValue().equals("No")) {
	System.out.println("No");
}
} else if (input.get(4).getValue().equals("None")) {
	System.out.println("No");
}

}
}
}