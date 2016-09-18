package krishna.structure.attribute;

public class NumericAttribute extends Attribute 
{
	private static final long serialVersionUID = 1L;

	/**
     * Constructor computes a normalisation factor.
     *
     * @param attrType Attribute type.
     * @param name     Name of this attribute.
     */
    public NumericAttribute(Type attrType, String name)
    {
        super(attrType, ValueSet.numeric, name);
    }

    /**
     * Checks whether this attribute is numeric.
     *
     * @return True if this attribute is numeric false otherwise.
     */
    public boolean isNumeric()
    {
        return true;
    }
	
}
