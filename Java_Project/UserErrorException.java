public class UserErrorException extends Exception {
  
  /*Class: UserErrorException
   * 
   * This class contains the exception used for user error exceptions
   * 
   */
  
  private String userError;
  
  //Constructor
  public UserErrorException (String valueError) {
    this.userError = userError;
  }
  
  //Copy constructor
  public UserErrorException(UserErrorException copy) {
    this.userError = copy.userError;
  }
  
  // Accessor
  public String getMessage() { 
    return ("Invalid input. Please try again.");
  }
}