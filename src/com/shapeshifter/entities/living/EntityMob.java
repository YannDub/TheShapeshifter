package com.shapeshifter.entities.living;

import com.shapeshifter.gfx.Animation;
import com.shapeshifter.gfx.Art;
import com.shapeshifter.gfx.Screen;
import com.shapeshifter.world.Level;

public class EntityMob extends EntityLiving {

	private Animation idle;
	
	public EntityMob(Level level) {
		super(level);
		this.texture = Art.bandit[0][0];
		this.idle = new Animation(10, 3, 0, 3, Art.bandit);
	}
	
	public void render(Screen screen) {
		this.idle.render(this.pos.x, this.pos.y, screen);
	}
	
	public void update() {
		this.idle.play();
	}
}
