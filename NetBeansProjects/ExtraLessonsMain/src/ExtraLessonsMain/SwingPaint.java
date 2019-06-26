/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
public class SwingPaint {//creates a ethod that runs and displays the draw Area for the input of the user' signiture
 
  JButton clearBtn, done;//instantiates two JButtons to clear and to finish drawing
  DrawArea drawArea = new DrawArea();//instantiates a draw Area object from the class DrawArea
  public static JFrame frame;//instantiates a static JFrame for the draw Area display
  public static boolean sigchange = false;//creates a variable boolean to indicate whether the user is inserting or changing thier signiture
  
  ActionListener actionListener = new ActionListener() {//creates a new action listener object for the clear and done buttons
  public void actionPerformed(ActionEvent e) {//creates a method that instantiates an actionPerformed action for the clear and done buttons
      if (e.getSource() == clearBtn) {//checks if the clear button was clicked
        drawArea.clear();//crears the draw area
      } else {//if the clear button was not clicked
          if (e.getSource() == done) {//checks if the done button was pushed
              if (sigchange) {//checks if the signiture is being changed
                  drawArea.createImage(drawArea, true);//changes the signiture
              } else {//if the signiture is being inserted for the first time
                  drawArea.createImage(drawArea);//inserts the signiture (writting to a signiture file)
              }
          }
      }
    }//closes the actionPerformed method
  };//closes the action listener
 
  public static void main(String[] args) {//creates the main static void method to show the JFrame
    new SwingPaint().show();//opens the jFrame and displays the draw area and the buttons
  }//closes the main method
 
  public void show() {//creates a method that creates and formats the JFrame for the signiture input
    frame = new JFrame("Draw Signiture");//creates a new Jframe object
    Container content = frame.getContentPane();//creates a container object of the contant pane of the jFrame
    content.setLayout(new BorderLayout());//sets the conent layout to a border layout type
 
    content.add(drawArea, BorderLayout.CENTER);//adds the draw area to the center of the pane (and subsequentJFrame)
 
    // create controls to apply colors and call clear feature
    JPanel controls = new JPanel();//creates a JPanel for the control buttons
 
    clearBtn = new JButton("Clear");//instantiates the claerbtn button and sets the text to 'clear'
    clearBtn.addActionListener(actionListener);//connects an action listener to the clearBtn button
    done = new JButton("Done");//instantiates the done button and sets the text to 'Done'
    done.addActionListener(actionListener);//connects an action listener to the done button
 
    controls.add(clearBtn);//adds the clearBtn button to the JPanel
    controls.add(done);//adds the done button to the JPanel
 
    content.add(controls, BorderLayout.NORTH);//adds the content (controls) to the pane
 
    frame.setSize(600, 600);//sets the frame size
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//sets the JFrame close action to dispose
    frame.setVisible(true);//show the swing paint result
  }//closes the show method
  
}//closes the SwingPaint class