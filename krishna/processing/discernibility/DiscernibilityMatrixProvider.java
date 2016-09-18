package krishna.processing.discernibility;
import java.util.BitSet;
import java.util.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;

import krishna.processing.discernibility.ClassicGeneralizedDecisionProvider;
import krishna.processing.discernibility.GeneralizedDecisionProvider;
import krishna.processing.discernibility.TransitiveClosureGeneralizedDecisionProvider;
import krishna.structure.attribute.Header;
import krishna.structure.data.DoubleData;
import krishna.structure.data.DoubleDataWithDecision;
import krishna.structure.indiscernibility.ClassicIndiscernibility;
import krishna.structure.indiscernibility.Indiscernibility;
import krishna.structure.indiscernibility.NonsymmetricSimilarityIndiscernibility;
import krishna.structure.indiscernibility.SymmetricSimilarityIndiscernibility;
import krishna.structure.table.DoubleDataTable;
import krishna.system.Configuration;
import krishna.system.PropertyConfigurationException;

public class DiscernibilityMatrixProvider extends Configuration 
{
	private enum IndiscernibilityRelation { DiscernFromValue, DiscernFromValueOneWay, DontDiscernFromValue; };
	private enum DiscernibilityMethod { All, GeneralizedDecision, GeneralizedDecisionAndOrdinaryChecked, OrdinaryDecisionAndInconsistenciesOmitted; };
	
	public static final String s_sIndiscernibilityRelation = "IndiscernibilityForMissing";
	public static final String s_sDiscernibilityMethod = "DiscernibilityMethod";
	public static final String s_sGeneralizedDecisionTransitiveClosure = "GeneralizedDecisionTransitiveClosure";

    Indiscernibility m_indiscernibility;

    /**
     * select used discernibility method
     */

    DiscernibilityMethod m_nDiscernibilityMethod;

    GeneralizedDecisionProvider m_nGeneralizedDecisionProvider;

    Header m_Header;

    Collection<DoubleData> m_Objects;
    
    /**
     * @throws PropertyConfigurationException 
     * 
     */

    public DiscernibilityMatrixProvider(Properties prop, DoubleDataTable table) throws PropertyConfigurationException
    {
        super(prop);

        setIndiscernibilityRelation(getProperty(s_sIndiscernibilityRelation));

        setDiscernibilityMethod(getProperty(s_sDiscernibilityMethod));

        if (m_nDiscernibilityMethod==DiscernibilityMethod.GeneralizedDecision
        	|| m_nDiscernibilityMethod==DiscernibilityMethod.GeneralizedDecisionAndOrdinaryChecked)

       	setGeneralizedDecisionProvider(getBoolProperty(s_sGeneralizedDecisionTransitiveClosure), table);

        m_Header = table.attributes();

        m_Objects = table.getDataObjects();

        //System.out.println("Prop: ");
        //prop.list(System.out);
    }

    private void setIndiscernibilityRelation(String aIndiscernibilityRelation) throws PropertyConfigurationException
    {
    	try
    	{
        	switch (IndiscernibilityRelation.valueOf(aIndiscernibilityRelation))
        	{

        	case DiscernFromValue:

        		m_indiscernibility = new ClassicIndiscernibility();
        		break;

        	case DontDiscernFromValue:

                m_indiscernibility = new SymmetricSimilarityIndiscernibility();
                break;

        	case DiscernFromValueOneWay:

                m_indiscernibility = new NonsymmetricSimilarityIndiscernibility();
        	}
    	}

    	catch (IllegalArgumentException e)
    	{
			throw new PropertyConfigurationException("Unknown indiscernibility relation for mising values: "+aIndiscernibilityRelation);
        }
        //System.out.println("Setting "+s_sIndiscernibilityRelation+" = "+aIndiscernibilityRelation);
    }

