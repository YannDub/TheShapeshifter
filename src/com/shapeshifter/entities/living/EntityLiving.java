package com.shapeshifter.entities.living;

import com.shapeshifter.entities.Entity;
import com.shapeshifter.gfx.Art;
import com.shapeshifter.gfx.Bitmap;
import com.shapeshifter.gfx.Screen;
import com.shapeshifter.maths.Vector2d;
import com.shapeshifter.world.Level;
import com.shapeshifter.world.tiles.Tile;

public class EntityLiving extends Entity {
	
	protected Bitmap texture;
	protected int speed;
	protected Vector2d applyForce;
	protected boolean isFall;
	protected boolean isJump;
	protected boolean canMove;
	protected boolean isDead;
	
	public EntityLiving(Level level) {
		super(level);
		this.texture = Art.test;
		this.speed = 0;
		this.applyForce = new Vector2d(0, 0);
		this.isFall = false;
		this.isJump = false;
		this.canMove = true;
		this.isDead = false;
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderBitmap(this.pos.x, this.pos.y, this.texture);
	}
	
	public void kill() {
		this.isDead = true;
	}
	
	public boolean isDead() {
		return this.isDead;
	}
	
	@Override
	public void update() {
		if(this.isDead) {
			this.canMove = false;
		}
		
		if(!this.canMove && !isFall) this.applyForce.x = 0;
		
		this.pos.add(this.applyForce); 

		this.fall();
		this.box.setPosition(this.pos.x, this.pos.y);
	}
	
	public boolean isCollidedWithEntity(Entity entity) {
		return this.getBox().collided(entity.getBox());
	}
	
	protected void fall() {
		if(!this.level.getTile(this.pos.x / Tile.SIZE, this.pos.y / Tile.SIZE + 1).canBeCollided()) {			
			this.isFall = true;
			if(this.applyForce.y > 4) this.applyForce.y = 4;
			this.applyForce.y += 5.0 / 60;
		} else {
			this.applyForce.y = 0;
			this.isFall = false;
			this.isJump = false;
		}
	}
	
	public void applyForce(double x, double y) {
		this.applyForce.x += x;
		this.applyForce.y += y;
	}
	
	public void setForce(double x, double y) {
		this.applyForce.x = x;
		this.applyForce.y = y;
	}
	
	public Vector2d getForce() {
		return this.applyForce;
	}
	
	protected void jump() {
		this.applyForce.y -= 2;
	}
	
	public boolean isJumping() {
		return this.isJump;
	}
	
	public boolean isFalling() {
		return this.isFall;
	}
	
	public void canMove(boolean move) {
		this.canMove = move;
	}
}
