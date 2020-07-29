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

  /**
   * Board doesn't *need* the individual locations to be able to see it - board just stores and changes them.
   */
  public Location(){

  }

  /**
   * If for whatever reason you need to store the board in the location, use this constructor instead.
   * @param b
   */
  public Location(Board b){
  board=b;
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
