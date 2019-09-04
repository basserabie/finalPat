/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
  
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.swing.JComponent;
 
/**
* Component for drawing !
*
* @author YishaiBasserabie
*
*/
public class DrawArea extends JComponent {//creates class called DrawArea handling the drawing components of the user's signtiture
 
  // Image in which the user will draw to going to draw
  private Image image;
  // Graphics2D object ==> used to draw on
  private Graphics2D g2;
  // Mouse coordinates
  private int currentX, currentY, oldX, oldY;
 
  public DrawArea() {//creates a constructor to handler the mouse pressed and dragged listeners to be used for drawing
    setDoubleBuffered(false);//disable background drawing in frame for proper display
    addMouseListener(new MouseAdapter() {//creates mouse listener
      public void mousePressed(MouseEvent e) {//adds a mouse pressed listener
        // save coord x,y when mouse is pressed
        oldX = e.getX();
        oldY = e.getY();
      }//closes the mouse pressed component of listener
    });//closes mouse listener
 
    addMouseMotionListener(new MouseMotionAdapter() {//creates mouse listener
      public void mouseDragged(MouseEvent e) {//adds a mouse dragged component to mouse listener
        // sets coord x,y when drag mouse to mouse coords
        currentX = e.getX();
        currentY = e.getY();
 
        if (g2 != null) {//checks if the g2 context is not null
          // draw line if g2 context not null
          g2.drawLine(oldX, oldY, currentX, currentY);//draws line
          // refresh draw area to repaint
          repaint();//refresh draw area
          // store current coords x,y as olds x,y
          oldX = currentX;//stores x
          oldY = currentY;//stores y
        }
      }//closes the mouse dragged component of mouse listener
    });//closes the mouse listener
  }//closes the Draw Area constructor
 
  protected void paintComponent(Graphics g) {//creates a method that instantiates the paint component used to draw in: a graphic g
    if (image == null) {//checks if the image is clear (null)
      // image to draw null ==> we create
      image = createImage(getSize().width, getSize().height);//creates an image to draw to
      g2 = (Graphics2D) image.getGraphics();//sets the 2dGraphics component g2 to the image graphics
      // enable antialiasing
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//sets the rendering hint of the drawing by enabling antialiasing
      // clear draw area
      clear();//clears the draw area
    }
 
    g.drawImage(image, 0, 0, null);//draws the image to the g graphic passed in
  }//closes the paitnComponent method
 
  public void clear() {//creates method to clear the drawArea
    g2.setPaint(Color.white);//sets the paint colour to white
    // draw white on entire draw area to clear
    g2.fillRect(0, 0, getSize().width, getSize().height);//fills the area with white
    g2.setPaint(Color.black);//sets the paint coour back to black
    repaint();//refreshes the area to clear
  }//closes the clear method
 
  public void createImage(DrawArea panel) {//creates a method to create a png file from the drawn image in: DrawArea panel
    int w = panel.getWidth();//creates an integer representing the width of the draw area
    int h = panel.getHeight();//creates an integer representing the height of the draw area
    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);//creates a new buffered image object of sepcified dimensions according to the draw area
    Graphics2D g = bi.createGraphics();//creates a graphics object g and sets it to the created graphic of the buffered image
    panel.print(g);//prints the graphic
    g.dispose();//disposes of the graphic
    
    File outputfile = new File("signiture.png");//creates a new file object to store the image (signiture)
      try {//opens the trcatch statement
          ImageIO.write(bi, "png", outputfile);//writes the image to the png file created
      } catch (IOException ex) {//opens the catch statement
          Logger.getLogger(DrawArea.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error writting the image to the created file
      }//closes the catch
      SwingPaint.frame.setVisible(false);//sets the frame invisible
  }//closes the createImage method
  
  public void deleteSig() {//creates a method to delete the signiture png file
        File file = new File("signiture" + ".png");//creates a new file object representing the signiture file
        if(file.delete()){//deletes the file and checks if deleted
            System.out.println("file.txt File deleted from Project root directory");//alerts the class user that the file has been deleted
        }else System.out.println("File file.txt doesn't exist in the project root directory");//alerts the class user that the file does not exist
    }//closes the deleteSig method
  
  public void createImage(DrawArea panel, boolean changing) {//creates a method to create the signature image file if the user is changing their signature in: DrawArea panel and whether the icon is being changed boolean
    this.deleteSig();//calls the method that deletes the signiture png file
      
    int w = panel.getWidth();//creates an integer representing the width of the draw area
    int h = panel.getHeight();//creates an integer representing the height of the draw area
    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);//creates a new buffered image object of sepcified dimensions according to the draw area
    Graphics2D g = bi.createGraphics();//creates a graphics object g and sets it to the created graphic of the buffered image
    panel.print(g);//prints the graphic
    g.dispose();//disposes of the graphic
    
    File outputfile = new File("signiture.png");//creates a new file object to store the image (signiture)
      try {//opens the trcatch statement
          ImageIO.write(bi, "png", outputfile);//writes the image to the png file created
      } catch (IOException ex) {//opens the catch statement
          Logger.getLogger(DrawArea.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error writting the image to the created file
      }//closes the catch
      SwingPaint.frame.setVisible(false);//sets the frame invisible
  }//closes the deleteSig method
      
}
