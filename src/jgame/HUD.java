package jgame;

import java.awt.Color;
import java.awt.Graphics;


public class HUD {
	private long score = 0;
	private int level = 1;
	
	private Handler handler;
	
	public HUD(Handler handler) {
		this.handler = handler;
	}
	
	public void tick() {		
		score++;
		if (score % 200 == 0) {
			level++;
			if (level % 4 == 0) handler.spawnEnemy(GameObjectType.SMARTENEMY);
			else handler.spawnEnemy(GameObjectType.BASICENEMY);
		}
	}
	
	public void render(Graphics g) {
		int health = handler.getPlayer().getHealth();
		
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(255 * (1 - health / 100), 255 * health / 100, 0));
		g.fillRect(15, 15, health * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
		g.drawString("Score: " + score, 10, 64);
		g.drawString("Level: " + level, 10, 80);
	}
}
