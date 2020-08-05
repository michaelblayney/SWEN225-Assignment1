package code;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Board {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	Character[] characters = new Character[6];
	Weapon[] weapons = new Weapon[6];
	HashMap<String, Weapon> weaponMap = new HashMap<>();
	HashMap<String, Character> characterMap = new HashMap<>();
	HashMap<String, ArrayList<Location>> exitMap= new HashMap<>();
	HashMap<String, ArrayList<MoveablePiece>> roomContentsMap;
	Location[][] cells;//25 high, 24 wide
	// Board Associations
	private Game game;

	//Constants
	static final int width=24;
	static final int height=25;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Board(Game aGame, String[] roomNamesToInit) {
		for(String s:roomNamesToInit){
			exitMap.put(s, new ArrayList<>());
			//System.out.println("DEBUG: REGISTERING ROOM:"+s);
		}
		cells = loadMap();

	}

	private Location[][] loadMap(){
		Location[][] toReturn = new Location[width][height];//One-letter initiation guide since the map's going to be hard-coded:


		try {
			Scanner sc = new Scanner(new File("map.txt"));
			System.out.println("Map loaded:");
			for (int i = 0; i < height; i++) {
				String line = sc.nextLine();

				for (int j = 0; j < width; j++) {
					toReturn[j][i] = loadLocationFromChar(line.charAt(j), j, i);
					//System.out.print(line.charAt(j));//Debug purposes only.
				}
				//System.out.println("");//Debug purposes only

			}

			return toReturn;
		}catch(IOException e){System.out.println("ERROR LOADING MAP:"+e);}
		System.out.println("CRITICAL ERROR LOADING MAP");//If it didn't return in the try/catch function then something has gone horribly wrong.
		return null;
	}

	public Location loadLocationFromChar(char c, int x, int y){
		//1-9 are room tiles, used for 'storage'. The special character of each number represents a door
		//IE: 1:Ball room, !: Ball room door, 2: Conservatory, @: Conservatory door
		//1:Ball room 2:Conservatory 3:Billiard room 4:Library 5:Study
		//6:Hall 7:Lounge 8:Dining Room 9:Kitchen
		//n: Null space, can't be walked into
		//h: hallway, free access to walk into
		//r: Room space. Not a doorway, but should be rendered as "empty space" rather than a wall.
		boolean doorStatus=false;
		String name;
		switch (c){
			case 'n':
				return null;
			case 'h':
				return new Hallway(x, y);

			case '!':
				doorStatus=true;
			case '1':
				name="Ball Room";
				break;

			case '@':
				doorStatus=true;
			case '2':
				name="Conservatory";
				break;

			case '#':
				doorStatus=true;
			case '3':
				name="Billiard Room";
				break;

			case '$':
				doorStatus=true;
			case '4':
				name="Library";
				break;

			case '%':
				doorStatus=true;
			case '5':
				name="Study";
				break;

			case '^':
				doorStatus=true;
			case '6':
				name="Hall";
				break;

			case '&':
				doorStatus=true;
			case '7':
				name="Lounge";
				break;

			case '*':
				doorStatus=true;
			case '8':
				name="Dining Room";
				break;

			case '(':
				doorStatus=true;
			case '9':
				name="Kitchen";
				break;

			default: System.out.println("ERROR! UNEXPECTED TYPE! TRIED TO GENERATE A LOCATION USING INVALID CHAR:"+c);
			return null;
		}
		if(doorStatus){
			Location door= (new Room(name, true, x, y));
			//System.out.println("DEBUG: ADDING DOOR FOR:"+name);
			exitMap.get(name).add(door);//Register the door under the list of room exits!

			return door;
		} else{
			return (new Room(name, x, y));
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
	public void addCharacter(Character c, int xpos, int ypos) {
		for(int i = 0; i < characters.length; i++) {
			//At the first empty slot in the array, add this character
			if(characters[i] == null) {
				characters[i] = c;
				characterMap.put(c.getName(), c);
				c.teleportToCoordinate(xpos, ypos);
				cells[xpos][ypos].storePiece(c);
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

	public ArrayList<Location> getAvailableExits(Player p){
		String roomname= p.getCharacter().getRoom().getName();
		return exitMap.get(roomname);
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
	//SHOULD *NOT* BE USED FOR EXITING A ROOM!
	public void movePlayer(Player p, char c) {
		if(isPlayerMoveValid(p,c)==false)//This needs to be called from the game method, checks internally anyway incase of mis-use.
			return;
		int playX= p.getCharacter().getX();//playX rather than charX to stop confusion between character and directional char
		int playY= p.getCharacter().getY();

		Location currentCell=cells[playX][playY];
		Location newCell = cells[playX+xDirFromChar(c)][playY+yDirFromChar(c)];//The new cell, adjacent to the current one.


		if(newCell instanceof Room){//TODO entering a room logic


		}else{
			newCell.storePiece(currentCell.removePiece());//Removes the character from the old cell and puts them in the new one simultaneously.
			p.getCharacter().teleportToCoordinate(playX+xDirFromChar(c),playY+yDirFromChar(c));
		}

	}

	public boolean isPlayerMoveValid(Player p, char c){
		int newX=p.getCharacter().getX()+xDirFromChar(c);
		int newY=p.getCharacter().getY()+yDirFromChar(c);
		if(newX<0||newY<0||newX>width||newY>height)//If out of bounds, immediately return false rather than letting an error ensue.
			return false;

		Location cellToCheck=cells[newX][newY];
		if(cellToCheck==null|| (cellToCheck.getPiece()!=null))//Null cells are walls that we absolutely cannot walk into.
			return false;
		if(cellToCheck instanceof Room){
			Room roomToCheck = (Room) cellToCheck;//Cast to room so we can call room methods on it
			if(roomToCheck.isDoor()){//TODO: Implement complex wall-checking logic here later
				return true;
			} else{//Trying to walk through an implied wall to get into a room, do not allow
				return false;
			}
		}
		return true;
	}

	/**
	 * sub-function which returns the change in x direction from the inputted character.
	 * An improvement may be an enum.
	 * @param c
	 * @return
	 */
	public int xDirFromChar(char c){
		switch(c){
			case 'n': //North and south are here as they're still valid inputs!
			case 's':
			return 0;
			case 'e':
				return 1;
			case 'w':
				return -1;
			default:
				System.out.println("ERROR! INVALID MOVEMENT CHARACTER:"+c);
				return 0;
		}
	}

	/**
	 * sub-function which returns the change in y direction from the inputted character.
	 * An improvement may be an enum.
	 * @param c
	 * @return
	 */
	public int yDirFromChar(char c){
		switch(c){
			case 'n':
				return 1;
			case 's':
				return -1;
			case 'e': //East and west are here so that it won't see an invalid input and signal an error.
			case 'w':
			return 0;
			default:
				System.out.println("ERROR! INVALID MOVEMENT CHARACTER:"+c);
				return 0;
		}
	}
	
	//TODO Teleport style move, used for suggest etc.
	public void movePlayerTo(Player p, String roomname) {





	}

	public void delete() {
		game = null;
	}

}
