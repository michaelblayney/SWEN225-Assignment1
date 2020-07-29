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

  /**
   * This needs to ask how many people are playing, wait on the user's input, then sent that user's input back to whatever called it.
   * It also needs to ensure that the result is within the provided minimum and maximum
   * If not, ask the user again
   * @return
   */
    public int getNumberOfPlayers(int minimum, int maximum) {
      System.out.println("How many people are playing?");//Delegate this to the UI once that's working!

      return 0;
    }
}