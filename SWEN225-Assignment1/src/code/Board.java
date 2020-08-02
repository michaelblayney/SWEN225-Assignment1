package code;

public class Board {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	Character[] characters;
	Weapon[] weapons;
	Location[][] cells;//25 high, 24 wide
	// Board Associations
	private Game game;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Board(Game aGame) {
		characters = new Character[6];
		weapons = new Weapon[6];
		cells = new Location[24][25];//One-letter initiation guide since the map's going to be hard-coded:
		//1-9 are room tiles, they may or may not be able to be walked into
		//1:Ball room 2:Conservatory 3:Billiard room 4:Library 5:Study
		// 6:Hall 7:Lounge 8:Dining Room 9:Kitchen
		//n: Null space, can't be walked into
		//h: hallway, free access to walk into
		//r: Room space. Not a doorway, but should be rendered as "empty space" rather than a wall.

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
	
	//TODO This method should return true or false depending on whether they are in a room or not
	//Think this is the right class for this method?
	public boolean isPlayerInRoom(Player p) {
		return true;
	}

	public void delete() {
		game = null;
	}

}