    private void setDiscernibilityMethod(String aDiscernibilityMethod) throws PropertyConfigurationException
    {
    	try
    	{
    		m_nDiscernibilityMethod = DiscernibilityMethod.valueOf(aDiscernibilityMethod);
    	}
    	catch (IllegalArgumentException e)
    	{
			throw new PropertyConfigurationException("Unknown discernibility method: "+aDiscernibilityMethod);
        }
        //System.out.println("Setting "+s_sGeneralizedDecisionMethod+" = "+aGeneralizedDecisionMethod);
    }
    
    private void setGeneralizedDecisionProvider(boolean transitiveClosure, DoubleDataTable table) throws PropertyConfigurationException
    {
    	if (transitiveClosure)
    		m_nGeneralizedDecisionProvider = new TransitiveClosureGeneralizedDecisionProvider(table, m_indiscernibility);
    	else
    		m_nGeneralizedDecisionProvider = new ClassicGeneralizedDecisionProvider(table, m_indiscernibility);
    }

    public ArrayList getDiscernibilityMatrix()
    {
        ArrayList discern_attrs = new ArrayList();

        for (DoubleData object : m_Objects)

        	addDiscernibility(discern_attrs, object);
                return discern_attrs;
    }
    
    public ArrayList getLocalDiscernibility(DoubleData object)
    {
        ArrayList discern_attrs = new ArrayList();

       	addDiscernibility(discern_attrs, object);

        return discern_attrs;
    }
    
    private void addDiscernibility(ArrayList discern_attrs, DoubleData object)
    {
    	DoubleDataWithDecision objectWithDec = null;

        if (m_nDiscernibilityMethod==DiscernibilityMethod.GeneralizedDecisionAndOrdinaryChecked
            	|| m_nDiscernibilityMethod==DiscernibilityMethod.OrdinaryDecisionAndInconsistenciesOmitted)

        	objectWithDec = (DoubleDataWithDecision)object;

    	ArrayList bv;

        for (DoubleData dd : m_Objects)
        {
        	switch (m_nDiscernibilityMethod)
        	{
        	case All:
        		bv = new ArrayList(m_Header.noOfAttr());
        		for (int a=0; a<m_Header.noOfAttr(); a++)
        			if (m_Header.isConditional(a) &&
        					!m_indiscernibility.similar(object.get(a),dd.get(a),a))
        				bv.add(a);
        		if (!bv.isEmpty())
			
			discern_attrs.add(bv);

        		break;

        	case GeneralizedDecision:
        		if (!m_nGeneralizedDecisionProvider.haveTheSameDecision(object,dd))
        		{
        			bv = new ArrayList(m_Header.noOfAttr());
        			for (int a=0; a<m_Header.noOfAttr(); a++)
        				if (m_Header.isConditional(a) &&
        						!m_indiscernibility.similar(object.get(a),dd.get(a),a))
        					bv.add(a);
        			if (!bv.isEmpty()) discern_attrs.add(bv);
        		}
        		break;

        	case GeneralizedDecisionAndOrdinaryChecked:
        		if (objectWithDec.getDecision()!=((DoubleDataWithDecision)dd).getDecision()
        				&& !m_nGeneralizedDecisionProvider.haveTheSameDecision(object,dd))
        		{
        			bv = new ArrayList(m_Header.noOfAttr());
        			for (int a=0; a<m_Header.noOfAttr(); a++)
        				if (m_Header.isConditional(a) &&
        						!m_indiscernibility.similar(object.get(a),dd.get(a),a))
        					bv.add(a);
        			if (!bv.isEmpty()) discern_attrs.add(bv);
        		}
        		break;

        	case OrdinaryDecisionAndInconsistenciesOmitted:
        		if (objectWithDec.getDecision()!=((DoubleDataWithDecision)dd).getDecision())
        		{
        			bv = new ArrayList(m_Header.noOfAttr());
        			for (int a=0; a<m_Header.noOfAttr(); a++)
        				if (m_Header.isConditional(a) &&
        						!m_indiscernibility.similar(object.get(a),dd.get(a),a))
        					bv.add(a);
        			if (!bv.isEmpty()) discern_attrs.add(bv);
        		}
        		break;
        	}
        }
    }

    public Indiscernibility getIndiscernibilityForMissing()
    {
    	return m_indiscernibility;
    }
}
