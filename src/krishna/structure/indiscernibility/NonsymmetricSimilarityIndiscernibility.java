package krishna.structure.indiscernibility;

public class NonsymmetricSimilarityIndiscernibility extends AbstractIndiscernibility 
{
	public NonsymmetricSimilarityIndiscernibility()
    {
    }

    /**
     * @see rseslib.structure.indiscernibility.AbstractIndiscernibility#similar(double, double, int)
     */
    public boolean similar(double value1, double value2, int attribute)
    {
        return (value1==value2)||Double.isNaN(value1);
    }

    public boolean equals(Object o)
    {
        return (o instanceof NonsymmetricSimilarityIndiscernibility);
    }


    public int hashCode()
    {
               //NONSSIMI
        return 0x66677464;
    }
}
