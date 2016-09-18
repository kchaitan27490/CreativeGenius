package krishna.structure.attribute.formats.rses;

public class RSLibProgress 
{
	public static boolean ACTION_TERMINATED = false;
	  public static Process CURRENT_RSES_KERNEL_PROCESS = null;

	  static private int MESSAGE_LEVEL = 1;
	  static private int PROGRESS_LEVEL = 1;

	  static public void messageOn()
	  {
//	    System.out.println("MESSAGES ON");
	    MESSAGE_LEVEL = 1;
	  }

	  static public void messageOff()
	  {
	  //  System.out.println("MESSAGES OFF");
	    MESSAGE_LEVEL = 0;
	  }

	  static public void progressOn()
	  {
//	    System.out.println("PROGRESS ON");
	    PROGRESS_LEVEL = 1;
	  }

	  static public void progressOff()
	  {
//	    System.out.println("PROGRESS OFF");
	    PROGRESS_LEVEL = 0;
	  }

	  static public int getMessageLevel() { return MESSAGE_LEVEL; };
	  static public int getProgressLevel() { return PROGRESS_LEVEL; };

	  public boolean getTaskTerminated()
	  {
	    return false;
	  }

	  int PREV_P = -1;
	  public void progress(int p) throws InterruptedException
	  {
	    if (PROGRESS_LEVEL==0) return;

	    if (p!=PREV_P)
	    {
	      System.out.println("Progress-Java: "+p);
	      PREV_P = p;
	    }
	  }

	  public void progress(String s)
	  {
	    if (PROGRESS_LEVEL==0) return;
	    System.out.println("Progress Java-string: "+s);
	  }


	  public void message(String s)
	  {
	    if (MESSAGE_LEVEL==0) return;
	    System.out.println("Message-Java: "+s);
	  }

	  public void message(String s,boolean toTitleRunDialog,int indentLevel)
	  {
	    if (MESSAGE_LEVEL==0) return;
	    System.out.println("Message-Java: "+s+"  toTitleRunDialog="+toTitleRunDialog+"  indentLevel="+indentLevel);
	  }

	  public void writeln(String s)
	  {
	   if (MESSAGE_LEVEL==0) return;
	    message("Writeln-string: "+s);
	  }

	  public void write(String s)
	  {
	    if (MESSAGE_LEVEL==0) return;
	    System.out.print("Write-string"+s);
	  }

	  static public void makePause()
	  {
	    for (long l=0; l<100000; l++)
	    {
	      double d = 2345678;
	      d = d / 345675;
	    }
	  }
}
