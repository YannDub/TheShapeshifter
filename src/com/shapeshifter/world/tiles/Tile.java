package com.shapeshifter.world.tiles;

public class Tile {
	
	private int color;
	public static final int SIZE = 16;
	
	public Tile(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return this.color;
	}
}
