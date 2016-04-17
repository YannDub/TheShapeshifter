package com.shapeshifter.world.tiles;

import com.shapeshifter.gfx.Art;
import com.shapeshifter.gfx.Screen;
import com.shapeshifter.world.Level;

public class TileBuildingWindow extends Tile {

	public TileBuildingWindow(int id) {
		super(id);
	}
	
	public void render(Level level, int x, int y, Screen screen) {
		screen.renderBitmap(x * SIZE, y * SIZE, Art.city[1][1]);
	}
	
}
