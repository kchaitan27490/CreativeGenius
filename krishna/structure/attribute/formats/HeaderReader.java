package krishna.structure.attribute.formats;
import java.util.Collection;
import krishna.structure.attribute.Attribute;
public interface HeaderReader 
{
	 /**
     * Returns the set of all strings denoting missing value.
     *
     * @return Set of all strings denoting missing value.
     */
    public abstract Collection<String> allMissing();

    /**
     * Returns missing value.
     *
     * @return String that denotes the missing value.
     */
    public abstract String singleMissing();

    /**
     * Returns the bit mask indicating
     * which original attributes are to be read in
     * while loading data from file.
     *
     * @return The bit mask where true at a position i
     *         indicates that attribute i is to be read in
     *         and false indicates that the attribute is to be skipped.
     */
    public abstract boolean[] bitMaskOfLoaded();

    /**
     * Returns the information about attributes (loaded only).
     *
     * @return Array of attributes.
     */
    public abstract Attribute[] attributesForLoading();
	
}
