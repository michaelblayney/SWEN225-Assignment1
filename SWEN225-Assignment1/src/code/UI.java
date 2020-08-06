package code;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// UI Associations
	private Game game;
	
	char[][] base;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public UI(Game aGame) {
		if (!setGame(aGame)) {
			throw new RuntimeException(
					"Unable to create UI due to aGame. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
		this.loadBase();
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
	/** Draws a very simple text based visual of the board using single characters to represent board tiles and pieces.*/
	public void drawBoard(Board board) {
		if (base.length == 0) {
			// quick boundary check, shouldn't actually ever trigger
			System.out.println("Can't draw board, base array not initiallised! Check loadBase(). ");
			return;
		}
		
		/* The idea for drawing the board is using the 2D Location Array "cells" in the Board
		 * and from each "cell"/Location check what is in that location to see what to print.*/
		for (int y = 0 ; y < board.height ; y++) {
			
			for (int x = 0 ; x < board.width ; x++) {
				
				Location cell = board.cells[x][y]; // get the Location from the array
				if (cell == null) {
					//if the Location is null it means it is an out of bounds tile
					System.out.print(base[y][x]);
				} else if (!cell.hasPiece()) {
					//if the Location isn't null but doesn't contain a piece, it is a room or a hallway tile
					if (cell instanceof Hallway) {System.out.print("h");}
					else if (cell instanceof Room) { 
						/*System.out.print("r");*/ 
						System.out.print(base[y][x]);
						}
					//if it isn't a hallway or room tile, is an unrecognized tile 
					else {System.out.print("?");}
				} else {
					/* the location has a piece and the name is checked against the cases to print the appropriate
					 * char, at the moment all characters are printed with a C to indicate it is a character and
					 * weapons are printed as a W, discuss with group what chars to use to make them unique or if a
					 * better drawing method should be put together*/
					String name = cell.getPiece().getName();
					
					switch (name) {
						case "Miss Scarlett":
							System.out.print("C");
							break;
							
						case "Col. Mustard":
							System.out.print("C");
							break;
							
						case "Mrs. White":
							System.out.print("C");
							break;
							
						case "Mr. Green":
							System.out.print("C");
							break;
							
						case "Mrs. Peacock":
							System.out.print("C");
							break;
							
						case "Prof. Plum":
							System.out.print("C");
							break;
							
						case "Candlestick":
							System.out.print("W");
							break;
							
						case "Dagger":
							System.out.print("W");
							break;
							
						case "Lead Pipe":
							System.out.print("W");
							break;
							
						case "Revolver":
							System.out.print("W");
							break;
							
						case "Rope":
							System.out.print("W");
							break;
							
						case "Spanner":
							System.out.print("W");
							break;
							
						default: System.out.print("?");
						break;
					}
				}
				
				
			}
			
			System.out.println();
		}
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
	
	/** Loads a base map for the board as a 2d array of chars.
	 *  Array is for use in drawing the board.
	 *  Uses the map.txt file and is hard coded to the dimensions 24X25
	 * */
	private void loadBase() {
		base = new char[25][24];
		// this method is an altered copy from the Board's loadMap() method.
		try {
			Scanner sc = new Scanner(new File("./SWEN225-Assignment1/src/code/map.txt"));
			System.out.println("Map loaded:");
			for (int y = 0; y < 25; y++) {
				String line = sc.nextLine();

				for (int x = 0; x < 24; x++) {
					base[y][x] = line.charAt(x);
					
				}
				

			}
			
		}catch(IOException e){System.out.println("ERROR LOADING MAP:"+e);}
		
	}
}
