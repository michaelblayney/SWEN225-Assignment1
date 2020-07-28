/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.0.5071.d9da8f6cd modeling language!*/



// line 35 "model.ump"
// line 129 "model.ump"
public class CharacterCard extends Card
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CharacterCard Associations
  private Character character;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CharacterCard(String aName, Player aPlayer, Character aCharacter)
  {
    super(aName, aPlayer);
    if (aCharacter == null || aCharacter.getCharacterCard() != null)
    {
      throw new RuntimeException("Unable to create CharacterCard due to aCharacter. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    character = aCharacter;
  }

  public CharacterCard(String aName, Player aPlayer, String aNameForCharacter, Board aBoardForCharacter)
  {
    super(aName, aPlayer);
    character = new Character(aNameForCharacter, aBoardForCharacter, this);
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