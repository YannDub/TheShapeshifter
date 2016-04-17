package com.shapeshifter.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.shapeshifter.gfx.Screen;
import com.shapeshifter.gui.MainScene;
import com.shapeshifter.gui.Scene;
import com.shapeshifter.gui.WinScene;
import com.shapeshifter.utils.InputHandler;

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
	public static final InputHandler INPUT = new InputHandler();
	
	
	private Scene scene;
	
	public Game() {
		this.screen = new Screen(this.width / scale, this.height / scale);
		this.scene = new MainScene(this);
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
		
		this.addKeyListener(INPUT);
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
		long lastTime = System.nanoTime();
		double delta = 0;
		double nsPerTick = 1000000000.0 / 60.0;
		int frames = 0;
		int ticks = 0;
		long timer = System.currentTimeMillis();
		
		this.init();
		
		while(this.run) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			while(delta >= 1) {
				ticks++;
				this.update(delta);
				delta -= 1;
			}
			
			frames++;
			this.render();
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
//				System.out.println(ticks + " ticks, " + frames + " fps");
				frames = 0;
				ticks = 0;
			}
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
		this.scene.render(this.screen);
		
		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, this.width / scale, this.height / scale);
		
		g.drawImage(this.screen.getImage(), 0, 0, width * scale, height * scale, null);
		g.dispose();
		bs.show();
	}
	
	private void update(double delta) {
		this.scene.update();
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	public static void main(String[] argc) {
		Game game = new Game();
		game.start();
	}
}
