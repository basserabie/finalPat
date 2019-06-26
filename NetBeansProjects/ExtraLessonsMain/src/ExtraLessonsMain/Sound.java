/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

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
public class Sound {
    
   public static void playbyebye() {//creates a static method to play the byebye sound
       File doh = new File("b.WAV");//creates a nel file object according to the byebye sounf WAV file
       try {//opens the tryctach statement
           Clip clip = AudioSystem.getClip();//instantiates an audio clip to be played
           clip.open(AudioSystem.getAudioInputStream(doh));//sets the clip to the byebye WAV file
           clip.start();//plays the sound
           Thread.sleep(clip.getMicrosecondLength()/1000);//stops the sound playing
       } catch (Exception e) {//opens the catch statement
           System.out.println("problem playing sound");//alerts the class user that there was an error playing the sound
       }//closes the catch statement
    }//closes the playbyebye method
   
   public static void playcam() {//creates a static method to play the cam sound
       File doh = new File("cam.WAV");//creates a nel file object according to the cam sounf WAV file
       try {//opens the tryctach statement
           Clip clip = AudioSystem.getClip();//instantiates an audio clip to be played
           clip.open(AudioSystem.getAudioInputStream(doh));//sets the clip to the cam WAV file
           clip.start();//plays the sound
           Thread.sleep(clip.getMicrosecondLength()/1000);//stops the sound playing
       } catch (Exception e) {//opens the catch statement
            System.out.println("problem playing sound");//alerts the class user that there was an error playing the sound
       }//closes the catch statement
    }//closes the playcam method
   
   public static void playbumb() {//creates a static method to play the bumb sound
       File doh = new File("r.WAV");//creates a nel file object according to the bumb sounf WAV file
       try {//opens the tryctach statement
           Clip clip = AudioSystem.getClip();//instantiates an audio clip to be played
           clip.open(AudioSystem.getAudioInputStream(doh));//sets the clip to the bumb WAV file
           clip.start();//plays the sound
           Thread.sleep(clip.getMicrosecondLength()/1000);//stops the sound playing
       } catch (Exception e) {//opens the catch statement
            System.out.println("problem playing sound");//alerts the class user that there was an error playing the sound
       }//closes the catch statement
    }//closes the playbumb method
   
    public static void playgoahead() {//creates a static method to play the goahead sound
       File doh = new File("g.WAV");//creates a nel file object according to the goahead sounf WAV file
       try {//opens the tryctach statement
           Clip clip = AudioSystem.getClip();//instantiates an audio clip to be played
           clip.open(AudioSystem.getAudioInputStream(doh));//sets the clip to the goahead WAV file
           clip.start();//plays the sound
           Thread.sleep(clip.getMicrosecondLength()/1000);//stops the sound playing
       } catch (Exception e) {//opens the catch statement
            System.out.println("problem playing sound");//alerts the class user that there was an error playing the sound
       }//closes the catch statement
    }//closes the playgoahead method
    
     public static void playdamnit() {//creates a static method to play the damnit sound
       File doh = new File("d.WAV");//creates a nel file object according to the damnit sounf WAV file
       try {//opens the tryctach statement
           Clip clip = AudioSystem.getClip();//instantiates an audio clip to be played
           clip.open(AudioSystem.getAudioInputStream(doh));//sets the clip to the damnit WAV file
           clip.start();//plays the sound
           Thread.sleep(clip.getMicrosecondLength()/1000);//stops the sound playing
       } catch (Exception e) {//opens the catch statement
           System.out.println("problem playing sound");//alerts the class user that there was an error playing the sound
       }//closes the catch statement
    }//closes the playdamnit method
     
     public static void playtoy() {//creates a static method to play the toy sound
       File doh = new File("s.WAV");//creates a nel file object according to the toy sounf WAV file
       try {//opens the tryctach statement
           Clip clip = AudioSystem.getClip();//instantiates an audio clip to be played
           clip.open(AudioSystem.getAudioInputStream(doh));//sets the clip to the toy WAV file
           clip.start();//plays the sound
           Thread.sleep(clip.getMicrosecondLength()/1000);//stops the sound playing
       } catch (Exception e) {//opens the catch statement
            System.out.println("problem playing sound");//alerts the class user that there was an error playing the sound
       }//closes the catch statement
    }//closes the playtoy method
     
     public static void playcoin() {//creates a static method to play the coin sound
       File doh = new File("c.WAV");//creates a nel file object according to the coin sounf WAV file
       try {//opens the tryctach statement
           Clip clip = AudioSystem.getClip();//instantiates an audio clip to be played
           clip.open(AudioSystem.getAudioInputStream(doh));//sets the clip to the coin WAV file
           clip.start();//plays the sound
           Thread.sleep(clip.getMicrosecondLength()/1000);//stops the sound playing
       } catch (Exception e) {//opens the catch statement
            System.out.println("problem playing sound");//alerts the class user that there was an error playing the sound
       }//closes the catch statement
    }//closes the playcoin method
     
     public static void playflagpole() {//creates a static method to play the flagpole sound
       File doh = new File("f.WAV");//creates a nel file object according to the flagpole sounf WAV file
       try {//opens the tryctach statement
           Clip clip = AudioSystem.getClip();//instantiates an audio clip to be played
           clip.open(AudioSystem.getAudioInputStream(doh));//sets the clip to the flagpole WAV file
           clip.start();//plays the sound
           Thread.sleep(clip.getMicrosecondLength()/1000);//stops the sound playing
       } catch (Exception e) {//opens the catch statement
            System.out.println("problem playing sound");//alerts the class user that there was an error playing the sound
       }//closes the catch statement
    }//closes the playflagpole method
}