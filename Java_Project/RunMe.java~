import java.util.Scanner;
import java.io.*;

public class RunMe{
  public static void main(String[] args){
    
    UseGUI useGUI = new UseGUI();
    
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
    
    boolean answered = false;
    while(answered == false){
      answered = useGUI.answered;
      System.out.print("");
    }
    
    if(useGUI.use == true){
     
      GuiApp myGui = new GuiApp();
      while(!myGui.dataStored){System.out.print("");}
      InputData systemData = myGui.data;
      systemData.setGUI(true);
      systemData.setFromFiles(myGui.fromFiles);
      Fluid fluid = new Fluid();
      Packing packing = new Packing(systemData.getPackingType());
      AbsorptionColumn myColumn = new AbsorptionColumn(packing, fluid, systemData);
      ResultsScreen myResults = new ResultsScreen(myColumn.z, myColumn.optL);
      if(myResults.exportV == true){DataExport myExport = new DataExport(myColumn);}
      
    }
    
    if(useGUI.use == false){
      
      InputData systemData = new InputData();
      Scanner myScan = new Scanner(System.in);
      
      System.out.println("Welcome to column calculator");
      
      //GETTING INPUTS
      String dataType; //The input for the method used to get inputs
      String packType = "";
      
      boolean flag = false;
      
      while(!flag){
        System.out.println("How would like to input conditions through a file ('f') or by input ('i')");
        dataType = myScan.nextLine();
        
        switch(dataType){
          case "f":
            valuesFromFile(myScan, systemData);
            flag=true;
            break;
          case "i":
            valuesFromInput(myScan, systemData);
            packType = myScan.nextLine();
            flag=true;
            break;
          default:
            System.out.println("Not a valid input");
        }
      }
      
      
      //DONE GETTING INPUTS
      
      Fluid fluid = new Fluid();
      Packing packing = new Packing(systemData.getPackingType());
      AbsorptionColumn myColumn = new AbsorptionColumn(packing, fluid, systemData);
      System.out.println("The height of the column is "+myColumn.z+"m.");
      System.out.println("The optimal liquid flow rate through the column for optimization of mass transfer is "+myColumn.optL+"kmol/h.");
      
      //Exporting Data
      
      while(true){
        System.out.println("Would you like to export data to a csv (excel)? (1 for yes, 0 for no)");
        int answer_export = myScan.nextInt();
        
        if(answer_export == 1){
          DataExport myExport = new DataExport(myColumn);
          System.out.println("Data exported");
          break;
        }
        if(answer_export == 0){
          System.out.println("Not exporting");
          break;
        }
        else{
          System.out.println("Not a valid input");
        }
        
      }     

    }    

  }
  
  public static void valuesFromFile(Scanner myScan, InputData myData){
    
    double[] inputs = new double[7];

    System.out.println("Please input a file name (leave blank for default: 'inputs.txt')");
    String fileName = myScan.nextLine();
    
    if(fileName.isEmpty()){
      System.out.println("-- Using default --");
      fileName = "inputs.txt";
    }
    
    try{
      File file = new File(fileName);
      Scanner fileInput = new Scanner(file);
      int i = 0;
      while(fileInput.hasNext() && i<inputs.length){
        String[] valueString = fileInput.nextLine().split("=");
        inputs[i] = new Double(valueString[1]).doubleValue();
        System.out.println(valueString[0] + " = " + inputs[i]);
        i++;
      }
      
      String pack = fileInput.nextLine().split("=")[1];
      myData.setPackingType(pack);
      System.out.println("The packing type is " + myData.getPackingType());
      
    }
    catch(FileNotFoundException e){
      System.out.println("Not a valid file name");
      valuesFromFile(myScan, myData);
    }
    
    
    myData.setSC(inputs);
    
  }
 
  public static void valuesFromInput(Scanner myScan, InputData myData){
    
    double[] inputs = new double[7];
    
    System.out.println("Please input a starting gas inlet flow rate");
    inputs[0] = myScan.nextDouble();
    //inputs[0] = 10000;
    
    System.out.println("Please input a starting gas mole fraction");
    inputs[1] = myScan.nextDouble();
    //inputs[1] = 0.05;
    
    System.out.println("Please input a starting liquid inlet flow rate");
    inputs[2] = myScan.nextDouble();
    //inputs[2] = 30000;
    
    System.out.println("Please input a starting liquid mole fraction");
    inputs[3] = myScan.nextDouble();
    //inputs[3] = 0;
    
    System.out.println("Please input a recovery value");
    inputs[4] = myScan.nextDouble();
    //inputs[4] = 0.95;
    
    System.out.println("Please input an inlet temp");
    inputs[5] = myScan.nextDouble();
    //inputs[5] = 25;
    
    System.out.println("Please enter a number of iterations");
    inputs[6] = myScan.nextInt();
    myScan.nextLine();
    //inputs[6] = 100;
    
    System.out.println("Please input an packing type ('berl', 'rashig', 'pall')");
    myData.setPackingType(myScan.nextLine());
    
    myData.setSC(inputs);
    
  }
}
    