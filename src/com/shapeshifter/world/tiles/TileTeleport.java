package com.shapeshifter.world.tiles;

import com.shapeshifter.gfx.Art;
import com.shapeshifter.gfx.Bitmap;
import com.shapeshifter.gfx.Screen;
import com.shapeshifter.world.Level;

public class TileTeleport extends Tile {

	public TileTeleport(int id) {
		super(id);
	}
	
	public void render(Level level, int x, int y, Screen screen) {
		Bitmap bitmap = Art.city[0][4];
		
		if(level.getTile(x, y - 1) == Tile.air) bitmap = Art.city[0][2];
		else if(level.getTile(x, y + 1) == Tile.teleport) bitmap = Art.city[0][3];
		
		screen.renderBitmap(x * Tile.SIZE, y * Tile.SIZE, bitmap);
	}

}
