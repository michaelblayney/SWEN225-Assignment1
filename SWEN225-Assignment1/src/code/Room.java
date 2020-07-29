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

  public Room(String aName)
  {
    name = aName;

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




  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "roomCard = "+(getRoomCard()!=null?Integer.toHexString(System.identityHashCode(getRoomCard())):"null");
  }
}