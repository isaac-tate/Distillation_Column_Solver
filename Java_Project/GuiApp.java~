//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import java.awt.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;



public class GuiApp{
  
  public boolean fromFiles;
  public boolean dataStored = false;
  String[] packingTypes = {"Rashig", "Berl", "Pall"};
  InputData data = new InputData();
  
  //Textboxes
  JTextField in1Field = new JTextField();
  JTextField in2Field = new JTextField();
  JTextField in3Field = new JTextField();
  JTextField in4Field = new JTextField();
  JTextField in5Field = new JTextField();
  JTextField in6Field = new JTextField();
  JTextField in7Field = new JTextField();
  JTextField eq0Field = new JTextField();
  JTextField eq1Field = new JTextField();
  JTextField eq2Field = new JTextField();
  JTextField eq3Field = new JTextField();
  JTextField eq4Field = new JTextField();
  JTextField eq5Field = new JTextField();
  JTextField eq6Field = new JTextField();
  JComboBox<String> in8Field = new JComboBox<>(packingTypes);

  public GuiApp()
  {
    JFrame guiFrame = new JFrame();
    
    guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    guiFrame.setTitle("Column Calculator");
    guiFrame.setSize(350,150);
    
    guiFrame.setLocationRelativeTo(null);
    
//The first JPanel contains a JLabel and JCombobox
    final JPanel startingPanel = new JPanel();
    JLabel welcomeText = new JLabel("Welcome to Column Calculator! \n");
    startingPanel.add(welcomeText);
    
    final JPanel authorPanel = new JPanel();
    JLabel authorText = new JLabel("Created by: Samantha, Isaac, Kaitlin, Curtis and Chris");
    JCheckBox opFile = new JCheckBox("Optimize existing file input?");
    authorPanel.add(authorText);
    authorPanel.add(opFile);
    
    final JPanel buttonPanel = new JPanel();
    JButton startFileButton = new JButton("Use existing files");
    JButton startInputButton = new JButton("Use user input");

    buttonPanel.add(startInputButton);
    buttonPanel.add(startFileButton);
    
//Create the second JPanel. Add a JLabel and JList and
//make use the JPanel is not visible.
    
    guiFrame.add(startingPanel, BorderLayout.NORTH);
    guiFrame.add(authorPanel);
    guiFrame.add(buttonPanel, BorderLayout.SOUTH);
    
//make sure the JFrame is visible
    guiFrame.setVisible(true);
    
    //BUTTONS
    
    startInputButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent event)
      {
        fromFiles = false;
        guiFrame.setVisible(false);
        userInput();
      }
    });
    
    startFileButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent event)
      {
        fromFiles = true;
        data.optimize = opFile.isSelected();
        guiFrame.setVisible(false);
        storeDataFromFile();
      }
    });
  }
  
  public void userInput(){
    JFrame inputFrame = new JFrame();
    inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    inputFrame.setTitle("User Inputs");
    inputFrame.setSize(550,400);
    GridLayout layout = new GridLayout(1, 2);
    inputFrame.setLayout(layout);
    inputFrame.setLocationRelativeTo(null);
    
    final JPanel systemPanel = new JPanel();
    systemPanel.setLayout(new BoxLayout(systemPanel, BoxLayout.Y_AXIS));
    //systemPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    final JPanel equilibriumPanel = new JPanel();
    equilibriumPanel.setLayout(new BoxLayout(equilibriumPanel, BoxLayout.Y_AXIS));
    
    final JPanel buttonPanel = new JPanel();
    
    //System panel
    JLabel inTitle = new JLabel("System Inputs");
    inTitle.setFont(new Font("Serif", Font.PLAIN, 18));
    
    JLabel blank = new JLabel(" ");
    JLabel in1Text = new JLabel("Inlet gas flow rate (kmol/h)");  
    JLabel in2Text = new JLabel("Starting gas mole fraction");
    JLabel in3Text = new JLabel("Starting liquid inlet flow rate (kmol/h)");
    JLabel in4Text = new JLabel("Starting liquid mole fraction");
    JLabel in5Text = new JLabel("Recovery value as a decimal");

    JLabel in8Text = new JLabel("Packing Type");
    
    in1Text.setAlignmentX(Component.CENTER_ALIGNMENT);
    in2Text.setAlignmentX(Component.CENTER_ALIGNMENT);
    in3Text.setAlignmentX(Component.CENTER_ALIGNMENT);
    in4Text.setAlignmentX(Component.CENTER_ALIGNMENT);
    in5Text.setAlignmentX(Component.CENTER_ALIGNMENT);
    in8Text.setAlignmentX(Component.CENTER_ALIGNMENT);
    inTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

    systemPanel.add(inTitle);
    systemPanel.add(blank);
    systemPanel.add(in1Text);
    systemPanel.add(in1Field);
    systemPanel.add(in2Text);
    systemPanel.add(in2Field);
    systemPanel.add(in3Text);
    systemPanel.add(in3Field);
    systemPanel.add(in4Text);
    systemPanel.add(in4Field);
    systemPanel.add(in5Text);
    systemPanel.add(in5Field);
    systemPanel.add(in8Text);
    systemPanel.add(in8Field);
    
    inputFrame.add(systemPanel);

    //EQ Panel

    JLabel eqTitle = new JLabel("Equilibrium Inputs (Including 0's)");
    eqTitle.setFont(new Font("Serif", Font.PLAIN, 18));
    JLabel eqblank = new JLabel(" ");
    JLabel in0Equ = new JLabel("Coefficient of x^0"); 
    JLabel in1Equ = new JLabel("Coefficient of x^1");  
    JLabel in2Equ = new JLabel("Coefficient of x^2");
    JLabel in3Equ = new JLabel("Coefficient of x^3");
    JLabel in4Equ = new JLabel("Coefficient of x^4");

    
    in0Equ.setAlignmentX(Component.CENTER_ALIGNMENT);
    in1Equ.setAlignmentX(Component.CENTER_ALIGNMENT);
    in2Equ.setAlignmentX(Component.CENTER_ALIGNMENT);
    in3Equ.setAlignmentX(Component.CENTER_ALIGNMENT);
    in4Equ.setAlignmentX(Component.CENTER_ALIGNMENT);
    eqTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    eqblank.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    
    equilibriumPanel.add(eqTitle);
    equilibriumPanel.add(eqblank);
    equilibriumPanel.add(in0Equ);
    equilibriumPanel.add(eq0Field);
    equilibriumPanel.add(in1Equ);
    equilibriumPanel.add(eq1Field);
    equilibriumPanel.add(in2Equ);
    equilibriumPanel.add(eq2Field);
    equilibriumPanel.add(in3Equ);
    equilibriumPanel.add(eq3Field);
    equilibriumPanel.add(in4Equ);
    equilibriumPanel.add(eq4Field);
   

    JButton noOpButton = new JButton("Do not optimize");
    noOpButton.setForeground(Color.red);
    buttonPanel.add(noOpButton);
    
    JButton opButton = new JButton("Optimize");
    Color DARK_GREEN = new Color(0,102,0);
    opButton.setForeground(DARK_GREEN);
    buttonPanel.add(opButton);
    
    noOpButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent event)
      {
        data.optimize = false;
        storeData();
        if(dataStored==true){inputFrame.setVisible(false);}
      }
    });
    
    opButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent event)
      {
        data.optimize = true;
        storeData();
        if(dataStored==true){inputFrame.setVisible(false);}
      }
    });
    
    
    equilibriumPanel.add(buttonPanel);
    
    inputFrame.add(equilibriumPanel);
    
