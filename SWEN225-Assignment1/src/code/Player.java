/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5071.d9da8f6cd modeling language!*/



// line 11 "model.ump"
// line 103 "model.ump"
public class Player
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Associations
  private Game game;
  private Character character;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(Game aGame, Character aCharacter)
  {
    if (!setGame(aGame))
    {
      throw new RuntimeException("Unable to create Player due to aGame. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddCharacter = setCharacter(aCharacter);
    if (!didAddCharacter)
    {
      throw new RuntimeException("Unable to create player due to character. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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
  /* Code from template association_GetOne */
  public Character getCharacter()
  {
    return character;
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
  /* Code from template association_SetOneToOptionalOne */
  public boolean setCharacter(Character aNewCharacter)
  {
    boolean wasSet = false;
    if (aNewCharacter == null)
    {
      //Unable to setCharacter to null, as player must always be associated to a character
      return wasSet;
    }
    
    Player existingPlayer = aNewCharacter.getPlayer();
    if (existingPlayer != null && !equals(existingPlayer))
    {
      //Unable to setCharacter, the current character already has a player, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Character anOldCharacter = character;
    character = aNewCharacter;
    character.setPlayer(this);

    if (anOldCharacter != null)
    {
      anOldCharacter.setPlayer(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    game = null;
    Character existingCharacter = character;
    character = null;
    if (existingCharacter != null)
    {
      existingCharacter.setPlayer(null);
    }
  }

}