package code;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.HashMap;
import java.util.Scanner;

public class Board {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	Character[] characters;
	Weapon[] weapons;
	HashMap<String, Weapon> weaponMap;
	HashMap<String, Character> characterMap;
	Location[][] cells;//25 high, 24 wide
	// Board Associations
	private Game game;

	//Constants
	static final int width=24;
	static final int height=25;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Board(Game aGame) {
		characters = new Character[6];
		weapons = new Weapon[6];
		cells = loadMap();
		weaponMap=new HashMap<>();
		characterMap=new HashMap<>();
	}

	private Location[][] loadMap(){
		Location[][] toReturn = new Location[width][height];//One-letter initiation guide since the map's going to be hard-coded:


		try {
			Scanner sc = new Scanner(new File("map.txt"));
			System.out.println("Map loaded:");
			for (int i = 0; i < height; i++) {
				String line = sc.nextLine();

				for (int j = 0; j < width; j++) {
					toReturn[j][i] = loadLocationFromChar(line.charAt(j));
					//System.out.print(line.charAt(j));//Debug purposes only.
				}
				//System.out.println("");//Debug purposes only

			}

			return toReturn;
		}catch(IOException e){System.out.println("ERROR LOADING MAP:"+e);}
		System.out.println("CRITICAL ERROR LOADING MAP");//If it didn't return in the try/catch function then something has gone horribly wrong.
		return null;
	}

	public Location loadLocationFromChar(char c){
		//1-9 are room tiles, used for 'storage'. The special character of each number represents a door
		//IE: 1:Ball room, !: Ball room door, 2: Conservatory, @: Conservatory door
		//1:Ball room 2:Conservatory 3:Billiard room 4:Library 5:Study
		//6:Hall 7:Lounge 8:Dining Room 9:Kitchen
		//n: Null space, can't be walked into
		//h: hallway, free access to walk into
		//r: Room space. Not a doorway, but should be rendered as "empty space" rather than a wall.
		switch (c){
			case 'n':
				return null;
			case 'h':
				return new Hallway();

			case '1':
				return (new Room("Ball room"));
			case '!':
				return (new Room("Ball room", true));


			case '2':
				return (new Room("Conservatory"));
			case '@':
				return (new Room("Conservatory", true));

			case '3':
				return (new Room("Billiard room"));
			case '#':
				return (new Room("Billiard room", true));

			case '4':
				return (new Room("Library"));
			case '$':
				return (new Room("Library", true));

			case '5':
				return (new Room("Study"));
			case '%':
				return (new Room("Study", true));

			case '6':
				return (new Room("Hall"));
			case '^':
				return (new Room("Hall", true));

			case '7':
				return (new Room("Lounge"));
			case '&':
				return (new Room("Lounge", true));

			case '8':
				return (new Room("Dining room"));
			case '*':
				return (new Room("Dining Room", true));

			case '9':
				return (new Room("Kitchen"));
			case '(':
				return (new Room("Kitchen", true));

			default: System.out.println("ERROR! UNEXPECTED TYPE! TRIED TO GENERATE A LOCATION USING INVALID CHAR:"+c);
			return null;
		}


	}

	public Location getCell(int xVal, int yVal){
		return cells[xVal][yVal];
	}

	public void setCell(int xVal, int yVal, Location l){
		cells[xVal][yVal]=l;
	}

	// ------------------------
	// INTERFACE
	// ------------------------
	/* Code from template association_GetOne */
	public Game getGame() {
		return game;
	}

	/* Code from template association_SetUnidirectionalOne */
	public boolean setGame(Game aNewGame) {
		boolean wasSet = false;
		if (aNewGame != null) {
			game = aNewGame;
			wasSet = true;
		}
		return wasSet;
	}

	/**
	 * For use during init. Stub method for now.
	 */
	public void addCharacter(Character c) {
		for(int i = 0; i < characters.length; i++) {
			//At the first empty slot in the array, add this character
			if(characters[i] == null) {
				characters[i] = c;
				characterMap.put(c.getName(), c);
				break;
			}
		}
	}

	/**
	 * For use during init. Stub method for now.
	 */
	public void addWeapon(Weapon w) {
		for(int i = 0; i < weapons.length; i++) {
			//At the first empty slot in the array, add this character
			if(weapons[i] == null) {
				weapons[i] = w;
				weaponMap.put(w.getName(),w);
				break;
			}
		}
	}

	/**
	 * Registers all of the rooms so that they can be set up and used accordingly
	 * @param r
	 */
	//TODO Not sure if this is where you'd do it, but when setting up the rooms
	// it would be super helpful if you could store the room exits i.e. 1, 2, 3
	// in the given int array: exits, in the Room class
	public void registerRoom(Room r){
		
	}

	public Character[] getCharacters() {
		return characters;
	}
	
	//TODO This method should return true or false depending on whether they are in a room or not
	//Think this is the right class for this method?
	public boolean isPlayerInRoom(Player p) {
		return p.getCharacter().isInRoom();
	}

	/**
	 * Returns the room that the given player is in.
	 * The room itself may be useless, it may be more useful to use .getName() on the room.
	 * @param p
	 * @return
	 */
	public Room getRoomPlayerIsIn(Player p) {
		return p.getCharacter().getRoom();
	}
	
	//TODO Move the given player by 1, in direction 'n', 's', 'e', or 'w' as indicated by given char
	//Should be preceeded by calling isPlayerMoveValid(Player p, char c)
	public void movePlayer(Player p, char c) {
		
	}

	public boolean isPlayerMoveValid(Player p, char c){
		return true;
	}
	
	//TODO Teleport style move, used for suggest etc.
	public void movePlayerTo(Player p, String roomname) {
		
	}

	public void delete() {
		game = null;
	}

}
