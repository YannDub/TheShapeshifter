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
	
	public void renderBitmap(int x, int y, Bitmap bitmap) {
		int w = bitmap.width;
		int h = bitmap.height;
		
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				int posX = i + x;
				int posY = j + y;
				if(posX < 0) break;
				if(posY < 0) continue;
				if(posX >= this.width) break;
				if(posY >= this.height) break;
				int col = bitmap.pixels[i + j * w];
				if(col != 0xffff00ff) this.pixels[posX + posY * this.width] = col;
			}
		}
	}
	
	public Bitmap scale(float scale) {
		int newWidth = (int) (this.width * scale);
		int newHeight = (int) (this.height * scale);
		Bitmap bitmap = new Bitmap(newWidth, newHeight);
		
		for(int i = 0; i < this.width * scale; i++) {
			for(int j = 0; j < this.height * scale; j++) {
				int posPixelBitmap = (int) (i + j * newWidth);
				int posPixel = (int) ((int)(i / scale) + (int)(j / scale) * this.width);
				
				bitmap.pixels[posPixelBitmap] = this.pixels[posPixel];
			}
		}
		
		return bitmap;
	}
	
	public Bitmap flip() {
		Bitmap bitmap = new Bitmap(this.width, this.height);
		
		for(int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.height; j++) {
				bitmap.pixels[i + j * this.width] = this.pixels[((this.width - 1) - i) + j * this.width];
			}
		}
		
		return bitmap;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
}
