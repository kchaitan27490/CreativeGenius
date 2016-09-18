package krishna.examples;

import java.util.*;
import java.io.File;
import java.lang.*;

import krishna.*;
import krishna.processing.discernibility.DiscernibilityMatrixProvider;
import krishna.structure.table.ArrayListDoubleDataTable;
import krishna.structure.table.DoubleDataTable;
import krishna.system.progress.EmptyProgress;
import krishna.system.PropertyConfigurationException;
import krishna.system.Configuration;
public class experimenter extends Configuration
{
	
	public static void main(String[] args) throws Exception
	{
		System.out.println(args[0]);
		File f1 = new File(args[0]);
		DoubleDataTable TestTable = new ArrayListDoubleDataTable(f1, new EmptyProgress());
		
		String IndiscernibilityForMissing="DiscernFromValue";
		System.out.println("the end");
		ArrayList<String> dm=new ArrayList<String>();
		experimenter im=new experimenter();
		Class implement=experimenter.class;
		Properties prop=im.loadDefaultProperties(implement);
		DiscernibilityMatrixProvider getdm;
		getdm=new DiscernibilityMatrixProvider(prop,TestTable);
		
		dm.addAll(getdm.getDiscernibilityMatrix());
		
		ArrayList<String> newimd=new ArrayList<String>();
	    	Scanner sc=new Scanner(System.in);
	   
	    	for(int i=0;i<10;i++)
	    	{
	    		newimd.add(dm.get(i));
	    	}
	    	System.out.println("newimd is");
	    	System.out.println(newimd);
	    	    	
	    	ArrayList<String> absorber=new ArrayList<String>();
	    	String str1=new String();
	    	String str2=new String();
	    	String car=new String();
	    	for(String s:newimd)
	    	{
	    		for(String d:newimd)
	    		{
	    			if(s.equals(d))
	    			{
	    				System.out.println("same elements");    				
	    			}
	    			else if(s.contains(d))
	    			{
	    				car=d;
	    			}
	    			else
	    				car=s;
	    			absorber.add(car);
	    		}
	    		int i=0;
	    	  System.out.println("choose A and a");
	    	  System.out.println(absorber.get(i));
	    	  ArrayList<String> A= new ArrayList<String>();
	    	  A.add(sc.next());
	    	  ArrayList<String> a= new ArrayList<String>();
	    	  a.add(absorber.get(i).replace(A.get(i),null));
	    	  System.out.println(a);
	    	  
		}
	}

}
