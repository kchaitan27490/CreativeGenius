package krishna.examples;

import java.io.*;
import java.util.*;

import com.sun.corba.se.impl.orbutil.GetPropertyAction;

import krishna.structure.data.*;
import krishna.structure.table.ArrayListDoubleDataTable;
import krishna.structure.table.DoubleDataTable;
import krishna.system.Configuration;
import krishna.system.PropertyConfigurationException;
import krishna.system.progress.EmptyProgress;
import krishna.processing.discernibility.*;
import krishna.system.progress.*;
import krishna.system.output.*;
import krishna.system.Report;
import krishna.processing.reducts.*;
public class Implementer1 extends Configuration
{
public static void main(String[] args) throws Exception
{
	System.out.println(args[0]);
	File f1 = new File(args[0]);
	DoubleDataTable TestTable = new ArrayListDoubleDataTable(f1, new EmptyProgress());
	
	ArrayList data=new ArrayList();
	ArrayList data1=new ArrayList();
	
	data.add(TestTable.attributes());
	System.out.println("the data is");
	System.out.println(data);
	int i=0;
	
	data1.addAll(TestTable.getDataObjects());
	String IndiscernibilityForMissing="DiscernFromValue";
	System.out.println("the end");
	ArrayList dm=new ArrayList();
	implementer im=new implementer();
	Class implement=implementer.class;
	Properties prop=im.loadDefaultProperties(implement);
	DiscernibilityMatrixProvider getdm;
	getdm=new DiscernibilityMatrixProvider(prop,TestTable);
	
	dm.addAll(getdm.getDiscernibilityMatrix());
	ArrayList<String> adm=new ArrayList();
	Iterator it=dm.iterator();
	ArrayList<String> obj=new ArrayList();
	ArrayList<String> subset=new ArrayList();
	for(i=0;i<10;i++)
	{
		adm.add(dm.get(i).toString().substring(1, dm.get(i).toString().length()-1));
		obj.add(dm.get(i).toString().substring(1, dm.get(i).toString().length()-1));
	}
	System.out.println(adm);
	for(String str : adm)
	{
		for(String str1 : obj)
		{
			if(str.equals(str1))
			{
				
				System.out.println("same");
			}
			else if(str.contains(str1))
			{
				
				subset.add("["+str1+"]");
					
			}
					
		}
	}
	ArrayList<String> fin = new ArrayList();
	for(String s : adm)
	{
		for(String s1: subset)
		{
			if(fin.isEmpty())
			{
				if(s.contains(s1.substring(1, s1.length()-1)))
				{
					fin.add(s1);
					break;
				}
				else
				{
					fin.add("["+s+"]");
				}
			}
			else if(fin.contains("["+s+"]")|| fin.contains(s1))
				;
			else
			{
				if(s.contains(s1.substring(1, s1.length()-1)))
					{
					fin.add(s1);
					}
					
				else
				{
					fin.add("["+s+"]");
				}
			}
			
		}
		
	}
	for(String s: adm)
		System.out.print("["+s+"]");
	ArrayList<String> temp = new ArrayList(fin);
	System.out.println();
		for(String str:adm)
			for(String s1: temp)
			{
				if(fin.contains("["+str+"]"))
				{
					if(fin.contains(s1))
						break;
					else
						fin.add("["+str+"]") ;
				}
				else
				{

					if((str.contains(s1.substring(1, s1.length()-1))))
					{
						fin.add(s1);break;
					}
				}
			}
	System.out.println(fin);
	

}

}