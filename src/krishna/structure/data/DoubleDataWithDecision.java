package krishna.structure.data;

public interface DoubleDataWithDecision extends DoubleData
{
	 /**
     * Sets decision.
     *
     * @param decVal Decision value.
     */
    public abstract void setDecision(double decVal);

    /**
     * Returns decision.
     *
     * @return Decision value.
     */
    public abstract double getDecision();
}
