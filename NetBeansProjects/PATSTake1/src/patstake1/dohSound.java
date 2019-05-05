/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JOptionPane;

/**
 *
 * @author YishaiBasserabie
 */
public class dohSound {
    
   public void playSound() {
       File doh = new File("doh.WAV");
       try {
           Clip clip = AudioSystem.getClip();
           clip.open(AudioSystem.getAudioInputStream(doh));
           clip.start();
           Thread.sleep(clip.getMicrosecondLength()/1000);
       } catch (Exception e) {
       }
    }
}