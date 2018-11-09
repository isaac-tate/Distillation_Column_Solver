public class RootFinding2{
  
  private double x;
  
  public static double Ridders(Function f){
    double xu = 0.9999;//set upper bound
    double xl = 0;//set lower bound
    double x = (0.9999-0)/2;
    double e = 1000;//set initial error to 1000
    //double xal = myColumn.x_a1; //set initial x_al
    //double yag = myColumn.y_a1; //set initial y_ag
    int i = 0;
    int iterations = 1000;//take from file?
    double error = 0.001;
    
    double sign, xnew, y, m, b, xm;
    
    do{
      
    double fxl = f.setX(xl);
    double fxu = f.setX(xu);
      
    //fxl = function(data[i].getData(6),data[i].getData(7),yag[i],xl,xal[i],coefficients);//find f(xl)
   // fxu = function(data[i].getData(6),data[i].getData(7),yag[i],xu,xal[i],coefficients);//find f(xu)
    
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
    
    double fx = f.setX(x);
    //fx = function(data[i].getData(6),data[i].getData(7),yag[i],x,xal[i],coefficients);//find f(x) where x is guess
    
    xnew = x+(x-xl)*(sign*fx)/(Math.pow(Math.pow(fx,2)-fxl*fxu,0.5));//calculate new x
    
    double fxnew  = f.setX(xnew);

    //fxnew = function(data[i].getData(6),data[i].getData(7), yag[i],xnew,xal[i],coefficients);//calculate f(new x values)
    
    
    e = Math.abs((xnew-x)/xnew);//find error
    
    x = xnew;//set x = newly found x
    
    if(xnew<x){//determine new bounds based on position of new x within the numerical line
      if(fxl*fxnew<0){
        xu = xnew;}
      else if(fxnew*fx<0){
        xu = x;
        xl = xnew;
      }
    }
    else if(xnew>x){
      if(fx*fxnew<0){
        xu = xnew;
        xl = x;
      }
      else if(fxnew*fxu<0){
        xl = xnew;
      }
    }
    //calculate new yag and new xal
    //yag = ((myColumn.lPrime/myColumn.vPrime)*(((xal-delta_x)/(1-(xal-delta_x)))-(xal/(1-xal)))+(yag/(1-yag)))/((myColumn.lPrime/myColumn.vPrime)*(((xal-delta_x)/(1-(xal-delta_x)))-(xal/(1-xal)))+(yag/(1-yag))+1);
    //xal = xal-delta_x; 
    i++;
      
    }while(e>error&&i<iterations);//continue until e is less than the desired error and/or more than the desired number of iterations have occured
    
      return x;//return the new x value 
  }
}