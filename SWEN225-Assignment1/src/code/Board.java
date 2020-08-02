package code;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.Scanner;

public class Board {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	Character[] characters;
	Weapon[] weapons;
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
		//1-9 are room tiles, they may or may not be able to be walked into
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
			case 'r':
				return new Room("");
			case '1':
				return (new Room("Ball room"));
			case '2':
				return (new Room("Conservatory"));
			case '3':
				return (new Room("Billiard room"));
			case '4':
				return (new Room("Library"));
			case '5':
				return (new Room("Study"));
			case '6':
				return (new Room("Hall"));
			case '7':
				return (new Room("Lounge"));
			case '8':
				return (new Room("Dining room"));
			case '9':
				return (new Room("Kitchen"));
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
			if(characters[i] == null) characters[i] = c;
		}
	}

	/**
	 * For use during init. Stub method for now.
	 */
	public void addWeapon(Weapon w) {
		for(int i = 0; i < weapons.length; i++) {
			//At the first empty slot in the array, add this character
			if(weapons[i] == null) weapons[i] = w;
		}
	}

	/**
	 * Registers all of the rooms so that they can be set up and used accordingly
	 * @param r
	 */
	public void registerRoom(Room r){

	}

	public Character[] getCharacters() {
		return characters;
	}

	public void delete() {
		game = null;
	}

}
