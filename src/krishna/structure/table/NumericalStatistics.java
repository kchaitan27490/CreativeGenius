package krishna.structure.table;
import java.util.Collection;

import krishna.structure.data.DoubleData;
import krishna.system.Report;
public class NumericalStatistics implements Cloneable
{
	 /** The minimal value of this attribute. */
    private double m_nMinValue = Double.NaN;
    /** The maximal value of this attribute. */
    private double m_nMaxValue = Double.NaN;
    /** The average value of this attribute. */
    private double m_nAvgValue = Double.NaN;
    /** Standard deviation of this attribute. */
    private double m_nStdDev = Double.NaN;

    /**
     * Constructor computes a normalisation factor.
     *
     * @param objects Data set that the normalisation factor is based on.
     * @param index Attribute index.
     */
    public NumericalStatistics(Collection<DoubleData> objects, int index)
    {
    	int noOfAdded = 0;
        for (DoubleData obj : objects)
        {
            if (!Double.isNaN(obj.get(index)))
            {
            	if (noOfAdded==0)
            	{
            		m_nMinValue = obj.get(index);
            		m_nMaxValue = obj.get(index);
            		m_nAvgValue = obj.get(index);
            	}
            	else
            	{
            		if (obj.get(index) < m_nMinValue) m_nMinValue = obj.get(index);
            		if (obj.get(index) > m_nMaxValue) m_nMaxValue = obj.get(index);
            		m_nAvgValue += obj.get(index);
            	}
                noOfAdded++;
            }
        }
        if (noOfAdded > 0)
        {
        	m_nAvgValue /= (double)noOfAdded;
        	m_nStdDev = 0;
        	for (DoubleData obj : objects)
        		if (!Double.isNaN(obj.get(index)))
        			m_nStdDev += (obj.get(index)-m_nAvgValue)*(obj.get(index)-m_nAvgValue);
        	m_nStdDev = Math.sqrt(m_nStdDev / (double)noOfAdded);
        }
    }

    /**
     * Returns the minimal value of this attribute.
     *
     * @return The minimal value of this attribute.
     */
    public double getMinimum()
    {
    	return m_nMinValue;
    }

    /**
     * Returns the maximal value of this attribute.
     *
     * @return The maximal value of this attribute.
     */
    public double getMaximum()
    {
    	return m_nMaxValue;
    }

    /**
     * Returns the average value of this attribute.
     *
     * @return The average value of this attribute.
     */
    public double getAverage()
    {
    	return m_nAvgValue;
    }

    /**
     * Returns the standard deviation of this attribute.
     *
     * @return The standatd deviation of this attribute.
     */
    public double getStandardDeviation()
    {
    	return m_nStdDev;
    }

    /**
     * Constructs string representation of this attribute.
     *
     * @return String representation of this attribute.
     */
    public String toString()
    {
    	StringBuffer sbuf = new StringBuffer();
    	sbuf.append("Numerical attribute"+Report.lineSeparator);
    	sbuf.append("Minimum value: "+m_nMinValue+Report.lineSeparator);
    	sbuf.append("Maximum value: "+m_nMaxValue+Report.lineSeparator);
    	sbuf.append("Average value: "+m_nAvgValue+Report.lineSeparator);
    	sbuf.append("Standard deviation: "+m_nStdDev+Report.lineSeparator);
    	return sbuf.toString();
    }

    /**
     * Create and return a copy of this object.
     * 
     * @return Copy of this object.
     */
    public Object clone()
    {
       try
       {
          return super.clone();
       }
       catch (CloneNotSupportedException e)
       {
    	   return null;
       }
    }
}
