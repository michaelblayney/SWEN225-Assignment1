package code;
public class Board
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Board Associations
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Board(Game aGame)
  {
    if (!setGame(aGame))
    {
      throw new RuntimeException("Unable to create Board due to aGame. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setGame(Game aNewGame)
  {
    boolean wasSet = false;
    if (aNewGame != null)
    {
      game = aNewGame;
      wasSet = true;
    }
    return wasSet;
  }

  /**
   * For use during init. Stub method for now.
   */
  public void addCharacter(Character c){

  }
  /**
   * For use during init. Stub method for now.
   */
  public void addWeapon(Weapon w){

  }

  public void delete()
  {
    game = null;
  }

}