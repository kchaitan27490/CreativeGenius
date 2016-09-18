package krishna.processing.reducts;
import java.util.*;
import java.util.Arrays;

import krishna.examples.implementer;
import krishna.processing.discernibility.DiscernibilityMatrixProvider; 
import krishna.processing.reducts.reductsprovider;
import krishna.structure.attribute.Header;
import krishna.structure.indiscernibility.Indiscernibility;
import krishna.structure.table.DoubleDataTable;
import krishna.system.Configuration;
import krishna.system.PropertyConfigurationException;
import weka.core.Instances;

public class Test extends Configuration implements reductsprovider 
{
	ArrayList dm=new ArrayList();
	DiscernibilityMatrixProvider getdm;
	int i=0;
	
	public Test(Properties prop, DoubleDataTable table) throws PropertyConfigurationException
	{
		super(prop);
		getdm= new DiscernibilityMatrixProvider(getProperties(),table);
	
	}
	
	public ArrayList<String> getReducts()
	{
		dm.addAll(getdm.getDiscernibilityMatrix());
		ArrayList<String> adm=new ArrayList();
		ArrayList<String> obj=new ArrayList();
		ArrayList<String> subset=new ArrayList();
		for(i=0;i<100;i++)
		{
			adm.add(dm.get(i).toString().substring(1, dm.get(i).toString().length()-1));
			obj.add(dm.get(i).toString().substring(1, dm.get(i).toString().length()-1));
		}
		for(String str : adm)
		{
			for(String str1 : obj)
			{
				if(str.equals(str1))
				{
					//System.out.println("same");
					;
				}
				else if(str.contains(str1))
				{
					subset.add("["+str1+"]");
				}
			}
		}
		ArrayList<String> fin = new ArrayList<String>();
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
	ArrayList<String> temp = new ArrayList(fin);
	//	System.out.println();
		if(fin.isEmpty())
			for(i=0;i<adm.size();i++)
			fin.add("["+adm.get(i)+"]");
		else
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
		//		System.out.println(fin);
		return fin;	
	}
	
    public String[] attributeranker(Instances table) throws Exception
    {
    	weka.attributeSelection.AttributeSelection atsel=new weka.attributeSelection.AttributeSelection();
   	
    	weka.attributeSelection.GainRatioAttributeEval grat=new weka.attributeSelection.GainRatioAttributeEval();
    	
    	weka.attributeSelection.Ranker ranker = new weka.attributeSelection.Ranker();
    		
    	atsel.setEvaluator(grat);
    	atsel.setSearch(ranker);
    	atsel.SelectAttributes(table);
    	grat.buildEvaluator(table);
    	double[] gainratioarray=new double[table.numAttributes()];
    	for(i=0;i<table.numAttributes();i++)
    		{
    			gainratioarray[i]=grat.evaluateAttribute(i);   			
    		}
    	ArrayList rankedattrholder=new ArrayList();

    	int size=table.numAttributes();
    	int j=0;
    	double[] GRattrholder=new double[table.numAttributes()];
    	for(i=0;i<table.numAttributes();i++)
		{
			GRattrholder[i]=grat.evaluateAttribute(i);
			
		}
    	
    	Arrays.sort(gainratioarray);
    	String rank[]=new String[table.numAttributes()];
    	while(j<size)
    	{
    		for(Integer i=0;i<table.numAttributes();i++)
    		{
    			if(GRattrholder[i]==gainratioarray[j])
    	    	{
    				rankedattrholder.add(table.attribute(i));
    				rank[j]=i.toString();
    		   	}
    		}
    				j++;
    	}
    	
    //	System.out.println(rankedattrholder);
    	ArrayList<String> rankholder=new ArrayList();
    	System.out.println(" ");
    	for(i=0;i<table.numAttributes();i++)
    		rankholder.add(rank[i]);
    //	System.out.println("The order of attributes in increasing order of rank are: ");
    //	System.out.println(rankholder);
    	return rank;

    }
    
  public ArrayList<String> deletion(ArrayList<String> rarray,String rank[])
  {

	  System.out.println("The ranks are :");
	  for(int i=0;i<rank.length;i++)
	  System.out.println(rank[i]);
	  int threshold=0;
  	  Scanner sc=new Scanner(System.in);
	  System.out.println("enter threshold for deletion");
	  threshold=sc.nextInt();
	  if(threshold<0 || threshold>rank.length)
		  System.out.println("enter threshold values between 1 to "+rank.length);
	  
	    
	  ArrayList<String> oarray=new ArrayList<String>();  
	  int count=0;
	  int i=0;
	  ArrayList inter=new ArrayList();
	  //String str2[];
	  for(String str: rarray)
	  {
		  String str1[]=str.substring(1, str.indexOf("]")).split(",");
		  
		  for(String s:str1)
		  {
			  //count=0;
			 // System.out.println("count value"+count);
			  //System.out.println("s value"+s);  
				 while(i<threshold)
				 {
				  if(s.equals(rank[i]))
				  {
					  if(inter.isEmpty())
						  ;
					  else
						  inter.remove(s);
					 
					  break;
				  }
					  
				  else
				  {	
					  if(inter.isEmpty())
					  inter.add(s);
					  else if(inter.contains(s))
						  ;
					  else
						  inter.add(s);
					  i++;
					  
				  }
				  
				 // System.out.println("count values"+count);
				 }	  
				/*  else
				  {
					  if(oarray.isEmpty())
						  oarray.add(s);
					  else if(oarray.contains(s))
						  ;
					  else
						  oarray.add(s);
					  
				  }
				  } */
		  }
		  
	  }
	  System.out.println("Intermediary values are");
	  System.out.println(inter);
	/*  System.out.println("OARRAY values are");
	  for(i=0;i<oarray.size();i++)
		  System.out.println(oarray.get(i)); */
	  
	/*  String finstr="[";
	  for(i=0;i<str1.length;i++)
		  for(int j=0;j<rank.length;j++)
		  {
			  if(j<threshold)
			  {
				  if(str1[i].equals(rank[i]))
				  {
					  if(finstr.contains(rank[i]))
							finstr = finstr.replace(rank[i]+",", "");
						break;
				  }
				  
					  
			  }
		  }
		  */
	
	  return oarray;
  }
}