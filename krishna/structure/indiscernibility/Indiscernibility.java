package krishna.structure.indiscernibility;

import krishna.structure.data.DoubleData;

public interface Indiscernibility 
{
	/**
	 * Defines the indiscernibility between two objects.
	 * 
	 * @param object1	First object to be compared.
	 * @param object2	Second object to be compared.
	 * @return			True if the objects are indiscernible false otherwise.
	 */
	public boolean similar(DoubleData object1, DoubleData object2);

	/**
	 * Defines the indiscernibility between two values of an attribute.
	 * 
	 * @param value1	First value to be compared.
	 * @param value2	Second value to be compared.
	 * @param attribute	Attribute index.
	 * @return			True if the values are indiscernible false otherwise.
	 */
	public boolean similar(double value1, double value2, int attribute);
}
