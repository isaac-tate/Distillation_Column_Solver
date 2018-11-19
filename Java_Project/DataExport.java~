import java.lang.*;
import java.io.*;

public class DataExport{
  
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
      for(Double element : dataColumn.dzv){
        dzvString.append(element);
        dzvString.append(",");
      }
      dzvString.append("\n");
      br.write(dzvString.toString());
      
      //DZL
      dzlString.append("dzl values:,");
      for(Double element : dataColumn.dzl){
        dzlString.append(element);
        dzlString.append(",");
      }
      dzlString.append("\n");
      br.write(dzlString.toString());
      
      //XAL
      xalString.append("xal values:,");
      for(Double element : dataColumn.xal){
        xalString.append(element);
        xalString.append(",");
      }
      xalString.append("\n");
      br.write(xalString.toString());
      
      //YAG
      yagString.append("yag values:,");
      for(Double element : dataColumn.yag){
        yagString.append(element);
        yagString.append(",");
      }
      yagString.append("\n");
      br.write(yagString.toString());
      
      //XAI
      xaiString.append("xai values:,");
      for(Double element : dataColumn.xai){
        xaiString.append(element);
        xaiString.append(",");
      }
      xaiString.append("\n");
      br.write(xaiString.toString());
      
      //YAI
      yaiString.append("yai values:,");
      for(Double element : dataColumn.yai){
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
  
  