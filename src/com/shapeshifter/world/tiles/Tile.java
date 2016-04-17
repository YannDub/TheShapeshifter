package com.shapeshifter.world.tiles;

import com.shapeshifter.gfx.Screen;
import com.shapeshifter.world.Level;

public class Tile {
	
	public static final int SIZE = 16;
	
	public static final Tile air = new Tile(0);
	public static final Tile building = new TileBuilding(1);
	public static final Tile window = new TileBuildingWindow(2);
	public static final Tile teleport = new TileTeleport(3);
	
	private int id;
	
	public Tile(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void render(Level level, int x, int y, Screen screen) {
		return;
	}
	
	public boolean canBeCollided() {
		return false;
	}
	
}
