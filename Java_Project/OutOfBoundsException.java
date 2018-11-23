public class OutOfBoundsException extends Exception {
  
  private boolean outOfBounds;
  
  public OutOfBoundsException (boolean outOfBounds) { // constructor
    super();
    this.outOfBounds = outOfBounds;
  }
  
  public boolean getBounds() { // getter
    return this.outOfBounds;
  }
}

//NoRootsException