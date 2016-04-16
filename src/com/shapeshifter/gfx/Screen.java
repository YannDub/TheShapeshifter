package com.shapeshifter.gfx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.shapeshifter.world.tiles.Tile;

public class Screen extends Bitmap {
	
	private BufferedImage image;
	private int xOffset = 0, yOffset = 0;
	
	public Screen(int width, int height) {
		super(width, height);
		this.image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
		this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = -xOffset;
		this.yOffset = -yOffset;
	}
	
	public void renderTile(int x, int y, Tile tile) {
		this.render(x * 16 + xOffset, y * 16 + xOffset, Tile.SIZE, Tile.SIZE, tile.getColor());
	}
	
	public BufferedImage getImage() {
		return this.image;
	}

}
