/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5071.d9da8f6cd modeling language!*/



// line 40 "model.ump"
// line 134 "model.ump"
public class RoomCard extends Card
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //RoomCard Associations
  private Room room;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RoomCard(String aName, Player aPlayer, Room aRoom)
  {
    super(aName, aPlayer);
    if (aRoom == null || aRoom.getRoomCard() != null)
    {
      throw new RuntimeException("Unable to create RoomCard due to aRoom. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    room = aRoom;
  }

  public RoomCard(String aName, Player aPlayer, Board aBoardForRoom, String aNameForRoom)
  {
    super(aName, aPlayer);
    room = new Room(aBoardForRoom, aNameForRoom, this);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Room getRoom()
  {
    return room;
  }

  public void delete()
  {
    Room existingRoom = room;
    room = null;
    if (existingRoom != null)
    {
      existingRoom.delete();
    }
    super.delete();
  }

}