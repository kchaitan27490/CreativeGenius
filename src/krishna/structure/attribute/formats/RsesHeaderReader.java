package krishna.structure.attribute.formats;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import krishna.structure.attribute.Attribute;
import krishna.structure.attribute.NominalAttribute;
import krishna.structure.attribute.NumericAttribute;
public class RsesHeaderReader  implements HeaderReader
{
	/** The set of strings that denote missing values. */
    private Collection<String> m_MissingValues = new ArrayList<String>();
    /** The first missing value enumerated in header file. */
    private String m_Missing = "NULL";
    /**
     * The table indicating which attributes
     * are to be read in while loading data from file.
     */
    private boolean[] m_AttrLoaded;
    /** Array of attributes (read in only). */
    private Attribute[] m_arrAttributes;

    /**
     * Constructor.
     *
     * @param dataFile   Data file in RSES2 format.
     * @throws IOException if an I/O error has occured.
     */
    public RsesHeaderReader(File dataFile) throws IOException
    {
        krishna.structure.attribute.formats.rses.Table rsesTab = new krishna.structure.attribute.formats.rses.Table();
        try
        {
            rsesTab.loadTable(dataFile.getPath());
        }
        catch (InterruptedException e)
        {
            throw new IOException(e.getMessage());
        }
        extractHeaderInfo(rsesTab);
    }

    /**
     * Constructor.
     *
     * @param rsesTab   Data table structure from RSES2.
     * @throws IOException if an I/O error has occured.
     */
    public RsesHeaderReader(krishna.structure.attribute.formats.rses.Table rsesTab)
    {
        extractHeaderInfo(rsesTab);
    }

    /**
     * Extracts header information
     * from data table structure from RSES2.
     *
     * @param rsesTab   Data table structure from RSES2.
     * @throws IOException if an I/O error has occured.
     */
    private void extractHeaderInfo(krishna.structure.attribute.formats.rses.Table rsesTab)
    {
        m_MissingValues.add(m_Missing);
        m_AttrLoaded = new boolean[rsesTab.getNoAttr()];
        m_arrAttributes = new Attribute[rsesTab.getNoAttr()];
        for (int attr = 0; attr < m_arrAttributes.length; attr++)
        {
            m_AttrLoaded[attr] = true;
            if (rsesTab.getAttrType(attr))
            {
                if (attr < rsesTab.getNoAttr()-1)
                    m_arrAttributes[attr] = new NominalAttribute(Attribute.Type.conditional, rsesTab.getAttrName(attr));
                else
                    m_arrAttributes[attr] = new NominalAttribute(Attribute.Type.decision, rsesTab.getAttrName(attr));
            }
            else
            {
                if (attr < rsesTab.getNoAttr()-1)
                    m_arrAttributes[attr] = new NumericAttribute(Attribute.Type.conditional, rsesTab.getAttrName(attr));
                else
                    m_arrAttributes[attr] = new NominalAttribute(Attribute.Type.decision, rsesTab.getAttrName(attr));
            }
        }
    }

    /**
     * Returns the set of all strings denoting missing value.
     *
     * @return Set of all strings denoting missing value.
     */
    public Collection<String> allMissing()
    {
        return m_MissingValues;
    }

    /**
     * Returns missing value.
     *
     * @return String that denotes the missing value.
     */
    public String singleMissing()
    {
        return m_Missing;
    }

    /**
     * Returns the bit mask indicating
     * which original attributes are to be read in
     * while loading data from file.
     *
     * @return The bit mask where true at a position i
     *         indicates that attribute i is to be read in
     *         and false indicates that the attribute is to be skipped.
     */
    public boolean[] bitMaskOfLoaded()
    {
        return m_AttrLoaded;
    }

    /**
     * Returns the information about attributes (loaded only).
     *
     * @return Array of attributes.
     */
    public Attribute[] attributesForLoading()
    {
        return m_arrAttributes;
    }
}
