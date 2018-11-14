import java.util.*;
import java.io.*;

public class EquilibriumData{
  
  private double [] eqdata;
  Scanner myScan = new Scanner(System.in);
  
  public EquilibriumData(){
    
    String answer_outside;
    
    while(true){
      System.out.println("How would like to input equilibrium data through a file ('f') or by input ('i')");
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
  //Copy constructor
  public EquilibriumData(EquilibriumData source){
    this.eqdata = new double[source.eqdata.length];
    for(int i = 0;i<source.eqdata.length;i++){
      this.eqdata[i] = source.eqdata[i];
    }
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
    
    System.out.println("Enter the highest power of x within the equilibrium data.");
    int j = myScan.nextInt();
    while(j<0){
      System.out.println("That is not a valid power, please reenter a positive integer or exit the program.");
      j = myScan.nextInt();
    }
    //takes in the equilibrium coefficients from user input
    this.eqdata = new double[j+1];
    System.out.println("Enter the coefficients of the equilibrium equation in increasing order.");
    for(int i = 0;i<j+1;i++){
      System.out.println("Enter the coefficient of x^"+i+".");
      this.eqdata[i] = myScan.nextDouble();
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