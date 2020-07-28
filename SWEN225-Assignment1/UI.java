/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5071.d9da8f6cd modeling language!*/



// line 79 "model.ump"
// line 177 "model.ump"
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
    if (aGame == null || aGame.getUI() != null)
    {
      throw new RuntimeException("Unable to create UI due to aGame. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    game = aGame;
  }

  public UI()
  {
    game = new Game(this);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }

  public void delete()
  {
    Game existingGame = game;
    game = null;
    if (existingGame != null)
    {
      existingGame.delete();
    }
  }

}