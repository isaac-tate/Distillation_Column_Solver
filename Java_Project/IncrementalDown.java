public class IncrementalDown implements RootFinding{
  
  private double xl,xu;
 
  public IncrementalDown(double xl,double xu){
    this.xl = xl;
    this.xu = xu;
  }
  
 public double calculate (Function f){ 
    
    double delx = 100;//to change
    double sign,fxdel,fxu;
    double error = 0.001;
    
      do{
        fxu = f.setX(xu);
        fxdel = f.setX(xu-delx);
        
        sign = fxu*fxdel;//determine the sign of f(xl)-f(xu) to find new x
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
          xu = xu-delx;
        }
      }while(Math.abs(fxdel-fxu)>error&&xl<xu);
      return xu;
    }
}