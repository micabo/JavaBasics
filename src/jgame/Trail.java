package jgame;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject {
	static final int WIDTH = 16;
	static final int HEIGHT = 16;
	
	private float alpha = 1;
	private Handler handler;
	private Color color;

	public Trail(float x, float y, Color color, Handler handler) {
		super(x, y, ID.Trail);
		this.color = color;
		this.handler = handler;
	}

	@Override
	public void tick() {
		if (alpha > 0.1) alpha -= 0.02;
		else handler.removeObject(this);
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g.setColor(color);
		g.fillRect((int) x, (int) y, WIDTH, HEIGHT);
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return AlphaComposite.getInstance(type, alpha); 
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
