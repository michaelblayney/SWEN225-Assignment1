package code;

public class CardCombination {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// CardCombination Associations
	private Game game;
	private RoomCard room;
	private CharacterCard character;
	private WeaponCard weapon;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public CardCombination(Game aGame) {
		if (!setGame(aGame)) {
			throw new RuntimeException(
					"Unable to create CardCombination due to aGame. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
	}

	/* Create a new card combination with cards*/
	public CardCombination(RoomCard room, CharacterCard character, WeaponCard weapon) {
		this.room = room;
		this.character = character;
		this.weapon = weapon;
	}

	// ------------------------
	// INTERFACE
	// ------------------------
	/* Code from template association_GetOne */
	public Game getGame() {
		return game;
	}

	public RoomCard getRoom() {
		return room;
	}

	public CharacterCard getCharacter() {
		return character;
	}

	public WeaponCard getWeapon() {
		return weapon;
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

	public void delete() {
		game = null;
	}

}