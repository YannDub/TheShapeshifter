package com.shapeshifter.gfx;

public class Parallax {
	
	private int factor;
	private int xO = 0;
	
	public Parallax(int factor) {
		this.factor = factor;
	}
	
	public void render(Screen screen) {
		xO = (int) (((float) screen.getXOffset() / (float)this.factor) % Art.parallax.getWidth());
		
		int diff = Art.parallax.getWidth() + xO;
		if(xO + diff < 0) {
			screen.renderImage(diff, 0, Art.parallax);
		}
		screen.renderImage(xO, 0, Art.parallax);
	}
}
