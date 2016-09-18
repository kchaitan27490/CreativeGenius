package krishna.system.output;
import java.io.IOException;
public class StandardDebugOutput implements Output
{
	 /**
     * Outputs the information about a given object.
     *
     * @param obj Object to be output.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void display(Object obj) throws IOException
    {
        System.out.print("DEBUG: ");
        System.out.print(obj);
    }

    /**
     * Outputs the end of line.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void nl() throws IOException
    {
        System.out.println();
    }

    /**
     * Closes this output.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void close() throws IOException
    {
    }
}
