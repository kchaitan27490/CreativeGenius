package krishna.structure.attribute.formats.rses;

public class IntWrap 
{
	/**
	 * The variable into which the integer number is stored.
	 */
	int intValue;

	/**
	 * Constructs a newly allocated IntWrap object
	 * with the specified initial values assigned to field: <tt>intValue</tt>.
	 *
	 */

	public IntWrap(int val)
	{
		intValue = val;
	}

	/**
	 * Constructs a newly allocated IntWrap object
	 * with the initial values equals 0.
	 *
	 */

	public IntWrap()
	{
		intValue = 0;
	}

	/**
	 * Returns the value of field: <tt>intValue</tt>.
	 *
	 */

	public int getValue(){ return intValue; }

	/**
	 * Sets a new value of the field: <tt>intValue</tt>.
	 *
	 */

	public void setValue(int val) { intValue = val;}

	public void incValue() { intValue++;}

	public String toString()
	{
	  Integer int_val = new Integer(intValue);
	  return int_val.toString();
	}

	
}
