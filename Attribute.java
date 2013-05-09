/**
 * Attribute class, for handling our attributes (e.g. Patrons, Wait time, etc.)
 * The value corresponding to an Attribute is a string
 */

/**
 * @author Chris Erlendson
 *
 */
public class Attribute {
	private String value;
	
	public Attribute(String v)
	{
		value = v;
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
}
