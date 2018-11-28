public class Fluid{
  
  /*Class: Fluid
   * 
   * Fluid is the class that holds all the properties of the working fluid
   * It can be created using default values without arguements, or arguements can be specified for custom fluids
   * 
   */
  
  private double d_AB_L, d_AB_V , mu_L , rho_L ,mu_V ,rho_V ,mW_A ,mW_L ,mW_V, nsc_L, nsc_V;
  
  public Fluid(){ //This is the default
    
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
  //in case the user wants a different fluid set
  public Fluid(double d_AB_L, double d_AB_V, double mu_L, double rho_L, double mu_V, double rho_V, double mW_A, double mW_L, double mW_V){
    
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
  
  //copy constructor for deep copying
  public Fluid(Fluid source){
    this.d_AB_L = source.d_AB_L;
    this.d_AB_V = source.d_AB_V;
    this.mu_L = source.mu_L;
    this.rho_L = source.rho_L;
    this.mu_V = source.mu_V;
    this.rho_V = source.rho_V;
    this.mW_A = source.mW_A;
    this.mW_L = source.mW_L;
    this.mW_V = source.mW_V;
    this.nsc_L = source.nsc_L;
    this.nsc_V = source.nsc_V;
  }
  
  //Accessors and mutators
  public double calculateNsc_L(){return mu_L/(rho_L*d_AB_L);}
  
  public double calculateNsc_V(){return mu_V/(rho_V*d_AB_V);}
  
  public double getD_ab_L(){return this.d_AB_L;}
  
  public double getD_ab_V(){return this.d_AB_V;}
  
  public double getMu_L(){return this.mu_L;}
  
  public double getMu_V(){return this.mu_V;}
  
  public double getMW_L(){return this.mW_L;}
  
  public double getMW_V(){return this.mW_V;}
  
  public double getRho_L(){return this.rho_L;}
  
  public double getRho_V(){return this.rho_V;}
  
  public double getMW_A(){return this.mW_A;}
  
  public double getNsc_L(){return this.nsc_L;}
  
  public double getNsc_V(){return this.nsc_V;}
  
  public void setD_ab_L(double d_AB_L){this.d_AB_L = d_AB_L;}
  
  public void setD_ab_V(double d_AB_V){this.d_AB_V = d_AB_V;}
  
  public void setMu_L(double mu_L){this.mu_L = mu_L;}
  
  public void setMu_V(double mu_V){this.mu_V = mu_V;}
  
  public void setMW_L(double mW_L){this.mW_L = mW_L;}
  
  public void setMW_V(double mW_V){this.mW_V = mW_V;}
  
  public void setRho_L(double rho_L){this.rho_L = rho_L;}
  
  public void setRho_V(double rho_V){this.rho_V = rho_V;}
  
  public void setMW_A(double mW_A){this.mW_A = mW_A;}
  
  public void setNsc_L(double nsc_L){this.nsc_L = nsc_L;}
  
  public void setNsc_V(double nsc_V){this.nsc_V = nsc_V;}
  
  public Fluid clone(){return new Fluid(this);}
    
}