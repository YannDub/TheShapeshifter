package com.shapeshifter.entities.particles;

import com.shapeshifter.gfx.Art;
import com.shapeshifter.maths.Vector2d;
import com.shapeshifter.world.Level;

public class EntityBubble extends EntityParticle {

	public EntityBubble(Level level, int timeToDeath, Vector2d dir, double speed) {
		super(level, timeToDeath, dir, speed);
		this.texture = Art.bubble[0][0];
	}
	
}
