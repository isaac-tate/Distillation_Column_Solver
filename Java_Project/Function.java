public class Function{//a generalized function class that can contain a number of different functions for use in the rootfinding class, can be extended
  
  double xal;
  double yag;
  double x;
  double [][] data;
  EquilibriumData eqdata;
  int i;
  
  public Function(double [][] data, double xal, double yag, double x, EquilibriumData eqdata, int i){
    this.xal = xal;
    this.yag = yag;
    System.out.println(data.length);
    System.out.println(data[0].length);
      
    this.data = new double [data.length][data[0].length];
    for(int j = 0;j<data.length;j++){
      for(int k = 0;k<data[0].length;k++){
        this.data[j][k] = data[j][k];
      }
    }
    /*double []data = new double [8];
    data[0]= (xal*myColumn.fluid.mW_A + (1-xal)*myColumn.fluid.mW_L); //Calculate MW_av,L
    data[1] = (yag*myColumn.fluid.mW_A + (1-yag)*myColumn.fluid.mW_V); //Calculate MW_av,V
    data[2] = myColumn.lPrime/(1-xal); //Calculating L
    data[3] = myColumn.vPrime/(1-yag); //Calculating V
    data[4] = data[2]*data[0]/myColumn.crossArea;  //Calculating G_L
    data[5] = data[3]*data[1]/myColumn.crossArea; //Calculating G_V
    //Calculating k'xa
    this.data[6] = Math.pow(((myColumn.crossArea/data[2]) * (0.357/myColumn.packing.fpPacking) * Math.pow( (myColumn.fluid.nsc_L/372), 0.5) * Math.pow((data[4]/myColumn.fluid.mu_L)/(6.782/0.0008937), 0.3)), -1);
    //Calculating k'ya
    this.data[7] = Math.pow(((myColumn.crossArea/data[3]) * (0.226/myColumn.packing.fpPacking) * Math.pow((myColumn.fluid.nsc_V/0.660), 0.5) * Math.pow((data[4]/6.782), -0.5) * Math.pow((data[5]/0.678), 0.35)), -1);*/
    double y = eqdata.equilibriumDataY(x);//calculate y using the equilibrium data
    double lmlconc =((1-this.xal)-(1-x))/Math.log((1-this.xal)/(1-x));///log mean liquid concentration at interface
    double lmgconc =((1-y)-(1-this.yag))/Math.log((1-y)/(1-this.yag));//log mean gas concentration at interface
    double m = -((data[6][i]/lmlconc)/(data[7][i]/lmgconc));
    double b = 0.5*(this.yag+y-m*(this.xal+x));
    this.x = y-(m*x+b);//calculate the function for the determination of xai
  }
  public double setX(double x){
    System.out.println(x);
    double y = this.eqdata.equilibriumDataY(x);//calculate y using the equilibrium data
    double lmlconc =((1-this.xal)-(1-x))/Math.log((1-this.xal)/(1-x));///log mean liquid concentration at interface
    double lmgconc =((1-y)-(1-this.yag))/Math.log((1-y)/(1-this.yag));//log mean gas concentration at interface
    double m = -((this.data[6][this.i]/lmlconc)/(this.data[7][this.i]/lmgconc));
    double b = 0.5*(this.yag+y-m*(this.xal+x));
    this.x = y-(m*x+b);//calculate the function for the determination of xai
    return this.x;
  }
}