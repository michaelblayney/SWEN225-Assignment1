package code;
public class Location
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Location Associations
  private Board board;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Location(Board aBoard)
  {
    if (!setBoard(aBoard))
    {
      throw new RuntimeException("Unable to create Location due to aBoard. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Board getBoard()
  {
    return board;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setBoard(Board aNewBoard)
  {
    boolean wasSet = false;
    if (aNewBoard != null)
    {
      board = aNewBoard;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    board = null;
  }

}