import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class ResultsScreen{
  
  double height, flow;
  boolean exportV;
  InputData systemData;
  
  ResultsScreen(double height, double flow, InputData systemData){
    this.height = height;
    this.flow = flow;
    this.systemData = systemData;
    
    JFrame guiFrame = new JFrame();
    
    guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    guiFrame.setTitle("Results!");
    guiFrame.setSize(350,125);
    
    guiFrame.setLocationRelativeTo(null);
    GridLayout layout = new GridLayout(3, 1);
    guiFrame.setLayout(layout);
    
//The first JPanel contains a JLabel and JCombobox
    final JPanel resultsPanel = new JPanel();
    final JPanel titlePanel = new JPanel();
    final JPanel buttonPanel = new JPanel();
    
    resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
    
    JLabel titleText = new JLabel("Results from the calculation:");
    titlePanel.add(titleText);
    
    DecimalFormat df = new DecimalFormat("#######.###");
    
    JLabel heightText = new JLabel("Height is found to be " + df.format(height) + " meters");
    JLabel flowText;
    if(systemData.optimize==true){flowText = new JLabel("Flow is found to be " + df.format(flow) + " Kmol/h");}
    else{flowText = new JLabel("");}
    heightText.setAlignmentX(Component.CENTER_ALIGNMENT);
    flowText.setAlignmentX(Component.CENTER_ALIGNMENT);
    resultsPanel.add(heightText);
    resultsPanel.add(flowText);
    
    JButton export = new JButton("Export Results");
    buttonPanel.add(export);
    
    JButton dontExport = new JButton("Don't Export");
    buttonPanel.add(dontExport);
    
    
    guiFrame.add(titlePanel);
    guiFrame.add(resultsPanel);
    guiFrame.add(buttonPanel);
    
//make sure the JFrame is visible
    guiFrame.setVisible(true);
    
    
    //BUTTONS
    
    export.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent event)
      {
        //System.out.print("EXPORT");
        guiFrame.setVisible(false);
        exportV = true;
        finishScreen();
      }
    });
    
    dontExport.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent event)
      {
        //System.out.print("DONT EXPORT");
        guiFrame.setVisible(false);
        exportV = false;
        finishScreen();
      }
    });
  }
  
  public void finishScreen(){
    
    String didExport = "";
    if(exportV == true){didExport = "Data has been exported to CSV";}
    else{didExport = "Data was not exported";}
    
    JFrame guiFrame = new JFrame();
    
    guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    guiFrame.setTitle("Thanks for using Column Calculator");
    guiFrame.setSize(350,125);
    
    guiFrame.setLocationRelativeTo(null);
    GridLayout layout = new GridLayout(3, 1);
    guiFrame.setLayout(layout);
    
//The first JPanel contains a JLabel and JCombobox
    final JPanel resultsPanel = new JPanel();
    final JPanel titlePanel = new JPanel();
    final JPanel buttonPanel = new JPanel();
    
    resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
    
    JLabel titleText = new JLabel("Thanks for using our Column Calculator!");
    titlePanel.add(titleText);
    
    JLabel exportText = new JLabel(didExport);
    exportText.setAlignmentX(Component.CENTER_ALIGNMENT);
    resultsPanel.add(exportText);
    
    JButton done = new JButton("Done");
    buttonPanel.add(done);
    
    
    guiFrame.add(titlePanel);
    guiFrame.add(resultsPanel);
    guiFrame.add(buttonPanel);
    
//make sure the JFrame is visible
    guiFrame.setVisible(true);
    
    
    //BUTTONS
    
    done.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent event)
      {
        System.exit(0);
      }
    });
    
  }
    
}