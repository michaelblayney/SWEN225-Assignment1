/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5071.d9da8f6cd modeling language!*/



// line 38 "model.ump"
// line 136 "model.ump"
public class PersonCard extends Card
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PersonCard Associations
  private Character character;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PersonCard(String aName, Player aPlayer, Character aCharacter)
  {
    super(aName, aPlayer);
    if (aCharacter == null || aCharacter.getPersonCard() != null)
    {
      throw new RuntimeException("Unable to create PersonCard due to aCharacter. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    character = aCharacter;
  }

  public PersonCard(String aName, Player aPlayer, String aNameForCharacter, Board aBoardForCharacter, Game aGameForCharacter)
  {
    super(aName, aPlayer);
    character = new Character(aNameForCharacter, aBoardForCharacter, this, aGameForCharacter);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Character getCharacter()
  {
    return character;
  }

  public void delete()
  {
    Character existingCharacter = character;
    character = null;
    if (existingCharacter != null)
    {
      existingCharacter.delete();
    }
    super.delete();
  }

}