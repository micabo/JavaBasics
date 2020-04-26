package jgame;

import java.awt.Graphics;
import java.awt.Rectangle;

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
		return (float) Math.sqrt(vx * vx + vy * vy);
	}
}
