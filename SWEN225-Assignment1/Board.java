/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5071.d9da8f6cd modeling language!*/


import java.util.*;

// line 19 "model.ump"
// line 115 "model.ump"
public class Board
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Board Associations
  private List<Location> locations;
  private List<MoveablePiece> moveablePieces;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Board(Game aGame)
  {
    locations = new ArrayList<Location>();
    moveablePieces = new ArrayList<MoveablePiece>();
    if (!setGame(aGame))
    {
      throw new RuntimeException("Unable to create Board due to aGame. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Location getLocation(int index)
  {
    Location aLocation = locations.get(index);
    return aLocation;
  }

  public List<Location> getLocations()
  {
    List<Location> newLocations = Collections.unmodifiableList(locations);
    return newLocations;
  }

  public int numberOfLocations()
  {
    int number = locations.size();
    return number;
  }

  public boolean hasLocations()
  {
    boolean has = locations.size() > 0;
    return has;
  }

  public int indexOfLocation(Location aLocation)
  {
    int index = locations.indexOf(aLocation);
    return index;
  }
  /* Code from template association_GetMany */
  public MoveablePiece getMoveablePiece(int index)
  {
    MoveablePiece aMoveablePiece = moveablePieces.get(index);
    return aMoveablePiece;
  }

  public List<MoveablePiece> getMoveablePieces()
  {
    List<MoveablePiece> newMoveablePieces = Collections.unmodifiableList(moveablePieces);
    return newMoveablePieces;
  }

  public int numberOfMoveablePieces()
  {
    int number = moveablePieces.size();
    return number;
  }

  public boolean hasMoveablePieces()
  {
    boolean has = moveablePieces.size() > 0;
    return has;
  }

  public int indexOfMoveablePiece(MoveablePiece aMoveablePiece)
  {
    int index = moveablePieces.indexOf(aMoveablePiece);
    return index;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLocations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Location addLocation()
  {
    return new Location(this);
  }

  public boolean addLocation(Location aLocation)
  {
    boolean wasAdded = false;
    if (locations.contains(aLocation)) { return false; }
    Board existingBoard = aLocation.getBoard();
    boolean isNewBoard = existingBoard != null && !this.equals(existingBoard);
    if (isNewBoard)
    {
      aLocation.setBoard(this);
    }
    else
    {
      locations.add(aLocation);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLocation(Location aLocation)
  {
    boolean wasRemoved = false;
    //Unable to remove aLocation, as it must always have a board
    if (!this.equals(aLocation.getBoard()))
    {
      locations.remove(aLocation);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLocationAt(Location aLocation, int index)
  {  
    boolean wasAdded = false;
    if(addLocation(aLocation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLocations()) { index = numberOfLocations() - 1; }
      locations.remove(aLocation);
      locations.add(index, aLocation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLocationAt(Location aLocation, int index)
  {
    boolean wasAdded = false;
    if(locations.contains(aLocation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLocations()) { index = numberOfLocations() - 1; }
      locations.remove(aLocation);
      locations.add(index, aLocation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLocationAt(aLocation, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMoveablePieces()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MoveablePiece addMoveablePiece(String aName)
  {
    return new MoveablePiece(aName, this);
  }

  public boolean addMoveablePiece(MoveablePiece aMoveablePiece)
  {
    boolean wasAdded = false;
    if (moveablePieces.contains(aMoveablePiece)) { return false; }
    Board existingBoard = aMoveablePiece.getBoard();
    boolean isNewBoard = existingBoard != null && !this.equals(existingBoard);
    if (isNewBoard)
    {
      aMoveablePiece.setBoard(this);
    }
    else
    {
      moveablePieces.add(aMoveablePiece);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMoveablePiece(MoveablePiece aMoveablePiece)
  {
    boolean wasRemoved = false;
    //Unable to remove aMoveablePiece, as it must always have a board
    if (!this.equals(aMoveablePiece.getBoard()))
    {
      moveablePieces.remove(aMoveablePiece);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMoveablePieceAt(MoveablePiece aMoveablePiece, int index)
  {  
    boolean wasAdded = false;
    if(addMoveablePiece(aMoveablePiece))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMoveablePieces()) { index = numberOfMoveablePieces() - 1; }
      moveablePieces.remove(aMoveablePiece);
      moveablePieces.add(index, aMoveablePiece);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMoveablePieceAt(MoveablePiece aMoveablePiece, int index)
  {
    boolean wasAdded = false;
    if(moveablePieces.contains(aMoveablePiece))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMoveablePieces()) { index = numberOfMoveablePieces() - 1; }
      moveablePieces.remove(aMoveablePiece);
      moveablePieces.add(index, aMoveablePiece);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMoveablePieceAt(aMoveablePiece, index);
    }
    return wasAdded;
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
    for(int i=locations.size(); i > 0; i--)
    {
      Location aLocation = locations.get(i - 1);
      aLocation.delete();
    }
    for(int i=moveablePieces.size(); i > 0; i--)
    {
      MoveablePiece aMoveablePiece = moveablePieces.get(i - 1);
      aMoveablePiece.delete();
    }
    game = null;
  }

}