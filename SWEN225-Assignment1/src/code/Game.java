package code;

import java.util.ArrayList;
import java.util.HashMap;
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
	private final String[] characterNames = { "Mrs. White", "Mr. Green", "Mrs. Peacock", "Prof. Plum", "Miss Scarlett",
			"Col. Mustard" };
	private final String[] roomNames = { "Kitchen", "Ball Room", "Conservatory", "Billiard Room", "Library", "Study",
			"Hall", "Lounge", "Dining Room" };

	// Variables/fields

	private UI ui;
	private Board board;
	private boolean gameFinished = false; // If set to true, immediately kills the game loop.
	private CardCombination murderSolution;
	private Player[] players;
	private Scanner scan;
	private int numPlayers;

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
		ui = new UI(this);
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

		// Getting number of players
		ui.println("CLUEDO");
		ui.println("How many people are playing?");
		
		numPlayers = ui.scanInt(minNumOfPlayers, maxNumOfPlayers, scan);
		
		ui.println("Num of players: " + numPlayers);
		
		// Creating Players, and assigning the players to characters
		createPlayers(numPlayers);
		
	}

	private void createPlayers(int numPlayers) {
		players = new Player[numPlayers];
		for(int i = 0; i < numPlayers; i++) { //Asking each player which character they want to be
			int index = 0;
			HashMap<Integer, Integer> indexTable = new HashMap<Integer, Integer>();
			
			ui.println("-------------------");
			ui.println("Player " + (i + 1) + " please select your character");
			//Displaying all the characters withouth players
			for(int j = 0; j < board.characters.length; j++) {
				if(!board.characters[j].hasPlayer()) {
					index += 1;
					indexTable.put(index, j);
					ui.println(index + ". " + board.characters[j]);
				}
			}
			
			int selection = ui.scanInt(1, index, scan);
			Player player = new Player(this, board.characters[indexTable.get(selection)]);
			board.characters[indexTable.get(selection)].setPlayer(player);
			
			//Add player to players
			for(int j = 0; j < players.length; j++) {
				//At the first empty slot in the array, add this character
				if(players[j] == null) {
					players[j] = player;
					break;
				}
			}
			
			ui.println("Player " + (i + 1) + " has chosen: " + board.characters[indexTable.get(selection)].toString());
			index = 0;
		}
	}

	
	private void doGameLoop() {
		int whichPlayersTurn = 0;
		while (!gameFinished) {
			//Getting correct player whom is taking the turn
			Player currentPlayer = players[whichPlayersTurn];
			
			ui.println("-------------------");
			ui.println("Player " + (whichPlayersTurn + 1) + "'s turn");
			doTurn(currentPlayer);
			
			//Loops the players turn once the final player has had theirs
			if(whichPlayersTurn + 1 >= numPlayers) whichPlayersTurn = 0;
			else whichPlayersTurn += 1;
		}
	}
	
	private void doTurn(Player currentPlayer) {
		char[] validYesNoChars = {'y', 'n'};
		
		int roll = RollDice();
		ui.println("You rolled: " + roll);
		//If player is in a room
		if(board.isPlayerInRoom(currentPlayer)) {
			Room currentRoom = board.getRoomPlayerIsIn(currentPlayer);
			
			ui.println("Do you want to make an accusation? (y / n)");
			char accuseChar = ui.scanChar(validYesNoChars, scan);
			if(accuseChar == 'y') {} //doAccuse()
			ui.println("Do you want to make an suggestion? (y / n)");
			char suggestChar = ui.scanChar(validYesNoChars, scan);
			if(suggestChar == 'y') {} //doSuggest()
			ui.println("Which exit would you like to take? (");
		}
		//board.movePlayer(currentPlayer, String move);
		
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
