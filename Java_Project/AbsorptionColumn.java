import java.math.*;
import java.util.Scanner;
import java.util.InputMismatchException;

/* Inputted Data Should Include
 * 
 * Gas inlet flow (v_1)
 * Gas phase mole fraction (y_a1)
 * Liquid inlet flow (l_2)
 * Liquid phase mole fraction (x_a2)
 * Recovery (recovery)
 * Inlet temp (temp_in)
 * Packing type (packing)
 * 
 * Outputted Data should Include
 * 
 * Liquid outlet flow (optL)
 * Tower Height (z)
 * 
 */

/*Class: AbsorptionColumn
 * 
 * This class contains the instance variables and methods for the creation of an object representing an absorption column
 * Along with a large number of double and double [] instance variables representing the various properties of the column, 
 * the absorption column also contains a Packing object, a Fluid object, and an EquilibriumData object
 * 
 * To construct the object the class requires an input of an object of type Packing, an object of type Fluid, and the inputted
 * data described above.
 * 
 * The class contains a method used to calculate the difference between the liquid and vapour heights of the column as well as
 * a method that optimizes the mass transfer through the column by adjusting the liquid flow rates.
 * 
 * The required accessor methods as well as a copy constructor and a clone method are in place. However, due to the complex nature
 * of the column, no mutators are in place to change the variables once the column has been constructed.
 * 
 */ 

public class AbsorptionColumn{
  
  //Input objects
  Packing packing;
  Fluid fluid;
  
  //Input values
  private double v_1, y_a1, l_2, x_a2, recovery, temp_in;
  
  //System constants
  private double vPrime, lPrime, dC, crossArea, x_a1, y_a2, v_2, l_1, zl, zv;
  
  //Other output values
  private double  z, optL;
  
  //System properties
  int iterations;
  EquilibriumData eqdata;
  private double [] xal, yag, xai, yai, dzv, dzl;
  
  //Constructor
  public AbsorptionColumn(Packing packing, Fluid fluid, InputData data){
    
    //Initialize variables from inputs
    double[] conditions = data.getSC();
    this.packing = packing.clone();
    this.fluid = fluid.clone();
      
    //Initialize given inputs
    this.v_1 = conditions[0];
    this.y_a1 = conditions[1];
    this.l_2 = conditions[2];
    this.x_a2 = conditions[3];
    this.recovery = conditions[4];
    
    //Calculating system constants
    this.crossArea = ((Math.PI*Math.pow((packing.getColDiameterHeuristic()*packing.nominalSize), 2))/4);
    this.vPrime = (v_1*(1-y_a1)/3600);
    this.v_2 = vPrime*3600+(1-recovery)*v_1*y_a1;
    this.y_a2 = y_a1*(1-recovery)*v_1/v_2;
    
    //Calculating system properties
    this.iterations = 1000;
    this.eqdata = new EquilibriumData(data);
    
    //The method recalculateHeightDifference sets the rest of the instance variables and allows for optimization
    //by allowing the column to be recalculated with different liquid flow rates
    double zdiff = calculateHeightDifference(conditions[2]);
    
    
    //Choose whether or not to optimize the column
    Scanner myscan = new Scanner(System.in);
    boolean flag = false;
    
    int i = 0;
    
    //If you use a GUI, the program will skip the console text
    if(data.useGUI == false){
      try {
        System.out.println("Would you like to optimize the column? (1 for yes, 0 for no)");
        i = myscan.nextInt();
        flag = true;
      }
      //If a value that is not a number is entered the column will automatically not be optimized
      catch (InputMismatchException e) {
        myscan.nextLine();
        System.out.println("The column will not be optimized.");
      }
    }
    else{
      if(data.optimize==true){i = 1;}
      else{i = 0;}
    }
    
    if(i==1){ this.optL = optimizeLiquidFlow();//optimize the column
      double a  = calculateHeightDifference(this.optL);//use the optimized liquid value to recalculate the height
    }
    //If the column is not optimized the optimized liquid flow rate is set as zero
    else this.optL = 0;   
  }
  //Copy constructor
  public AbsorptionColumn(AbsorptionColumn source){
    this.packing = source.packing.clone();
    this.fluid = source.fluid.clone();
    
    //Given inputs
    this.v_1 = source.v_1;
    this.y_a1 = source.y_a1;
    this.l_2 = source.l_2;
    this.x_a2 = source.x_a2;
    this.recovery = source.recovery;
    this.temp_in = source.temp_in;
    
    //Calculating system constants
    this.crossArea = source.crossArea;
    this.vPrime = source.vPrime;
    this.lPrime = source.lPrime;
    this.v_2 = source.v_2;
    this.y_a2 = source.y_a2;
    this.x_a1 = source.x_a1;
    this.l_1 = source.l_1;
    this.iterations = source.iterations;
    this.eqdata = source.eqdata.clone();
    
    //Calculating system properties
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
    
    //Calculating system outputs
    this.z = source.z;
    this.optL = source.optL;
  }
  
