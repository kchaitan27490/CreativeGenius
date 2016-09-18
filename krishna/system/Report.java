package krishna.system;
import java.io.IOException;
import java.util.Map;

import krishna.system.output.Output;
public class Report 
{
	 public static final String lineSeparator = System.getProperty("line.separator");

	    /** Outputs for error messages. */
	    private static Output[] s_errorOutputs = new Output[0];
	    /** Outputs for information messages. */
	    private static Output[] s_infoOutputs = new Output[0];
	    /** Switch indicating whether information messages are displayed. */
	    private static boolean s_bDisplayInfo = false;
	    /** Outputs for debugging messages. */
	    private static Output[] s_debugOutputs = new Output[0];
	    /** Switch indicating whether debugging messages are displayed. */
	    private static boolean s_bDisplayDebug = false;

	    /**
	     * Adds an output for error messages.
	     *
	     * @param output  New output.
	     */
	    public static void addErrorOutput(Output output)
	    {
	        Output[] newOutputs = new Output[s_errorOutputs.length+1];
	        for (int i = 0; i < s_errorOutputs.length; i++)
	            newOutputs[i] = s_errorOutputs[i];
	        newOutputs[s_errorOutputs.length] = output;
	        s_errorOutputs = newOutputs;
	    }

	    /**
	     * Reports an exception.
	     *
	     * @param exc Exception to be reported.
	     */
	    public static void exception(Exception exc)
	    {
	        try
	        {
	            for (int i = 0; i < s_errorOutputs.length; i++)
	            {
	                s_errorOutputs[i].display(exc);
	                s_errorOutputs[i].nl();
	                StackTraceElement[] trace = exc.getStackTrace();
	                for (int j = 0; j < trace.length; j++)
	                {
	                    s_errorOutputs[i].display("\tat " + trace[j]);
	                    s_errorOutputs[i].nl();
	                }
	                s_errorOutputs[i].nl();
	            }
	        }
	        catch (IOException e)
	        {
	            System.err.println(e);
	            StackTraceElement[] trace = e.getStackTrace();
	            for (int i=0; i < trace.length; i++)
	                System.err.println("\tat " + trace[i]);
	        }
	    }

	    /**
	     * Adds an output for information messages.
	     *
	     * @param output  New output.
	     */
	    public static void addInfoOutput(Output output)
	    {
	        Output[] newOutputs = new Output[s_infoOutputs.length+1];
	        for (int i = 0; i < s_infoOutputs.length; i++)
	            newOutputs[i] = s_infoOutputs[i];
	        newOutputs[s_infoOutputs.length] = output;
	        s_infoOutputs = newOutputs;
	        if (s_infoOutputs.length==1) s_bDisplayInfo = true;
	    }

	    /**
	     * Sets the flag for displaying information messages.
	     *
	     * @param displayInfo The flag indicating whether information messages are displayed.
	     */
	    public static void setInfoDisplay(boolean displayInfo)
	    {
	        s_bDisplayInfo = displayInfo;
	    }

	    /**
	     * Outputs an information about a given object
	     * if the corresponding flag is set on.
	     *
	     * @param obj Object to be displayed.
	     */
	    public static void display(Object obj)
	    {
	        if (s_bDisplayInfo)
	            try
	            {
	                for (int i = 0; i < s_infoOutputs.length; i++)
	                    s_infoOutputs[i].display(obj);
	            }
	            catch (IOException e)
	            {
	                System.err.println(e);
	                StackTraceElement[] trace = e.getStackTrace();
	                for (int i=0; i < trace.length; i++)
	                    System.err.println("\tat " + trace[i]);
	            }
	    }

	    /**
	     * Outputs an information about a given object
	     * and appends the end of line
	     * if the corresponding flag is set on.
	     *
	     * @param obj Object to be displayed.
	     */
	    public static void displaynl(Object obj)
	    {
	        if (s_bDisplayInfo)
	            try
	            {
	                for (int i = 0; i < s_infoOutputs.length; i++)
	                {
	                    s_infoOutputs[i].display(obj);
	                    s_infoOutputs[i].nl();
	                }
	            }
	            catch (IOException e)
	            {
	                System.err.println(e);
	                StackTraceElement[] trace = e.getStackTrace();
	                for (int i=0; i < trace.length; i++)
	                    System.err.println("\tat " + trace[i]);
	            }
	    }

