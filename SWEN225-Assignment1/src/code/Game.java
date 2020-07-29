package code;

public class Game {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Game() {

	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {

	}

	// Method to get the sum of 2 rolled dice (Might be better in the player class?
	// i.e. player rolls the dice)
	public static int RollDice() {
		int die1 = (int) (Math.random() * 6 + 1);
		int die2 = (int) (Math.random() * 6 + 1);
		return die1 + die2;
	}

	public static void main(String[] args) {
		Game game = new Game();
		System.out.println("CLUEDO");
		System.out.println("How many people are playing?");

		System.out.println(RollDice());
	}

}