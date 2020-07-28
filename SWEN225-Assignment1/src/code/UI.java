package code;
public class UI
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //UI Associations
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public UI(Game aGame)
  {
    if (!setGame(aGame))
    {
      throw new RuntimeException("Unable to create UI due to aGame. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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

  public void delete()
  {
    game = null;
  }

}