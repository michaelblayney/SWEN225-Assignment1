/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5071.d9da8f6cd modeling language!*/



// line 73 "model.ump"
// line 171 "model.ump"
public class Weapon extends MoveablePiece
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Weapon Associations
  private WeaponCard weaponCard;
  private Game game;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Weapon(String aName, Board aBoard, WeaponCard aWeaponCard, Game aGame)
  {
    super(aName, aBoard);
    if (aWeaponCard == null || aWeaponCard.getWeapon() != null)
    {
      throw new RuntimeException("Unable to create Weapon due to aWeaponCard. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    weaponCard = aWeaponCard;
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create weapon due to game. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public Weapon(String aName, Board aBoard, String aNameForWeaponCard, Player aPlayerForWeaponCard, Game aGame)
  {
    super(aName, aBoard);
    weaponCard = new WeaponCard(aNameForWeaponCard, aPlayerForWeaponCard, this);
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create weapon due to game. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public WeaponCard getWeaponCard()
  {
    return weaponCard;
  }
  /* Code from template association_GetOne */
  public Game getGame()
  {
    return game;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    //Must provide game to weapon
    if (aGame == null)
    {
      return wasSet;
    }

    //game already at maximum (6)
    if (aGame.numberOfWeapons() >= Game.maximumNumberOfWeapons())
    {
      return wasSet;
    }
    
    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      boolean didRemove = existingGame.removeWeapon(this);
      if (!didRemove)
      {
        game = existingGame;
        return wasSet;
      }
    }
    game.addWeapon(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    WeaponCard existingWeaponCard = weaponCard;
    weaponCard = null;
    if (existingWeaponCard != null)
    {
      existingWeaponCard.delete();
    }
    Game placeholderGame = game;
    this.game = null;
    if(placeholderGame != null)
    {
      placeholderGame.removeWeapon(this);
    }
    super.delete();
  }

}