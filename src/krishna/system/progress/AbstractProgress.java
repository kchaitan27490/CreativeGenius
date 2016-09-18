package krishna.system.progress;

public abstract class AbstractProgress implements progress 
{
	/** End point of this progress. */
    protected int m_nEndPoint;
    /** Name of this progress. */
    protected String m_Name;

    /**
     * Sets the total number of steps to be done.
     *
     * @param name      Name of this progress.
     * @param noOfSteps Number of steps in this progress.
     */
    public void set(String name, int noOfSteps)
    {
        m_Name = name;
        m_nEndPoint = noOfSteps;
    }
}
