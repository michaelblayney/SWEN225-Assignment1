package code;

public class Hallway extends Location {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

/**
*We don't need to be able to see the board, so this constructor should work just fine.
*/
  public Hallway()
  {
    super();
  }

	public Hallway(Board aBoard) {
		super(aBoard);
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {
		super.delete();
	}

}
