package jgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class SmartEnemy extends GameObject {
	static final int WIDTH = 16;
	static final int HEIGHT = 16;
	static final float MAXVEL = 5.0f;
	
	static final Random R = new Random();
	
	private Handler handler;
	private Color color;
	
	public SmartEnemy(float x, float y, Handler handler) {
		super(x, y, ID.Enemy);
		this.handler = handler;
		
		do {
			vx = MAXVEL * (R.nextFloat() - 0.5f);
		} while (Math.abs(vx) < 0.5f);
		do {
			vy = MAXVEL * (R.nextFloat() - 0.5f);
		} while (Math.abs(vy) < 0.5f);
		
		float velocity = getVelocity();
		float maxVelocity = (float) Math.sqrt(2 * MAXVEL * MAXVEL);
		float hue = velocity / maxVelocity;
		color = new Color((int) (hue * 255), 0, (int) ((1 - hue) * 255));
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, WIDTH, HEIGHT);
	}

	@Override
	public void tick() {
		addTrail();
		aimAtPlayer();
		updatePosition();
	}
	
	private void addTrail() {
		handler.addObject(new Trail(x, y, color, handler));
	}
	
	private void aimAtPlayer() {
		Player player = (Player) handler.object.getFirst();
		
		float delta_x = player.x - x;
		float delta_y = player.y - y;
		float distance = (float) Math.sqrt(delta_x * delta_x + delta_y * delta_y);

		float v_tot = getVelocity();
		vx = delta_x * v_tot / distance;
		vy = delta_y * v_tot / distance;
	}
	
	private void updatePosition() {
		float x_new = x + vx;
		
		if (x_new <= 0.0f) {
			vx *= -1.0f;
			x = -x_new;
		}
		else if (x_new >= Game.WIDTH - BasicEnemy.WIDTH) {
			vx *= -1.0f;
			x = 2.0f * (Game.WIDTH - BasicEnemy.WIDTH) - x_new;
			
		}
		else {
			x = x_new;
		}
		
		float y_new = y + vy;
		
		if (y_new <= 0.0f) {
			vy *= -1.0f;
			y = -y_new;
		}
		else if (y_new >= Game.HEIGHT - BasicEnemy.HEIGHT) {
			vy *= -1.0f;
			y = 2.0f * (Game.HEIGHT - BasicEnemy.HEIGHT) - y_new;
		}
		else {
			y = y_new;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int) x, (int) y, BasicEnemy.WIDTH, BasicEnemy.HEIGHT);
	}
}

