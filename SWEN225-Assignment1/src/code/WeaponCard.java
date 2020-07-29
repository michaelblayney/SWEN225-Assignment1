package code;

public class WeaponCard extends Card {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// WeaponCard Associations
	private Weapon weapon;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public WeaponCard(String aName, Player aPlayer, Weapon aWeapon) {
		super(aName, aPlayer);
		if (aWeapon == null || aWeapon.getWeaponCard() != null) {
			throw new RuntimeException(
					"Unable to create WeaponCard due to aWeapon. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
		weapon = aWeapon;
	}

	public WeaponCard(String aName, Player aPlayer, String aNameForWeapon, Board aBoardForWeapon) {
		super(aName, aPlayer);
		weapon = new Weapon(aNameForWeapon, aBoardForWeapon, this);
	}

	// ------------------------
	// INTERFACE
	// ------------------------
	/* Code from template association_GetOne */
	public Weapon getWeapon() {
		return weapon;
	}

	public void delete() {
		Weapon existingWeapon = weapon;
		weapon = null;
		if (existingWeapon != null) {
			existingWeapon.delete();
		}
		super.delete();
	}

}