  // Accessors for given inputs
  public double getV1() {return this.v_1;}
  public double getYA1() {return this.y_a1;}
  public double getL2() {return this.l_2;}
  public double getXA2() {return this.x_a2;}
  public double getRecovery() {return this.recovery;}
  public double getTempIn() {return this.temp_in;}
  
  // Accessors for system constants
  public double getVPrime() {return this.vPrime;}
  public double getLPrime() {return this.lPrime;}
  public double getDC() {return this.dC;}
  public double getCrossArea() {return this.crossArea;}
  public double getXA1() {return this.x_a1;}
  public double getYA2() {return this.y_a2;}
  public double getV2() {return this.v_2;}
  public double getL1() {return this.l_1;}
  public double getZL() {return this.zl;}
  public double getZV() {return this.zv;}
  
  // Accessors for output values
  public double getZ() {return this.z;}
  public double getOptL() {return this.optL;}
  
  // Accessors for system properties
  public double[] getXAL() {
    double[] copy1 = new double [this.iterations];
    for(int j = 0;j<this.iterations;j++){
      copy1[j] = this.xal[j];} return copy1;
  }
  public double[] getYAG() {
    double[] copy2 = new double [this.iterations];
    for(int k = 0;k<this.iterations;k++){
      copy2[k] = this.yag[k];} return copy2;
  }
  public double[] getXAI() {
    double[] copy3 = new double [this.iterations];
    for(int k = 0;k<this.iterations;k++){
      copy3[k] = this.xai[k];} return copy3;
  }
  public double[] getYAI() {
    double[] copy4 = new double [this.iterations];
    for(int k = 0;k<this.iterations;k++){
      copy4[k] = this.yai[k];} return copy4;
  }
  public double[] getDZV() {
    double[] copy5 = new double [this.iterations];
    for(int k = 0;k<this.iterations;k++){
      copy5[k] = this.dzv[k];} return copy5;
  }
  public double[] getDZL() {
    double[] copy6 = new double [this.iterations];
    for(int k = 0;k<this.iterations;k++){
      copy6[k] = this.dzl[k];} return copy6;
  }
  
  //Clone method to properly copy the object
  public AbsorptionColumn clone(){
    return new AbsorptionColumn(this);
  }
  
  //Method that optimizes the column liquid flow
  public double optimizeLiquidFlow(){
    //Creates a function to be optimized based on the current absorptionColumn object and the current liquid flow
    OptimizationFunction f = new OptimizationFunction(this,this.l_2);
    //Determines in which direction to incrementally search for an optimal liquid flow rate
    if(this.zv>this.zl){
      //In this case the program sets a lower bound and searches upwardly for the optimial flow rate
      IncrementalUp i = new IncrementalUp(this.l_2,this.l_2*100);
      //Returns the optimized liquid flow rate
      return i.calculate(f);
    }
    else{
      //In this case the program sets an upper bound and searches downwardly for the optimal flow rate
      IncrementalDown i = new IncrementalDown(this.l_2/100,this.l_2);
      return i.calculate(f);
    }
  }
  
