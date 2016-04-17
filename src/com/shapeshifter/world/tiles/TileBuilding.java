package com.shapeshifter.world.tiles;

import com.shapeshifter.gfx.Art;
import com.shapeshifter.gfx.Bitmap;
import com.shapeshifter.gfx.Screen;
import com.shapeshifter.world.Level;

public class TileBuilding extends Tile {
	
	public TileBuilding(int id) {
		super(id);
	}

	@Override
	public void render(Level level, int x, int y, Screen screen) {
		Bitmap bitmap = Art.city[0][1];
		
		if(level.getTile(x, y - 1).equals(Tile.air)) bitmap = Art.city[0][0];
		
		screen.renderBitmap(x * SIZE, y * SIZE, bitmap);
	}
	
	@Override
	public boolean canBeCollided() {
		return true;
	}
}
