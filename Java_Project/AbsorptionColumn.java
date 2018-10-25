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

public class AbsorptionColumn{
  
  
  
  //Input objects
  Packing packing;
  Fluid fluid;
  Iterator iterator;

  //Input values
  double v_1, y_a1, l_2, x_a2, recovery, temp_in;
  
  //System constants
  double vPrime, lPrime, dC, crossArea, x_a1, y_a2;
 
  //Other output values
  double v_2, l_1, z, zl, zv;
  
  
  
  //Constructor
  AbsorptionColumn(Packing packing, Fluid fluid, double[] conditions){
    
    this.packing = packing; //Needs deep copy
    this.fluid = fluid; //Needs deep copy
    this.iterator = iterator; //Needs deep copy
    
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

  }
  
  
  
}