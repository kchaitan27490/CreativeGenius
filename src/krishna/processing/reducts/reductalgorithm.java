package krishna.processing.reducts;

import java.util.*;

import krishna.processing.discernibility.DiscernibilityMatrixProvider;
import krishna.structure.table.DoubleDataTable;
import krishna.system.PropertyConfigurationException;
import krishna.system.Configuration;
import krishna.processing.reducts.reductsprovider;
import krishna.structure.attribute.Header;
import krishna.structure.indiscernibility.Indiscernibility;
public class reductalgorithm extends Configuration implements reductsprovider 
{
	private DiscernibilityMatrixProvider m_Discernibility;

    private Header m_Header;
    Scanner sc=new Scanner(System.in);
    public reductalgorithm(Properties prop, DoubleDataTable table) throws PropertyConfigurationException
    {
        super(prop);
        m_Discernibility = new DiscernibilityMatrixProvider(getProperties(), table);
        m_Header = table.attributes();
    }
	
	public ArrayList getReducts()
	{
		ArrayList discernmats=new ArrayList();
		
		discernmats.add(m_Discernibility.getDiscernibilityMatrix());
		ArrayList submatrix=new ArrayList();
		
		for(int i=0;i<discernmats.size();i++)
		{
			String str1=new String();
			   String str2=new String();
			   
			   str1=(String)discernmats.get(i).toString();
			   if(i!=discernmats.size()-1)
			   {   str2=(String)discernmats.get(i+1).toString();
					System.out.println("str2 is" + str2);
			   }
			   
			   System.out.println("size of str2 is"+ str2.length());
			   if(str2.length()!=0)
		       if(str1.contains(str2))
				  {
		    	   str1=str2;
		    	   submatrix.add(str1);
		    	   System.out.println(str1+" "+"added to submatrix");
				  }
		       else 
		       {
		    	   submatrix.add(str1);
		    	   if(i==discernmats.size()-1 && str2.length()!=0)
		    	   submatrix.add(str2);
		    	
		       }
			   
		       if(i==discernmats.size()-1)
		    	   submatrix.add(discernmats.get(i));
			
		}
		System.out.println(submatrix+" ");
		String str3=new String();
		String str4=new String();
		for(int i=0;i<submatrix.size();i++)
		{
			str3=(String)submatrix.get(i).toString();
			 if(i!=submatrix.size()-1)
			        str4=(String)submatrix.get(i+1).toString();
			 if(str3.contains(str4))
			  {
	    	   str3=str4;
	    	   submatrix.set(i,str3);
	    	  }
		}
		System.out.println(submatrix+" ");
		
		//*****2ND PHASE OF ALGM STARTS HERE
		
		int k=0;
		System.out.println("the available attributes at M[i,j] are");
		System.out.println(submatrix.get(k).toString());
		System.out.println("select an attribute");
		String strsingle= new String();
		strsingle= sc.next(); // the single attribute
		String strnew = new String();
		strnew= (String)submatrix.get(k).toString();
		strnew=strnew.replace(strsingle," ");// the M[i,j]-a
		submatrix.set(k,strsingle);
		
		for(int j=1;j<submatrix.size();j++)
		{
			String str=new String();
			str=(String)submatrix.get(j);
			if(str.contains(strsingle))
			{	
				str=strsingle;
			    submatrix.set(j,str);
			}
			else
			{
				str=strnew;
				submatrix.set(j,str);
			}
		}
		
		
	 return submatrix;
	}
		
}
	
	
	
	
	
	


