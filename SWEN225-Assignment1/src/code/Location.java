package code;
public class Location
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  private MoveablePiece containedPiece;
  private boolean hasPiece=false;

  //Location Associations
  private Board board;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  /**
   * Board doesn't *need* the individual locations to be able to see it - board just stores and changes them.
   */
  public Location(){

  }

  /**
   * If for whatever reason you need to store the board in the location, use this constructor instead.
   * @param b
   */
  public Location(Board b){
  board=b;
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */

  public void storePiece(MoveablePiece m){
    containedPiece=m;
    hasPiece=true;
  }

  public MoveablePiece getPiece(){
    return containedPiece;
  }

  /**
   * Removes the piece stored inside this location. Returns it in the call, though this is optional and can be treated as a Void method instead.
   * @return
   */
  public MoveablePiece removePiece(){
    MoveablePiece toReturn=containedPiece;
    containedPiece=null;
    hasPiece=false;
    return toReturn;
  }



  public Board getBoard()
  {
    return board;
  }




}
