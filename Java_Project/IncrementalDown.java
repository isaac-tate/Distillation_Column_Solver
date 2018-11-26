public class IncrementalDown implements RootFinding{
  
  private double xl,xu;
 
  public IncrementalDown(double xl,double xu){
    this.xl = xl;
    this.xu = xu;
  }
  
 public double calculate (Function f){ 
    
    double delx = xu/5;//to change
    double sign,fxdel,fxu;
    double error = 0.00001;
    int i = 0;
    int iterations = 1000;
      
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
        
        if(Math.abs(fxu)>error&&Math.abs(fxdel)>error){
          if(sign<0){
            delx = delx/2;
          }
          else if(sign>0){
            xu = xu-delx;
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
        
      }while(Math.abs(fxu)>error&&xl<xu&&Math.abs(fxdel)>error);
      
      if(Math.abs(fxu)<Math.abs(fxdel)){
        return xu;
      }
      else{
        return xu-delx;
      }
    }
}