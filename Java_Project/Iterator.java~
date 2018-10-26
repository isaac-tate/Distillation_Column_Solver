//[x_AL - y_AG - MW_av,L - MW_av,V - L - V - G_L - G_V - k'xa - k'ya - x_Ai - y_ai - (1-x)im - (1-y)im - m - b - F(xai) - dzv - dzl - dyag - dxaL - zv - zl]

public class Iterator{
  
  int iterations;
  double delta_x;
  AbsorptionColumn myColumn;
  
  double[] workingValues = new double[23];
  
  
  Iterator(AbsorptionColumn myColumn, int iterations){
    
    this.iterations = iterations;
    this.myColumn = myColumn;
    this.delta_x = myColumn.x_a1/iterations;
    workingValues[0] = myColumn.x_a1; //set initial x_ag
    workingValues[1] = myColumn.y_a1; //set initial y_ag
    
  }
  
  public void run(){
    
    //Calculate MW_av,L
    workingValues[2] = (workingValues[0]*myColumn.fluid.mW_A + (1-workingValues[0])*myColumn.fluid.mW_L);
    
    //Calculate MW_av,V
    workingValues[3] = (workingValues[1]*myColumn.fluid.mW_A + (1-workingValues[1])*myColumn.fluid.mW_V);
    
    //Calculating L
    workingValues[4] = myColumn.lPrime/(1-workingValues[0]);
    
    //Calculating V
    workingValues[5] = myColumn.vPrime/(1-workingValues[1]);
    
    //Calculating G_L
    workingValues[6] = workingValues[4]*workingValues[2]/myColumn.crossArea;
    
    //Calculating G_V
    workingValues[7] = workingValues[5]*workingValues[3]/myColumn.crossArea;
    
    //Calculating k'xa
    workingValues[8] = Math.pow(((myColumn.crossArea/workingValues[4]) * (0.357/myColumn.packing.fpPacking) * Math.pow( (myColumn.fluid.nsc_L/372), 0.5) * Math.pow((workingValues[6]/myColumn.fluid.mu_L)/(6.782/0.0008937), 0.3)), -1);
    
    //Calculating y'xa
    workingValues[9] = Math.pow(((myColumn.crossArea/workingValues[5]) * (0.226/myColumn.packing.fpPacking) * Math.pow((myColumn.fluid.nsc_V/0.660), 0.5) * Math.pow((workingValues[6]/6.782), -0.5) * Math.pow((workingValues[7]/0.678), 0.35)), -1);
    
    //Calculating HELP ME
    
  }
  
  
}