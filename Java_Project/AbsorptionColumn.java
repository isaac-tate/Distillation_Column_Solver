import java.math.*;
import java.util.Scanner;

    /* Inputted Data Should Include
     * 
     * Gas inlet flow (gas_in_flow)
     * Gas phase mole fraction (gas_in_mole_frac)
     * Liquid inlet flow (liq_in_flow)
     * Liquid phase mole fraction (liq_in_mole_frac)
     * Recovery (recovery)
     * Inlet temp (temp_in)
     * Packing type (packing)
     * 
     * Outputted Data should include
     * Gas outlet flow (gas_flow_out)
     * Gas phase mole fraction (gas_out_mole_frac)
     * Liquid outlet flow (liq_out_flow)
     * Liquid mole fraction (liq_out_mole_frac)
     * Tower Height (tower_height)
     * 
     */

public class AbsorptionColumn{
  
  //Input objects
  Packing packing;
  Fluid fluid;

  //Input values
  double v_1, y_a1, l_2, x_a2, recovery, temp_in;
  
  //System constants
  double vPrime, lPrime, dC, crossArea, x_a1, y_a2;
 
  //Other output values
  double v_2, l_1, z, zl, zv,optL;
  
  //System properties
  int iterations;
  EquilibriumData eqdata;
  double [] xal, yag, xai, yai, dzv, dzl;
  
  //Constructor
  public AbsorptionColumn(Packing packing, Fluid fluid, InputData data){
    
    double[] conditions = data.getSC();
    
    this.packing = new Packing(packing); //Needs deep copy
    this.fluid = new Fluid(fluid);
    
    //Given Inputs
    this.v_1 = conditions[0];
    this.y_a1 = conditions[1];
    this.x_a2 = conditions[3];
    this.recovery = conditions[4];
    this.temp_in = conditions[5];
    
    //Calculating System Constants
    this.crossArea = ((Math.PI*Math.pow((packing.getColDiameterHeuristic()*packing.nominalSize), 2))/4);
    this.vPrime = (v_1*(1-y_a1)/3600);
    this.v_2 = vPrime*3600+(1-recovery)*v_1*y_a1;
    this.y_a2 = y_a1*(1-recovery)*v_1/v_2;
    
    //Calculating System Properties
    this.iterations = (int)conditions[6];
    this.eqdata = new EquilibriumData(data);
    
    //The method recalculateHeightDifference sets the rest of the instance variables and allows for optimization
    double zdiff = calculateHeightDifference(conditions[2]);
    
    Scanner myscan = new Scanner(System.in);
    boolean flag = false;
   //Choose whether or not to optimize the column

    int i;
    if(data.useGUI == false){
      System.out.println("Would you like to optimize the column? (1 for yes, 0 for no)");
      i = myscan.nextInt();
    }
    else{
      if(data.optimize==true){i = 1;}
      else{i = 0;}
    }
    
    if(i==1) this.optL = optimizeLiquidFlow();
    else this.optL = 0;

    

  }
  //copy constructor
  public AbsorptionColumn(AbsorptionColumn source){
    this.packing = new Packing(source.packing); //Needs deep copy
    this.fluid = new Fluid(source.fluid);
    
    //Given Inputs
    this.v_1 = source.v_1;
    this.y_a1 = source.y_a1;
    this.l_2 = source.l_2;
    this.x_a2 = source.x_a2;
    this.recovery = source.recovery;
    this.temp_in = source.temp_in;
    
    //Calculating System Constants
    this.crossArea = source.crossArea;
    this.vPrime = source.vPrime;
    this.lPrime = source.lPrime;
    this.v_2 = source.v_2;
    this.y_a2 = source.y_a2;
    this.x_a1 = source.x_a1;
    this.l_1 = source.l_1;
    this.iterations = source.iterations;
    this.eqdata = new EquilibriumData(source.eqdata);
    
    //Calculate System Properties
    this.xal = new double [this.iterations];
    for(int j = 0;j<this.iterations;j++){
      this.xal[j] = source.xal[j];
    }
    this.yag = new double [this.iterations];
    for(int k = 0;k<this.iterations;k++){
      this.yag[k] = source.yag[k];
    }
    this.xai = new double [this.iterations];
    for(int k = 0;k<this.iterations;k++){
      this.xai[k] = source.xai[k];
    }
    this.yai = new double [this.iterations];
    for(int k = 0;k<this.iterations;k++){
      this.yai[k] = source.yai[k];
    }
    this.dzv = new double [this.iterations];
    for(int k = 0;k<this.iterations;k++){
      this.dzv[k] = source.dzv[k];
    }
    this.dzl = new double [this.iterations];
    for(int k = 0;k<this.iterations;k++){
      this.dzl[k] = source.dzl[k];
    }
    this.zl = source.zl;
    this.zv = source.zv;
    this.z = source.z;
  }
  
  //Method that optimizes the column liquid flow
  public double optimizeLiquidFlow(){
    OptimizationFunction f = new OptimizationFunction(this,10000);
    if(this.zv>this.zl){
      IncrementalUp i = new IncrementalUp(this.l_2,this.l_2*100);
      return i.calculate(f);
    }
    else{
      IncrementalDown i = new IncrementalDown(this.l_2/100,this.l_2);
      return i.calculate(f);
    }
    //use incremental search to optimize the column based on a certain liquid flow
  }
  
