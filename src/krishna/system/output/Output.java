package krishna.system.output;
import java.io.IOException;
public interface Output 
{
	/**
     * Outputs the information about a given object.
     *
     * @param obj Object to be output.
     *
     * @throws IOException If an I/O error occurs.
     */
    public abstract void display(Object obj) throws IOException;

    /**
     * Outputs the end of line.
     *
     * @throws IOException If an I/O error occurs.
     */
    public abstract void nl() throws IOException;

    /**
     * Closes this output.
     *
     * @throws IOException If an I/O error occurs.
     */
    public abstract void close() throws IOException;
}
