package com.shapeshifter.world;

import java.util.Random;

import com.shapeshifter.gfx.Screen;
import com.shapeshifter.world.tiles.Tile;

public class Level {
	
	private int width, height;
	private Tile[] tiles;
	private Random random;
	
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		this.random = new Random();
		this.tiles = new Tile[this.width * this.height];
		
		this.initLevel();
	}
	
	private void initLevel() {
		for(int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.height; j++) {
				this.tiles[i + j * this.width] = new Tile(0xff000000 + random.nextInt(0x00ffffff));
			}
		}
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void render(Screen screen) {
		for(int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.height; j++) {
				screen.renderTile(i, j, this.tiles[i + j * this.width]);
			}
		}
	}
}
