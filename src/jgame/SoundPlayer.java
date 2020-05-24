package jgame;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class SoundPlayer implements Runnable {
	
	private File audioIn;
	private Clip clip;
	
	private SoundPlayer() {
		try {
			audioIn = new File("res/beep-08b.wav");
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(audioIn));
			clip.setMicrosecondPosition(0);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void playSound() {
		Thread thread = new Thread(new SoundPlayer());
		thread.start();
	}
	
	public void run() {
		try {
			clip.start();
			Thread.sleep(clip.getMicrosecondLength() / 1_000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			clip.close();
		}
	}
}
