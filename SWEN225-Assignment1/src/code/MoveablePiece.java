package code;

public class MoveablePiece {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// MoveablePiece Attributes
	private String name;

	// MoveablePiece Associations
	private Board board;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public MoveablePiece(String aName, Board aBoard) {
		name = aName;
		if (!setBoard(aBoard)) {
			throw new RuntimeException(
					"Unable to create MoveablePiece due to aBoard. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setName(String aName) {
		boolean wasSet = false;
		name = aName;
		wasSet = true;
		return wasSet;
	}

	public String getName() {
		return name;
	}

	/* Code from template association_GetOne */
	public Board getBoard() {
		return board;
	}

	/* Code from template association_SetUnidirectionalOne */
	public boolean setBoard(Board aNewBoard) {
		boolean wasSet = false;
		if (aNewBoard != null) {
			board = aNewBoard;
			wasSet = true;
		}
		return wasSet;
	}

	public void delete() {
		board = null;
	}

	public String toString() {
		return super.toString() + "[" + "name" + ":" + getName() + "]"
				+ System.getProperties().getProperty("line.separator") + "  " + "board = "
				+ (getBoard() != null ? Integer.toHexString(System.identityHashCode(getBoard())) : "null");
	}
}