package uk.alij.tanks;

/**
 * Created by Ali J on 4/29/2015.
 */

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public enum SoundEffect {
    EXPLOSION1("sounds/explosion.wav"),		// An Enemy Exploding
    EXPLOSION2("sounds/explosion2.wav"),	// An Enemy Exploding
    FIRING("sounds/firing.wav");	// The Tank Firing

    // Nested class for specifying volume
    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }

    public static Volume volume = Volume.LOW;

    // Each sound effect has its own clip, loaded with its own sound file.
    private Clip clip;

    // Constructor to construct each element of the enum with its own sound file.
    SoundEffect(String soundFileName) {
        try {
            File dot = new File(".");
            System.out.println("current dir is: " + dot.getCanonicalPath() );

            String soundFileLocation = dot.getCanonicalPath() + "/" + soundFileName;
            File soundFile = new File( soundFileLocation );
            System.out.println("Sound file is: " + soundFile.getCanonicalPath() );
            if ( soundFile.exists() ) {
                System.out.println("File exists");
                if ( soundFile.canRead())  System.out.println("Can Read...");
            }

            Class cl = this.getClass();
            System.out.println("Class toString is:" + cl.toString());
            System.out.println("Class name is:" + cl.getCanonicalName());

            // Use URL (instead of File) to read from disk and JAR.
            URL url = this.getClass().getResource(soundFileName);
            //URL url = this.getClass().getClassLoader().getResource(soundFileLocation);

            System.out.println(" URL = " + url.getPath());

            // Set up an audio input stream piped from the sound file.
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            //AudioInputStream audioInputStream = AudioSystem.getAudioInputStream( soundFile );

            System.out.println(" input stream is: " + audioInputStream.toString() );

            // Get a clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioInputStream);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        catch( Exception e ){
            System.out.println("ERROR!! " + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }

    // Play or Re-play the sound effect from the beginning, by rewinding.
    public void play() {
        if (volume != Volume.MUTE) {
            if (clip.isRunning())
                clip.stop();   // Stop the player if it is still running
            clip.setFramePosition(0); // rewind to the beginning
            clip.start();     // Start playing
        }
    }

    // Optional static method to pre-load all the sound files.
    static void init() {
        values(); // calls the constructor for all the elements
    }
}
