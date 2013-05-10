import java.util.Set;

/**
 * Attribute class, for handling our attributes (e.g. Patrons, Wait time, etc.)
 * The value corresponding to an Attribute is a string
 */

/**
 * @author Chris Erlendson
 *
 */
public class Attribute {
	/* numerical reference to attribute (4 refers to Patrons, for example) */
	private int col; 
	/* refers to value this attribute took on (e.g. "None", "Some", or "Full") */
	private String value;
	/* refers to what the class is; will mostly be null unless leaf node in tree */
	private String classifier;
	private Set<String> posValues;
	
	public Attribute()
	{
		
	}
	
	public Attribute(String v)
	{
		value = v;
		classifier = null;
	}
	
	public Attribute(String v, String cls)
	{
		value = v;
		classifier = cls;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public void setCol(int c)
	{
		col = c;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public String setValue(String v)
	{
		value = v;
		return value;
	}
	
	public Set<String> getPosValues()
	{
		return posValues;
	}
	
	public void setPosValues(Set<String> values)
	{
		posValues = values;
	}
	
	public String getCls()
	{
		return classifier;
	}
	
	public void setCls(String cls)
	{
		classifier = cls;
	}
	
	public String toString()
	{
		return value;
	}
}
