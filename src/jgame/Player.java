package jgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends GameObject {
	static final int WIDTH = 32;
	static final int HEIGHT = 32;
	private static final int MAXVEL = 8;
	
	Handler handler;
	private int health;
	
	public Player(int x, int y, Handler handler) {
		super(x, y, ID.Player);
		this.handler = handler;
		health = 100;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	@Override
	public void tick() {
		x += vx;
		y += vy;
		
		x = Game.clamp(x, 0, Game.WIDTH - Player.WIDTH);
		y = Game.clamp(y, 0, Game.HEIGHT - Player.HEIGHT);
		
		// reset velocity when hitting a wall
		if (x == 0 || x == Game.WIDTH - Player.WIDTH) vx = 0;
		if (y == 0 || y == Game.HEIGHT - Player.HEIGHT) vy = 0;
		
		collision();
	}
	
	private void collision() {
		Rectangle playerBounds = getBounds();
		for (GameObject gameObject : handler.object) {
			if (gameObject.id == ID.Enemy &&
					playerBounds.intersects(gameObject.getBounds())) {
				BasicEnemy tmp = (BasicEnemy) gameObject;
				int damage = (int) tmp.getVelocity();
				health = health - damage > 0 ? health - damage : 0;
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
	
	public void move(Direction d) {
		switch (d) {
		case UP:
			vy = -MAXVEL;
			break;
		case DOWN:
			vy = MAXVEL;
			break;
		case VERTICAL_ZERO:
			vy = 0;
			break;
		case LEFT:
			vx = -MAXVEL;
			break;
		case RIGHT:
			vx = MAXVEL;
			break;
		case HORIZONTAL_ZERO:
			vx = 0;
			break;
		}
	}
	
	public int getHealth() {
		return health;
	}
	
	public boolean isDead() {
		return health <= 0;
	}
}
