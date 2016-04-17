package com.shapeshifter.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
	
	private boolean[] keys;
	
	public InputHandler() {
		this.keys = new boolean[65465];
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.keys[e.getKeyCode()] = false;
	}
	
	public boolean isKeyDown(int keyCode) {
		return this.keys[keyCode];
	}
	
}
