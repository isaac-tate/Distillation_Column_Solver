public class Incremental implements RootFinding2{
  
  public double calculate (Function f){ 
  double xl = 5000;
  double xu = 200000;//to change
  
  double delx = 1000;//to change
  double sign,fxl,fxu,fxdel;
  double error = 0.0001;
  
  do{
  fxl = f.setX(xl,"xl");
  fxdel = f.setX(xl+delx,"xl+xdel");
  
  sign = fxl*fxdel;//determine the sign of f(xl)-f(xu) to find new x
    if(sign>0){
      sign = 1;
    }
    else if (sign<0){
      sign = -1;
    }
    else{
      sign = 0;
    }
    
    if(sign<0){
      delx = delx/2;
    }
    else if(sign>0){
      xl = xl+delx;
    }
  }while(Math.abs(fxl)>error&&xl<xu);
    return xl;
  }
}