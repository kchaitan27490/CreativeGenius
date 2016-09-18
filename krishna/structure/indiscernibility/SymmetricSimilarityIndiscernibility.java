package krishna.structure.indiscernibility;

public class SymmetricSimilarityIndiscernibility extends AbstractIndiscernibility
{
	public SymmetricSimilarityIndiscernibility()
    {
    }

    /**
     * @see rseslib.structure.indiscernibility.AbstractIndiscernibility#similar(double, double, int)
     */
    public boolean similar(double value1, double value2, int attribute)
    {
        return (value1==value2)||Double.isNaN(value1)||Double.isNaN(value2);
    }

    public boolean equals(Object o)
    {
        return (o instanceof SymmetricSimilarityIndiscernibility);
    }


    public int hashCode()
    {
               //SYMESIMI
        return 0x79637464;
    }
}
