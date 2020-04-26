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
	protected int x, y;
	protected int vx, vy;
	protected ID id;
	
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
}
