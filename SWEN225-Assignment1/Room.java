/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5071.d9da8f6cd modeling language!*/



// line 62 "model.ump"
// line 161 "model.ump"
public class Room extends Location
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Room Attributes
  private String name;

  //Room Associations
  private Game game;
  private RoomCard roomCard;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Room(Board aBoard, String aName, Game aGame, RoomCard aRoomCard)
  {
    super(aBoard);
    name = aName;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create room due to game. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aRoomCard == null || aRoomCard.getRoom() != null)
    {
      throw new RuntimeException("Unable to create Room due to aRoomCard. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    roomCard = aRoomCard;
  }

  public Room(Board aBoard, String aName, Game aGame, String aNameForRoomCard, Player aPlayerForRoomCard)
  {
    super(aBoard);
    name = aName;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create room due to game. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    roomCard = new RoomCard(aNameForRoomCard, aPlayerForRoomCard, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_GetOne */
  public RoomCard getRoomCard()
  {
    return roomCard;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    //Must provide game to room
    if (aGame == null)
    {
      return wasSet;
    }

    //game already at maximum (9)
    if (aGame.numberOfRooms() >= Game.maximumNumberOfRooms())
    {
      return wasSet;
    }
    
    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      boolean didRemove = existingGame.removeRoom(this);
      if (!didRemove)
      {
        game = existingGame;
        return wasSet;
      }
    }
    game.addRoom(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeRoom(this);
    }
    RoomCard existingRoomCard = roomCard;
    roomCard = null;
    if (existingRoomCard != null)
    {
      existingRoomCard.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "roomCard = "+(getRoomCard()!=null?Integer.toHexString(System.identityHashCode(getRoomCard())):"null");
  }
}