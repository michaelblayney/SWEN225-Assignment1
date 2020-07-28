package code;
public class CardCombination
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CardCombination Associations
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CardCombination(Game aGame)
  {
    if (!setGame(aGame))
    {
      throw new RuntimeException("Unable to create CardCombination due to aGame. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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