public class Blarg{

  public static double Incremental(Function f){
  
  double xl = 0;
  double xu = 100;//to change
  
  double delx = 2;//to change
  double sign,fxl,fxu,fxdel;
  double error = 0.001;
  
  do{
  fxl = f.setX2(xl);
  fxu = f.setX2(xu);
  fxdel = f.setX2(xl+delx);
  
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
  }while(Math.abs(fxl)>error);
    return xl;
}
}