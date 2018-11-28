public class InputData{
  
  /*Class: Input Data
   * 
   * The input data class is responsible for carrying information throughout the program
   * It has varibales which include the packing type, system constants and equlibData
   * It also carries data about if the user would like to optimize, or if data is from files.]
   * Essentially, this is the memory of the whole program, but has no functionality without other classes
   * 
   */ 
  
  private String packingType;
  private double[] systemConstants;
  private double[] equlibData;
  public boolean optimize = true;
  public boolean dataFromFiles;
  
  public void setFromFiles(boolean set){dataFromFiles=set;}
  
  public void setSC(double[] systemConstants){
    this.systemConstants = systemConstants;
  }
  
  public void setED(double[] equlibData){
    this.equlibData = equlibData;
  }
  
  public void setPackingType(String packingType){
    this.packingType = packingType;
  }
  
  public double[] getSC(){
    return systemConstants;
  }
  
  public double[] getED(){
    return equlibData;
  }
  
  public String getPackingType(){
    return packingType;
  }
}
