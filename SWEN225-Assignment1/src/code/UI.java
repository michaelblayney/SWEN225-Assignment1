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
	
	public void print(String s) {
		System.out.print(s);
	}
	
	public void println(String s) {
		System.out.println(s);
	}
	
	
	//TODO This should draw the ASCII art of the game board and all characters, weapons, etc
	public void drawBoard(Board board) {
		
	}
	
	// Gets an input from System.in and returns it, or -1 if it is an invalid int
	// Loops until valid int is found
	// Note doesnt allow you to enter int of -1
	public int scanInt(Scanner input) {
		String s = "";
		int i = -1;
		while (i == -1) {
			s = input.nextLine();
			try { // Integer input
				i = Integer.parseInt(s);
			} catch (NumberFormatException e) { // Non integer input
				System.out.println("Please input a valid Integer");
			}
		}
		return i;
	}

	// Same as scanInt() but allows you to add a range, and loops until an integer in that range is found
	public int scanInt(int min, int max, Scanner input) {
		String s = "";
		int i = -1;
		while (i == -1) {
			s = input.nextLine();
			try { // Integer input
				i = Integer.parseInt(s);
				if (i < min || i > max) {
					println("Please input an Integer between " + min + " and " + max);
					i = -1;
				}
			} catch (NumberFormatException e) { // Non integer input
				println("Please input a valid Integer");
				i = -1;
			}
		}
		return i;
	}
	
	// Gets an input from System.in and returns it, or a null char if it's invalid
	// Loops until valid char is found
	public char scanChar(Scanner input) {
		char c = '\u0000'; //Null value for char
		while (c == '\u0000') {
			try { // Char input
				c = input.next().charAt(0);
			} catch (InputMismatchException e) { // Non char input
				println("Please input a valid Character");
			}
		}
		return c;
	}
	
	// Same as scanChar() but allows you to add input an array of expected chars as a parameter
	//It will loop until there is a valid and expected character input
	public char scanChar(char[] expectedChars, Scanner input) {
		char c = '\u0000'; //Null value for char
		while (c == '\u0000') {
			try { // Char input
				c = input.next().charAt(0);
				boolean validChar = false;
				for(int i = 0; i < expectedChars.length; i ++) {
					if(c == expectedChars[i]) validChar = true;
				}
				if(!validChar) {
					print("Please enter one of the following Characters: ");
					print("" + expectedChars[0]);
					for(int i = 1; i < expectedChars.length; i++) print(", " + expectedChars[i]);
					println("");
					c = '\u0000';
				}
			} catch (InputMismatchException e) { // Non char input
				println("Please input a valid Character");
			}
		}
		return c;
	}
}