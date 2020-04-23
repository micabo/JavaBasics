package jgame;

import java.awt.Graphics;
import java.awt.Rectangle;

enum ID {
	Player(),
	Enemy(),
	Trail();
}

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
