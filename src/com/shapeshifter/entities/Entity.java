package com.shapeshifter.entities;

import com.shapeshifter.gfx.Screen;
import com.shapeshifter.maths.BoundingBox;
import com.shapeshifter.maths.Vector2i;
import com.shapeshifter.world.Level;

public abstract class Entity {
	
	protected Level level;
	protected Vector2i pos;
	protected Vector2i size;
	protected BoundingBox box;
	
	public Entity(Level level) {
		this.level = level;
		this.pos = new Vector2i(0, 0);
		this.size = new Vector2i(16,16);
		this.box = new BoundingBox(this.pos, new Vector2i(this.size.x, this.size.y));
	}
	
	public abstract void render(Screen screen);
	
	public abstract void update();
	
	public void setPosition(int x, int y) {
		this.pos.x = x;
		this.pos.y = y;
		this.box.setPosition(this.pos.x, this.pos.y);
	}
	
	public Vector2i getPos() {
		return this.pos;
	}
	
	public Vector2i getSize() {
		return this.size;
	}

	public BoundingBox getBox() {
		return box;
	}
	
	public Level getLevel() {
		return this.level;
	}
}
