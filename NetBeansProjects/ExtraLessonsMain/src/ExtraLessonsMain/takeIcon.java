/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.awt.image.RenderedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static ExtraLessonsMain.image.path;


@SuppressWarnings("serial")
public class takeIcon extends JFrame {//creates a class to handle the icon of the user

    public static boolean changing = false;//creates a boolean indicating whether the icon is being changed or added for the first time
    public static String path = "";//creates a string for the absolute path of the rounded off picture
    private static String tempPath = "";//creates a string for the absolute path of the square picture
    
    public static boolean fileExists() {//creates a method to check if the image exists
        boolean exists = true;//creates a boolean indicating whether the file exists
        File file = new File(takeIcon.getPath());//Create new file object according to the path
        if (!file.exists()) {//checks if file doesn't exist
            exists = false;//flips exists to false
        }
        return exists;//returns exists
    }//closes the fileExists method
    
	private class SnapMeAction extends AbstractAction {//creates a new class to handle the action of taking the photo

		public SnapMeAction() {//create a method to class the take picture of the parent class of AbstractAction
			super("Take Picture!");//names the snap action takePicture!
		}//closes the SnapMeAction method

		@Override
		public void actionPerformed(ActionEvent e) {//creates a method for the action oerformed of the take picture
                    File file = new File(String.format("icon.jpg", 0));//creates a file object for the square icon
                    File file2 = new File(String.format("iconr.jpg", 0));//creates a file object for the round icon
                    path = file2.getAbsolutePath();//sets the path to the file path of the round icon
                    tempPath = file.getAbsolutePath();//sets the tempPath to the file path of the square icon
                    if (!changing) {//checks if the icon is being added for the first time
                        try {
                        Webcam webcam = webcams.get(0);//creates a webcam object
                        takeIcon.createPath();//creates a text file of the icon path
                        ImageIO.write(webcam.getImage(), "JPG", file);//writes the ficture taken to the file file
                        ImageIO.write((RenderedImage) takeIcon.getIcon(), "jpg", file2);//writes the rounded image to the file2 file
                    } catch (IOException ex) {
                        Logger.getLogger(takeIcon.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error taking the photo
                    }
                    } else {//if the icon is being changed
                        takeIcon.deleteImage(path);//deletes the icon
                        takeIcon.deleteImage(tempPath);//deletes the rounded icon
                        try {
                        Webcam webcam = webcams.get(0);//creates a webcam object
                        takeIcon.createPath();//creates a text file of the icon path
                        ImageIO.write(webcam.getImage(), "JPG", file);//writes the ficture taken to the file file
                        ImageIO.write((RenderedImage) takeIcon.getIcon(), "jpg", file2);//writes the rounded image to the file2 file
                    } catch (IOException ex) {
                        Logger.getLogger(takeIcon.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error taking the photo
                    }
                    }
                   JOptionPane.showMessageDialog(null, "Picture taken! looking gooood.\nPlease click 'Ok', and then 'Done'");//alerts the user that the picture was taken
                    
		}//closes the actionPerformed method
	}//closes the SnapMeAction class

        public static Image getIcon() throws IOException {//creates method to to get a rounded icon
            File file = new File(tempPath);//creates a file object for the square photo
            BufferedImage image = ImageIO.read(file);//creates a bufferedImage object for the image in the file file
            Area clip = new Area(new Rectangle(0, 0, image.getWidth(), image.getHeight()));//creates an area object of the image
            Area oval = new Area(new Ellipse2D.Double(0, 0, image.getWidth() - 1, image.getHeight() - 1));//creates an oval area object of the image
            clip.subtract(oval);//removes the oval from the clip area
            Graphics g2d = image.createGraphics();//creates a 2d graphics object
            g2d.setClip(clip);//sets the clip of the g2d object to the clip area object
            Color myc = new Color(153,204,255);//creates a colour object of white(ish)
            g2d.setColor(myc);//sets the colour of the clip to the myc colour
            g2d.fillRect(0, 0, image.getWidth(), image.getHeight());//fills a rectangle with the g2d object
            Image rimage = new ImageIcon(image).getImage();//creates an image object for the buffered image
            return rimage;//returns the image
        }//closes the getIcon method
                
	private class StartAction extends AbstractAction implements Runnable {//creates a class for the start action 
		public StartAction() {//creates a method to name the im ready button constructor
			super("Im ready for my photoshoot");//names the button
		}//closes the StartAction method
		@Override
		public void actionPerformed(ActionEvent e) {//creates a method for the action performed
			btStart.setEnabled(false);//sets the the start button disabled
			btSnapMe.setEnabled(true);//sets the snap button enabled
			executor.execute(this);//extecutes the current buttona ction
		}
		@Override
		public void run() {//creates a method to run photoshoot
			btStop.setEnabled(true);//sets the stop enabled
			for (WebcamPanel panel : panels) {//iterates throug the panels in the webcam object
				panel.start();//starts the iterated panel
			}
		}//closes the run method
	}

	private class StopAction extends AbstractAction {//creates a method for the stop action
		public StopAction() {//names the stop button
			super("Done(I look sexy)");
		}
		@Override
		public void actionPerformed(ActionEvent e) {//creates a method for the action performed
			btStart.setEnabled(true);//sets the start enabled
			btSnapMe.setEnabled(false);//sets the snap disabled
			btStop.setEnabled(false);//sets the stop disabled

			for (WebcamPanel panel : panels) {//iterates throug the panels in the webcam object
				panel.stop();//stops the iterated panel
                                JOptionPane.showMessageDialog(null, "Icon saved, you may now exit this screen :)");//alerts the user that their icon was saved
			}
		}//closes the actionPerformed method
	}

        //sets the specifications of the webcam and sixe of picture and instantiates the buttons with their actions
	private Executor executor = Executors.newSingleThreadExecutor();
	private Dimension size = WebcamResolution.QQVGA.getSize();
	private List<Webcam> webcams = Webcam.getWebcams();
	private List<WebcamPanel> panels = new ArrayList<WebcamPanel>();
	private JButton btSnapMe = new JButton(new SnapMeAction());
	private JButton btStart = new JButton(new StartAction());
	private JButton btStop = new JButton(new StopAction());

	public takeIcon() {//creates a method to take the picture
		super("Looking Great!");//names it looking great!
		for (Webcam webcam : webcams) {//iterates through the webcams items
			webcam.setViewSize(size);//sets the siz
			WebcamPanel panel = new WebcamPanel(webcam, size, false);//creates a new panel object
			panel.setFPSDisplayed(true);//sets the displayed to true
			panel.setFillArea(true);//fills the area
			panels.add(panel);//adds the panel to the panels array list
                        JOptionPane.showMessageDialog(null, "Please click 'Ok', then click 'Im ready...' and wait untill you\ncan see your pretty face on-screen.");//alerts the user to click take picture
		}
		// start application with disable snapshot button - we enable it when
		// webcam is started
		btSnapMe.setEnabled(false);//disables snap
		btStop.setEnabled(false);//disables stop
		setLayout(new FlowLayout());//sets the layout
		for (WebcamPanel panel : panels) {//iterates trough the panels
			add(panel);//adds the panel to the layout
		}
                add(btStart);//adds the start button
		add(btSnapMe);//adds the snap button
		add(btStop);//adds the stop button

		pack();//consolidates the layout
		setVisible(true);//sets the frame visible
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//sets the closep operation to dispose
	}//closes the take icon method

	public static void main(String[] args) {//creates the main method
		new takeIcon();//takes an icon of the user
	}//closes the main method
        
        public static void createPath() {//creates the path text file for the image
            AddStudentNote add = new AddStudentNote();//creates an object for the AddStudentNote class
            add.createNote("path");//creates the text file
            add.writing("path", path);//adds the path to the text file
        }//closes the createPath method
        
        public static String getPath() {//gets the path from the text file
            AddStudentNote add = new AddStudentNote();//creates an object for the AddStudentNote class
            return add.reading("path");//gets the path
        }//closes the getPath method
        
        public static String getNoIconPath() {//creates a method to get the no icon icon path
            File file = new File("noIconTemp.jpg");//creates a file object for the no icon icon file
            String realPath = file.getAbsolutePath().replaceAll("Temp", "");//creates a string for the path
            return realPath;//returns the path
        }//closes the getNoIconPath method
        
        public static void deleteImage(String name) {//creates a method to delete the image
        //file name only
        File file = new File(name);//creates a file object of the file name passed in
        if(file.delete()){//deletes the file and checks if deleted
            System.out.println("file.txt File deleted from Project root directory");//alerts the class user that the file was deleted
        } else System.out.println("File file.txt doesn't exist in the project root directory");//alerts the class user of the error deleting the file
    }//closes the deleteImage method
        
}//closes the takeIcon class