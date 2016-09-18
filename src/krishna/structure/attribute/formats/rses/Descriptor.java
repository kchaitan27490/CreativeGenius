package krishna.structure.attribute.formats.rses;

public class Descriptor
{
  Attr attr;
  IntSack values;

  public Descriptor(Attr a,int singleValue)
  {
    attr  =  new Attr(a);
    values = new IntSack();
    values.add(singleValue);
  }

  public Descriptor(Attr a,IntSack intSack)
  {
    attr  =  new Attr(a);
    values = new IntSack(intSack);
  }

  public void setAtrr(Attr a)
  {
    attr = new Attr(a);
  }

  public Attr getAttr() { return attr; }
  public IntSack getVal() { return values; }

  public boolean equals(Descriptor d)
  {
    if (!attr.equals(d.getAttr())) return false;
    return values.equals(d.getVal());
  }

  public String toString()
  {
    return new String("("+attr+"="+values.toString()+")");
  }

}
