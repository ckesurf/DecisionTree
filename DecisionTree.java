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
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;


public class DecisionTree {

	JTree root;

	/**
	 * 
	 * @param examples - our data set, from which we will calculate the entropy of
	 * @param classifiers - all different classes we can output.
	 * 	Key is a String of classifier, Value is number of occurences
	 * @return entropy - uncertainty of data
	 */
	public float entropy(int total, Hashtable<String, Integer> classifiers)
	{
		// System.out.println("Running entropy function...");
		float total_entropy = 0;
		// System.out.println("total # of examples: " + total);

		/* enumerate over all keys in examples (HashTable) */
		for (Enumeration<String> e = classifiers.keys(); e.hasMoreElements();) {
			/* c is the number of occurrences of the corresponding key */
			float c = classifiers.get(e.nextElement());
			total_entropy += (c/total)* Math.log(c/total)/Math.log(2);
		}
		return -total_entropy;
	}

	/**
	 * remainder() gives us the remaining entropy when focusing on a particular
	 * attribute, thus taking the entropy of subsets based on value instead.
	 *  
	 * @param examples - given data, usually training_set or something
	 * @param attr_values - values that an attribute can have
	 * @param attr - which attribute do we want to focus on? i.e. 4 = Patrons
	 * @return remaining entropy, usually smaller than total if given 
	 * attribute divides up our original set nicely.
	 */
	public float remainder(	Vector<Vector<Attribute>> examples,
			Vector<Set<String>> attr_values,
			int attr)
	{
		float entropy = 0;
		float total = examples.size();
		int count;
		Iterator<String> itValue = attr_values.get(attr).iterator();

		/* for each value possible */
		while(itValue.hasNext()) {
			count = 0;
			String value = itValue.next();
			Iterator<Vector<Attribute>> itVector = examples.iterator();

			/*keep a Hashtable to 
			 * keep track of different classifiers within this subset, for
			 * passing to our entropy function. Think the "Full" value of 
			 * Patrons attribute, its mix of 2 positives and 3 negatives */
			Hashtable<String, Integer> clrs = new Hashtable<String, Integer>();

			/* how many examples had this value? */
			while(itVector.hasNext()) {
				Vector<Attribute> vec = itVector.next();
				/* hit? */
				if (vec.get(attr).toString().equals(value)) {
					count++;
					/* put it to our temporary Hashtable */
					String cl = vec.lastElement().toString();
					if (!clrs.containsKey(cl))
						clrs.put(cl, 1);
					else
						clrs.put(cl, clrs.get(cl) + 1);

				}

			}
			entropy += (count/total)*entropy(count, clrs);
		}

		return entropy;
	}

	public float gain(	Hashtable<String, Integer> classifiers, 
			Vector<Vector<Attribute>> examples,
			Vector<Set<String>> attr_values,
			int attr)
	{
		int totalSize = examples.size();
		float initialEntropy = entropy(totalSize, classifiers);
		float remainEntropy = remainder(examples, attr_values, attr);
		return initialEntropy - remainEntropy;
	}



	public String traverse(Node<Attribute> node, Vector<Attribute> input)
	{
		/* leaf node? */
		/* if the classifier is not null, then its a leaf, return classifier! */
		if (node.data.getCls() != null)
			return node.data.getCls();
		else {
			/* question node */
			int att = node.data.getCol();
			String v = input.get(att).getValue();

			/* traverse children, e.g. which one has "Some"? */
			Iterator<Node<Attribute>> itChild = node.children.iterator();
			while(itChild.hasNext()) {
				Node<Attribute> child = itChild.next();
				if (child.getData().getValue().equals(v))
					return traverse(child, input);
			}
			/* if we get here, throw exception: 
			 * 	input had a value that we've never seen before */
		}
		return null;
	}

	/**
	 * same returns true if every vector of data has the same class,
	 * specifically if the last element all have the same non-null cls
	 * 
	 * @param examples - the vectors of data
	 * @return true if they are all the same, false otherwise
	 */
	public boolean same(Vector<Vector<Attribute>> examples)
	{
		String cls = examples.firstElement().lastElement().getCls();
		for (Vector<Attribute> vec : examples) {
			if (!vec.lastElement().getCls().equals(cls))
				return false;
		}
		return true;
	}

	public Node<Attribute> plurality(Vector<Vector<Attribute>> examples)
	{
		/* Hashtable of how many classes, will tally up and see max later */
		Hashtable<String, Integer> clsrs = new Hashtable<String, Integer>();
		for (Vector<Attribute> vec : examples) {
			String cl = vec.lastElement().getCls();
			if (!clsrs.containsKey(cl))
				clsrs.put(cl, 1);
			else
				clsrs.put(cl, clsrs.get(cl) + 1);
		}
		/* which key in clsrs has the most hits? */
		String maxKey=null;
		Integer maxValue = Integer.MIN_VALUE; 
		for(Map.Entry<String, Integer> entry : clsrs.entrySet()) {
		     if(entry.getValue() > maxValue) {
		         maxValue = entry.getValue();
		         maxKey = entry.getKey();
		     }
		}
		/* make leaf node with maxKey as cls */
		Attribute leaf = new Attribute(null, maxKey);
		return new Node<Attribute>(leaf);
		
	}

	public int importance(	Hashtable<String, Integer> classifiers, 
							Vector<Vector<Attribute>> examples,
							Vector<Set<String>> attr_values)
	{
		int index = 0;
		float maxGain = 0;
		for (int i = 0; i < attr_values.size(); i++) {
			float tempGain = gain(classifiers, examples, attr_values, i);
			if (tempGain > maxGain) {
				maxGain = tempGain;
				index = i;
			}
		}
		return index;
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

					/* for the first row, add to attr_values empty sets fore
					 * every new attribute */
					if (first_time)
						attr_values.add(new HashSet());

					/* add the value to the corresponding attribute,
					 * if not already added */
					attr_values.get(col).add(value);
					col++;
				}

				/* remove the last element in attr_values; it is a
				 * classifier, not an attribute */
				if (first_time) {
					first_time = false;
				}

				/* if classifier isn't a key in our Hashtable, add it */
				String cl = input.lastElement().toString();
				if (!classifiers.containsKey(cl))
					classifiers.put(cl, 1);
				else
					classifiers.put(cl, classifiers.get(cl) + 1);

				training_set.add(input);

			}
			/* remove last set in attr_values, its a classifier */
			attr_values.remove(attr_values.size()-1);
			System.out.println("Classifiers: " + classifiers.toString());
			System.out.println("Possible values for Attributes: " + attr_values.toString());


			DecisionTree dt = new DecisionTree();
			float r_entropy = dt.remainder(training_set, attr_values, 4);
			System.out.println("remaining entropy on Patrons: " + r_entropy);
			float gain_entropy = dt.gain(classifiers, training_set, attr_values, 4);
			System.out.println("gain entropy on Patrons: " + gain_entropy);

			int bestAttr = dt.importance(classifiers, training_set, attr_values);
			
			System.out.println("bestAttr: " + bestAttr);

		} catch(Exception e) {
			System.out.println("Exception while reading csv file: " + e);                  
		}		
	}
}	