	    /**
	     * Outputs the end of line
	     * if the corresponding flag is set on.
	     */
	    public static void displaynl()
	    {
	        if (s_bDisplayInfo)
	            try
	            {
	                for (int i = 0; i < s_infoOutputs.length; i++)
	                {
	                	System.out.println(s_infoOutputs[i]);
	                    s_infoOutputs[i].nl();
	                }
	            }
	            catch (IOException e)
	            {
	                System.err.println(e);
	                StackTraceElement[] trace = e.getStackTrace();
	                for (int i=0; i < trace.length; i++)
	                    System.err.println("\tat " + trace[i]);
	            }
	    }

	    /**
	     * Displays all properties with values from a map.
	     * It assumes that all property and value names
	     * fit into a single line.
	     *
	     * @param name          Name of a set of property.
	     * @param parameters    Map between properties and values.
	     */
	    public static void displayMapWithSingleLines(String name, Map parameters)
	    {
	        displaynl(name+":");
	        for (Object par : parameters.entrySet())
	            displaynl(((Map.Entry)par).getKey() + " = " + ((Map.Entry)par).getValue());
	        displaynl();
	    }

	    /**
	     * Displays all properties with values from a map.
	     * The method works well in case
	     * when property and value names
	     * require multiple lines.
	     *
	     * @param name          Name of a set of property.
	     * @param parameters    Map between properties and values.
	     */
	    public static void displayMapWithMultiLines(String name, Map parameters)
	    {
	        displaynl(name+":");
	        displaynl();
	        for (Object par : parameters.entrySet())
	        {
	            displaynl(((Map.Entry)par).getKey());
	            displaynl(((Map.Entry)par).getValue());
	        }
	        displaynl();
	    }

	    /**
	     * Adds an output for debugging messages.
	     *
	     * @param output  New output.
	     */
	    public static void addDebugOutput(Output output)
	    {
	        Output[] newOutputs = new Output[s_debugOutputs.length+1];
	        for (int i = 0; i < s_debugOutputs.length; i++)
	            newOutputs[i] = s_debugOutputs[i];
	        newOutputs[s_debugOutputs.length] = output;
	        s_debugOutputs = newOutputs;
	        if (s_debugOutputs.length==1) s_bDisplayDebug = true;
	    }

	    /**
	     * Sets the flag for displaying debugging messages.
	     *
	     * @param displayDebug The flag indicating whether debugging messages are displayed.
	     */
	    public static void setDebugDisplay(boolean displayDebug)
	    {
	        s_bDisplayDebug = displayDebug;
	    }

	    /**
	     * Outputs a debugging message
	     * and appends the end of line
	     * if the corresponding flag is set on.
	     *
	     * @param message Debugging message.
	     */
	    public static void debugnl(String message)
	    {
	        if (s_bDisplayDebug)
	            try
	            {
	                for (int i = 0; i < s_debugOutputs.length; i++)
	                {
	                    s_debugOutputs[i].display(message);
	                    s_debugOutputs[i].nl();
	                }
	            }
	            catch (IOException e)
	            {
	                System.err.println(e);
	                StackTraceElement[] trace = e.getStackTrace();
	                for (int i=0; i < trace.length; i++)
	                    System.err.println("\tat " + trace[i]);
	            }
	    }

	    /**
	     * Outputs the empty debugging message with the end of line
	     * if the corresponding flag is set on.
	     */
	    public static void debugnl()
	    {
	        if (s_bDisplayDebug)
	            try
	            {
	                for (int i = 0; i < s_debugOutputs.length; i++)
	                    s_debugOutputs[i].nl();
	            }
	            catch (IOException e)
	            {
	                System.err.println(e);
	                StackTraceElement[] trace = e.getStackTrace();
	                for (int i=0; i < trace.length; i++)
	                    System.err.println("\tat " + trace[i]);
	            }
	    }

	    /**
	     * Closes all outputs. The method should be invoked
	     * at the end of program runtime.
	     *
	     * @throws IOException If an I/O error occurs.
	     */
	    public static void close() throws IOException
	    {
	        for (int i = 0; i < s_infoOutputs.length; i++)
	            s_infoOutputs[i].close();
	        s_infoOutputs = new Output[0];
	        s_bDisplayInfo = false;
	        for (int i = 0; i < s_debugOutputs.length; i++)
	            s_debugOutputs[i].close();
	        s_debugOutputs = new Output[0];
	        for (int i = 0; i < s_errorOutputs.length; i++)
	            s_errorOutputs[i].close();
	        s_errorOutputs = new Output[0];
	        s_bDisplayDebug = false;
	    }
}
