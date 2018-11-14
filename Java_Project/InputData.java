public class InputData{
  
  private String packingType;
  private double[] systemConstants;
  private double[] equlibData;
  
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
