import java.lang.*;
import java.io.*;

public class DataExport{
  
  /*Class: Data Export
   * 
   * The Data Export class is responsible for exporting the absorption column data to a cvs (comma seperated value) file
   * The imported StringBuilder is used to build a string of all the values we would like to export
   * This string is then saved to a file using the imported FileWriter to "outputData.cvs"
   * Commas are added between each value to complie with csv file format
   * 
   */
  
  AbsorptionColumn dataColumn;
  
  DataExport(AbsorptionColumn dataColumn){
    this.dataColumn = dataColumn;
    exportToExcel();
  }
  
  public void exportToExcel(){
    
    //xal, yag, xai, yai
    
    try{
      BufferedWriter br = new BufferedWriter(new FileWriter("outputData.csv"));
      StringBuilder dzvString = new StringBuilder();
      StringBuilder dzlString = new StringBuilder();
      StringBuilder l1String = new StringBuilder();
      StringBuilder l2String = new StringBuilder();
      StringBuilder v1String = new StringBuilder();
      StringBuilder v2String = new StringBuilder();
      StringBuilder x1String = new StringBuilder();
      StringBuilder x2String = new StringBuilder();
      StringBuilder y1String = new StringBuilder();
      StringBuilder y2String = new StringBuilder();
      StringBuilder zvString = new StringBuilder();
      StringBuilder zlString = new StringBuilder();
      StringBuilder xalString = new StringBuilder();
      StringBuilder yagString = new StringBuilder();
      StringBuilder xaiString = new StringBuilder();
      StringBuilder yaiString = new StringBuilder();
      
      //L1
      l1String.append("L1 value:,");
      l1String.append(dataColumn.getL1());
      l1String.append(",");
      l1String.append("\n");
      br.write(l1String.toString());
      
      //L2
      l2String.append("L2 value:,");
      l2String.append(dataColumn.getL2());
      l2String.append(",");
      l2String.append("\n");
      br.write(l2String.toString());
      
      //V1
      v1String.append("V1 value:,");
      v1String.append(dataColumn.getV1());
      v1String.append(",");
      v1String.append("\n");
      br.write(v1String.toString());
      
      //V2
      v2String.append("V2 value:,");
      v2String.append(dataColumn.getV2());
      v2String.append(",");
      v2String.append("\n");
      br.write(v2String.toString());
      
      //XA1
      x1String.append("XA1 value:,");
      x1String.append(dataColumn.getXA1());
      x1String.append(",");
      x1String.append("\n");
      br.write(x1String.toString());
      
      //XA2
      x2String.append("XA2 value:,");
      x2String.append(dataColumn.getXA2());
      x2String.append(",");
      x2String.append("\n");
      br.write(x2String.toString());
      
      //YA1
      y1String.append("YA1 value:,");
      y1String.append(dataColumn.getYA1());
      y1String.append(",");
      y1String.append("\n");
      br.write(y1String.toString());
      
      //YA2
      y2String.append("YA2 value:,");
      y2String.append(dataColumn.getYA2());
      y2String.append(",");
      y2String.append("\n");
      br.write(y2String.toString());
      
      //ZLArray
      zlString.append("zl values:,");
      for(Double element : dataColumn.getZLArray()){
        zlString.append(element);
        zlString.append(",");
      }
      zlString.append("\n");
      br.write(zlString.toString());
      
      //XAL
      xalString.append("xal values:,");
      for(Double element : dataColumn.getXAL()){
        xalString.append(element);
        xalString.append(",");
      }
      xalString.append("\n");
      br.write(xalString.toString());
      
      //ZVArray
      zvString.append("zv values:,");
      for(Double element : dataColumn.getZVArray()){
        zvString.append(element);
        zvString.append(",");
      }
      zvString.append("\n");
      br.write(zvString.toString());
      
      //YAG
      yagString.append("yag values:,");
      for(Double element : dataColumn.getYAG()){
        yagString.append(element);
        yagString.append(",");
      }
      yagString.append("\n");
      br.write(yagString.toString());
      
      //XAI
      xaiString.append("xai values:,");
      for(Double element : dataColumn.getXAI()){
        xaiString.append(element);
        xaiString.append(",");
      }
      xaiString.append("\n");
      br.write(xaiString.toString());
      
      //YAI
      yaiString.append("yai values:,");
      for(Double element : dataColumn.getYAI()){
        yaiString.append(element);
        yaiString.append(",");
      }
      yaiString.append("\n");
      br.write(yaiString.toString());
      
      //DZV
      dzvString.append("dzv values:,");
      for(Double element : dataColumn.getDZV()){
        dzvString.append(element);
        dzvString.append(",");
      }
      dzvString.append("\n");
      br.write(dzvString.toString());
      
      //DZL
      dzlString.append("dzl values:,");
      for(Double element : dataColumn.getDZL()){
        dzlString.append(element);
        dzlString.append(",");
      }
      dzlString.append("\n");
      br.write(dzlString.toString());
    
      br.close();
    }
    catch(IOException e){
      System.out.println("Gotcha ya");
    }
  }
}
  
  