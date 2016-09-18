package krishna.system.progress;

public class EmptyProgress implements progress
{
	public void set(String name, int noOfSteps)
    {
    }

    /**
     * Makes a single step.
     *
     * @throws InterruptedException When the process is requested to be stopped.
     */
    public void step() throws InterruptedException
    {
    }
}
