public class Ridders implements RootFinding{
  
   /*Class: Ridders
   * 
   * This class contains the ridders method used for root finding
   * 
   * This class implements the interface RootFinding and so contains a method 
   * public double calculate(Function f)
   * 
   * 
   */
  
  public double calculate(Function f){
    
    //Set upper bound to equilibrium data limit
    double xu = 0.118;
    //Set lower bound equal to zero
    double xl = 0;
    //Set midpoint
    double xm = (xu+xl)/2;
    double xold = xm;
    //set initial error to 100000 so the loop runs for the first time
    double e = 100000;
    int i = 0;
    //Set maximum number of iterations
    int iterations = 1000;
    //Set error tolerance
    double error = 0.0000001;
    
    double sign, xnew, y, m, b,fxl,fxu,fxm,fxnew;
    
    do{
      
      fxl = f.setX(xl);
      fxu = f.setX(xu);
      
      //determine the sign of f(xl)-f(xu)
      sign = fxl-fxu;
      if(sign>0){
        sign = 1;
      }
      else if (sign<0){
        sign = -1;
      }
      else{
        sign = 0;
      }
      
      xm = (xu+xl)/2;
      
      fxm = f.setX(xm);
      //Calculate new x
      xnew = xm+(xm-xl)*((sign*fxm)/(Math.pow(Math.pow(fxm,2)-fxl*fxu,0.5)));
      fxnew  = f.setX(xnew);
      //Find error
      e = Math.abs((xnew-xold)/xnew);
      
      //Set x = newly found x
      xold = xnew;
      
      //Determine new bounds based on position of new x within the numerical line
      if(xnew<xm){
        if(fxl*fxnew<0){
          xu = xnew;}
        else if(fxnew*fxm<0){
          xu = xm;
          xl = xnew;
        }
      }
      else if(xnew>xm){
        if(fxm*fxnew<0){
          xu = xnew;
          xl = xm;
        }
        else if(fxnew*fxu<0){
          xl = xnew;
        }
      }
      i++; 
      
      //Endless loop exception in case no root can be found within tolerance
      try {
        if (iterations > 1000) throw new Exception("The iterations in the root finiding method exceed 1000");                                               
      }
      catch(Exception thrown) {
        System.out.println(thrown.getMessage());
        System.exit(0);
      }
      
      //Continue until e is less than the desired error and/or more than the desired number of iterations have occured
    }while(e>error&&i<iterations);
    
    //Return the new x value 
    return xnew;
  }
}