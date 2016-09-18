package krishna.structure.attribute.formats;
import java.io.*;

public class DataFormatRecognizer 
{

	public enum Format { ARFF, RSES, CSV }; 
  
	/**
     * Checks whether the format of data in a given file is from RSES2.x.
     *
     * @param dataFile Data to be checked.
     * @return         True if the format of data is from RSES2.x, false otherwise.
     * @throws IOException if an I/O error has occured.
     */
    public Format recognizeFormat(File dataFile) throws IOException
    {
    	Format frm = null;
    	boolean arff_possible = true;    	
        BufferedReader bw = new BufferedReader(new FileReader(dataFile));
        while (bw.ready() && frm==null)
        {
        	String first_line = bw.readLine().trim();
        	if (first_line.length() > 0)
        		switch (first_line.charAt(0))
        		{
        		case '#':
        			frm = Format.CSV;
        			break;
        		case '$':
        			arff_possible = false;
        			break;
        		case '%':
        			break;
        		default:
        			if (first_line.startsWith("TABLE"))
        				frm = Format.RSES;
        			else if (arff_possible && first_line.toLowerCase().startsWith("@relation"))
        				frm = Format.ARFF;
        			else
        				frm = Format.CSV;
        		}
        }
        bw.close();
        return frm;
    }
}
