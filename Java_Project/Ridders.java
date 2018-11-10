public class Ridders implements RootFinding2{
  
  public double calculate(Function f){
    double xu = 0.118;//set upper bound to equilibrium data limit
    double xl = 0;//set lower bound
    double xm = (xu+xl)/2;
    double xold = xm;
    double e = 1000;//set initial error to 1000
    int i = 0;
    int iterations = 1000;
    double error = 0.001;//take from file!!
    
    double sign, xnew, y, m, b,fxl,fxu,fxm,fxnew;
    
    do{
      
    fxl = f.setX(xl);
    fxu = f.setX(xu);
    
    sign = fxl-fxu;//determine the sign of f(xl)-f(xu) to find new x
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
    xnew = xm+(xm-xl)*((sign*fxm)/(Math.pow(Math.pow(fxm,2)-fxl*fxu,0.5)));//calculate new x
    fxnew  = f.setX(xnew);
    e = Math.abs((xnew-xold)/xnew);//find error
    
    xold = xnew;//set x = newly found x
    
    if(xnew<xm){//determine new bounds based on position of new x within the numerical line
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
    }while(e>error&&i<iterations);//continue until e is less than the desired error and/or more than the desired number of iterations have occured
    
      return xnew;//return the new x value 
  }
}