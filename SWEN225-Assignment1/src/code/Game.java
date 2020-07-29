package code;

import java.util.ArrayList;
import java.util.List;

public class Game{

    //Constants
    int maxNumOfPlayers=6;
    int minNumOfPlayers=2;


    //For NOW, these are hard-coded. It may be beneficial to replace them with enums.
    private final String[] weaponNames={"Candlestick","Lead pipe","Dagger","Revolver","Rope","Spanner"};
    private final String[] characterNames={"Mrs. White","Mr. Green","Mrs. Peacock","Prof. Plum","Miss Scarlett","Col. Mustard"};
    private final String[] roomNames={"Kitchen","Ball Room","Conservatory","Billiard Room","Library","Study","Hall","Lounge","Dining Room"};

  //Variables/fields

    private UI ui;
    private Board board;
    private boolean exitCondition=false;//If set to true, immediately kills the game loop.
    private CardCombination murderSolution;



  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game() {}


      //------------------------
      // INTERFACE
      //------------------------

      public void delete () {

      }

      /**
       Initializes and instantiates everything as-needed. FOR NOW, this includes the hard-coded values for weapon/room/character names.
       Set up the moveablepiece/character FIRST, then use them to initialize the card. Not the other way around.
       */
      private void init(){
          UI ui = new UI(this);
          ui.println("CLUEDO");
      		ui.println("How many people are playing?");
      		int numPlayers = ui.scanInt(minNumOfPlayers,maxNumOfPlayers);
      		ui.println("Num of players: " + numPlayers);
          board = new Board(this);

          ArrayList<Card> weaponDeck=new ArrayList<>(),characterDeck=new ArrayList<>(),roomDeck = new ArrayList<>();
          for(String s:weaponNames){
              Weapon w = new Weapon(s);
              WeaponCard wc = new WeaponCard(s, w);
              board.addWeapon(w);
              weaponDeck.add(wc);

          }
          for(String s:characterNames){
              Character c = new Character(s);
              CharacterCard cc = new CharacterCard(s, c);
              board.addCharacter(c);
              characterDeck.add(cc);
          }
          for(String s:roomNames){
              Room r = new Room(s);
              RoomCard rc = new RoomCard(s, r);
              roomDeck.add(rc);//'Room' isn't a moveable piece, so it isn't added to the board.
          }

      }

    private void doGameLoop () {
        while (!exitCondition) {
            //This is PSEUDOCODE, feel free to adjust
        }
    }


    // Method to get the sum of 2 rolled dice (Might be better in the player class?
  	// i.e. player rolls the dice)
  	public int RollDice() {
  		int die1 = (int) (Math.random() * 6 + 1);
  		int die2 = (int) (Math.random() * 6 + 1);
  		return die1 + die2;
  	}


      public static void main (String[]args){
          Game g = new Game();
          g.init();
          g.doGameLoop();

      }



  }
