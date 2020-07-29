package code;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// UI Associations
	private Game game;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public UI(Game aGame) {
		if (!setGame(aGame)) {
			throw new RuntimeException(
					"Unable to create UI due to aGame. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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

	public void delete() {
		game = null;
	}

	public void println(String s) {
		System.out.println(s);
	}
	
	

	// Gets an input from System.in and returns it, or -1 if it is an invalid int
	// Loops until valid int is found
	// Note doesnt allow you to enter int of -1
	public int scanInt() {
		int i = -1;
		while (i == -1) {
			Scanner input = new Scanner(System.in);
			try { // Integer input
				i = input.nextInt();
				input.close();
			} catch (InputMismatchException e) { // Non integer input
				System.out.println("Please input a valid Integer");
			}
		}

		return i;
	}

	// Same as scanInt() but allows you to add a range
	public int scanInt(int min, int max) {
		int i = -1;
		while (i == -1) {
			Scanner input = new Scanner(System.in);
			try { // Integer input
				i = input.nextInt();
				if (i < min || i > max) {
					System.out.println("Please input an Integer between " + min + " and " + max);
					i = -1;
				} else {
					input.close();
				}
			} catch (InputMismatchException e) { // Non integer input
				System.out.println("Please input a valid Integer");
			}
		}

		return i;
	}

}