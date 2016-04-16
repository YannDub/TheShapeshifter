package com.shapeshifter.gfx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Screen extends Bitmap {
	
	private BufferedImage image;
	
	public Screen(int width, int height) {
		super(width, height);
		this.image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
		this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}
	
	public BufferedImage getImage() {
		return this.image;
	}

}
