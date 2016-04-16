package com.shapeshifter.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.shapeshifter.gfx.Screen;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private Thread thread;
	private JFrame window;
	
	private final int width = 1024;
	private final int height = width * 9 / 16;
	private final int scale = 2;
	private boolean run;
	
	private Screen screen;
	private BufferStrategy bs;
	
	public Game() {
		this.screen = new Screen(this.width / scale, this.height / scale);
		
		this.init();
	}
	
	private void init() {
		Dimension dim = new Dimension(this.width, this.height);
		this.setPreferredSize(dim);
		this.setMaximumSize(dim);
		this.setMinimumSize(dim);
		
		this.window = new JFrame("The Shapeshifter");
		this.window.add(this);
		this.window.pack();
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setLocationRelativeTo(null);
		this.window.setResizable(false);
		this.window.setVisible(true);
	}
	
	public synchronized void start() {
		this.thread = new Thread("Game");
		this.thread.start();
		this.run = true;
		this.run();
	}
	
	public synchronized void stop() {
		try {
			this.thread.join();
			this.run = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(this.run) {
			this.render();
			this.update();
		}
		System.exit(0);
	}
	
	private void render() {
		this.bs = this.getBufferStrategy();
		if(this.bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		this.screen.fill(0xff000000);
		this.screen.render(0, 0, 16, 16, 0xffff0000);
		
		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, this.width / scale, this.height / scale);
		
		g.drawImage(this.screen.getImage(), 0, 0, width * scale, height * scale, null);
		g.dispose();
		bs.show();
	}
	
	private void update() {
		
	}
	
	public static void main(String[] argc) {
		Game game = new Game();
		game.start();
	}
}
