import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResultsScreen{
  
  double height, flow;
  boolean exportV;
  
  ResultsScreen(double height, double flow){
    this.height = height;
    this.flow = flow;
    
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
    
    JLabel titleText = new JLabel("Results from the calulation:");
    titlePanel.add(titleText);
    
    JLabel heightText = new JLabel("Height is found to be " + height + " meters");
    JLabel flowText = new JLabel("Flow is found to be " + flow + " Kmol/h");
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
      }
    });
    
    dontExport.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent event)
      {
        //System.out.print("DONT EXPORT");
        guiFrame.setVisible(false);
        exportV = false;
      }
    });
  }
  
}