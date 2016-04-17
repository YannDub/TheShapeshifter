package com.shapeshifter.maths;

public class Vector2i {
	
	public int x, y;
	
	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(Vector2i v) {
		this.x += v.x;
		this.y += v.y;
	}

	public void mult(int n) {
		this.x *= n;
		this.y *= n;
	}

	public int dot(Vector2i v) {
		return this.x * v.x + this.y * v.y;
	}

	public void interpolate(Vector2i v, double time) {
		this.x += (v.x - this.x) * time;
		this.y += (v.y - this.y) * time;
	}

	public void add(Vector2d v) {
		this.x += v.x;
		this.y += v.y;
	}
}
