/**********************
*
* Created and maintanined
* by Allen Rocha
* For more information
* please visit:
* allenerocha.com 
* 
* *********************/


import java.util.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class IntrestRateCalculator extends JFrame implements ActionListener
{
  private JButton button[];
  private JButton calculateButton;
  private TextField[] textField;
  private int frameSizeX;
  private int frameSizeY;
    
  private int buttonSizeX;
  private int buttonSizeY;
  private int mode;
  private JFrame f;
  
  private double doubleArray[] = new double[4];
  
  public IntrestRateCalculator()
  {
    frameSizeX = 480;
    frameSizeY = 640;
    
    buttonSizeX = (int)(frameSizeX / 6);
    buttonSizeY = (int)(frameSizeY / 3);
    mode = 0;
    
    
    OnDrawGUI();
  }
  
  
  public static void main(String args[])
  {
    new IntrestRateCalculator();
  }
  
  public void OnDrawGUI()
  {
    String buttonLabels[] = {"Amount Saved", "Time Needed"};
    button = new JButton[2];
    
    f = new JFrame("Intrest Rate Calculator");
    f.setSize(frameSizeY, frameSizeX);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JPanel p = new JPanel();
    p.setBackground(Color.WHITE);
    
    for(int index = 0; index < button.length; index++)
    {
      button[index] = new JButton(buttonLabels[index]);
      button[index].setPreferredSize(new Dimension(buttonSizeY, buttonSizeX)); 
      
      button[index].addActionListener(this);
      
      p.add(button[index]);
    }
    
    f.setVisible(true);
    f.add(p);
  }
  
  public void actionPerformed(ActionEvent e)
  {
    try
    {
      ////////////////////////////////////////////////////////////////////
      if(e.getSource() == button[0])
      {
        mode = 0;
        AmountSaved();
      }
      
      ////////////////////////////////////////////////////////////////////
      else if(e.getSource() == button[1])
      {
        mode = 1;
        TimeNeeded(); 
      }
      
      ////////////////////////////////////////////////////////////////////
      else if(e.getSource() == calculateButton)
      {
        for(int i = 0; i < 4; i++)
        {
          doubleArray[i] += Double.parseDouble(textField[i].getText());
        }
        JOptionPane.showMessageDialog(null,Calculate(doubleArray));
      }
    }
    catch(Exception ex)
    {
      System.out.println(ex);
    } 
  }
  
  //*AmountSaved function*//
  public void AmountSaved()
  {
    textField = new TextField[5];
    JLabel[] label = new JLabel[5];
    String labelStrings[] = {"Initial Investment:", "Intrest rate:",
                             "Intrest compounded per year:", "Time in years:"};
    
    f = new JFrame("Saved over time.");
    f.setSize(frameSizeY, frameSizeX);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JPanel p = new JPanel();
    p.setBackground(Color.WHITE);
    
    for(int x = 0; x < 4; x++)
    {
      label[x] = new JLabel(labelStrings[x]);
      label[x].setPreferredSize(new Dimension(buttonSizeY, buttonSizeX / 2));
      p.add(label[x]);
        
      textField[x] = new TextField();
      textField[x].setPreferredSize(new Dimension(buttonSizeY, buttonSizeX / 2));
      p.add(textField[x]);
    }  
    calculateButton = new JButton("Calculate");
    calculateButton.setPreferredSize(new Dimension(buttonSizeY, buttonSizeX));
    calculateButton.addActionListener(this);
    p.add(calculateButton);
    
    f.setVisible(true);
    f.add(p);
  }
  
  
  //*TimeNeeded function*//
  public void TimeNeeded()
  {
    textField = new TextField[5];
    JLabel[] label = new JLabel[5];
    String labelStrings[] = {"Initial Investment:", "Intrest rate:",
                             "Intrest compounded per year:", "Amount you want saved:"};
    
    f = new JFrame("Saved over time.");
    f.setSize(frameSizeY, frameSizeX);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JPanel p = new JPanel();
    p.setBackground(Color.WHITE);
    
    for(int x = 0; x < 4; x++)
    {
      label[x] = new JLabel(labelStrings[x]);
      label[x].setPreferredSize(new Dimension(buttonSizeY, buttonSizeX / 2));
      p.add(label[x]);
        
      textField[x] = new TextField();
      textField[x].setPreferredSize(new Dimension(buttonSizeY, buttonSizeX / 2));
      p.add(textField[x]);
    }  
    calculateButton = new JButton("Calculate");
    calculateButton.setPreferredSize(new Dimension(buttonSizeY, buttonSizeX));
    calculateButton.addActionListener(this);
    p.add(calculateButton);
    
    f.setVisible(true);
    f.add(p);
  }
  
  
  public String Calculate(double [] i)
  {
    String finalSum = "";
    if(mode == 0)
    {
      try  
      { 
        finalSum = Double.toString(i[0] * Math.pow((1 + ((i[1] / 100) / i[2])), (i[2] * i[3])));
        return ("Amount saved over " + i[3] + " years:\n$" + finalSum);
      }
      catch(NumberFormatException nfe)
      {
        return("Text boxes must ONLY contain numbers!");
      }
    }
    
    else
    {
      finalSum = Double.toString((Math.log(i[3] / i[0]))/(i[2] * Math.log(1 + ((i[1] / 100) / i[2]))));
      return ("To get " + i[3] + " it will take:\n" + finalSum + " years");
    }
  }
}