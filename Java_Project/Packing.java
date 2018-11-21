//No need for an abstract class here as it's only a difference in values and not methods between packings

public class Packing{
  
  public static final double nominalSize = 0.025; //[m]
  private double voidFraction, aPacking, fPacking, fpPacking, chPacking, colDiameterHeuristic;
  
  Packing(String type){
    this.setPackingType(type);
  }
  
  public void setPackingType(String type) {
   
    if(type.equals("rashig")){
      voidFraction = 0.74;
      aPacking = 190;
      fPacking = 587;
      fpPacking = 1.246;
      chPacking = 0.577;
      colDiameterHeuristic = 30;
    }
    
    else if(type.equals("berl")){
      voidFraction = 0.68;
      aPacking = 260;
      fPacking = 361;
      fpPacking = 1.361;
      chPacking = 0.62;
      colDiameterHeuristic = 15;
    }
    
    else if(type.equals("pall")){
      voidFraction = 0.94;
      aPacking = 225;
      fPacking = 180;
      fpPacking = 0.905;
      chPacking = 0.528;
      colDiameterHeuristic = 10;
    }
    
    else{System.out.println("Not a valid packing type");
      
    }
  }
  //Copy constructor
  public Packing(Packing source){
    this.voidFraction = source.voidFraction;
    this.aPacking = source.aPacking;
    this.fPacking = source.fPacking;
    this.fpPacking = source.fpPacking;
    this.chPacking = source.chPacking;
    this.colDiameterHeuristic = colDiameterHeuristic;
  }
  //Accessors
  public double getVoidFraction(){
    return this.voidFraction;
  }
  public double getAPacking(){
    return this.aPacking;
  }
  public double getFPacking(){
    return this.fPacking;
  }
    public double getFPPacking(){
    return this.fpPacking;
  }
  public double getChPacking(){
    return this.chPacking;
  }
  public double getColDiameterHeuristic(){
    return this.colDiameterHeuristic;
  }
  //Mutators
  public void setVoidFraction(double voidFraction){
    this.voidFraction = voidFraction;
  }
  public void setAPacking(double aPacking){
    this.aPacking = aPacking;
  }
  public void getFPacking(double fPacking){
    this.fPacking = fPacking;
  }
    public void setFPPacking(double fpPacking){
    this.fpPacking = fpPacking;
  }
  public void setChPacking(double chPacking){
    this.chPacking = chPacking;
  }
  public void setColDiameterHeuristic(double colDiameterHeuristic){
    this.colDiameterHeuristic = colDiameterHeuristic;
  }
}