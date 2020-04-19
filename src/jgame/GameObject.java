package jgame;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected int x, y;
	protected int velX, velY;
	
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
