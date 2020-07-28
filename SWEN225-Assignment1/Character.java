/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5071.d9da8f6cd modeling language!*/



// line 49 "model.ump"
// line 148 "model.ump"
public class Character extends MoveablePiece
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Character Associations
  private Player player;
  private PersonCard personCard;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Character(String aName, Board aBoard, PersonCard aPersonCard, Game aGame)
  {
    super(aName, aBoard);
    if (aPersonCard == null || aPersonCard.getCharacter() != null)
    {
      throw new RuntimeException("Unable to create Character due to aPersonCard. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    personCard = aPersonCard;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create character due to game. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Character(String aName, Board aBoard, String aNameForPersonCard, Player aPlayerForPersonCard, Game aGame)
  {
    super(aName, aBoard);
    personCard = new PersonCard(aNameForPersonCard, aPlayerForPersonCard, this);
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create character due to game. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }

  public boolean hasPlayer()
  {
    boolean has = player != null;
    return has;
  }
  /* Code from template association_GetOne */
  public PersonCard getPersonCard()
  {
    return personCard;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setPlayer(Player aNewPlayer)
  {
    boolean wasSet = false;
    if (player != null && !player.equals(aNewPlayer) && equals(player.getCharacter()))
    {
      //Unable to setPlayer, as existing player would become an orphan
      return wasSet;
    }

    player = aNewPlayer;
    Character anOldCharacter = aNewPlayer != null ? aNewPlayer.getCharacter() : null;

    if (!this.equals(anOldCharacter))
    {
      if (anOldCharacter != null)
      {
        anOldCharacter.player = null;
      }
      if (player != null)
      {
        player.setCharacter(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    //Must provide game to character
    if (aGame == null)
    {
      return wasSet;
    }

    //game already at maximum (6)
    if (aGame.numberOfCharacters() >= Game.maximumNumberOfCharacters())
    {
      return wasSet;
    }
    
    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      boolean didRemove = existingGame.removeCharacter(this);
      if (!didRemove)
      {
        game = existingGame;
        return wasSet;
      }
    }
    game.addCharacter(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Player existingPlayer = player;
    player = null;
    if (existingPlayer != null)
    {
      existingPlayer.delete();
    }
    PersonCard existingPersonCard = personCard;
    personCard = null;
    if (existingPersonCard != null)
    {
      existingPersonCard.delete();
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeCharacter(this);
    }
    super.delete();
  }

}