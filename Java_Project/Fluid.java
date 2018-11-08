public class Fluid{
  
  double d_AB_L, d_AB_V , mu_L , rho_L ,mu_V ,rho_V ,mW_A ,mW_L ,mW_V, nsc_L, nsc_V;
  
  Fluid(){ //This is the deafult
    
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
  
  Fluid(double d_AB_L, double d_AB_V, double mu_L, double rho_L, double mu_V, double rho_V, double mW_A, double mW_L, double mW_V, double nsc_L, double nsc_V){
    
      this.d_AB_L = d_AB_L;
      this.d_AB_V = d_AB_V;
      this.mu_L = mu_L;
      this.rho_L = rho_L;
      this.mu_V = mu_V;
      this.rho_V = rho_V;
      this.mW_A = mW_A;
      this.mW_L = mW_L;
      this.mW_V = mW_V;
      this.nsc_L = this.calculateNsc_L();
      this.nsc_V = this.calculateNsc_V();
  }
  
  public double calculateNsc_L(){return mu_L/(rho_L*d_AB_L);}
  
  public double calculateNsc_V(){return mu_V/(rho_V*d_AB_V);}
  
}