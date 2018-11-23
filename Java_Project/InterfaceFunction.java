public class InterfaceFunction implements Function{
  //The function to determine xai
  
  private double xal,yag,kxa,kya,x;
  private EquilibriumData eqdata;
  
  public InterfaceFunction(double kxa, double kya, double xal, double yag, double x, EquilibriumData eqdata){
    this.xal = xal;
    this.yag = yag;
    this.eqdata = eqdata.clone();
    this.kxa = kxa;
    this.kya = kya;
    double y = eqdata.equilibriumDataY(x);//calculate y using the equilibrium data
    double lmlconc =((1-this.xal)-(1-x))/Math.log((1-this.xal)/(1-x));///log mean liquid concentration at interface
    double lmgconc =((1-y)-(1-this.yag))/Math.log((1-y)/(1-this.yag));//log mean gas concentration at interface
    double m = -((kxa/lmlconc)/(kya/lmgconc));
    double b = 0.5*(this.yag+y-m*(this.xal+x));
    this.x = y-(m*x+b);//calculate the function for the determination of xai
  }
  //Copy constructor
  public InterfaceFunction(InterfaceFunction source){
    this.xal = source.xal;
    this.yag = source.yag;
    this.eqdata = new EquilibriumData(source.eqdata);
    this.kxa = source.kxa;
    this.kya = source.kya;
    this.x = source.x;
  }
  //Mutators
  public double setX(double x){
    double y = this.eqdata.equilibriumDataY(x);//calculate y using the equilibrium data
    double lmlconc =((1-this.xal)-(1-x))/Math.log((1-this.xal)/(1-x));///log mean liquid concentration at interface
    double lmgconc =((1-y)-(1-this.yag))/Math.log((1-y)/(1-this.yag));//log mean gas concentration at interface
    double m = -((this.kxa/lmlconc)/(this.kya/lmgconc));
    double b = 0.5*(this.yag+y-m*(this.xal+x));
    double g = y-(m*x+b);//calculate the function for the determination of xai
    return g;
  }
  public double setXal(double xal){
    double y = this.eqdata.equilibriumDataY(this.x);//calculate y using the equilibrium data
    double lmlconc =((1-xal)-(1-this.x))/Math.log((1-xal)/(1-this.x));///log mean liquid concentration at interface
    double lmgconc =((1-y)-(1-this.yag))/Math.log((1-y)/(1-this.yag));//log mean gas concentration at interface
    double m = -((this.kxa/lmlconc)/(this.kya/lmgconc));
    double b = 0.5*(this.yag+y-m*(xal+this.x));
    double g = y-(m*this.x+b);//calculate the function for the determination of xai
    return g;
  }
  public double setYag(double yag){
    double y = this.eqdata.equilibriumDataY(this.x);//calculate y using the equilibrium data
    double lmlconc =((1-this.xal)-(1-this.x))/Math.log((1-this.xal)/(1-this.x));///log mean liquid concentration at interface
    double lmgconc =((1-y)-(1-yag))/Math.log((1-y)/(1-yag));//log mean gas concentration at interface
    double m = -((this.kxa/lmlconc)/(this.kya/lmgconc));
    double b = 0.5*(yag+y-m*(this.xal+this.x));
    double g = y-(m*this.x+b);//calculate the function for the determination of xai
    return g;
  }
  public double setKxa(double kxa){
    double y = this.eqdata.equilibriumDataY(this.x);//calculate y using the equilibrium data
    double lmlconc =((1-this.xal)-(1-this.x))/Math.log((1-this.xal)/(1-this.x));///log mean liquid concentration at interface
    double lmgconc =((1-y)-(1-this.yag))/Math.log((1-y)/(1-this.yag));//log mean gas concentration at interface
    double m = -((kxa/lmlconc)/(this.kya/lmgconc));
    double b = 0.5*(this.yag+y-m*(this.xal+this.x));
    double g = y-(m*this.x+b);//calculate the function for the determination of xai
    return g;
  }
  public double setKya(double kya){
    double y = this.eqdata.equilibriumDataY(this.x);//calculate y using the equilibrium data
    double lmlconc =((1-this.xal)-(1-this.x))/Math.log((1-this.xal)/(1-this.x));///log mean liquid concentration at interface
    double lmgconc =((1-y)-(1-this.yag))/Math.log((1-y)/(1-this.yag));//log mean gas concentration at interface
    double m = -((this.kxa/lmlconc)/(kya/lmgconc));
    double b = 0.5*(this.yag+y-m*(this.xal+this.x));
    double g = y-(m*this.x+b);//calculate the function for the determination of xai
    return g;
  }
    public double setEqdata(EquilibriumData eqdata){
    double y = eqdata.equilibriumDataY(this.x);//calculate y using the equilibrium data
    double lmlconc =((1-this.xal)-(1-this.x))/Math.log((1-this.xal)/(1-this.x));///log mean liquid concentration at interface
    double lmgconc =((1-y)-(1-this.yag))/Math.log((1-y)/(1-this.yag));//log mean gas concentration at interface
    double m = -((this.kxa/lmlconc)/(this.kya/lmgconc));
    double b = 0.5*(this.yag+y-m*(this.xal+this.x));
    double g = y-(m*this.x+b);//calculate the function for the determination of xai
    return g;
  }
  //Accessors
  public double getXal(){
    return this.xal;
  }
  public double getYag(){
    return this.yag;
  }
  public double getKxa(){
    return this.kxa;
  }
  public double getKya(){
    return this.kya;
  }
  public double getX(){
    return this.x;
  }
  public EquilibriumData getEqdata(){
    return new EquilibriumData(this.eqdata);
  }
}