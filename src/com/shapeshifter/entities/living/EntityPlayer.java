package com.shapeshifter.entities.living;

import java.awt.event.KeyEvent;

import com.shapeshifter.game.Game;
import com.shapeshifter.gfx.Animation;
import com.shapeshifter.gfx.Art;
import com.shapeshifter.gfx.Screen;
import com.shapeshifter.maths.Vector2i;
import com.shapeshifter.transformers.AnvilTransformer;
import com.shapeshifter.transformers.Transformer;
import com.shapeshifter.world.Level;
import com.shapeshifter.world.tiles.Tile;

public class EntityPlayer extends EntityLiving {
	
	private boolean isTransform;
	private boolean canTransform;
	
	private Animation idle, walk, dead, currentAnimation;
	private Transformer currentTransformer;
	private int transformerCountdown;
	
	public EntityPlayer(Level level) {
		super(level);
		this.speed = 0;
		this.canTransform = true;
		this.isTransform = false;
		this.idle = new Animation(10, 3, 0, 0, Art.player);
		this.walk = new Animation(10, 3, 0, 1, Art.player);
		this.dead = new Animation(20, 3, 0, 2, Art.player);
		this.currentAnimation = this.idle;
		this.currentTransformer = new Transformer(this);
		this.transformerCountdown = 0;
	}
	
	public void render(Screen screen) {
		this.currentAnimation.render(this.pos.x, this.pos.y, screen);
	}
	
	public void update() {
		super.update();
		
		this.currentTransformer.update();
		
		if(transformerCountdown < 0) {
			this.currentTransformer.reinit();
			this.currentTransformer = new Transformer(this);
			this.transformerCountdown = 0;
		}
		
		this.pos.add(new Vector2i(this.speed, 0));
		if(Game.INPUT.isKeyDown(KeyEvent.VK_SPACE) && !isJump && !isTransform && !isDead) {
			this.isJump = true;
			this.jump();
		} else if(Game.INPUT.isKeyDown(KeyEvent.VK_CONTROL)) {
			if(this.canTransform) {
				this.currentTransformer = new AnvilTransformer(this);
				this.transformerCountdown = 128;
			}
		}
		
		if(this.isTransform) transformerCountdown--;
		
		if(this.level.getTile((int) (this.pos.x + this.size.x) / Tile.SIZE, this.pos.y / Tile.SIZE).canBeCollided()) {
			this.pos.x -= 8;
			this.applyForce.x = 0;
			this.canMove = false;
			this.kill();
			this.applyForce(-0.5, -0.5);
		} else if(this.applyForce.x < 5 && this.canMove) this.applyForce(0.05, 0);
		
		if(this.canMove && !this.isTransform) this.currentAnimation = this.walk;
		else if(!this.isTransform) this.currentAnimation = this.idle;
		
		if(this.pos.y > this.level.getHeight() * Tile.SIZE) {
			this.isDead = true;
		}
		
		if(this.level.getTile((int) (this.pos.x + this.size.x) / Tile.SIZE, this.pos.y / Tile.SIZE).equals(Tile.teleport)) {
			this.level.win();
			return;
		}
		
		if(this.isDead) {
			this.canMove = false;
			this.currentAnimation = this.dead;
			this.level.gameOver();
		}
		
		if(transformerCountdown > 0) this.currentTransformer.apply();
		this.currentAnimation.play();
	}
	
	public void canTransform(boolean transform) {
		this.canTransform = transform;
	}
	
	public boolean canTransform() {
		return this.canTransform;
	}
	
	public boolean isTransform() {
		return this.isTransform;
	}
	
	public void tranform(boolean transform) {
		this.isTransform = transform;
	}
	
	public void setCurrentAnimation(Animation anim) {
		this.currentAnimation = anim;
	}
}
