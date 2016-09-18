package krishna.system.output;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import krishna.system.Report;

public class FileOutput implements Output  
{
	 /**
     * Writer for information messages.
     */
    private Writer m_InfoWriter = null;

    public FileOutput(File reportFile) throws IOException
    {
        m_InfoWriter = new BufferedWriter(new FileWriter(reportFile));
    }

    /**
     * Outputs the information about a given object.
     *
     * @param obj Object to be output.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void display(Object obj) throws IOException
    {
        m_InfoWriter.write(obj.toString());
        m_InfoWriter.flush();
    }

    /**
     * Outputs the end of line.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void nl() throws IOException
    {
        m_InfoWriter.write(Report.lineSeparator);
        m_InfoWriter.flush();
    }

    /**
     * Closes this output.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void close() throws IOException
    {
        m_InfoWriter.close();
    }
}
