package krishna.structure.attribute;
import java.io.Serializable;

public class Attribute {
	/** Attribute types. */
	public enum Type { conditional, decision, text }; 
	/** Value set types. */
	public enum ValueSet { numeric, nominal, nonapplicable }; 
	
    /** Serialization version. */
	private static final long serialVersionUID = 1L;

    /** Attribute type. */
    protected Type m_nAttrType;
    /** Type of the set of values. */
    protected ValueSet m_nValueSetType;
    /** Attribute name. */
    protected String m_Name;

    /**
     * Constructor.
     *
     * @param attrType     Attribute type.
     * @param valueSetType Type of the set of values.
     * @param name         Attribute name.
     */
    public Attribute(Type attrType, ValueSet valueSetType, String name)
    {
        m_nAttrType = attrType;
        m_nValueSetType = valueSetType;
        m_Name = name;
    }

    /**
     * Copy constructor.
     *
     * @param attr         Original attribute.
     */
    public Attribute(Attribute attr)
    {
      m_nAttrType = attr.m_nAttrType;
      m_nValueSetType = attr.m_nValueSetType;
      m_Name = attr.m_Name;
    }

    /**
     * Checks whether this attribute is conditonal or decision.
     *
     * @return True if this attribute is conditional or decision false otherwise.
     */
    public boolean isInterpretable()
    {
        return (m_nAttrType==Type.conditional || m_nAttrType==Type.decision);
    }

    /**
     * Checks whether this attribute is conditional.
     *
     * @return True if this attribute is conditional false otherwise.
     */
    public boolean isConditional()
    {
        return (m_nAttrType==Type.conditional);
    }

    /**
     * Checks whether this attribute is decision.
     *
     * @return True if this attribute is decision false otherwise.
     */
    public boolean isDecision()
    {
        return (m_nAttrType==Type.decision);
    }

    /**
     * Checks whether this attribute is a text.
     *
     * @return True if this attribute is a text false otherwise.
     */
    public boolean isText()
    {
        return (m_nAttrType==Type.text);
    }

    /**
     * Checks whether this attribute is numeric.
     *
     * @return True if this attribute is numeric false otherwise.
     */
    public boolean isNumeric()
    {
        return (m_nValueSetType==ValueSet.numeric);
    }

    /**
     * Checks whether this attribute is nominal.
     *
     * @return True if this attribute is nominal false otherwise.
     */
    public boolean isNominal()
    {
        return (m_nValueSetType==ValueSet.nominal);
    }

    /**
     * Returns the name of this attribute.
     *
     * @return Name of this attribute.
     */
    public String name()
    {
        return m_Name;
    }

    /**
     * Constructs string representation of this attribute.
     *
     * @return String representation of this attribute.
     */
   public String toString()
   {
       StringBuffer sbuf = new StringBuffer();
       sbuf.append(m_Name);
       switch (m_nAttrType)
       {
           case conditional:
               sbuf.append(" CONDITIONAL ");
               break;
           case decision:
               sbuf.append(" DECISION    ");
               break;
           case text:
               sbuf.append(" TEXT        ");
               break;
           default:
               sbuf.append(" ATTR UNKNOWN");
       }
       switch (m_nValueSetType)
       {
           case nonapplicable:
               sbuf.append(" N/A");
               break;
           case numeric:
               sbuf.append(" NUMERIC");
               break;
           case nominal:
               sbuf.append(" NOMINAL");
               break;
           default:
               sbuf.append(" VALUE TYPE UNKNOWN");
       }
       return sbuf.toString();
   }

   /**
    * Returns true for equivallent attribute object.
    * @param obj - object for comparison 
    * @return true if attribute object is equivallent.
    * @see java.lang.Object#equals(java.lang.Object)
    */
   public boolean equals(Object obj)
   {
       if (obj instanceof Attribute)
       {
           if (!(m_nAttrType==((Attribute)obj).m_nAttrType)) return false;
           if (!(m_nValueSetType==((Attribute)obj).m_nValueSetType)) return false;
           return m_Name==((Attribute)obj).m_Name;
       }
       return false;
   }
}
