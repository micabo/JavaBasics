package jgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BasicEnemy extends GameObject {
	static final int WIDTH = 16;
	static final int HEIGHT = 16;
	
	static final Random R = new Random();
	
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
		
		double velocity = getVelocity();
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
		updatePosition();
	}
	
	private void updatePosition() {
		int x_new = x + vx;
		
		if (x_new <= 0) {
			vx *= -1;
			x = 0 - x_new;
		}
		else if (x_new >= Game.WIDTH - BasicEnemy.WIDTH) {
			vx *= -1;
			x = 2 * (Game.WIDTH - BasicEnemy.WIDTH) - x_new;
			
		}
		else {
			x = x_new;
		}
		
		int y_new = y + vy;
		
		if (y_new <= 0) {
			vy *= -1;
			y = 0 - y_new;
		}
		else if (y_new >= Game.HEIGHT - BasicEnemy.HEIGHT) {
			vy *= -1;
			y = 2 * (Game.HEIGHT - BasicEnemy.HEIGHT) - y_new;
		}
		else {
			y = y_new;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, BasicEnemy.WIDTH, BasicEnemy.HEIGHT);
	}
	
	public double getVelocity() {
		return Math.sqrt((double) vx * vx + vy * vy);
	}
}
