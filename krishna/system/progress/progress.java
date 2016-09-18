package krishna.system.progress;


public interface progress 
{

    /**
     * Sets the total number of steps to be done.
     *
     * @param name      Name of this progress.
     * @param noOfSteps Number of steps in this progress.
     */
    public abstract void set(String name, int noOfSteps);

    /**
     * Makes a single step.
     *
     * @throws InterruptedException When the process is requested to be stopped.
     */
    public abstract void step() throws InterruptedException;
}
