public class IncrementalUp implements RootFinding{
  
  private double xl,xu;
  
  public IncrementalUp(double xl,double xu){
    this.xl = xl;
    this.xu = xu;
  }
//Incremental search that returns the root of a specific function
  
  public double calculate (Function f){ 
    
    double delx = 100;//to change
    double sign,fxl,fxdel;
    double error = 0.001;
      
      do{
        fxl = f.setX(xl);
        fxdel = f.setX(xl+delx);
        
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
      }while(Math.abs(fxdel-fxl)>error&&xl<xu);
      return xl;
    }
    
  }