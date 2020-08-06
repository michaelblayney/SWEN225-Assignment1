package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.xml.stream.events.Characters;

import com.sun.tools.sjavac.server.SysInfo;

public class Game {

	// Constants
	int maxNumOfPlayers = 6;
	int minNumOfPlayers = 2;

	// For NOW, these are hard-coded. It may be beneficial to replace them with
	// enums.
	private final String[] weaponNames = { "Candlestick", "Lead pipe", "Dagger", "Revolver", "Rope", "Spanner" };
	private final String[] characterNames = { "Mrs. White", "Mr. Green", "Mrs. Peacock", "Prof. Plum", "Miss Scarlett",
			"Col. Mustard" };
	private final int[] charXCoords={9,14,23,23,24,0};
	private final int[] charYCoords={0,0,6,19,7,17};
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
		board = new Board(this, roomNames);
		ui = new UI(this);
		cardInit();

		// Getting number of players
		ui.println("CLUEDO");
		ui.println("How many people are playing?");

		numPlayers = ui.scanInt(minNumOfPlayers, maxNumOfPlayers, scan);

		ui.println("Num of players: " + numPlayers);

		// Creating Players, and assigning the players to characters
		createPlayers(numPlayers);

	}

	private void cardInit() {
		scan = new Scanner(System.in);

		ArrayList<Card> weaponDeck = new ArrayList<>(), characterDeck = new ArrayList<>(), roomDeck = new ArrayList<>();
		for (String s : weaponNames) {
			Weapon w = new Weapon(s);
			WeaponCard wc = new WeaponCard(s, w);
			board.addWeapon(w);
			weaponDeck.add(wc);

		}
		for (int i=0; i<characterNames.length; i++) {
			Character c = new Character(characterNames[i]);
			CharacterCard cc = new CharacterCard(characterNames[i], c);
			board.addCharacter(c, charXCoords[i],charYCoords[i]);
			characterDeck.add(cc);
		}
		for (String s : roomNames) {
			//Room r = new Room(s); //Room class is for the room tile specifically. SHOULD NOT INIT HERE.
			RoomCard rc = new RoomCard(s);
			roomDeck.add(rc);// 'Room' isn't a moveable piece, so it isn't added to the board.
		}

	}

	private void createPlayers(int numPlayers) {
		players = new Player[numPlayers];
		for(int i = 0; i < numPlayers; i++) { //Asking each player which character they want to be
			int index = 0;
			HashMap<Integer, Integer> indexTable = new HashMap<Integer, Integer>();

			ui.println("-------------------");
			ui.println("Player " + (i + 1) + " please select your character");
			//Displaying all the characters without players
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

		//Move Mrs. Scarlet to the front of players
		Player missSPlayer = null;
		int index = -1;

		for(int i = 0; i < players.length; i++) {//Checking to see if someone selected Miss Scarlett and finding the index if possible
			if(players[i].getCharacter().getName().equals("Miss Scarlett")) {
				missSPlayer = players[i];
				index = i;
				break;
			}
		}
		if(missSPlayer != null) { //If there is a player that selected Miss Scarlett, put them at the front of the player turn array
			for(int i = index; i > 0; i--) {
				players[i] = players[i - 1];
			}
			players[0] = missSPlayer;
		}
	}


	private void doGameLoop() {
		int whichPlayersTurn = 0;
		while (!gameFinished) {
			//Getting correct player whom is taking the turn
			Player currentPlayer = players[whichPlayersTurn];

			ui.println("-------------------");
			//ui.println("Player " + (whichPlayersTurn + 1) + " (" + currentPlayer.getCharacter().getName() + ")'s turn");
			ui.println("[" + currentPlayer.getCharacter().getName() + "'s turn]");
			doTurn(currentPlayer);

			//Loops the players turn once the final player has had theirs
			if(whichPlayersTurn + 1 >= numPlayers) whichPlayersTurn = 0;
			else whichPlayersTurn += 1;
		}
	}

	private void doTurn(Player currentPlayer) {
		char[] validYesNoChars = {'y', 'n'};
		char[] validMoveChars = {'n', 's', 'e', 'w', 'f'};

		ui.drawBoard(board);

		int movesLeft = RollDice();
		ui.println("You rolled: " + movesLeft);

		//Main turn loop
		while(movesLeft > 0) {
			// ---------------
			// If player is in a room
			// ---------------
			//(Should be suggestion then accusation)
			//suggestion mandatory on room entry?
			//Have to add clauses for previous turns disallowing repeat suggestions without leaving room
			if(board.isPlayerInRoom(currentPlayer)) {
				//Room currentRoom = board.getRoomPlayerIsIn(currentPlayer);

				//Accusation
				ui.println("Do you want to make an accusation? (y / n)");
				char accuseChar = ui.scanChar(validYesNoChars, scan);
				if(accuseChar == 'y') {
					doAccuse(currentPlayer);
				} else {
					//Suggestion
					ui.println("Do you want to make an suggestion? (y / n)");
					char suggestChar = ui.scanChar(validYesNoChars, scan);
					if(suggestChar == 'y') {
						doSuggest(currentPlayer);
					} else {
						//Leave room
						leaveRoom(currentPlayer);
					}
				}
			} else {
				// ---------------
				// If player is NOT in a room
				// ---------------
				//Move player or end turn
				//Store square been in this turn as you can not occupy a square multiple times in 1 turn
				ui.println("Moves left: " + movesLeft);
				ui.println("Please enter a direction to move in (n, s, e, w, or f to finish your turn)");
				char moveChar = ui.scanChar(validMoveChars, scan);
				if(moveChar == 'f') {
					movesLeft = 0;
				} else {
					board.movePlayer(currentPlayer, moveChar);
					ui.drawBoard(board);
					movesLeft -= 1;
				}
			}
		}
	}

	private void doAccuse(Player currentPlayer) {
		//Should be character room weapon
		//Character accusation
		ui.println("Accusation:");
		ui.println("Select who dunnit:");
		for(int i = 0; i < board.characters.length; i ++) {
			ui.println((i + 1) + ". " + board.characters[i]);
		}
		int accusedCharacter = ui.scanInt(1, board.characters.length, scan) - 1;

		//Weapon accusation
		ui.println("Accusation: " + board.characters[accusedCharacter] + " commited the murder with a ...");
		ui.println("Select the murder weapon:");
		for(int i = 0; i < weaponNames.length; i ++) {
			ui.println((i + 1) + ". " + weaponNames[i]);
		}
		int accusedWeapon = ui.scanInt(1, weaponNames.length, scan) - 1;

		//Room accusation
		ui.println("Accusation: " + board.characters[accusedCharacter] + " commited the murder with a " + weaponNames[accusedWeapon] + " in the ...");
		ui.println("Select what room the murder was commited in: ");
		for(int i = 0; i < roomNames.length; i ++) {
			ui.println((i + 1) + ". " + roomNames[i]);
		}
		int accusedRoom = ui.scanInt(1, roomNames.length, scan) - 1;

		//Final accusation
		ui.println("|Final Accusation: " + board.characters[accusedCharacter] + " commited the murder with a " + weaponNames[accusedWeapon] + " in the " + roomNames[accusedRoom] + ".|");

		//Store in appropriate structure and check against the murderSolution
		//Player wins the game if they're correct
		//Remove player from the game if wrong
	}

	private void doSuggest(Player currentPlayer) {
		//Card[] cards = currentPlayer.getCards();
		//Get player room
		//Get characters in room

		//Next player chooses a card to show
		//Repeat until one of the suggested cards is show (ends turn) or everyone has been around the table

	}

	private void leaveRoom(Player currentPlayer) {
		ui.print("Which exit would you like to take? (");
		//ui.print("" + currentRoom.getExits()[0]);
		//Printing valid exits
		//for(int i = 1; i < currentRoom.getExits().length; i++) ui.print(", " + currentRoom.getExits()[i]);
		ui.println(")");
		//int exit = ui.scanInt(1, currentRoom.getExits().length, scan);
		//board.vacatePlayerFromRoom(currentPlayer, exit);
		ui.drawBoard(board);
		//movesLeft -= 1; Maybe not sure
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
