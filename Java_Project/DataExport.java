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
      StringBuilder xalString = new StringBuilder();
      StringBuilder yagString = new StringBuilder();
      StringBuilder xaiString = new StringBuilder();
      StringBuilder yaiString = new StringBuilder();
      
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
      
      //XAL
      xalString.append("xal values:,");
      for(Double element : dataColumn.getXAL()){
        xalString.append(element);
        xalString.append(",");
      }
      xalString.append("\n");
      br.write(xalString.toString());
      
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
      
      br.close();
    }
    catch(IOException e){
      System.out.println("Gotcha ya");
    }
  }
}
  
  