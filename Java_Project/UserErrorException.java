public class UserErrorException extends Exception {
  
  private String userError;
  
  public UserErrorException (String valueError) {
    this.userError = userError;
  }
  
  public UserErrorException(UserErrorException copy) {
    this.userError = copy.userError;
  }
    
    public String getError() { // getter
      return ("Invalid input. Please try again.");
    }
  }
  /*public UserErrorException() {
   this ("Invalid input. Please try again.");
   } 
   }
   
   //IllegalValueException */