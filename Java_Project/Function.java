public class Function{//a generalized function class that can contain a number of different functions for use in the rootfinding class, can be extended
  
  double xal;
  double yag;
  double x;
  EquilibriumData eqdata;
  double kxa;
  double kya;
  AbsorptionColumn2 mycolumn;
  //this function is for the absorption column interface
  public Function(double kxa, double kya, double xal, double yag, double x, EquilibriumData eqdata){
    this.xal = xal;
    this.yag = yag;
    this.eqdata = eqdata; //needs deep copy
    this.kxa = kxa;
    this.kya = kya;
    double y = eqdata.equilibriumDataY(x);//calculate y using the equilibrium data
    double lmlconc =((1-this.xal)-(1-x))/Math.log((1-this.xal)/(1-x));///log mean liquid concentration at interface
    double lmgconc =((1-y)-(1-this.yag))/Math.log((1-y)/(1-this.yag));//log mean gas concentration at interface
    double m = -((kxa/lmlconc)/(kya/lmgconc));
    double b = 0.5*(this.yag+y-m*(this.xal+x));
    this.x = y-(m*x+b);//calculate the function for the determination of xai
  }
  public double setX(double x){
    double y = this.eqdata.equilibriumDataY(x);//calculate y using the equilibrium data
    double lmlconc =((1-this.xal)-(1-x))/Math.log((1-this.xal)/(1-x));///log mean liquid concentration at interface
    double lmgconc =((1-y)-(1-this.yag))/Math.log((1-y)/(1-this.yag));//log mean gas concentration at interface
    double m = -((this.kxa/lmlconc)/(this.kya/lmgconc));
    double b = 0.5*(this.yag+y-m*(this.xal+x));
    double g = y-(m*x+b);//calculate the function for the determination of xai
    return g;
  }
  public Function(AbsorptionColumn2 mycolumn, double l){
    mycolumn.setL(l);
    this.mycolumn = new AbsorptionColumn2(mycolumn);
    this.x = mycolumn.zl-mycolumn.zv;
  }
  public double setX2(double l){
    this.mycolumn.setL(l);
    this.mycolumn = new AbsorptionColumn2(this.mycolumn);
    System.out.println(this.mycolumn.l_2);
    System.out.println(this.mycolumn.zl-this.mycolumn.zv);
    return this.mycolumn.zl-this.mycolumn.zv;
  }
}