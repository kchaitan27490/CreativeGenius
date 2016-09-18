package krishna.processing.discernibility;
import krishna.structure.data.DoubleData;

public interface GeneralizedDecisionProvider 
{
public boolean haveTheSameDecision(DoubleData object1,DoubleData object2);
    
    public String getDecisionForObject(DoubleData object);
}