//make sure the JFrame is visible

    inputFrame.setVisible(true);
    
  }
  
  public void storeData(){
    
    
    try{
      
      if(Double.valueOf(in2Field.getText()) > 1 || Double.valueOf(in2Field.getText()) < 0){throw new Exception();}
      if(Double.valueOf(in4Field.getText()) > 1 || Double.valueOf(in4Field.getText()) < 0){throw new Exception();}
      if(Double.valueOf(in5Field.getText()) > 1 || Double.valueOf(in5Field.getText()) < 0){throw new Exception();}
      
      double[] systemConstant = new double[]{Double.valueOf(in1Field.getText()), Double.valueOf(in2Field.getText()), Double.valueOf(in3Field.getText()), Double.valueOf(in4Field.getText()), Double.valueOf(in5Field.getText()), 25, 1000};
      double[] eqConstant = new double[]{Double.valueOf(eq0Field.getText()), Double.valueOf(eq1Field.getText()), Double.valueOf(eq2Field.getText()), Double.valueOf(eq3Field.getText()), Double.valueOf(eq4Field.getText())};
      String packingType = (String)in8Field.getSelectedItem();
      packingType = packingType.toLowerCase();
      
      for(int i = 0; i<systemConstant.length; i++){
        if(systemConstant[i]<0){throw new Exception();}
      }
      
      data.setSC(systemConstant);
      data.setED(eqConstant);
      data.setPackingType(packingType);
      
      dataStored = true;

    }
    catch(Exception e){
      notValidInputs();
    }

  }
  
  public void storeDataFromFile(){
    
    
    double[] inputs = new double[5];
    

    String fileName = "inputs.txt";
    
    try{
      File file = new File(fileName);
      Scanner fileInput = new Scanner(file);
      int i = 0;
      while(fileInput.hasNext() && i<inputs.length){
        String[] valueString = fileInput.nextLine().split("=");
        inputs[i] = new Double(valueString[1]).doubleValue();
        i++;
      }
      
      String pack = fileInput.nextLine().split("=")[1];
      data.setPackingType(pack);
      
    }
    catch(FileNotFoundException e){
      
    }
    
    
    data.setSC(inputs);
    
    //----------
    
    ArrayList<String> valueList = new ArrayList<String>();

    
    String fileName2 = "equilibrium.txt";
    
    try{
      File file2 = new File(fileName2);
      Scanner fileInput2 = new Scanner(file2);
      
      while(fileInput2.hasNext()){
        String[] thisValue = fileInput2.nextLine().split("=");
        valueList.add(thisValue[1]);
      }
      
    }
    catch(FileNotFoundException e){
      //System.out.println("Not a valid file name");
    }
    
    double [] eqdata = new double[valueList.size()];
    
    for(int i = 0; i<valueList.size(); i++){
      eqdata[i] = new Double(valueList.get(i)).doubleValue();
    }                                      
    
    dataStored=true;
    data.setED(eqdata);
    
  }
    
  
  public void notValidInputs(){
    
    JFrame guiFrame = new JFrame();
    
    guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    guiFrame.setTitle("Oops, something went wrong!");
    guiFrame.setSize(250,125);
    
    guiFrame.setLocationRelativeTo(null);
    
//The first JPanel contains a JLabel and JCombobox
    final JPanel startingPanel = new JPanel();
    JLabel welcomeText = new JLabel("One or more of your inputs is not valid");
    startingPanel.add(welcomeText);
    
    
    final JPanel buttonPanel = new JPanel();
    JButton ok = new JButton("Gotcha!");
    buttonPanel.add(ok);

    
    guiFrame.add(startingPanel, BorderLayout.NORTH);
    guiFrame.add(buttonPanel, BorderLayout.SOUTH);
    
//make sure the JFrame is visible
    guiFrame.setVisible(true);
    
    
    //BUTTONS
    
    ok.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent event)
      {
        guiFrame.setVisible(false);
      }
    });
    
  }
  
}