public class IncrementalUp implements RootFinding{
  
  private double xl,xu;
  
  public IncrementalUp(double xl,double xu){
    this.xl = xl;
    this.xu = xu;
  }
//Incremental search that returns the root of a specific function
  
  public double calculate (Function f){ 
    
    System.out.println("incrementalup");
    double delx = xl/5;
    double sign,fxl,fxdel;
    double error = 0.00001;
    int i = 0;
    int iterations = 1000;
      
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
        if(Math.abs(fxl)>error&&Math.abs(fxdel)>error){
          if(sign<0){
            delx = delx/2;
          }
          else if(sign>0){
            xl = xl+delx;
          }
        }
        
        i++;
        
        try {
          if (iterations > 1000) throw new Exception("The iterations in the incremental search exceed 1000");                                               
        }
        catch(Exception thrown) {
          System.out.println(thrown.getMessage());
          System.exit(0);
        }
       
      }while(Math.abs(fxl)>error&&xl<xu&&Math.abs(fxdel)>error);
      
      if(Math.abs(fxl)<Math.abs(fxdel)){
        return xl;
      }
      else{
        return (delx+xl);
      }
    }
    
  }