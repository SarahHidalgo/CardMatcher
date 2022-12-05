package tse.fise2.image3.cardmatcher.util;


import tse.fise2.image3.cardmatcher.App;


import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Audio {


   public  static void play_sound(URL audioFilePath) {

       try {

           AudioInputStream audioIn = AudioSystem.getAudioInputStream(audioFilePath);
           // Get a sound clip resource.
           Clip clip = AudioSystem.getClip();
           // Open audio clip and load samples from the audio input stream.
           clip.open(audioIn);
           clip.start();

       } catch (UnsupportedAudioFileException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       } catch (LineUnavailableException e) {
           e.printStackTrace();
       }
   }

}