  public double calculateHeightDifference(double l){//use to calculate the difference in heights for optimization and construction
    
    this.l_2 = l;
    
    //Calculating System Constants
    this.lPrime = (l_2*(1-x_a2)/3600);
    this.x_a1 = ((vPrime/lPrime)*((y_a1/(1-y_a1))-(y_a2/(1-y_a2))))/(1+((vPrime/lPrime)*((y_a1/(1-y_a1))-(y_a2/(1-y_a2)))));
    this.l_1 = lPrime/(1-x_a1)*3600;
    
    //Calculating System Properties
    double delta_x = this.x_a1/this.iterations;//calculate delta x
    
    this.xal = new double [this.iterations];
    for(int j = 0;j<this.iterations;j++){
      xal[j] = this.x_a1-delta_x*j;//calculate an array of xal values at which each dzv will be calculated
    }
    
    this.yag = new double [this.iterations];
    this.yag[0] = y_a1;
    for(int k = 0;k<this.iterations-1;k++){
      this.yag[k+1] = ((this.lPrime/this.vPrime)*(((xal[k+1])/(1-(xal[k+1])))-(xal[k]/(1-xal[k])))+(yag[k]/(1-yag[k])))/((this.lPrime/this.vPrime)*(((xal[k+1])/(1-(xal[k+1])))-(xal[k]/(1-xal[k])))+(yag[k]/(1-yag[k]))+1);//same with yag using eqbm data
    }//calculate array of yag values
    
    this.xai = new double [this.iterations];
    double x = (0.9999-0)/2;
    double [][]data = new double [8][this.iterations];//mutlidimensional array that calculates 8 system properties for each iteration
    //calculates xai at each iteration using ridders method
    for (int i=0;i<this.iterations;i++){
      data[0][i]= (xal[i]*fluid.getMW_A() + (1-xal[i])*fluid.getMW_L()); //Calculate MW_av,L
      data[1][i] = (yag[i]*fluid.getMW_A() + (1-yag[i])*fluid.getMW_V()); //Calculate MW_av,V
      data[2][i] = this.lPrime/(1-xal[i]); //Calculating L
      data[3][i] = this.vPrime/(1-yag[i]); //Calculating V
      data[4][i] = data[2][i]*data[0][i]/this.crossArea;  //Calculating G_L
      data[5][i] = data[3][i]*data[1][i]/this.crossArea; //Calculating G_V
      //Calculating k'xa
      data[6][i] = Math.pow(((this.crossArea/data[2][i]) * (0.357/this.packing.getFPPacking()) * Math.pow( (this.fluid.getNsc_L()/372), 0.5) * Math.pow((data[4][i]/this.fluid.getMu_L())/(6.782/0.0008937), 0.3)), -1);
      //Calculating k'ya
      data[7][i] = Math.pow(((this.crossArea/data[3][i]) * (0.226/this.packing.getFPPacking()) * Math.pow((this.fluid.getNsc_V()/0.660), 0.5) * Math.pow((data[4][i]/6.782), -0.5) * Math.pow((data[5][i]/0.678), 0.35)), -1);
      InterfaceFunction f = new InterfaceFunction(data[6][i],data[7][i],xal[i],yag[i],x,eqdata);
      Ridders r = new Ridders();
      this.xai[i] = r.calculate(f);//solve for xai values using the ridders root finding method
      x = xai[i];
    }
    
    this.yai = new double [this.iterations];
    for(int i = 0;i<this.iterations;i++){//determine yai values with the eqbm coefficients
      this.yai[i] = eqdata.equilibriumDataY(xai[i]);
    }
    
    this.dzv = new double [this.iterations];
    for(int m = 0;m<this.iterations;m++){//calculate the incremental vapour height values
      this.dzv[m] = data[3][m]/(data[7][m]*this.crossArea/(((1-yai[m])-(1-yag[m]))/Math.log((1-yai[m])/(1-yag[m])))*(1-yag[m])*(yag[m]-yai[m]));
    }
    
    this.dzl = new double [this.iterations];
    for(int n = 0;n<this.iterations;n++){//calculate the incremental liquid height values
      this.dzl[n] = data[2][n]/(data[6][n]*this.crossArea/(((1-xal[n])-(1-xai[n]))/Math.log((1-xal[n])/(1-xai[n])))*(1-xal[n])*(xai[n]-xal[n]));
    }
    
    TrapezoidRule szl = new TrapezoidRule();
    this.zl = szl.calculate(xal,dzl);//solve liquid height using integration method
    //System.out.println(zl+"zl");
    TrapezoidRule szv = new TrapezoidRule();
    this.zv = szv.calculate(yag,dzv);//solve vapour height using integration method
    //System.out.println(zv+"zv");
    if(this.zl>=this.zv){this.z = zl;}
    else{this.z = zv;}
    return zl-zv;//returns height difference

  }
}