  //This method is used to calculate the difference in heights for  both optimization and construction
  public double calculateHeightDifference(double l) {
    
    this.l_2 = l;
    
    //Calculating remaining system constants
    this.lPrime = (this.l_2*(1-this.x_a2)/3600);
    this.x_a1 = ((this.vPrime/this.lPrime)*((this.y_a1/(1-this.y_a1))-(this.y_a2/(1-this.y_a2))))/(1+((this.vPrime/this.lPrime)*((this.y_a1/(1-this.y_a1))-(this.y_a2/(1-this.y_a2)))));
    this.l_1 = this.lPrime/(1-this.x_a1)*3600;
    
    //Calculating system properties
    double delta_x = this.x_a1/this.iterations;//calculate delta x
    
    //Calculate an array of xal values at which each dzv will be calculated
    this.xal = new double [this.iterations];
    for(int j = 0;j<this.iterations;j++){
      xal[j] = this.x_a1-delta_x*j;
    }
    
    //Calculate an array of yag values at which each dzl will be calculated
    this.yag = new double [this.iterations];
    this.yag[0] = y_a1;
    for(int k = 0;k<this.iterations-1;k++){
      this.yag[k+1] = ((this.lPrime/this.vPrime)*(((xal[k+1])/(1-(xal[k+1])))-(xal[k]/(1-xal[k])))+(yag[k]/(1-yag[k])))/((this.lPrime/this.vPrime)*(((xal[k+1])/(1-(xal[k+1])))-(xal[k]/(1-xal[k])))+(yag[k]/(1-yag[k]))+1);//same with yag using eqbm data
    }
    
    this.xai = new double [this.iterations];
    double x = (0.9999-0)/2;
    //data is a mutlidimensional array that calculates 8 system properties for each iteration
    double [][]data = new double [8][this.iterations];
    //This for loop calculates xai at each iteration using ridders method
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
      //The interface function is newly initilaized at each iteration for the new k, xal, and yag values
      InterfaceFunction f = new InterfaceFunction(data[6][i],data[7][i],xal[i],yag[i],x,eqdata);
      Ridders r = new Ridders();
      //Solve for xai values using the ridders root finding method
      this.xai[i] = r.calculate(f);
      x = xai[i];
    }
    
    //Determine the respective yai values with the equilibrium data
    this.yai = new double [this.iterations];
    for(int i = 0;i<this.iterations;i++){
      this.yai[i] = eqdata.equilibriumDataY(xai[i]);
    }
    
    //Calculate the incremental vapour height values
    this.dzv = new double [this.iterations];
    for(int m = 0;m<this.iterations;m++){
      this.dzv[m] = data[3][m]/(data[7][m]*this.crossArea/(((1-yai[m])-(1-yag[m]))/Math.log((1-yai[m])/(1-yag[m])))*(1-yag[m])*(yag[m]-yai[m]));
    }
    
    //Calculate the incremental liquid height values
    this.dzl = new double [this.iterations];
    for(int n = 0;n<this.iterations;n++){
      this.dzl[n] = data[2][n]/(data[6][n]*this.crossArea/(((1-xal[n])-(1-xai[n]))/Math.log((1-xal[n])/(1-xai[n])))*(1-xal[n])*(xai[n]-xal[n]));
    }
    
    //Determine whether simpson integration or trapezoid integration should be used based on whether or not the distance between
    //the points is constant
    if((xal[1]-xal[0]==xal[2]-xal[1])){
      //Since the distance between xal points is constant, use simpsons method to integrate for zl
      Simpsons szl = new Simpsons();
      this.zl = szl.calculate(xal,dzl);
    }
    else{
      //Since the distance between xal points is not constant, use trapezoid rule to integrate for zl
      TrapezoidRule szl = new TrapezoidRule();
      this.zl = szl.calculate(xal,dzl);
    }
    if((yag[1]-yag[0]==yag[2]-yag[1])){
      //Since the distance between yag points is constant, use simpsons method to integrate for zv
      Simpsons szv = new Simpsons();
      this.zv = szv.calculate(yag,dzv);
    }
    else{
      //Since the distance between yag points is not constant, use trapezoid rule to integrate for zv
      TrapezoidRule szv = new TrapezoidRule();
      this.zv = szv.calculate(yag,dzv);
    }
   
    //Set the height of the column to whichever height is larger
    if(this.zl>=this.zv){this.z = zl;}
    else{this.z = zv;}
    
    //Returns height difference  
    return zl-zv;
  } 
}