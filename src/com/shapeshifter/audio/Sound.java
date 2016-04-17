package com.shapeshifter.audio;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	
	public static final Sound music = new Sound("/audio/music.wav");
	
	private AudioClip clip;
	
	private Sound(String name) {
		this.clip = Applet.newAudioClip(Sound.class.getResource(name));
	}
	
	public void play() {
		new Thread() {
			public void run() {
				clip.play();
			}
		}.start();
	}
	
	public void loop() {
		new Thread() {
			public void run() {
				clip.loop();
			}
		}.start();
	}
}
