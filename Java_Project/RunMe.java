import java.util.Scanner;
import java.io.*;
import java.util.InputMismatchException;
import java.lang.*;
import java.text.DecimalFormatSymbols;

public class RunMe{
  
  /* Class: RunMe
   * 
   * This is the Mack Daddy of all classes
   * It contains the main method
   * It is responsible for creating all instances of objects (including Input Data, and AbCol)
   * It also sets the Input Data variables, before passing it to the required classes to be used for calculation
   * This is the "brains" of the operation
   * 
   */
  
  public static void main(String[] args){
    
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
      
      InputData systemData = new InputData();
      Scanner myScan = new Scanner(System.in);
      
      System.out.println("Welcome to column calculator.");
      
      //GETTING INPUTS
      String dataType; //The input for the method used to get inputs
      String packType = "";
      
      boolean flag = false;
      
      while(!flag){
        
        System.out.println("Would you like to input conditions through a file ('f') or by input ('i')?");
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
      
      Fluid fluid;
      double d_AB_L = 0, d_AB_V = 0,mu_L = 0,mu_V = 0,rho_L = 0,rho_V = 0,mw_A = 0,mw_L = 0,mw_V = 0; 
      while(true){
        try{
          //Allows the user to input fluid values or use the default parameters
          System.out.println("Would you like to use the default fluid parameters (0) or input a different set (1)?");
          int defaultOpt = myScan.nextInt();
          if(defaultOpt == 1){
            String userError = ""; // for UserErrorException message
            boolean check1 = false;
            boolean check2 = false;
            boolean check3 = false;
            boolean check4 = false;
            boolean check5 = false;
            boolean check6 = false;
            boolean check7 = false;
            boolean check8 = false;
            boolean check9 = false;
            while(!check1) {
              try { 
                System.out.println("Please input the diffusion coefficient of the liquid in m^2/s.");
                d_AB_L = myScan.nextDouble();
                if (d_AB_L <= 0.) throw new UserErrorException(userError);
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
                System.out.println("Please input the diffusion coefficient of the vapour in m^2/s.");
                d_AB_V = myScan.nextDouble();
                if (d_AB_V <= 0.) throw new UserErrorException(userError);
                check2 = true;
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
            while(!check3) {
              try { 
                System.out.println("Please input the viscosity of the liquid in Pa-s.");
                mu_L = myScan.nextDouble();
                if (mu_L <= 0.) throw new UserErrorException(userError);
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
                System.out.println("Please input the viscosity of the vapour in Pa-s.");
                mu_V = myScan.nextDouble();
                if (mu_V <= 0.) throw new UserErrorException(userError);
                check4 = true;
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
            while(!check5) {
              try { 
                System.out.println("Please input the density of the liquid in kg/m^3.");
                rho_L = myScan.nextDouble();
                if (rho_L <= 0.) throw new UserErrorException(userError);
                check5 = true;
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
            while(!check6) {
              try { 
                System.out.println("Please input the density of the vapour in kg/m^3.");
                rho_V = myScan.nextDouble();
                if (rho_V <= 0.) throw new UserErrorException(userError);
                check6 = true;
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
            while(!check7) {
              try { 
                System.out.println("Please input the molar weight of the liquid in g/mol.");
                mw_L = myScan.nextDouble();
                if (mw_L <= 0.) throw new UserErrorException(userError);
                check7 = true;
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
            while(!check8) {
              try { 
                System.out.println("Please input the molar weight of the vapour in g/mol.");
                mw_V = myScan.nextDouble();
                if (mw_V <= 0.) throw new UserErrorException(userError);
                check8 = true;
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
            while(!check9) {
              try { 
                System.out.println("Please input the average molar weight in g/mol.");
                mw_A = myScan.nextDouble();
                if (mw_A <= 0.) throw new UserErrorException(userError);
                check9 = true;
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
            fluid = new Fluid(d_AB_L,d_AB_V,mu_L,rho_L,mu_V,rho_V,mw_A,mw_L,mw_V);
            break;
          }
          if(defaultOpt == 0){
            fluid = new Fluid();
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
        
      Packing pack = new Packing(systemData.getPackingType());
      AbsorptionColumn myColumn = new AbsorptionColumn(pack, fluid, systemData);
      //Determine whether or not the parameters resulted in a functioning column
      boolean z = Double.isNaN(myColumn.getZ());
      if(z==false){
        System.out.printf("The outlet liquid flow rate of the column is %.2f kmol/h.\n",myColumn.getL1());
        System.out.printf("The inlet liquid flow rate of the column is %.2f kmol/h.\n",myColumn.getL2());
        System.out.printf("The inlet vapour flow rate of the column is %.2f kmol/h.\n",myColumn.getV1());
        System.out.printf("The outlet vapour flow rate of the column is %.2f kmol/h.\n",myColumn.getL2());
        System.out.printf("The outlet liquid mole fraction of the column is %.2f.\n",myColumn.getXA1());
        System.out.printf("The inlet liquid mole fraction of the column is %.2f.\n",myColumn.getXA2());
        System.out.printf("The inlet vapour mole fraction of the column is %.2f.\n",myColumn.getYA1());
        System.out.printf("The outlet vapour mole fraction of the column is %.2f.\n",myColumn.getYA2());
        System.out.printf("The height of the column is %.2f m.\n",myColumn.getZ());
        if(myColumn.getOptL()!=0){
          System.out.printf("The optimal liquid flow rate through the column for optimization of mass transfer is %.2f kmol/h.\n",myColumn.getOptL());
        }
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
      }
      else System.out.println("These parameters do not result in a functioning column.");
      //Exporting Data
      
      System.out.println("Thank you for using column calculator.");
    }
  
  public static void valuesFromFile(Scanner myScan, InputData myData){
    
    double[] inputs = new double[7];
    String string = "";
    int num = 0;
    
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
        if(isStringNumeric(valueString[1])==false){
          System.out.println("One or more of the file inputs is incorrect.");
          System.out.println("Enter anything into the scanner to exit.");
          Scanner exit = new Scanner(System.in);
          String m = exit.nextLine();
          System.exit(0);
        }
          
        inputs[i] = new Double(valueString[1]).doubleValue();
        System.out.println(valueString[0] + " = " + inputs[i]);
        i++;
      }
      inputs[5] = 25;
      inputs[6] = 1000;
      
      String pack = fileInput.nextLine().split("=")[1];
      if(pack.equals("berl")==false&&pack.equals("pall")==false&&pack.equals("rashig")==false){
        System.out.println("This is not an acceptable packing type.");
        System.out.println("Enter anything into the scanner to exit.");
        Scanner exit = new Scanner(System.in);
        String m = exit.nextLine();
        System.exit(0);
      }
      myData.setPackingType(pack);
      System.out.println("The packing type is " + myData.getPackingType());
      
    }
    catch(FileNotFoundException e){
      System.out.println("Not a valid file name");
      valuesFromFile(myScan, myData);
    }
    if(inputs[0]<0||inputs[1]<0||inputs[1]>1||inputs[2]<0||inputs[3]<0||inputs[3]>1||inputs[4]<0||inputs[4]>1){
      System.out.println("One or more of your file inputs do not meet the require parameters.");
      System.out.println("Enter anything into the scanner to exit.");
      Scanner exit = new Scanner(System.in);
      String m = exit.nextLine();
      System.exit(0);
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
        System.out.println("Please input a starting gas inlet flow rate in kmol/h.");
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
        System.out.println("Please input a starting gas mole fraction as a decimal between 0 and 1.");
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
        System.out.println("Please input a starting liquid inlet flow rate in kmol/h.");
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
        System.out.println("Please input a starting liquid mole fraction as a decimal between 0 and 1.");
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
        System.out.println("Please input a recovery value as a decimal between 0 and 1.");
        inputs[4] = myScan.nextDouble();
        //inputs[4] = 0.92;
        if (inputs[4] < 0.) throw new UserErrorException(userError);
        if (inputs[4] > 1.) throw new UserErrorException(userError);
        check5 = true;  
      }
      catch (InputMismatchException inputThrow1) {
        myScan.nextLine();
        System.out.println("Try again and enter a positive numerical value between 0 and 1.");
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
  
  public static boolean isStringNumeric( String source )
  {
    DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
    char negative = symbols.getMinusSign();
    
    if ( !Character.isDigit( source.charAt( 0 ) ) && source.charAt( 0 ) != negative ) return false;
    
    boolean decimalFound = false;
    char decimal = symbols.getDecimalSeparator();
    
    for ( char c : source.substring( 1 ).toCharArray() )
    {
      if ( !Character.isDigit( c ) )
      {
        if ( c == decimal && !decimalFound )
        {
          decimalFound = true;
          continue;
        }
        return false;
      }
    }
    return true;
  }
}
