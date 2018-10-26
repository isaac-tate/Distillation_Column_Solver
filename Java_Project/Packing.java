public class Packing{
  
  double nominalSize = 0.025; //[m]
  double voidFraction, aPacking, fPacking, fpPacking, chPacking, colDiamterHeuristic;
  
  Packing(String type){
    this.setPackingType(type);
  }
  
  public void setPackingType(String type){
    
    if(type.equals("rashig")){
      voidFraction = 0.74;
      aPacking = 190;
      fPacking = 587;
      fpPacking = 1.246;
      chPacking = 0.577;
      colDiamterHeuristic = 30;
    }
    
    else if(type.equals("berl")){
      voidFraction = 0.68;
      aPacking = 260;
      fPacking = 361;
      fpPacking = 1.361;
      chPacking = 0.62;
      colDiamterHeuristic = 15;
    }
    
    else if(type.equals("pall")){
      voidFraction = 0.94;
      aPacking = 225;
      fPacking = 180;
      fpPacking = 0.905;
      chPacking = 0.528;
      colDiamterHeuristic = 10;
    } 
    
    else{System.out.println("Not a valid packing type");
      
    }
  }
}