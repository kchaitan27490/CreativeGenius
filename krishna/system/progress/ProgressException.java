package krishna.system.progress;

public class ProgressException extends Exception
{
	/** Serial version number */
	private static final long serialVersionUID = 1L;

   /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message.
     */
    public ProgressException(String message)
    {
        super(message);
    }
}
