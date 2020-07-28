/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5071.d9da8f6cd modeling language!*/


import java.util.*;

/**
 * Unable to update umple code due to error at [19,2]
 * Unable to update umple code due to error at [18,2]
 */
// line 4 "model.ump"
// line 99 "model.ump"
public class Game
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Associations
  private UI uI;
  private List<Weapon> weapons;
  private List<Room> rooms;
  private List<Character> characters;
  private List<CardCombination> cardCombinations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(UI aUI)
  {
    if (aUI == null || aUI.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aUI. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    uI = aUI;
    weapons = new ArrayList<Weapon>();
    rooms = new ArrayList<Room>();
    characters = new ArrayList<Character>();
    cardCombinations = new ArrayList<CardCombination>();
  }

  public Game()
  {
    players = new ArrayList<Player>();
    board = new Board(null);
    uI = new UI(this);
    weapons = new ArrayList<Weapon>();
    rooms = new ArrayList<Room>();
    characters = new ArrayList<Character>();
    cardCombinations = new ArrayList<CardCombination>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public UI getUI()
  {
    return uI;
  }
  /* Code from template association_GetMany */
  public Weapon getWeapon(int index)
  {
    Weapon aWeapon = weapons.get(index);
    return aWeapon;
  }

  public List<Weapon> getWeapons()
  {
    List<Weapon> newWeapons = Collections.unmodifiableList(weapons);
    return newWeapons;
  }

  public int numberOfWeapons()
  {
    int number = weapons.size();
    return number;
  }

  public boolean hasWeapons()
  {
    boolean has = weapons.size() > 0;
    return has;
  }

  public int indexOfWeapon(Weapon aWeapon)
  {
    int index = weapons.indexOf(aWeapon);
    return index;
  }
  /* Code from template association_GetMany */
  public Room getRoom(int index)
  {
    Room aRoom = rooms.get(index);
    return aRoom;
  }

  public List<Room> getRooms()
  {
    List<Room> newRooms = Collections.unmodifiableList(rooms);
    return newRooms;
  }

  public int numberOfRooms()
  {
    int number = rooms.size();
    return number;
  }

  public boolean hasRooms()
  {
    boolean has = rooms.size() > 0;
    return has;
  }

  public int indexOfRoom(Room aRoom)
  {
    int index = rooms.indexOf(aRoom);
    return index;
  }
  /* Code from template association_GetMany */
  public Character getCharacter(int index)
  {
    Character aCharacter = characters.get(index);
    return aCharacter;
  }

  public List<Character> getCharacters()
  {
    List<Character> newCharacters = Collections.unmodifiableList(characters);
    return newCharacters;
  }

  public int numberOfCharacters()
  {
    int number = characters.size();
    return number;
  }

  public boolean hasCharacters()
  {
    boolean has = characters.size() > 0;
    return has;
  }

  public int indexOfCharacter(Character aCharacter)
  {
    int index = characters.indexOf(aCharacter);
    return index;
  }
  /* Code from template association_GetMany */
  public CardCombination getCardCombination(int index)
  {
    CardCombination aCardCombination = cardCombinations.get(index);
    return aCardCombination;
  }

  public List<CardCombination> getCardCombinations()
  {
    List<CardCombination> newCardCombinations = Collections.unmodifiableList(cardCombinations);
    return newCardCombinations;
  }

  public int numberOfCardCombinations()
  {
    int number = cardCombinations.size();
    return number;
  }

  public boolean hasCardCombinations()
  {
    boolean has = cardCombinations.size() > 0;
    return has;
  }

  public int indexOfCardCombination(CardCombination aCardCombination)
  {
    int index = cardCombinations.indexOf(aCardCombination);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfWeaponsValid()
  {
    boolean isValid = numberOfWeapons() >= minimumNumberOfWeapons() && numberOfWeapons() <= maximumNumberOfWeapons();
    return isValid;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfWeapons()
  {
    return 6;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWeapons()
  {
    return 6;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfWeapons()
  {
    return 6;
  }
  /* Code from template association_AddMNToOnlyOne */
  public Weapon addWeapon(String aName, Board aBoard, WeaponCard aWeaponCard)
  {
    if (numberOfWeapons() >= maximumNumberOfWeapons())
    {
      return null;
    }
    else
    {
      return new Weapon(aName, aBoard, aWeaponCard, this);
    }
  }

  public boolean addWeapon(Weapon aWeapon)
  {
    boolean wasAdded = false;
    if (weapons.contains(aWeapon)) { return false; }
    if (numberOfWeapons() >= maximumNumberOfWeapons())
    {
      return wasAdded;
    }

    Game existingGame = aWeapon.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);

    if (isNewGame && existingGame.numberOfWeapons() <= minimumNumberOfWeapons())
    {
      return wasAdded;
    }

    if (isNewGame)
    {
      aWeapon.setGame(this);
    }
    else
    {
      weapons.add(aWeapon);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWeapon(Weapon aWeapon)
  {
    boolean wasRemoved = false;
    //Unable to remove aWeapon, as it must always have a game
    if (this.equals(aWeapon.getGame()))
    {
      return wasRemoved;
    }

    //game already at minimum (6)
    if (numberOfWeapons() <= minimumNumberOfWeapons())
    {
      return wasRemoved;
    }
    weapons.remove(aWeapon);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfRoomsValid()
  {
    boolean isValid = numberOfRooms() >= minimumNumberOfRooms() && numberOfRooms() <= maximumNumberOfRooms();
    return isValid;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfRooms()
  {
    return 9;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRooms()
  {
    return 9;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfRooms()
  {
    return 9;
  }
  /* Code from template association_AddMNToOnlyOne */
  public Room addRoom(Board aBoard, String aName, RoomCard aRoomCard)
  {
    if (numberOfRooms() >= maximumNumberOfRooms())
    {
      return null;
    }
    else
    {
      return new Room(aBoard, aName, this, aRoomCard);
    }
  }

  public boolean addRoom(Room aRoom)
  {
    boolean wasAdded = false;
    if (rooms.contains(aRoom)) { return false; }
    if (numberOfRooms() >= maximumNumberOfRooms())
    {
      return wasAdded;
    }

    Game existingGame = aRoom.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);

    if (isNewGame && existingGame.numberOfRooms() <= minimumNumberOfRooms())
    {
      return wasAdded;
    }

    if (isNewGame)
    {
      aRoom.setGame(this);
    }
    else
    {
      rooms.add(aRoom);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRoom(Room aRoom)
  {
    boolean wasRemoved = false;
    //Unable to remove aRoom, as it must always have a game
    if (this.equals(aRoom.getGame()))
    {
      return wasRemoved;
    }

    //game already at minimum (9)
    if (numberOfRooms() <= minimumNumberOfRooms())
    {
      return wasRemoved;
    }
    rooms.remove(aRoom);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfCharactersValid()
  {
    boolean isValid = numberOfCharacters() >= minimumNumberOfCharacters() && numberOfCharacters() <= maximumNumberOfCharacters();
    return isValid;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfCharacters()
  {
    return 6;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCharacters()
  {
    return 6;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfCharacters()
  {
    return 6;
  }
  /* Code from template association_AddMNToOnlyOne */
  public Character addCharacter(String aName, Board aBoard, PersonCard aPersonCard)
  {
    if (numberOfCharacters() >= maximumNumberOfCharacters())
    {
      return null;
    }
    else
    {
      return new Character(aName, aBoard, aPersonCard, this);
    }
  }

  public boolean addCharacter(Character aCharacter)
  {
    boolean wasAdded = false;
    if (characters.contains(aCharacter)) { return false; }
    if (numberOfCharacters() >= maximumNumberOfCharacters())
    {
      return wasAdded;
    }

    Game existingGame = aCharacter.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);

    if (isNewGame && existingGame.numberOfCharacters() <= minimumNumberOfCharacters())
    {
      return wasAdded;
    }

    if (isNewGame)
    {
      aCharacter.setGame(this);
    }
    else
    {
      characters.add(aCharacter);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCharacter(Character aCharacter)
  {
    boolean wasRemoved = false;
    //Unable to remove aCharacter, as it must always have a game
    if (this.equals(aCharacter.getGame()))
    {
      return wasRemoved;
    }

    //game already at minimum (6)
    if (numberOfCharacters() <= minimumNumberOfCharacters())
    {
      return wasRemoved;
    }
    characters.remove(aCharacter);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCardCombinations()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addCardCombination(CardCombination aCardCombination)
  {
    boolean wasAdded = false;
    if (cardCombinations.contains(aCardCombination)) { return false; }
    cardCombinations.add(aCardCombination);
    if (aCardCombination.indexOfGame(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCardCombination.addGame(this);
      if (!wasAdded)
      {
        cardCombinations.remove(aCardCombination);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeCardCombination(CardCombination aCardCombination)
  {
    boolean wasRemoved = false;
    if (!cardCombinations.contains(aCardCombination))
    {
      return wasRemoved;
    }

    int oldIndex = cardCombinations.indexOf(aCardCombination);
    cardCombinations.remove(oldIndex);
    if (aCardCombination.indexOfGame(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCardCombination.removeGame(this);
      if (!wasRemoved)
      {
        cardCombinations.add(oldIndex,aCardCombination);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCardCombinationAt(CardCombination aCardCombination, int index)
  {  
    boolean wasAdded = false;
    if(addCardCombination(aCardCombination))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCardCombinations()) { index = numberOfCardCombinations() - 1; }
      cardCombinations.remove(aCardCombination);
      cardCombinations.add(index, aCardCombination);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCardCombinationAt(CardCombination aCardCombination, int index)
  {
    boolean wasAdded = false;
    if(cardCombinations.contains(aCardCombination))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCardCombinations()) { index = numberOfCardCombinations() - 1; }
      cardCombinations.remove(aCardCombination);
      cardCombinations.add(index, aCardCombination);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCardCombinationAt(aCardCombination, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    UI existingUI = uI;
    uI = null;
    if (existingUI != null)
    {
      existingUI.delete();
    }
    for(int i=weapons.size(); i > 0; i--)
    {
      Weapon aWeapon = weapons.get(i - 1);
      aWeapon.delete();
    }
    for(int i=rooms.size(); i > 0; i--)
    {
      Room aRoom = rooms.get(i - 1);
      aRoom.delete();
    }
    for(int i=characters.size(); i > 0; i--)
    {
      Character aCharacter = characters.get(i - 1);
      aCharacter.delete();
    }
    ArrayList<CardCombination> copyOfCardCombinations = new ArrayList<CardCombination>(cardCombinations);
    cardCombinations.clear();
    for(CardCombination aCardCombination : copyOfCardCombinations)
    {
      aCardCombination.removeGame(this);
    }
  }

}