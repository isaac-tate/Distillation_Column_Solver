//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import java.awt.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class UseGUI{
  
  public boolean use;
  public boolean answered = false;


  public UseGUI()
  {
    JFrame guiFrame = new JFrame();
    
    guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    guiFrame.setTitle("Use GUI");
    guiFrame.setSize(350,125);
    
    guiFrame.setLocationRelativeTo(null);
    
//The first JPanel contains a JLabel and JCombobox
    final JPanel startingPanel = new JPanel();
    JLabel welcomeText = new JLabel("Would you like to use the Graphic User Interface (GUI)?");
    startingPanel.add(welcomeText);
    
    
    final JPanel buttonPanel = new JPanel();
    JButton yes = new JButton("Yes, please!");
    buttonPanel.add(yes);
    
    JButton no = new JButton("No, thanks!");
    buttonPanel.add(no);
    
    
    guiFrame.add(startingPanel, BorderLayout.NORTH);
    guiFrame.add(buttonPanel, BorderLayout.SOUTH);
    
//make sure the JFrame is visible
    guiFrame.setVisible(true);
    
    
    //BUTTONS
    
    yes.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent event)
      {
        use = true;
        answered = true;
        guiFrame.setVisible(false);
      }
    });
    
    no.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent event)
      {
        use = false;
        answered = true;
        guiFrame.setVisible(false);
      }
    });
  }
}