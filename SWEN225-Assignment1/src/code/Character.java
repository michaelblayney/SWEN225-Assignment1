package code;

public class Character extends MoveablePiece {

  //Character Associations
  private Player player;//If null, means it's not played by a character.
  private CharacterCard characterCard;



  /**
   * Constructor to use if the character is controlled by a player.
   * @param aName
   * @param p
   */
  public Character(String aName, Player p){
    super(aName);
    player=p;
  }

  /**
   * Constructor to use if a character is an "NPC".
   * @param aName
   */
  public Character(String aName){
    super(aName);
    player=null;
  }

  public void setCharacterCard(CharacterCard c){ characterCard=c; }
  public CharacterCard getCharacterCard()
  {
    return characterCard;
  }
  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }

  public boolean hasPlayer()
  {
    boolean has = player != null;
    return has;
  }


  public void setPlayer(Player p) {
    player= p;
  }
}
