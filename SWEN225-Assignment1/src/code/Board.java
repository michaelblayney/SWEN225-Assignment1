package code;

public class Board {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	Character[] characters = new Character[6];
	// Board Associations
	private Game game;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Board(Game aGame) {
		if (!setGame(aGame)) {
			throw new RuntimeException(
					"Unable to create Board due to aGame. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
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

	}

	public Character[] getCharacters() {
		return characters;
	}

	public void delete() {
		game = null;
	}

}
