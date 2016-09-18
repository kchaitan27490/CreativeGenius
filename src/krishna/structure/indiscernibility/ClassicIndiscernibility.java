package krishna.structure.indiscernibility;

public class ClassicIndiscernibility extends AbstractIndiscernibility 
{
	public ClassicIndiscernibility()
    {
    }

    /**
     * @see rseslib.structure.indiscernibility.AbstractIndiscernibility#similar(double, double, int)
     */
    public boolean similar(double value1, double value2, int attribute)
    {
        return value1==value2||(Double.isNaN(value1)&&Double.isNaN(value2));
    }

    public boolean equals(Object o)
    {
        return (o instanceof ClassicIndiscernibility);
    }

    public int hashCode()
    {
               //CLASIIND
        return 0x25274463;
    }
}
