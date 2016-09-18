package Application;
import java.awt.*;
import java.applet.*;
/* 
<applet code="appletexp" width=" 300" height="100">
</applet>

*/

public class appletadder extends Applet{
	String msg;
	int state;
	boolean stopFlag;
	Thread t=null;
	
	public void init()
	{
		setBackground(Color.black);
		setForeground(Color.white);
		msg="Inside init--";
	}
	public void start()
	{
		msg+="Inside start ---";
	}
	public void paint(Graphics g)
	{
		msg+="Inside paint {  }.";
		g.drawString(msg,10 , 30);
	}
	

}
