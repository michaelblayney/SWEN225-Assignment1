package code;
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


  public RoomCard(String aName, Room aRoom)
  {
    super(aName);
    this.room=aRoom;


  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Room getRoom()
  {
    return room;
  }

}
