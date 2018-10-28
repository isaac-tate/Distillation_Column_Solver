public class Data{
  double [] data = new double [8];
  
  public Data(double xal, double yag, AbsorptionColumn myColumn){
    this.data[0]= (xal*myColumn.fluid.mW_A + (1-xal)*myColumn.fluid.mW_L); //Calculate MW_av,L
    this.data[1] = (yag*myColumn.fluid.mW_A + (1-yag)*myColumn.fluid.mW_V); //Calculate MW_av,V
    this.data[2] = myColumn.lPrime/(1-xal); //Calculating L
    this.data[3] = myColumn.vPrime/(1-yag); //Calculating V
    this.data[4] = data[2]*data[0]/myColumn.crossArea;  //Calculating G_L
    this.data[5] = data[3]*data[1]/myColumn.crossArea; //Calculating G_V
    //Calculating k'xa
    this.data[6] = Math.pow(((myColumn.crossArea/data[2]) * (0.357/myColumn.packing.fpPacking) * Math.pow( (myColumn.fluid.nsc_L/372), 0.5) * Math.pow((data[4]/myColumn.fluid.mu_L)/(6.782/0.0008937), 0.3)), -1);
    //Calculating k'ya
    this.data[7] = Math.pow(((myColumn.crossArea/data[3]) * (0.226/myColumn.packing.fpPacking) * Math.pow((myColumn.fluid.nsc_V/0.660), 0.5) * Math.pow((data[4]/6.782), -0.5) * Math.pow((data[5]/0.678), 0.35)), -1);
  }
  public double getData(int m){
    return this.data[m];
  }
}

    