
import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;

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
      
      //Opening the Gui
      GuiApp myGui = new GuiApp();
      //Making sure data has been stored, if not loop till it is
      while(!myGui.dataStored){System.out.print("");}
      //Store data from GUI into data type
      InputData systemData = myGui.data;
      //Tell the data class you are using a GUI
      systemData.setGUI(true);
      //Tell the data class where the data is from
      systemData.setFromFiles(myGui.fromFiles);
      //Creating new fluid
      Fluid fluid = new Fluid();
      //Creating new packing
      Packing packing = new Packing(systemData.getPackingType());
      //Creating new ab col
      AbsorptionColumn myColumn = new AbsorptionColumn(packing, fluid, systemData);
      ResultsScreen myResults = new ResultsScreen(myColumn.getZ(), myColumn.getOptL());
      if(myResults.exportV == true){DataExport myExport = new DataExport(myColumn);}
      
    }
    
    if(useGUI.use == false){
      
      InputData systemData = new InputData();
      Scanner myScan = new Scanner(System.in);
      
      System.out.println("Welcome to column calculator.");
      
      //GETTING INPUTS
      String dataType; //The input for the method used to get inputs
      String packType = "";
      
      boolean flag = false;
      
      while(!flag){
        
        System.out.println("Would like to input conditions through a file ('f') or by input ('i')?");
        dataType = myScan.nextLine();
        
        switch(dataType){
          case "f":
            valuesFromFile(myScan, systemData);
            flag=true;
            break;
          case "i":
            valuesFromInput(myScan, systemData);
            flag=true;
            break;
          default:
            System.out.println("Not a valid input. Try again.");
        }
      }
      
      //DONE GETTING INPUTS
      
      Fluid fluid = new Fluid();
      Packing pack = new Packing(systemData.getPackingType());
      AbsorptionColumn myColumn = new AbsorptionColumn(pack, fluid, systemData);
      System.out.printf("The height of the column is %.2f m.\n",myColumn.getZ());
      if(myColumn.getOptL()!=0){
        System.out.printf("The optimal liquid flow rate through the column for optimization of mass transfer is %.2f kmol/h.\n",myColumn.getOptL());
      }
      //Exporting Data
      
      while(true){
        try{
          System.out.println("Would you like to export data to a csv (excel)? (1 for yes, 0 for no)");
          int answer_export = myScan.nextInt();
          
          if(answer_export == 1){
            DataExport myExport = new DataExport(myColumn);
            System.out.println("Data exported.");
            break;
          }
          if(answer_export == 0){
            System.out.println("Not exporting.");
            break;
          }
          else{
            System.out.println("Not a valid input.");
          }
        }
        catch (InputMismatchException export) {
          myScan.nextLine();
          System.out.print("Please try again and enter a numerical interger.\n");
        }     
      }
      System.out.println("Thank you for using column calculator.");
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
      while(fileInput.hasNext() && i<inputs.length-2){
        String[] valueString = fileInput.nextLine().split("=");
        inputs[i] = new Double(valueString[1]).doubleValue();
        System.out.println(valueString[0] + " = " + inputs[i]);
        i++;
      }
      inputs[5] = 25;
      inputs[6] = 1000;
      
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
    String userError = ""; // for UserErrorException message
    boolean check = false;
    boolean check1 = false;
    boolean check2 = false;
    boolean check3 = false;
    boolean check4 = false;
    boolean check5 = false;
    
    while(!check1) {
      try { 
        System.out.println("Please input a starting gas inlet flow rate");
        inputs[0] = myScan.nextDouble();
        //inputs[0] = 15;
        if (inputs[0] <= 0.) throw new UserErrorException(userError);
        check1 = true;
      } 
      catch (InputMismatchException inputThrow1) {
        myScan.nextLine();
        System.out.println("Try again and enter a positive numerical value.");
      }
      catch (UserErrorException inputThrow1) {
        myScan.nextLine();
        System.out.println(inputThrow1.getMessage());
      } 
    }
    
    while(!check2) {
      try {   
        System.out.println("Please input a starting gas mole fraction");
        inputs[1] = myScan.nextDouble();
        //inputs[1] = 0.12;
        if (inputs[1] < 0.) throw new UserErrorException(userError);
        if (inputs[1] > 1.) throw new UserErrorException(userError);
        check2 = true;  
      }
      catch (InputMismatchException inputThrow1) {
        myScan.nextLine();
        System.out.println("Try again and enter a positive numerical value between 1 and 0."); 
      }
      catch (UserErrorException inputThrow1) {
        myScan.nextLine();
        System.out.println(inputThrow1.getMessage());
      } 
    }
    
    while(!check3) {
      try {   
        System.out.println("Please input a starting liquid inlet flow rate");
        inputs[2] = myScan.nextDouble();
        //inputs[2] = 40;
        if (inputs[2] <= 0.) throw new UserErrorException(userError);
        check3 = true;  
      }
      catch (InputMismatchException inputThrow1) {
        myScan.nextLine();
        System.out.println("Try again and enter a positive numerical value.");
      }
      catch (UserErrorException inputThrow1) {
        myScan.nextLine();
        System.out.println(inputThrow1.getMessage());
      } 
    }
     
    while(!check4) {
      try {   
        System.out.println("Please input a starting liquid mole fraction");
        inputs[3] = myScan.nextDouble();
        //inputs[3] = 0;
        if (inputs[3] < 0.) throw new UserErrorException(userError);
        if (inputs[3] > 1.) throw new UserErrorException(userError);
        check4 = true;  
      }
      catch (InputMismatchException inputThrow1) {
        myScan.nextLine();
        System.out.println("Try again and enter a positive numerical value between 1 and 0.");
      }
      catch (UserErrorException inputThrow1) {
        myScan.nextLine();
        System.out.println(inputThrow1.getMessage());
      } 
    }
    
    while(!check5) {
      try {   
        System.out.println("Please input a recovery value in decimal form");
        inputs[4] = myScan.nextDouble();
        //inputs[4] = 0.92;
        if (inputs[4] < 0.) throw new UserErrorException(userError);
        if (inputs[4] > 1.) throw new UserErrorException(userError);
        check5 = true;  
      }
      catch (InputMismatchException inputThrow1) {
        myScan.nextLine();
        System.out.println("Try again and enter a positive numerical value between 1 and 0.");
      }
      catch (UserErrorException inputThrow1) {
        myScan.nextLine();
        System.out.println(inputThrow1.getMessage());
      } 
    }
    //Temperature input
    inputs[5] = 25.0;
    //Iteration input
    inputs[6] = 1000;
    
    myScan.nextLine();
    
    boolean checkPack = false;
    System.out.println("Please input a packing type ('berl', 'rashig', 'pall')");
    
    while(!checkPack){
      try{
        String packing = myScan.nextLine();
        if(packing.equals("berl") || packing.equals("rashig") || packing.equals("pall")){
          myData.setPackingType(packing);
          checkPack = true;
        }
        else{
          throw new Exception();
        }
      }
      catch(Exception e){
        System.out.println("Not a valid packing type - please try again");
      }
    }
    myData.setSC(inputs);
  }
}
