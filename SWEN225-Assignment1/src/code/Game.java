package code;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.stream.events.Characters;

public class Game {

	// Constants
	int maxNumOfPlayers = 6;
	int minNumOfPlayers = 2;

	// For NOW, these are hard-coded. It may be beneficial to replace them with
	// enums.
	private final String[] weaponNames = { "Candlestick", "Lead pipe", "Dagger", "Revolver", "Rope", "Spanner" };
	private final String[] characterNames = { "Mrs. White", "Mr. Green", "Mrs. Peacock", "Prof. Plum", "Miss Scarlett", "Col. Mustard" };
	private final String[] roomNames = { "Kitchen", "Ball Room", "Conservatory", "Billiard Room", "Library", "Study", "Hall", "Lounge", "Dining Room" };

	// Variables/fields

	private UI ui;
	private Board board;
	private boolean gameFinished = false;	// If set to true, immediately kills the game loop.
	private CardCombination murderSolution;
	private Player[] players;
	private Scanner scan;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Game() {
		this.init();
		this.doGameLoop();
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public void delete() {

	}

	/**
	 * Initializes and instantiates everything as-needed. FOR NOW, this includes the
	 * hard-coded values for weapon/room/character names. Set up the
	 * moveablepiece/character FIRST, then use them to initialize the card. Not the
	 * other way around.
	 */
	private void init() {
		board = new Board(this);
		UI ui = new UI(this);
		scan = new Scanner(System.in);
		

		ArrayList<Card> weaponDeck = new ArrayList<>(), characterDeck = new ArrayList<>(), roomDeck = new ArrayList<>();
		for (String s : weaponNames) {
			Weapon w = new Weapon(s);
			WeaponCard wc = new WeaponCard(s, w);
			board.addWeapon(w);
			weaponDeck.add(wc);

		}
		for (String s : characterNames) {
			Character c = new Character(s);
			CharacterCard cc = new CharacterCard(s, c);
			board.addCharacter(c);
			characterDeck.add(cc);
		}
		for (String s : roomNames) {
			Room r = new Room(s);
			RoomCard rc = new RoomCard(s, r);
			roomDeck.add(rc);// 'Room' isn't a moveable piece, so it isn't added to the board.
		}
		
		
		//This next section could probably do with it's own method
		//Getting number of players
		ui.println("CLUEDO");
		ui.println("How many people are playing?");
		
		
		int numPlayers = ui.scanInt(minNumOfPlayers, maxNumOfPlayers, scan);
		
		ui.println("Num of players: " + numPlayers);
		
		
		//Creating Players, and assigning the players to characters
		players = new Player[numPlayers];
		for(int i = 0; i < numPlayers; i++) { //Asking each player which character they want to be
			Character[] characters = board.getCharacters();
			Character[] availableCharacters = new Character[6 - i]; //Available characters will be all the characters - the number of players that have chosen before them
			
			//Loops through all the characters, and if they have not already been selected, add them to unselectedCharacters
			for(int j = 0; j < availableCharacters.length; j++) {
				for(int k = 0; k < characters.length; k++) {
					if(characters[j] != null) {
						availableCharacters[j] = characters[k];
						break;
					}
				}
			}
			
			
			//THIS IS FOR TESTING
			ui.println("----------------");
			ui.println("AVAILABLE CHARACTERS");
			for(int j = 0; j < availableCharacters.length; j++) {
				ui.println(availableCharacters[j].toString());
			}
			ui.println("----------------");
			
			
			//Display the available characters and allow the player to select which one they want
			ui.println("Player " + (i + 1) + " please select your character:");
			ui.showAvailableCharacters(availableCharacters);
			
			int input = ui.scanInt(1, availableCharacters.length, scan);
			
			ui.println("Player " + (i + 1) + " has selected " + availableCharacters[input - 1]);
			
			Player newPlayer = new Player(this, availableCharacters[input - 1]); //Not sure if my availableCharacter array stores copies or pointers to the original characters
			players[i] = newPlayer;
		}
	}

	private void doGameLoop() {
		while (!gameFinished) {
			// This is PSEUDOCODE, feel free to adjust
		}
	}

	// Method to get the sum of 2 rolled dice
	public int RollDice() {
		int die1 = (int) (Math.random() * 6 + 1);
		int die2 = (int) (Math.random() * 6 + 1);
		return die1 + die2;
	}

	public static void main(String[] args) {
		new Game();
	}

}
