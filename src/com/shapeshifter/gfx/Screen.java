package com.shapeshifter.gfx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Screen extends Bitmap {
	
	private BufferedImage image;
	private int xOffset = 0, yOffset = 0;
	
	public Screen(int width, int height) {
		super(width, height);
		this.image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
		this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = -xOffset;
		this.yOffset = -yOffset;
	}
	
	public void renderBitmap(int x, int y, Bitmap bitmap) {
		super.renderBitmap(x + xOffset, y + yOffset, bitmap);
	}
	
	public void renderImage(int x, int y, Bitmap bitmap) {
		super.renderBitmap(x, y, bitmap);
	}
	
	public BufferedImage getImage() {
		return this.image;
	}
	
	public int getXOffset() {
		return this.xOffset;
	}
	
	public int getYOffset() {
		return this.yOffset;
	}

}
