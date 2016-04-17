package com.shapeshifter.entities.particles;

import com.shapeshifter.entities.Entity;
import com.shapeshifter.gfx.Art;
import com.shapeshifter.gfx.Bitmap;
import com.shapeshifter.gfx.Screen;
import com.shapeshifter.maths.Vector2d;
import com.shapeshifter.world.Level;

public class EntityParticle extends Entity {
	
	protected Bitmap texture;
	protected int timeToDeath;
	protected Vector2d dir;
	protected Vector2d apply;
	protected double speed;
	
	public EntityParticle(Level level, int timeToDeath, Vector2d dir, double speed) {
		super(level);
		this.texture = Art.blood;
		this.timeToDeath = timeToDeath;
		this.dir = dir;
		this.apply = new Vector2d(0, 0);
	}
	
	@Override
	public void render(Screen screen) {
		if(this.isAlive()) {
			screen.renderBitmap(this.pos.x, this.pos.y, this.texture);
		}
	}
	
	public boolean isAlive() {
		return this.timeToDeath > 0;
	}
	
	@Override
	public void update() {
		timeToDeath--;
		if(this.apply.y > -0.02) this.apply.add(dir);
		this.pos.add(apply);
	}

}
