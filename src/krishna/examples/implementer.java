package krishna.examples;

import java.io.*;
import java.util.*;

import com.sun.corba.se.impl.orbutil.GetPropertyAction;

import krishna.structure.attribute.formats.HeaderFormatException;
import krishna.structure.data.*;
import krishna.structure.data.formats.DataFormatException;
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
import weka.attributeSelection.ASEvaluation;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;

public class implementer extends Configuration
{
	public implementer()
	{
		
	}
	public File Fileloader(String fname)
	{
		System.out.println(fname);
		File f1 = new File(fname);
		return f1;
	}
	public DoubleDataTable Tableinitialiser(String fname) throws IOException, HeaderFormatException, DataFormatException, InterruptedException
	{
		File f1 = new File(fname);
	
			DoubleDataTable TestTable = new ArrayListDoubleDataTable(f1, new EmptyProgress());
		return TestTable;
		
	}
	public ArrayList Attributeviewer(String fname) throws IOException, HeaderFormatException, DataFormatException, InterruptedException
	{
		ArrayList attributes=new ArrayList();
		DoubleDataTable table=Tableinitialiser(fname);
		attributes.add(table.attributes());
		return attributes;
	}
	public ArrayList DataViewer(String fname) throws IOException, HeaderFormatException, DataFormatException, InterruptedException 
	{
		ArrayList data=new ArrayList();
		DoubleDataTable table=Tableinitialiser(fname);
		data.add(table.getDataObjects());
		return data;
	}
	public int[] RankerCaller(String fname) throws Exception
	{
		ArrayList dm=new ArrayList();
		implementer im=new implementer();
		Class implement=implementer.class;
		Properties prop=im.loadDefaultProperties(implement);
		DiscernibilityMatrixProvider getdm;
		getdm=new DiscernibilityMatrixProvider(prop,Tableinitialiser(fname));
		Myreductalgorithm myred=new Myreductalgorithm(prop,Tableinitialiser(fname));
		DataSource source= new DataSource(fname);
		Instances instance= source.getDataSet();
		int[] rank=myred.attributeranker(instance);
		
		//System.out.println("the ranks are:");
		//System.out.println(rankset);
		return rank;
	}
	
	public ArrayList<String> FinalReductProducer(String fname) throws Exception
	{
		implementer imp=new implementer();
		Class implement=implementer.class;
		Properties prop=imp.loadDefaultProperties(implement);
		DiscernibilityMatrixProvider getdm;
		getdm=new DiscernibilityMatrixProvider(prop,Tableinitialiser(fname));
		Test myred=new Test(prop,Tableinitialiser(fname));
		DataSource source= new DataSource(fname);
		Instances instance= source.getDataSet();
		ArrayList<String> reduct;
		reduct=myred.deletion(myred.getReducts(), myred.attributeranker(instance));
		return reduct;
	}
}