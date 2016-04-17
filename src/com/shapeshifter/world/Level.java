package com.shapeshifter.world;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import com.shapeshifter.entities.Entity;
import com.shapeshifter.entities.living.EntityLiving;
import com.shapeshifter.entities.living.EntityMob;
import com.shapeshifter.entities.living.EntityPlayer;
import com.shapeshifter.entities.particles.EntityParticle;
import com.shapeshifter.gfx.Parallax;
import com.shapeshifter.gfx.Screen;
import com.shapeshifter.maths.Vector2i;
import com.shapeshifter.world.tiles.Tile;

public class Level {
	
	private int width, height;
	private Tile[] tiles;
	private Random rand;
	private List<Entity> entities;
	private EntityPlayer player;
	private List<Entity> entitiesToRemove;
	private List<Entity> entitiesToAdd;
	
	private Parallax parallax;
	private boolean loose = false;
	private String filePath;
	private boolean win = false;
	private boolean finish = false;
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		
		this.init();
		this.initLevel();
	}
	
	public Level(String fileName) {
		this.filePath = fileName;
		
		try {
			BufferedImage image = ImageIO.read(Level.class.getResourceAsStream(fileName));
			this.width = image.getWidth();
			this.height = image.getHeight();
			
			this.init();
			int pixels[] = new int[this.width * this.height];
			
			image.getRGB(0, 0, this.width, this.height, pixels, 0, this.width);
			for(int i = 0; i < this.width; i++) {
				for(int j = 0; j < this.height; j++) {
					int pix = pixels[i + j * width];
					if(pix == 0xff000000) this.player.setPosition(i * Tile.SIZE, j * Tile.SIZE);
					if(pix == 0xff0000ff) this.addEntityAt(new EntityMob(this), i * Tile.SIZE, j * Tile.SIZE);
					if(pix == 0xffff0000) {
						this.addTile(i, j, Tile.building);
						if(rand.nextFloat() <= 0.2f && this.getTile(i, j - 1) != Tile.air) this.addTile(i, j, Tile.window);
					}
					else if(pix == 0xff00ff00) this.addTile(i, j, Tile.teleport);
					else this.addTile(i, j, Tile.air);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addEntityAt(Entity entity, int x, int y) {
		entity.setPosition(x, y);
		this.entities.add(entity);
	}
	
	private void init() {
		this.rand = new Random();
		this.parallax = new Parallax(2);
		this.tiles = new Tile[this.width * this.height];
		this.entities = new ArrayList<Entity>();
		this.player = new EntityPlayer(this);
		this.entities.add(player);
		this.entitiesToRemove = new ArrayList<Entity>();
		this.entitiesToAdd = new ArrayList<Entity>();
	}
	
	private void addTile(int x, int y, Tile tile) {
		this.tiles[x + y * this.width] = tile;
	}
	
	private void initLevel() {
		for(int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.height; j++) {
				this.tiles[i + j * this.width] = Tile.air;
			}
		}
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void render(Screen screen) {
		int xo = this.player.getPos().x - 5 * Tile.SIZE;
		if(this.player.getPos().x < 5 * Tile.SIZE) xo = 0;
		int yo = this.player.getPos().y - 4 * Tile.SIZE;
		if(yo > this.height * Tile.SIZE) yo = this.height * Tile.SIZE;
		screen.setOffset(xo, yo);
		
		this.parallax.render(screen);
		
		this.renderTile(screen);
		this.renderEntities(screen);
	}
	
	private void renderTile(Screen screen) {
		for(int i = 0; i < this.width; i++) {
			for(int j = 0; j < this.height; j++) {
				this.tiles[i + j * this.width].render(this, i, j, screen);
			}
		}
	}
	
	private void renderEntities(Screen screen) {
		for(Entity e : this.entities) {
			e.render(screen);
		}
	}
	
	public void update() {
		this.entities.removeAll(entitiesToRemove);
		this.updateEntities();
		this.entities.addAll(entitiesToAdd);
	}
	
	private void updateEntities() {
		for(Entity e : this.entities) {
			e.update();
			
			if(e instanceof EntityLiving && !(e instanceof EntityPlayer)) {
				EntityLiving entity = (EntityLiving) e;
				if(entity.isDead()) {
					this.removeEntity(entity);
				}
			}
			
			if(e instanceof EntityParticle) {
				EntityParticle entity = (EntityParticle) e;
				if(!entity.isAlive()) {
					this.removeEntity(entity);
				}
			}
		}
	}
	
	public void addEntity(Entity e) {
		this.entitiesToAdd.add(e);
	}
	
	public void addParticle(int x, int y, EntityParticle particle) {
		particle.setPosition(x, y);
		this.addEntity(particle);
	}
	
	public void removeEntity(Entity e) {
		this.entitiesToRemove.add(e);
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= this.width || y >= this.height) return Tile.air;
		return this.tiles[x + y * this.width];
	}
	
	public List<Entity> getEntities() {
		return this.entities;
	}
	
	public void gameOver() {
		this.loose = true;
	}
	
	public boolean isLoosing() {
		return this.loose;
	}
	
	public String getFileName() {
		return this.filePath;
	}
	
	public void win() {
		this.win = true;
	}
	
	public boolean getWin() {
		return this.win;
	}
	
	public String nextLevel() {
		if(this.filePath.equals("/levels/test.png")) {
			return "/levels/level1.png";
		}
		this.finish = true;
		return "";
	}
	
	public boolean isFinished() {
		return this.finish;
	}
}
