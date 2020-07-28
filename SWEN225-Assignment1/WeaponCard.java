/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5071.d9da8f6cd modeling language!*/



// line 33 "model.ump"
// line 131 "model.ump"
public class WeaponCard extends Card
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //WeaponCard Associations
  private Weapon weapon;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WeaponCard(String aName, Player aPlayer, Weapon aWeapon)
  {
    super(aName, aPlayer);
    if (aWeapon == null || aWeapon.getWeaponCard() != null)
    {
      throw new RuntimeException("Unable to create WeaponCard due to aWeapon. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    weapon = aWeapon;
  }

  public WeaponCard(String aName, Player aPlayer, String aNameForWeapon, Board aBoardForWeapon, Game aGameForWeapon)
  {
    super(aName, aPlayer);
    weapon = new Weapon(aNameForWeapon, aBoardForWeapon, this, aGameForWeapon);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Weapon getWeapon()
  {
    return weapon;
  }

  public void delete()
  {
    Weapon existingWeapon = weapon;
    weapon = null;
    if (existingWeapon != null)
    {
      existingWeapon.delete();
    }
    super.delete();
  }

}