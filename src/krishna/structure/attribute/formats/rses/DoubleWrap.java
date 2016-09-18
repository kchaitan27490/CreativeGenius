package krishna.structure.attribute.formats.rses;

public class DoubleWrap 
{
	double doubleValue;

	public DoubleWrap(double val)
	{
		doubleValue = val;
	}

	public DoubleWrap()
	{
		doubleValue = 0.0;
	}

	public double getValue(){ return doubleValue; }


	public void setValue(double val) { doubleValue = val;}
	public void incValue() { doubleValue = doubleValue + 1.0;}

	public String toString()
	{
	  Double doubleVal = new Double(doubleValue);
	  return doubleVal.toString();
	}


}
