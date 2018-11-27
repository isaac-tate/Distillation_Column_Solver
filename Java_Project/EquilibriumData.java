import java.util.*;
import java.io.*;
import java.util.InputMismatchException;

/*CLass: EquilibriumData
 * 
 * The EquilibriumData class holds an array of double values that represent an equilibrium equation
 * 
 * The user may enter this data either through a file or through manual input.
 * If manual input is chosen the user is prompted to enter the highest coefficient in the equilibrium equation
 * The user is then prompter to enter the coefficients of the equation in increasing order, not neglecting zeros
 * 
 * A method exists within this class that, given an x value, returns the equivalent y value based on the equilibrium data
 * 
 */

public class EquilibriumData{
  
  //Equilibrium equation coefficients
  private double [] eqdata;
  Scanner myScan = new Scanner(System.in);
  
  public EquilibriumData(InputData data){
    
    String answer_outside;
    
    //If the GUI is used, the eqdata is taken directly from the GuiApp
    if(data.useGUI == true){
      eqdata = data.getED();
    }
    
    else{
      
      //Determine whether data is input from a file or an input
      while(true){
        System.out.println("Would like to input equilibrium data through a file ('f') or by input ('i')?");
        String answer = myScan.nextLine();
        if(answer.equals("f") || answer.equals("i")){
          answer_outside = answer;
          break;
        }
        else{
          System.out.println("This is not a valid input");
        }
      }
      
      switch(answer_outside){
        case "f":
          fileInputs();
          break;
        case "i":
          manualInputs();
          break;
      }
    }
  }
  //Copy constructor
  public EquilibriumData(EquilibriumData source){
    this.eqdata = new double[source.eqdata.length];
    for(int i = 0;i<source.eqdata.length;i++){
      this.eqdata[i] = source.eqdata[i];
    }
  }
  //Clone
  public EquilibriumData clone(){
    return new EquilibriumData(this);
  }
  //Accessor 
  public double [] getEqdata(){
    return this.eqdata;
  }
  //Mutator
  public void setEqdata(double [] eqdata){
    this.eqdata = new double[eqdata.length];
    for(int i = 0;i<eqdata.length;i++){
      this.eqdata[i] = eqdata[i];
    }
  }
  //Returns equivalent y for a given x based on equilibrium data       
  public double equilibriumDataY(double x){
    double y = 0;
    for(int i = 0;i<this.eqdata.length;i++){
      y = y+Math.pow(x,i)*this.eqdata[i];
    }
    return y;
  }
  
  //Manual inputs method
  public void manualInputs(){
    
    boolean track = false;
    int j = 0;
    
    //Prompts the user to enter the highest power value within the equilibrium equation
    try {
      System.out.println("Enter the highest power of x within the equilibrium data.");
      j = myScan.nextInt();
      track = false;
    }
    
    catch (InputMismatchException equilcheck1) {
      myScan.nextLine();
      System.out.println("Invalid. Please enter a numerical value.");
      j = myScan.nextInt();
    }
    
    while(j<0){
      System.out.println("That is not a valid power, please reenter a positive integer or exit the program.");
      j = myScan.nextInt();
    }
    
    //Takes in the equilibrium coefficients from user input
    this.eqdata = new double[j+1];
    System.out.println("Enter the coefficients of the equilibrium equation in increasing order.");
    for(int i = 0;i<j+1;i++){
      try {
      System.out.println("Enter the coefficient of x^"+i+".");
      this.eqdata[i] = myScan.nextDouble();
      track = false;
      }
      
      catch (InputMismatchException equilcheck2) {
        myScan.nextLine();
        System.out.println("Invalid. Please enter a numerical value.");
        this.eqdata[i] = myScan.nextDouble();
      }
    }
  }
  
  //File inputs method
  public void fileInputs(){
    
    ArrayList<String> valueList = new ArrayList<String>();
    
    System.out.println("Please input a file name (leave blank for default: 'equilibrium.txt')");
    String fileName = myScan.nextLine();
    
    if(fileName.isEmpty()){
      System.out.println("-- Using default --");
      fileName = "equilibrium.txt";
    }
    
    try{
      File file = new File(fileName);
      Scanner fileInput = new Scanner(file);
      
      while(fileInput.hasNext()){
        String[] thisValue = fileInput.nextLine().split("=");
        valueList.add(thisValue[1]);
        System.out.println(thisValue[0] + " = " + thisValue[1]);
      }
    }
    catch(FileNotFoundException e){
      System.out.println("Not a valid file name");
      fileInputs();
    }
    
    eqdata = new double[valueList.size()];
    
    for(int i = 0; i<valueList.size(); i++){
      eqdata[i] = new Double(valueList.get(i)).doubleValue();
    }                                      
  }
}