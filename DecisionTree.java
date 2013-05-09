/**
 * 
 */

/**
 * @author Chris Erlendson
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;


public class DecisionTree {

	/**
	 * 
	 * @param examples - our data set, from which we will calculate the entropy of
	 * @param classifiers - all different classes we can output.
	 * 	Key is a String of classifier, Value is number of occurences
	 * @return entropy - uncertainty of data
	 */
	public float entropy(int total, Hashtable<String, Integer> classifiers)
	{
		System.out.println("Running entropy function...");
		float total_entropy = 0;
		System.out.println("total # of examples: " + total);

		/* enumerate over all keys in examples (HashTable) */
		for (Enumeration<String> e = classifiers.keys(); e.hasMoreElements();) {
			/* c is the number of occurrences of the corresponding key */
			float c = classifiers.get(e.nextElement());
			total_entropy += (c/total)* Math.log(c/total)/Math.log(2);
		}
		return -total_entropy;
	}
	
	public float remainder(	Vector<Vector<Attribute>> examples,
							Vector<Set<String>> attr_values,
							Hashtable<String, Integer> classifiers, int attr)
	{
		float entropy = 0;
		int total = examples.size();
		Iterator<String> it = attr_values.get(attr).iterator();
		
		
		
		return entropy;
	}
	
	
	
	
	
	/**
	 * @param args - filename of csv file
	 */
	public static void main(String[] args) {

		/* parse filename and create a Vector of Attributes */
		Vector<Attribute> input;
		Vector<Vector<Attribute>> training_set = new Vector<Vector<Attribute>>();
		Vector<Set<String>> attr_values = new Vector<Set<String>>();

		try {

			//csv file containing data
			String strFile = "src/restaurant.csv";

			//create BufferedReader to read csv file
			BufferedReader br = new BufferedReader( new FileReader(strFile));
			String strLine = "";
			StringTokenizer st = null;
			/* HashTable of classifiers */
			Hashtable<String, Integer> classifiers = new Hashtable<String, Integer>();
			boolean first_time = true;

			//read comma separated file line by line
			while( (strLine = br.readLine()) != null) {
				input = new Vector<Attribute>(12);
				//break comma separated line using ","
				st = new StringTokenizer(strLine, ", ");
				int col = 0;

				while(st.hasMoreTokens()) {
					//display csv values
					String value = st.nextToken();
					input.add(new Attribute(value));
					/* add the value to the corresponding attribute,
					   if not already added */
					if (first_time)
						attr_values.add(new HashSet());
					attr_values.get(col).add(value);
					col++;
				}

				first_time = false;

				/* if classifier isn't a key in our Hashtable, add it */
				String cl = input.lastElement().toString();
				if (!classifiers.containsKey(cl))
					classifiers.put(cl, 1);
				else
					classifiers.put(cl, classifiers.get(cl) + 1);

				training_set.add(input);

			}

			System.out.println("Classifiers: " + classifiers.toString());
			System.out.println("Possible values for Attributes: " + attr_values.toString());

			/* Entropy function for total training set here */
//			float entropy = 0;
//
//			int total = training_set.size();
//			System.out.println("total # of examples: " + total);
//
//			/* enumerate over all keys in classifiers (HashTable) */
//			for (Enumeration<String> e = classifiers.keys(); e.hasMoreElements();) {
//				/* c is the number of occurrences of the corresponding key */
//				float c = classifiers.get(e.nextElement());
//				entropy += (c/total)* Math.log(c/total)/Math.log(2);
//			}
//
//			/* remember to negate */
//			System.out.println("entropy: " + (-entropy));

			DecisionTree dt = new DecisionTree();
			float entropy = dt.entropy(training_set.size(), classifiers);
			System.out.println("entroppy: " + entropy);


		} catch(Exception e) {
			System.out.println("Exception while reading csv file: " + e);                  
		}		
	}
}	

