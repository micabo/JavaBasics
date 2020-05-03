package jgame;

import static java.lang.Math.sqrt;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

enum ID {
	Player(),
	Enemy(),
	Trail();
}

/**
 * GameObject is the basic type of all moving objects in the Game
 * A GameObject is rather a struct than an object -> no access control
 */
public abstract class GameObject {
	public static Random R = new Random();
	
	public static float randomSignedFloatBetween(float min, float max) {
		return (min + R.nextFloat() * (max - min)) * (R.nextBoolean() ? 1: -1);
	}
	
	protected float x, y;
	protected float vx, vy;
	protected ID id;
	
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public float getVelocity() {
		return (float) sqrt(vx * vx + vy * vy);
	}
	
}
