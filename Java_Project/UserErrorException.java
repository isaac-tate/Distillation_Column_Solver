public class UserErrorException extends Exception {
  
  private String userError;
  
  public UserErrorException (String valueError) {
    this.userError = userError;
  }
  
  public UserErrorException(UserErrorException copy) {
    this.userError = copy.userError;
  }
  
  public String getMessage() { // getter
    return ("Invalid input.");
  }
}
/*public UserErrorException() {
 this ("Invalid input. Please try again.");
 } 
 }
 
 //IllegalValueException */