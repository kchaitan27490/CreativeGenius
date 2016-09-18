package krishna.system.progress;
import krishna.system.Report;
public class StdOutProgress extends AbstractProgress 
{
	/** Percentage of this progress. */
    int m_nPercantage = 0;
    /** The current step of this progress. */
    int m_nCurrentPoint = -1;
    /** Time when the progress started. */
    long m_nStartTime;

    /**
     * Sets the total number of steps to be done.
     *
     * @param name      Name of this progress.
     * @param noOfSteps Number of steps in this progress.
     */
    public void set(String name, int noOfSteps)
    {
        super.set(name, noOfSteps);
        m_nCurrentPoint = 0;
        System.out.println(m_Name+":");
        System.out.print("0");
        m_nStartTime = System.currentTimeMillis();
    }

    /**
     * Makes a single step.
     *
     * @throws InterruptedException When the process is requested to be stopped.
     */
    public void step() throws InterruptedException
    {
        if (m_nCurrentPoint == -1)
        {
            Report.exception(new ProgressException(m_Name+": progress not initialised"));
            return;
        }
        if (m_nCurrentPoint>=m_nEndPoint)
        {
            Report.exception(new ProgressException(m_Name+": more than "+m_nEndPoint+" progress steps reported"));
            return;
        }
        m_nCurrentPoint++;
        while (100*(m_nCurrentPoint)/(m_nEndPoint) >= m_nPercantage+1)
        {
            m_nPercantage++;
            if (m_nPercantage%50==0) System.out.println(m_nPercantage+"%");
            else if (m_nPercantage%10==0) System.out.print(m_nPercantage+"%");
            else if (m_nPercantage%2==0) System.out.print('.');
        }
        if (m_nCurrentPoint==m_nEndPoint)
        {
            long durationTime = System.currentTimeMillis() - m_nStartTime;
            System.out.println("Time: " + (durationTime/1000) + "s " +  (durationTime%1000) + "ms");
            System.out.println("");
        }
    }
}
