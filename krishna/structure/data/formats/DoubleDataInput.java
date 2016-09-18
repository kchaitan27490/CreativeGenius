package krishna.structure.data.formats;
import java.io.IOException;

import krishna.structure.Headerable;
import krishna.structure.data.DoubleData;
public interface DoubleDataInput extends Headerable
{
	/**
     * Returns true if there is more data to be read, false otherwise.
     *
     * @return True if there is more data to be read, false otherwise.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException  If user has interrupted reading data.
     */
    public abstract boolean available() throws IOException, InterruptedException;

    /**
     * Reads a new data from this stream.
     *
     * @return Read data.
     * @throws IOException           If an I/O error occurs.
     * @throws InterruptedException  If user has interrupted reading data.
     */
    public abstract DoubleData readDoubleData() throws IOException, DataFormatException, InterruptedException;
}
