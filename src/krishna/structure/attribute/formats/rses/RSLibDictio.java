package krishna.structure.attribute.formats.rses;
import java.util.*;
import java.io.*;
public class RSLibDictio 
{
	HashMap<String,IntWrap> hasMap;
	  ArrayList<String> words;
	  int GLOBAL_COUNTER;

	  public RSLibDictio()
	  {
	    hasMap = new HashMap<String,IntWrap>();
	    words = new ArrayList<String>();
	    GLOBAL_COUNTER = 0;
	  }

	  public int addWord(String oldWord)
	  {
	    String newWord = Element.addQuotationMarks(oldWord);

	    IntWrap keyWord = (IntWrap)hasMap.get(newWord);

	    if (keyWord==null)
	    {
	      String localWord = new String(newWord);
	      keyWord = new IntWrap(GLOBAL_COUNTER);
	      GLOBAL_COUNTER++;
	      hasMap.put(localWord,keyWord);
	      words.add(localWord);
	    }

	    return keyWord.getValue();
	  }

	  public String getWord(int key)
	  {
	    if ((key<0)||(key>=size()))
	    {
	       throw new IndexOutOfBoundsException("Bad key of word in GlobalDictio - Key: "+key+", Size: "+size());
	    }

	    return (String)words.get(key);
	  }

	  public int getKey(String word)
	  {
	    IntWrap keyWord = (IntWrap)hasMap.get(word);

	    if (keyWord==null)
	    {
	      throw new IndexOutOfBoundsException("Key for word <"+word+"> cannot find in global dictionary!");
	    }

	    return keyWord.getValue();

	  }

	  public int size()
	  {
	    return words.size();
	  }

	  public void saveDictio(String fName) throws IOException
	  {
	    FileOutputStream fos = new FileOutputStream(fName);
	    OutputStreamWriter osw = new OutputStreamWriter(fos);
	    PrintWriter pw = new PrintWriter(osw);

	    saveToFile(pw);

	    pw.close();
	  }

	  public void saveToFile(PrintWriter pw) throws IOException
	  {
	    pw.println(size());
	    for (int i=0; i<size(); i++)
	    {
	      pw.println(getWord(i));
	    }
	  }

	  public void loadDictio(String fName)
	  throws IOException, NumberFormatException
	  {

	    File inFile = new File(fName);

	    if (!inFile.exists())
		    throw new IOException("Can't open file with global dictionary: " + fName);

	    FileReader fr = new FileReader(inFile);
	    BufferedReader br = new BufferedReader(fr);

	    loadFromFile(br);

	    br.close();
	  }

	  public void loadFromFile(BufferedReader br)
	  throws IOException, NumberFormatException
	  {
	    int noWords = Element.loadIntFromFile(br,"Dictionary.loadFromFile");

	    for (int i=0; i<noWords; i++)
	    {
	      String line = br.readLine();
	      StringTokenizer st = new StringTokenizer(line);
	      String word = Element.addQuotationMarks(Element.readNextToken(st,null));
	      addWord(word);
	    }
	  }

	  static public IntSack searchValuesOfAttrWithoutTune(Table table,int attrNum)
	  {
	    IntSack sack = new IntSack();
	    for (int i=0; i<table.getNoObj(); i++)
	    {
	      sack.addNoEqual(table.getTable(i,attrNum));
	    }
	    return sack;
	  }



	  public static void main(String [] a)
	  {

	  }
}
