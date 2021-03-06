package jgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {
	static final int WIDTH = 16;
	static final int HEIGHT = 16;
	static final float MINVEL = 1.0f;
	static final float MAXVEL = 2.0f;
	
	private Handler handler;
	private Player player;
	private Color color;
	
	public SmartEnemy(float x, float y, Handler handler) {
		super(x, y, ID.Enemy);
		this.handler = handler;
		this.player = handler.getPlayer();
		
		vx = GameObject.randomSignedFloatBetween(MINVEL, MAXVEL);
		vy = GameObject.randomSignedFloatBetween(MINVEL, MAXVEL);
		
		float velocity = getVelocity();
		float maxVelocity = (float) Math.sqrt(2 * MAXVEL * MAXVEL);
		float minVelocity = (float) Math.sqrt(2 * MINVEL * MINVEL);
		float hue = (velocity - minVelocity) / (maxVelocity - minVelocity);
		color = new Color((int) (hue * 255), 0, (int) ((1 - hue) * 255));
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, WIDTH, HEIGHT);
	}

	@Override
	public void tick() {
		addTrailToHandler();
		aimAtPlayer();
		updatePosition();
	}
	
	private void addTrailToHandler() {
		handler.addTrail(x, y, color);
	}
	
	private void aimAtPlayer() {
		float delta_x = player.x - x;
		float delta_y = player.y - y;
		float distance = (float) Math.sqrt(delta_x * delta_x + delta_y * delta_y);

		float v_tot = getVelocity();
		vx =  v_tot * delta_x / distance;
		vy =  v_tot * delta_y / distance;
	}
	
	private void updatePosition() {
		float x_new = x + vx;
		
		if (x_new <= 0.0f) {
			vx *= -1.0f;
			x = -x_new;
		}
		else if (x_new >= Game.WIDTH - WIDTH) {
			vx *= -1.0f;
			x = 2.0f * (Game.WIDTH - WIDTH) - x_new;
			
		}
		else {
			x = x_new;
		}
		
		float y_new = y + vy;
		
		if (y_new <= 0.0f) {
			vy *= -1.0f;
			y = -y_new;
		}
		else if (y_new >= Game.HEIGHT - HEIGHT) {
			vy *= -1.0f;
			y = 2.0f * (Game.HEIGHT - HEIGHT) - y_new;
		}
		else {
			y = y_new;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int) x, (int) y, WIDTH, HEIGHT);
	}
	
}

