import java.math.*;

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

public class AbsorptionColumn2{
  
  
  
  //Input objects
  Packing packing;
  Fluid fluid;

  //Input values
  double v_1, y_a1, l_2, x_a2, recovery, temp_in;
  
  //System constants
  double vPrime, lPrime, dC, crossArea, x_a1, y_a2;
 
  //Other output values
  double v_2, l_1, z, zl, zv;
  
  //System properties
  int iterations;
  EquilibriumData eqdata;
  double [] xal, yag, xai, yai, dzv, dzl;
    
  
  //Constructor
  public AbsorptionColumn2(Packing packing, Fluid fluid, double[] conditions){
    
    this.packing = packing; //Needs deep copy
    this.fluid = fluid; //Needs deep copy
    
    //Given Inputs
    this.v_1 = conditions[0];
    this.y_a1 = conditions[1];
    this.l_2 = conditions[2];
    this.x_a2 = conditions[3];
    this.recovery = conditions[4];
    this.temp_in = conditions[5];
    
    //Calculating System Constants
    this.crossArea = ((Math.PI*Math.pow((packing.colDiamterHeuristic*packing.nominalSize), 2))/4);
    this.vPrime = (v_1*(1-y_a1)/3600);
    this.lPrime = (l_2*(1-x_a2)/3600);
    this.v_2 = vPrime*3600+(1-recovery)*v_1*y_a1;
    this.y_a2 = y_a1*(1-recovery)*v_1/v_2;
    this.x_a1 = ((vPrime/lPrime)*((y_a1/(1-y_a1))-(y_a2/(1-y_a2))))/(1+((vPrime/lPrime)*((y_a1/(1-y_a1))-(y_a2/(1-y_a2)))));
    this.l_1 = lPrime/(1-x_a1)*3600;
    
    //Calculating System Properties
    this.iterations = (int)conditions[6];
    double delta_x = this.x_a1/this.iterations;//calculate delta x
    this.eqdata = new EquilibriumData();
    
    this.xal = new double [this.iterations];
    for(int j = 0;j<this.iterations;j++){
      xal[j] = this.x_a1-delta_x*j;//calculate an array of xal values at which each dzv will be calculated
    }
    
    this.yag = new double [this.iterations];
    this.yag[0] = y_a1;
    for(int k = 0;k<this.iterations-1;k++){
      this.yag[k+1] = ((this.lPrime/this.vPrime)*(((xal[k+1])/(1-(xal[k+1])))-(xal[k]/(1-xal[k])))+(yag[k]/(1-yag[k])))/((this.lPrime/this.vPrime)*(((xal[k+1])/(1-(xal[k+1])))-(xal[k]/(1-xal[k])))+(yag[k]/(1-yag[k]))+1);//same with yag using eqbm data
    }
    
    this.xai = new double [this.iterations];
    double x = (0.9999-0)/2;
    double [][]data = new double [8][this.iterations];
    for (int i=0;i<this.iterations;i++){
      data[0][i]= (xal[i]*this.fluid.mW_A + (1-xal[i])*this.fluid.mW_L); //Calculate MW_av,L
      data[1][i] = (yag[i]*this.fluid.mW_A + (1-yag[i])*this.fluid.mW_V); //Calculate MW_av,V
      data[2][i] = this.lPrime/(1-xal[i]); //Calculating L
      data[3][i] = this.vPrime/(1-yag[i]); //Calculating V
      data[4][i] = data[2][i]*data[0][i]/this.crossArea;  //Calculating G_L
      data[5][i] = data[3][i]*data[1][i]/this.crossArea; //Calculating G_V
      //Calculating k'xa
      data[6][i] = Math.pow(((this.crossArea/data[2][i]) * (0.357/this.packing.fpPacking) * Math.pow( (this.fluid.nsc_L/372), 0.5) * Math.pow((data[4][i]/this.fluid.mu_L)/(6.782/0.0008937), 0.3)), -1);
      //Calculating k'ya
      data[7][i] = Math.pow(((this.crossArea/data[3][i]) * (0.226/this.packing.fpPacking) * Math.pow((this.fluid.nsc_V/0.660), 0.5) * Math.pow((data[4][i]/6.782), -0.5) * Math.pow((data[5][i]/0.678), 0.35)), -1);
      Function f = new Function(data[6][i],data[7][i],xal[i],yag[i],x,eqdata);
      this.xai[i] = RootFinding2.Ridders(f);//solve for xai values using the ridders root finding method
      x = xai[i];
    }
    
    this.yai = new double [this.iterations];
    for(int i = 0;i<this.iterations;i++){//determine yai values with the eqbm coefficients
      this.yai[i] = eqdata.equilibriumDataY(xai[i]);
    }
    
    this.dzv = new double [this.iterations];
    for(int m = 0;m<this.iterations;m++){//calculate the incremental height values
      this.dzv[m] = data[3][m]/(data[7][m]*this.crossArea/(((1-yai[m])-(1-yag[m]))/Math.log((1-yai[m])/(1-yag[m])))*(1-yag[m])*(yag[m]-yai[m]));
    }
    
    this.dzl = new double [this.iterations];
    for(int n = 0;n<this.iterations;n++){//calculate the incremental height values
      this.dzl[n] = data[2][n]/(data[6][n]*this.crossArea/(((1-xal[n])-(1-xai[n]))/Math.log((1-xal[n])/(1-xai[n])))*(1-xal[n])*(xai[n]-xal[n]));
    }
    
    this.zl = Integration.Simpsons(xal,dzl);
    System.out.println(zl);
    this.zv = Integration.Simpsons(yag,dzv);
    System.out.println(zv);
    if(this.zl>=this.zv){ z = zl;}
    else{ z = zv;}

  }
  
  
  
}