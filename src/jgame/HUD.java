package jgame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class HUD {
	private int greenValue = 255;
	
	private static Random R = new Random();
	
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
			handler.addObject(new BasicEnemy(R.nextInt(Game.WIDTH), R.nextInt(Game.HEIGHT), handler));
		}
	}
	
	public void render(Graphics g) {
		Player player = (Player) handler.object.getFirst();
		int health = player.getHealth();
		
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(255 * (1 - health / 100), greenValue * health / 100, 0));
		g.fillRect(15, 15, health * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
		g.drawString("Score: " + score, 10, 64);
		g.drawString("Level: " + level, 10, 80);
	}
}
