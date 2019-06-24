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
public class takeIcon extends JFrame {

    public static boolean changing = false;
    public static String path = "";
    private static String tempPath = "";
    
    public static boolean fileExists() {
        boolean exists = true;
        // Create new file
        File file = new File(takeIcon.getPath());
        // If file doesn't exists, then create it
        if (!file.exists()) {
            exists = false;
        }
        return exists;
    }
    
	private class SnapMeAction extends AbstractAction {

		public SnapMeAction() {
			super("Take Picture!");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
                    File file = new File(String.format("icon.jpg", 0));
                    File file2 = new File(String.format("iconr.jpg", 0));
                    path = file2.getAbsolutePath();
                    tempPath = file.getAbsolutePath();
                    if (!changing) {
                        try {
                        Webcam webcam = webcams.get(0);
                        takeIcon.createPath();
                        System.out.println(path);
                        ImageIO.write(webcam.getImage(), "JPG", file);
                        ImageIO.write((RenderedImage) takeIcon.getIcon(), "jpg", file2);
                        System.out.format("Image for icon \n", webcam.getName(), file);
                    } catch (IOException ex) {
                        Logger.getLogger(takeIcon.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    } else {
                        takeIcon.deleteImage(path);
                        takeIcon.deleteImage(tempPath);
                        
                        try {
                        Webcam webcam = webcams.get(0);
                        takeIcon.createPath();
                        System.out.println(path);
                        ImageIO.write(webcam.getImage(), "JPG", file);
                        ImageIO.write((RenderedImage) takeIcon.getIcon(), "jpg", file2);
                        System.out.format("Image for icon \n", webcam.getName(), file);
                    } catch (IOException ex) {
                        Logger.getLogger(takeIcon.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
			
                   JOptionPane.showMessageDialog(null, "Picture taken! looking gooood.\nPlease click 'Ok', and then 'Done'");
                    
		}
	}

        public static Image getIcon() throws IOException {
            File file = new File(tempPath);
            System.out.println();
        BufferedImage image = ImageIO.read(file);
        Area clip = new Area( new Rectangle(0, 0, image.getWidth(), image.getHeight()) );
        Area oval = new Area( new Ellipse2D.Double(0, 0, image.getWidth() - 1, image.getHeight() - 1) );
        clip.subtract( oval );
        Graphics g2d = image.createGraphics();
        g2d.setClip( clip );
        Color myc = new Color(153,204,255); // Color white
        g2d.setColor(myc);
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
        Image rimage = new ImageIcon(image).getImage();
        return rimage;
    }
                
	private class StartAction extends AbstractAction implements Runnable {

		public StartAction() {
			super("Im ready for my photoshoot");
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			btStart.setEnabled(false);
			btSnapMe.setEnabled(true);

			// remember to start panel asynchronously - otherwise GUI will be
			// blocked while OS is opening webcam HW (will have to wait for
			// webcam to be ready) and this causes GUI to hang, stop responding
			// and repainting

			executor.execute(this);
		}

		@Override
		public void run() {

			btStop.setEnabled(true);
			for (WebcamPanel panel : panels) {
				panel.start();
			}
		}
	}

	private class StopAction extends AbstractAction {

		public StopAction() {
			super("Done (I look sexy)");
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			btStart.setEnabled(true);
			btSnapMe.setEnabled(false);
			btStop.setEnabled(false);

			for (WebcamPanel panel : panels) {
				panel.stop();
                                JOptionPane.showMessageDialog(null, "Icon saved, you may now exit this screen :)");
			}
		}
	}

	private Executor executor = Executors.newSingleThreadExecutor();

	private Dimension size = WebcamResolution.QQVGA.getSize();

	private List<Webcam> webcams = Webcam.getWebcams();
	private List<WebcamPanel> panels = new ArrayList<WebcamPanel>();

	private JButton btSnapMe = new JButton(new SnapMeAction());
	private JButton btStart = new JButton(new StartAction());
	private JButton btStop = new JButton(new StopAction());

	public takeIcon() {

		super("Looking Great!");

		for (Webcam webcam : webcams) {
			webcam.setViewSize(size);
			WebcamPanel panel = new WebcamPanel(webcam, size, false);
			panel.setFPSDisplayed(true);
			panel.setFillArea(true);
			panels.add(panel);
                        JOptionPane.showMessageDialog(null, "Please click 'Ok', then click 'Im ready...' and wait untill you\ncan see your pretty face on-screen.");
		}

		// start application with disable snapshot button - we enable it when
		// webcam is started

		btSnapMe.setEnabled(false);
		btStop.setEnabled(false);

		setLayout(new FlowLayout());

		for (WebcamPanel panel : panels) {
			add(panel);
		}
                add(btStart);
		add(btSnapMe);
		add(btStop);

		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new takeIcon();
	}
        
        public static void createPath() {
            AddStudentNote add = new AddStudentNote();
            add.createNote("path");
            add.writing("path", path);
        }
        
        public static String getPath() {
            AddStudentNote add = new AddStudentNote();
            return add.reading("path");
        }
        
        public static String getNoIconPath() {
            File file = new File("noIconTemp.jpg");
            String realPath = file.getAbsolutePath().replaceAll("Temp", "");
            return realPath;
        }
        
        public static void deleteImage(String name) {
        //file name only
        File file = new File(name);
        if(file.delete()){
            System.out.println("file.txt File deleted from Project root directory");
        }else System.out.println("File file.txt doesn't exist in the project root directory");
    }
}