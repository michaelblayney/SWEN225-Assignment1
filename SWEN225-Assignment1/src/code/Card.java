package code;
public class Card
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Card Attributes
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Card(String aName)
  {
    this.name=aName;
  }



  //------------------------
  // INTERFACE
  //------------------------

  public String getName()
  {
    return name;
  }

}