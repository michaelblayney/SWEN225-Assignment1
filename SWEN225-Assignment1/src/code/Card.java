package code;

public class Card {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------
	
	String name;
	
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
