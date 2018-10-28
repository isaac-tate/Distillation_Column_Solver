import java.util.Scanner;

public class RunMe{
  public static void main(String[] args){
    
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
    
    double[] inputs = new double[6];
    Scanner myScan = new Scanner(System.in);

    System.out.println("Welcome to column calculator");
    
    //GETTING INPUTS
    
    System.out.println("Please input a starting gas inlet flow rate");
    inputs[0] = myScan.nextDouble();
    
    System.out.println("Please input a starting gas mole fraction");
    inputs[1] = myScan.nextDouble();
    
    System.out.println("Please input a starting liquid inlet flow rate");
    inputs[2] = myScan.nextDouble();
    
    System.out.println("Please input a starting liquid mole fraction");
    inputs[3] = myScan.nextDouble();
    
    System.out.println("Please input a recovery value");
    inputs[4] = myScan.nextDouble();
    
    System.out.println("Please input an inlet temp");
    inputs[5] = myScan.nextDouble();
    myScan.nextLine();
    
    System.out.println("Please input an packing type (berl, rashig, pall)");
    String packType = myScan.nextLine();
    
    System.out.println("Please amount of iterations");
    int interations = myScan.nextInt();
    
    //DONE GETTING INPUTS
    
    
    
    Fluid fluid = new Fluid();
    Packing packing = new Packing(packType);
    
    AbsorptionColumn myColumn = new AbsorptionColumn(packing, fluid, inputs);
    System.out.println(myColumn.v_2);
    
    Iterator iterator = new Iterator(myColumn, interations);
    
    
    
  }
}
    