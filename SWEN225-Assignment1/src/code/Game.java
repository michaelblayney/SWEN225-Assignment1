package code;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// Collection of players
	Board board;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Game() {
		// Object initialisation
		board = new Board(this);

		System.out.println("CLUEDO");	//This can be handled by the UI class in future
		System.out.println("How many people are playing?");	//Just using for testing
		int numPlayers = getNumPlayers();
		System.out.println("Num of players: " + numPlayers);
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

	public int getNumPlayers() {	//Also should be a mixture of the UI class
		int numPlayers = 0;
		
		//Loop user input until a valid integer between 2..6 is found;
		while (numPlayers == 0) {
			//Creating input scanner
			Scanner input = new Scanner(System.in);
			try { //Integer input
				numPlayers = input.nextInt();
				if(numPlayers < 2 || numPlayers > 6) {	//Checking the input is in the correct range
					System.out.println("Please enter a number between 2 and 6");
					numPlayers = 0;	//Reseting the number of players as it was in the invalid range
				} else {
					input.close();
				}
			} catch (InputMismatchException e) { //Non integer input
				System.out.println("Please enter an Integer");
			}
		}
		
		return numPlayers;
	}

	public static void main(String[] args) {
		Game game = new Game();

	}

}