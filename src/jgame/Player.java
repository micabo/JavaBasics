package jgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends GameObject {
	static final int WIDTH = 32;
	static final int HEIGHT = 32;
	
	Handler handler;
	
	public Player(int x, int y, Handler handler) {
		super(x, y, ID.Player);
		this.handler = handler;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - Player.WIDTH);
		y = Game.clamp(y, 0, Game.HEIGHT - Player.HEIGHT);
		
		collision();
	}
	
	private void collision() {
		Rectangle playerBounds = getBounds();
		for (var gameObject : handler.object) {
			if (gameObject.id == ID.BasicEnemy) {
				if (playerBounds.intersects(gameObject.getBounds())) {
					HUD.HEALTH--;
				}
			}
		}
	}
	
	@Override
	public void render(Graphics g) {
		// this is for the collision boundary
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.green);
		g2d.draw(getBounds());
		
		//g.setColor(Color.white);		
		//g.fillRect(x, y, Player.WIDTH, Player.HEIGHT);
	}
}
