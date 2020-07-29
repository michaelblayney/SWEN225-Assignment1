package code;

public class Game {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// Collection of players
	Board board;
	UI ui;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Game() {
		// Object initialisation
		board = new Board(this);
		ui = new UI(this);
		
		
		ui.println("CLUEDO");
		ui.println("How many people are playing?");
		int numPlayers = ui.scanInt(2, 6);
		ui.println("Num of players: " + numPlayers);
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {

	}

	// Method to get the sum of 2 rolled dice (Might be better in the player class?
	// i.e. player rolls the dice)
	public int RollDice() {
		int die1 = (int) (Math.random() * 6 + 1);
		int die2 = (int) (Math.random() * 6 + 1);
		return die1 + die2;
	}

	public static void main(String[] args) {
		new Game();
	}

}