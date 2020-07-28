package code;
public class Room extends Location
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Room Attributes
  private String name;

  //Room Associations
  private RoomCard roomCard;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Room(Board aBoard, String aName, RoomCard aRoomCard)
  {
    super(aBoard);
    name = aName;
    if (aRoomCard == null || aRoomCard.getRoom() != null)
    {
      throw new RuntimeException("Unable to create Room due to aRoomCard. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    roomCard = aRoomCard;
  }

  public Room(Board aBoard, String aName, String aNameForRoomCard, Player aPlayerForRoomCard)
  {
    super(aBoard);
    name = aName;
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
  public RoomCard getRoomCard()
  {
    return roomCard;
  }

  public void delete()
  {
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
            "  " + "roomCard = "+(getRoomCard()!=null?Integer.toHexString(System.identityHashCode(getRoomCard())):"null");
  }
}