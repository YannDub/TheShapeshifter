package com.shapeshifter.maths;

public class BoundingBox {
	
	private Vector2i src, size;
	
	public BoundingBox(Vector2i src, Vector2i size) {
		this.src = src;
		this.size = size;
	}
	
	public boolean collided(BoundingBox box) {
		int x1 = this.src.x;
		int y1 = this.src.y;
		int w1 = this.src.x + this.size.x;
		int h1 = this.src.y + this.size.y;
		int x2 = box.src.x;
		int y2 = box.src.y;
		int w2 = box.src.x + box.size.x;
		int h2 = box.src.y + box.size.y;
		
		return x1 < w2 && w1 > x2 && y1 < h2 && h1 > y2;
	}
	
	public void setPosition(int x, int y) {
		this.src.x = x;
		this.src.y = y;
	}
}
