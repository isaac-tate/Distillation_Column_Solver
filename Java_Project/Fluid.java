public class Fluid{
  
  double d_AB_L, d_AB_V , mu_L , rho_L ,mu_V ,rho_V ,mW_A ,mW_L ,mW_V, nsc_L, nsc_V;
  
  Fluid(){
    
      d_AB_L = 0.00000000124;
      d_AB_V = 0.0000135;
      mu_L = 0.00089;
      rho_L = 997;
      mu_V = 0.00000985;
      rho_V = 1.261;
      mW_A = 44.05;
      mW_L = 18.02;
      mW_V = 28.05;
      nsc_L = this.calculateNsc_L();
      nsc_V = this.calculateNsc_V();
    
  } 
  
  public double calculateNsc_L(){return mu_L/(rho_L*d_AB_L);}
  
  public double calculateNsc_V(){return mu_V/(rho_V*d_AB_V);}
  
}