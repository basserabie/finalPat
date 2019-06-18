/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

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
 
public class SwingPaint {
 
JButton clearBtn, done;
  DrawArea drawArea = new DrawArea();
  public static JFrame frame;
  public static boolean sigchange = false;
  
  ActionListener actionListener = new ActionListener() {
  public void actionPerformed(ActionEvent e) {
      if (e.getSource() == clearBtn) {
        drawArea.clear();
      } else {
          if (e.getSource() == done) {
              if (sigchange) {
                  drawArea.createImage(drawArea, true);
                  
              } else {
                  drawArea.createImage(drawArea);
              }
              
          }
      }
    }
  };
 
  public static void main(String[] args) {
    new SwingPaint().show();
  }
 
  public void show() {
    // create main frame
    frame = new JFrame("Swing Paint");
    Container content = frame.getContentPane();
    // set layout on content pane
    content.setLayout(new BorderLayout());
    // create draw area
//    drawArea = new DrawArea();
 
    // add to content pane
    content.add(drawArea, BorderLayout.CENTER);
 
    // create controls to apply colors and call clear feature
    JPanel controls = new JPanel();
 
    clearBtn = new JButton("Clear");
    clearBtn.addActionListener(actionListener);
    done = new JButton("Done");
    done.addActionListener(actionListener);
 
    // add to panel
    controls.add(clearBtn);
    controls.add(done);
 
    // add to content pane
    content.add(controls, BorderLayout.NORTH);
 
    frame.setSize(600, 600);
    // can close frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // show the swing paint result
    frame.setVisible(true);
 
    // Now you can try our Swing Paint !!! Enjoy <img draggable="false" class="emoji" alt="😀" src="https://s.w.org/images/core/emoji/12.0.0-1/svg/1f600.svg">
  }
}