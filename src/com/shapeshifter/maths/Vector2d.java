package com.shapeshifter.maths;

public class Vector2d {
	
	public double x, y;
	
	public Vector2d(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(Vector2d v) {
		this.x += v.x;
		this.y += v.y;
	}

	public void mult(double n) {
		this.x *= n;
		this.y *= n;
	}

	public double dot(Vector2d v) {
		return this.x * v.x + this.y * v.y;
	}

	public void interpolate(Vector2d v, double time) {
		this.x += (v.x - this.x) * time;
		this.y += (v.y - this.y) * time;
	}
	
	
}
