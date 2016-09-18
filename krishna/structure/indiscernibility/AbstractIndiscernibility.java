package krishna.structure.indiscernibility;

import krishna.structure.attribute.Header;
import krishna.structure.data.DoubleData;

public abstract class AbstractIndiscernibility implements Indiscernibility 
{
	 public boolean similar(DoubleData object1,DoubleData object2)
	    {
	    	Header hdr = object1.attributes();
	        for (int a=0; a<hdr.noOfAttr(); a++)
	        	if (hdr.isConditional(a) && !similar(object1.get(a),object2.get(a),a)) return false;
	        return true;
	    }
}
