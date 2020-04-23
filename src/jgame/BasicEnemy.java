package jgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BasicEnemy extends GameObject {
	static final int WIDTH = 16;
	static final int HEIGHT = 16;
	
	static Random R = new Random();
	
	private Handler handler;
	private Color color;
	
	public BasicEnemy(int x, int y, Handler handler) {
		super(x, y, ID.Enemy);
		this.handler = handler;
		
		do {
			vx = R.nextInt(10) - 5;
		} while(vx == 0);
		do {
			vy = R.nextInt(10) - 5;
		} while(vy == 0);
		
		double velocity = Math.sqrt((double) vx * vx + vy * vy);
		double maxVelocity = Math.sqrt(50);
		double hue = velocity / maxVelocity;
		color = new Color((int) (hue * 255), (int) ((1 - hue) * 255), 0);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	@Override
	public void tick() {
		handler.addObject(new Trail(x, y, color, handler));
		
		// SR -> interpolation would be a good idea
		
		x += vx;
		y += vy;
		
		if (x <= 0 || x >= Game.WIDTH - BasicEnemy.WIDTH) vx *= -1;
		if (y <= 0 || y >= Game.HEIGHT - BasicEnemy.HEIGHT) vy *= -1;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, BasicEnemy.WIDTH, BasicEnemy.HEIGHT);
	}
}
