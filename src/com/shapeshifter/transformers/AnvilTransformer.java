package com.shapeshifter.transformers;

import java.util.Random;

import com.shapeshifter.entities.living.EntityLiving;
import com.shapeshifter.entities.living.EntityPlayer;
import com.shapeshifter.entities.particles.EntityBubble;
import com.shapeshifter.entities.particles.EntityParticle;
import com.shapeshifter.gfx.Animation;
import com.shapeshifter.gfx.Art;
import com.shapeshifter.maths.Vector2d;

public class AnvilTransformer extends Transformer {

	private Random rand;
	
	public AnvilTransformer(EntityPlayer player) {
		super(player);
		this.rand = new Random();
	}
	
	protected void applyWhenCollided(EntityLiving e) {
		int nbParticle = 16 + this.rand.nextInt(16);
		int middleX = e.getPos().x + e.getSize().x / 2;
		for(int i = 0; i < nbParticle; i++) {
			int sign = rand.nextBoolean() ? 1 : -1;
			Vector2d dir = new Vector2d(0, 0);
			int bottom = e.getPos().y + e.getSize().y;
			EntityParticle particle = new EntityParticle(e.getLevel(), 1024 + rand.nextInt(128), dir, 0.5);
			
			e.getLevel().addParticle(middleX + sign * rand.nextInt(12), bottom - 2 - this.rand.nextInt(5), particle);
		}
		int sign = rand.nextBoolean() ? 1 : -1;
		e.getLevel().addParticle(middleX + this.rand.nextInt(12) * sign, e.getPos().y - 2, new EntityBubble(e.getLevel(), 128, new Vector2d(0, 0), 0));
		e.kill();
	}
	
	public void apply() {
		if(player.isJumping() && player.canTransform()) {			
			this.player.setForce(0, this.player.getForce().y);
			this.player.applyForce(0, 1);
			this.player.setCurrentAnimation(new Animation(Art.anvil));
			this.changeToTransformation();
		}
	}
}
