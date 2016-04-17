package com.shapeshifter.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Art {
	
	public static final Bitmap test = Art.load("/tiles/tiles.png");
	public static final Bitmap parallax = Art.load("/parallax.png").scale(2.25f);
	public static final Bitmap anvil = Art.load("/entities/anvil.png");
	public static final Bitmap blood = Art.load("/entities/particles/blood.png");
	public static final Bitmap title = Art.load("/gui/title.png");
	
	public static final Bitmap[][] city = Art.cut("/tiles/city.png", 16, 16);
	public static final Bitmap[][] player = Art.cut("/entities/player.png", 16, 16);
	public static final Bitmap[][] transform = Art.cut("/entities/particles/transform.png", 16, 16);
	public static final Bitmap[][] bandit = Art.cut("/entities/bandit.png", 16, 16);
	public static final Bitmap[][] font = Art.cut("/gui/font.png", 8, 8);
	public static final Bitmap[][] bubble = Art.cut("/entities/particles/bubbles.png", 16, 16);
	
	private static Bitmap load(String path) {
		try {
			BufferedImage image = ImageIO.read(Art.class.getResourceAsStream(path));
			int width = image.getWidth();
			int height = image.getHeight();
			Bitmap result = new Bitmap(width, height);
			image.getRGB(0, 0, width, height, result.pixels, 0, width);
			return result;
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	
	private static Bitmap[][] cut(String path, int xSize, int ySize) {
		try {
			BufferedImage image = ImageIO.read(Art.class.getResourceAsStream(path));
			int w = image.getWidth();
			int h = image.getHeight();
			int xTile = w / xSize;
			int yTile = h / ySize;
			
			Bitmap[][] results = new Bitmap[xTile][yTile];
			for(int i = 0; i < xTile; i++) {
				for(int j = 0; j < yTile; j++) {
					Bitmap bitmap = new Bitmap(xSize, ySize);
					image.getRGB(i * xSize, j * ySize, xSize, ySize, bitmap.pixels, 0, xSize);
					results[i][j] = bitmap;
				}
			}
			return results;
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
}
