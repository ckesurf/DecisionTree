/**
 * 
 */

/**
 * @author Chris Erlendson
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;


public class DecisionTree {

	/**
	 * @param args - filename of csv file
	 */
	public static void main(String[] args) {

		/* parse filename and create a Vector of Attributes */

		try
		{

			//csv file containing data
			String strFile = "src/restaurant.csv";

			//create BufferedReader to read csv file
			BufferedReader br = new BufferedReader( new FileReader(strFile));
			String strLine = "";
			StringTokenizer st = null;
			int lineNumber = 0, tokenNumber = 0;

			//read comma separated file line by line
			while( (strLine = br.readLine()) != null)
			{
				lineNumber++;

				//break comma separated line using ","
				st = new StringTokenizer(strLine, ", ");

				while(st.hasMoreTokens())
				{
					//display csv values
					tokenNumber++;
					System.out.println("Line # " + lineNumber +
							", Token # " + tokenNumber
							+ ", Token : "+ st.nextToken());
				}

				//reset token number
				tokenNumber = 0;

			}


		}
		catch(Exception e)
		{
			System.out.println("Exception while reading csv file: " + e);                  
		}
	}
}	

