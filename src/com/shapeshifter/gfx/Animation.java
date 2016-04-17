package com.shapeshifter.gfx;

public class Animation {
	
	private int time;
	private int nbFrame;
	private Bitmap[][] bitmaps;
	private int frameX, frameY;
	private boolean play;
	private int frame;
	
	public Animation(int time, int nbFrame, int frameX, int frameY, Bitmap[][] bitmaps) {
		this.time = time;
		this.nbFrame = nbFrame;
		this.bitmaps = bitmaps;
		this.frameX = frameX;
		this.frameY = frameY;
		this.play = false;
		this.frame = 0;
	}
	
	public Animation(Bitmap bitmap) {
		this.bitmaps = new Bitmap[1][1];
		this.bitmaps[0][0] = bitmap;
		this.time = 1;
		this.nbFrame = 1;
		this.frameX = 0;
		this.frameY = 0;
		this.play = false;
		this.frame = 0;
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderBitmap(x, y, bitmaps[this.frameX][this.frameY]);
	}
	
	public void play() {
		if(this.play) {
			this.frame++;
			this.frameX = this.frame / this.time % this.nbFrame;
			if(this.frame == time * nbFrame) {
				this.play = false;
				this.frame = 0;
			}
		} else {
			this.play = true;
		}
	}
	
	public void flip() {
		for(int i = 0; i < this.nbFrame; i++) {
			this.bitmaps[i][this.frameY] = this.bitmaps[i][this.frameY].flip();
		}
	}
}
