package de.Fraport.Feuerwehr.Easteregg;

import javax.sound.sampled.*;
import javax.sound.sampled.Clip;
import java.io.File;

public class Easteregg {
    public Easteregg(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("alarm.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
