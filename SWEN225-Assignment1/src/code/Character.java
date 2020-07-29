package code;

public class Character extends MoveablePiece {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// Character Associations
	private Player player;
	private CharacterCard characterCard;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Character(String aName, Board aBoard, CharacterCard aCharacterCard) {
		super(aName, aBoard);
		if (aCharacterCard == null || aCharacterCard.getCharacter() != null) {
			throw new RuntimeException(
					"Unable to create Character due to aCharacterCard. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
		characterCard = aCharacterCard;
	}

	public Character(String aName, Board aBoard, String aNameForCharacterCard, Player aPlayerForCharacterCard) {
		super(aName, aBoard);
		characterCard = new CharacterCard(aNameForCharacterCard, aPlayerForCharacterCard, this);
	}

	// ------------------------
	// INTERFACE
	// ------------------------
	/* Code from template association_GetOne */
	public Player getPlayer() {
		return player;
	}

	public boolean hasPlayer() {
		boolean has = player != null;
		return has;
	}

	/* Code from template association_GetOne */
	public CharacterCard getCharacterCard() {
		return characterCard;
	}

	/* Code from template association_SetOptionalOneToOne */
	public boolean setPlayer(Player aNewPlayer) {
		boolean wasSet = false;
		if (player != null && !player.equals(aNewPlayer) && equals(player.getCharacter())) {
			// Unable to setPlayer, as existing player would become an orphan
			return wasSet;
		}

		player = aNewPlayer;
		Character anOldCharacter = aNewPlayer != null ? aNewPlayer.getCharacter() : null;

		if (!this.equals(anOldCharacter)) {
			if (anOldCharacter != null) {
				anOldCharacter.player = null;
			}
			if (player != null) {
				player.setCharacter(this);
			}
		}
		wasSet = true;
		return wasSet;
	}

	public void delete() {
		Player existingPlayer = player;
		player = null;
		if (existingPlayer != null) {
			existingPlayer.delete();
		}
		CharacterCard existingCharacterCard = characterCard;
		characterCard = null;
		if (existingCharacterCard != null) {
			existingCharacterCard.delete();
		}
		super.delete();
	}

}