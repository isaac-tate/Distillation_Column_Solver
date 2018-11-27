public class IncrementalUp implements RootFinding{
  
  /*Class: IncrementalUp
   * 
   * This class contains an incremental search method that returns the root of a function using incremental search
   * In this particular class, a lower bound is set and the function searches upward until the root is found
   * 
   * This class implements the interface RootFinding and so contains a method 
   * public double calculate(Function f)
   * 
   */
  
  //Contains upper and lower bounds as instance variables
  private double xl,xu;
  
  public IncrementalUp(double xl,double xu){
    this.xl = xl;
    this.xu = xu;
  }
//Incremental search that returns the root of a specific function
  
  public double calculate (Function f){ 
    
    //Set the inital step size to be a fifth of the lower bound
    double delx = xl/5;
    double sign,fxl,fxdel;
    //Set the error tolerance
    double error = 0.00001;
    int i = 0;
    int iterations = 1000;
    
    do{
      //Solve the function with the lower bound x and the stepped lower bound x
      fxl = f.setX(xl);
      fxdel = f.setX(xl+delx);
      
      //Determine the sign of f(xl+delx)*f(xl)
      sign = fxl*fxdel;
      if(sign>0){
        sign = 1;
      }
      else if (sign<0){
        sign = -1;
      }
      else{
        sign = 0;
      }
      
      //If the tolerance isn't reached with either the stepped or lower bound functions, set new lower bound or step size
      if(Math.abs(fxl)>error&&Math.abs(fxdel)>error){
        if(sign<0){
          //If the root is between f(xl) and f(xl+delx), halve the step size
          delx = delx/2;
        }
        else if(sign>0){
          //If the root is not between f(xl) and f(xl+delx), change the upper bound
          xl = xl+delx;
        }
      }
      
      i++;
      
      //Endless loop exception in case no root can be found within tolerance
      try {
        if (iterations > 1000) throw new Exception("The iterations in the incremental search exceed 1000");                                               
      }
      catch(Exception thrown) {
        System.out.println(thrown.getMessage());
        System.exit(0);
      }
      
      //Repeat until tolerance is reached or the upper bound is lower than the lower bound
    }while(Math.abs(fxl)>error&&xl<xu&&Math.abs(fxdel)>error);
    
    //Return either the upper bound or the stepped value depending on which function has a value closer to zero
    if(Math.abs(fxl)<Math.abs(fxdel)){
      return xl;
    }
    else{
      return (delx+xl);
    }
  } 
}