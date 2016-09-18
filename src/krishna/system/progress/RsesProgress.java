package krishna.system.progress;
import krishna.system.Report;
import krishna.structure.attribute.formats.rses.RSLibProgress;

public class RsesProgress extends AbstractProgress 
{
	/** Progress object from rses library. */
    RSLibProgress m_RSLibProgress;
    /** Current value of this progress. */
    int m_nCurrentPoint = Integer.MIN_VALUE;
    /** Current percantege of this progress. */
    int m_nPercentage = 0;

    /**
     * Constructor.
     *
     * @param rslibPr Progress object from rses library.
     */
    public RsesProgress(RSLibProgress rslibPr)
    {
        m_RSLibProgress = rslibPr;
    }

    /**
     * Makes a single step.
     *
     * @throws InterruptedException When the process is requested to be stopped.
     */
    public void step() throws InterruptedException
    {
        if (m_nCurrentPoint>=m_nEndPoint)
        {
            Report.exception(new ProgressException(m_Name+": more than "+m_nEndPoint+" progress steps reported"));
            return;
        }
        if (m_nCurrentPoint==Integer.MIN_VALUE) m_nCurrentPoint = 0;
        m_nCurrentPoint++;
        int oldPercantege = m_nPercentage;
        while (100*(m_nCurrentPoint)/(m_nEndPoint) >= m_nPercentage + 1) m_nPercentage++;
        if (m_nPercentage > oldPercantege) m_RSLibProgress.progress(m_nPercentage);
    }
}
