package krishna.structure.attribute;

public class BadHeaderException extends Exception
{
	private static final long serialVersionUID = 1L;

	   /**
	     * Constructs a new exception with the specified detail message.
	     *
	     * @param message The detail message.
	     */
	    public BadHeaderException(String message)
	    {
	        super(message);
	    }
}
