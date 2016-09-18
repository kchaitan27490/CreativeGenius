package krishna.processing.reducts;
import java.util.*;

import krishna.examples.implementer;
import krishna.processing.discernibility.DiscernibilityMatrixProvider; 
import krishna.processing.reducts.reductsprovider;
import krishna.structure.attribute.Header;
import krishna.structure.indiscernibility.Indiscernibility;
import krishna.structure.table.DoubleDataTable;
import krishna.system.Configuration;
import krishna.system.PropertyConfigurationException;
import weka.core.Instances;

public class Myreductalgorithm extends Configuration implements reductsprovider 
{
	ArrayList dm=new ArrayList();
	DiscernibilityMatrixProvider getdm;
	int i=0;
	
	public Myreductalgorithm(Properties prop, DoubleDataTable table) throws PropertyConfigurationException
	{
		super(prop);
		getdm= new DiscernibilityMatrixProvider(getProperties(),table);
	
	}
	
	public ArrayList getReducts()
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
	//	System.out.println("The possible subsets of discernibility matrix");
	//	System.out.println(fin);
	//	System.out.println(" ");
	//	System.out.println("the Discernibility matrix elements are");
		
		// FIN ELEMENTS ARE COMPARED WITH MAIN ELEMENTS FOR ACTUAL ABSORPTION
	
//		for(String s: adm)
//			System.out.print("["+s+"]");
	//	System.out.println(" ");
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
		//		System.out.println("The result of absorption phase are as follows :");	
			//	System.out.println(fin);
		return fin;	
	}
	
    public int[] attributeranker(Instances table) throws Exception
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
    //	System.out.println(" ");
    //	System.out.println("The gain ratio of the attributes are as follows");
    	
    /*	for(i=0;i<table.numAttributes();i++)
    	{
    		System.out.println(table.attribute(i)+" :: " + gainratioarray[i]);
    		System.out.println(" ");
    	} */
    
    	ArrayList rankedattrholder=new ArrayList();

    	int size=table.numAttributes();
    	int j=0;
    	double[] GRattrholder=new double[table.numAttributes()];
    	for(i=0;i<table.numAttributes();i++)
		{
			GRattrholder[i]=grat.evaluateAttribute(i);
			
		}
    	
    	Arrays.sort(gainratioarray);
    /*	System.out.println("The sorted gain ratio's are as follows");
    	for(i=0;i<table.numAttributes();i++)
    	{
    		System.out.println(table.attribute(i)+" :: "+gainratioarray[i]);
    		System.out.println(" ");
    	}
    	*/
    	int[] rank=new int[table.numAttributes()];
    	while(j<size)
    	{
    		for(i=0;i<table.numAttributes();i++)
    		{
    			if(GRattrholder[i]==gainratioarray[j])
    	    	{
    				rankedattrholder.add(table.attribute(i));
    				rank[j]=i;
    		   	}
    		}
    				j++;
    	}
    	
    //	System.out.println(rankedattrholder);
    	ArrayList rankholder=new ArrayList();
    	System.out.println(" ");
    	for(i=0;i<table.numAttributes();i++)
    		rankholder.add(rank[i]);
    //	System.out.println("The order of attributes in increasing order of rank are: ");
    //	System.out.println(rankholder);
    	return rank;

    }
    
  public String deletion(ArrayList<String> tempo,int[] rank)
  {

	  String ranks=Arrays.toString(rank);
	  
	  System.out.println(" ");
	  System.out.println("The ranks are :");
	  System.out.println(ranks);
	  String[] strarray=new String[tempo.size()];
	  System.out.println("The strarray in deletion phase is");
	  System.out.println(tempo);
	  tempo.toArray(strarray);
	  
  	  int threshold=0;
  	  
  	  Scanner sc=new Scanner(System.in);
	  System.out.println(rank.length);
	  System.out.println("enter threshold for deletion");
	  threshold=sc.nextInt();
	  int rankholder[]=new int[threshold];
	  if(threshold<0 || threshold>rank.length)
		  System.out.println("enter threshold values between 1 to "+rank.length);
	  
	  ArrayList<String> strarrayholder=new ArrayList<String>();

	  ranks=ranks.replaceAll(", " ,"");
	  System.out.println("ranks for comparision-modified");
	  System.out.println(ranks);
		 
      for(i=0;i<strarray.length;i++)
		 strarray[i]=strarray[i].replaceAll(", ","");
      //char holder;
      
	for(int j=1;j<=rankholder.length;j++) 
	 {
		for(i=0;i<strarray.length;i++)
		{
	 		for(int k=1;k<strarray[i].length();k++)		  			
			{
	 			
				if((strarray[i].charAt(k)==ranks.charAt(j)))
				{	
	 				
	 				
	 				strarray[i]=strarray[i].replace(strarray[i].charAt(k),' ');
					strarrayholder.add(strarray[i]);
				}
		  				else
		  					;
	  		}		
	 	}	  
	 }
	 for(i=0;i<strarray.length;i++)
	 {
		 strarray[i]=strarray[i].replaceAll(" ","");
	 } 
	
	 // AGGREGATION OF REDUCED ATTRS
	 
	ArrayList<String> reductarray=new ArrayList<String>();
	 
	 for(String s: strarray)
	 {
		 String str = s.substring(1, s.length()-1);
		 for(i=0;i<str.length();i++)
		 {
			 String s1 =  Character.toString(str.charAt(i));
			 if(reductarray.isEmpty())
				 reductarray.add(s1);
			 else
			 {
				 if(reductarray.contains(s1))
					 ;
				 else
					 reductarray.add(s1);
			 }			
		 }					 
	 }
	 System.out.println("Reduct array is: "+reductarray); 
	 String reductset=reductarray.toString();
	 return reductset;
  }    
}