package code;
public class Weapon extends MoveablePiece
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Weapon Associations
  private WeaponCard weaponCard;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Weapon(String aName, Board aBoard, WeaponCard aWeaponCard)
  {
    super(aName, aBoard);
    if (aWeaponCard == null || aWeaponCard.getWeapon() != null)
    {
      throw new RuntimeException("Unable to create Weapon due to aWeaponCard. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    weaponCard = aWeaponCard;
  }

  public Weapon(String aName, Board aBoard, String aNameForWeaponCard, Player aPlayerForWeaponCard)
  {
    super(aName, aBoard);
    weaponCard = new WeaponCard(aNameForWeaponCard, aPlayerForWeaponCard, this);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public WeaponCard getWeaponCard()
  {
    return weaponCard;
  }

  public void delete()
  {
    WeaponCard existingWeaponCard = weaponCard;
    weaponCard = null;
    if (existingWeaponCard != null)
    {
      existingWeaponCard.delete();
    }
    super.delete();
  }

}