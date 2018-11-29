import java.util.Scanner;
public class IncrementalDown implements RootFinding{
  
  /*Class: IncrementalDown
   * 
   * This class contains an incremental search method that returns the root of a function using incremental search
   * In this particular class, an upper bound is set and the function searches downard until the root is found
   * 
   * This class implements the interface RootFinding and so contains a method 
   * public double calculate(Function f)
   * 
   */
  
  //Contains upper and lower bounds as instance variables
  private double xl,xu;
  
  public IncrementalDown(double xl,double xu){
    this.xl = xl;
    this.xu = xu;
  }
  
  public double calculate (Function f){ 
    
    //Set the inital step size to be a fifth of the upper bound
    double delx = xu/5;
    double sign,fxdel,fxu;
    //Set the error tolerance
    double error = 0.00001;
    int i = 0;
    int iterations = 1000;
    
    do{
      //Solve the function with the upper bound x and the stepped upper bound x
      fxu = f.setX(xu);
      fxdel = f.setX(xu-delx);
      
      //Determine the sign of f(xu-delx)*f(xu)
      sign = fxu*fxdel;
      if(sign>0){
        sign = 1;
      }
      else if (sign<0){
        sign = -1;
      }
      else{
        sign = 0;
      }
      
      //If the tolerance isn't reached with either the stepped or upper bound functions, set new upper bound or step size
      if(Math.abs(fxu)>error&&Math.abs(fxdel)>error){
        if(sign<0){
          //If the root is between f(xu) and f(xu-delx), halve the step size
          delx = delx/2;
        }
        else if(sign>0){
          //If the root is not between f(xu) and f(xu-delx), change the upper bound
          xu = xu-delx;
        }
      }
      
      i++;
      
      //Endless loop exception in case no root can be found within tolerance
      try {
        if (iterations > 1000) throw new Exception("The iterations in the incremental search exceed 1000");                                               
      }
      catch(Exception thrown) {
        System.out.println(thrown.getMessage());
        System.out.println("Enter anything into the scanner to exit.");
        Scanner exit = new Scanner(System.in);
        String a = exit.nextLine();
        System.exit(0);
      }
      
      //Repeat until tolerance is reached or the upper bound is lower than the lower bound
    }while(Math.abs(fxu)>error&&xl<xu&&Math.abs(fxdel)>error);
    
    //Return either the upper bound or the stepped value depending on which function has a value closer to zero
    if(Math.abs(fxu)<Math.abs(fxdel)){
      return xu;
    }
    else{
      return xu-delx;
    }
  }
}