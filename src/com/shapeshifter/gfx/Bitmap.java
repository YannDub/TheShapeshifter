package com.shapeshifter.gfx;

import java.util.Arrays;

public class Bitmap {
	
	protected int[] pixels;
	protected int width;
	protected int height;
	
	public Bitmap(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
	}
	
	public void fill(int color) {
		Arrays.fill(this.pixels, color);
	}
	
	public void render(int x, int y, int w, int h, int color) {
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				int posX = i + x;
				int posY = j + y;
				if(posX < 0) posX = 0;
				if(posY < 0) posY = 0;
				if(posX >= this.width) break;
				if(posY >= this.height) break;
				this.pixels[posX + posY * this.width] = color;
			}
		}
	}
}
