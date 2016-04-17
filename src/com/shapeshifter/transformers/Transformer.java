package com.shapeshifter.transformers;

import com.shapeshifter.entities.Entity;
import com.shapeshifter.entities.living.EntityLiving;
import com.shapeshifter.entities.living.EntityPlayer;

public class Transformer {
	
	protected EntityPlayer player;
	
	public Transformer(EntityPlayer player) {
		this.player = player;
	}
	
	public void update() {
		for(Entity e : this.player.getLevel().getEntities()) {
			if(e instanceof EntityLiving) {
				EntityLiving entity = (EntityLiving) e;
				if(this.player.isCollidedWithEntity(entity) && !entity.equals(player)) {
					this.applyWhenCollided(entity);
					return;
				}
			}
		}
	}
	
	protected void applyWhenCollided(EntityLiving e) {
		this.player.kill();
		this.player.applyForce(-0.5, -0.5);
	}
	
	public void apply() {
		
	}
	
	public void reinit() {
		this.changeToInitial();
	}
	
	protected void changeToTransformation() {
		this.player.canMove(false);
		this.player.canTransform(false);
		this.player.tranform(true);
	}
	
	protected void changeToInitial() {
		this.player.canMove(true);
		this.player.canTransform(true);
		this.player.tranform(false);
	}
}
