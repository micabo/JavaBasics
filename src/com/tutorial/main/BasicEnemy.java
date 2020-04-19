package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class BasicEnemy extends GameObject {
	static final int WIDTH = 16;
	static final int HEIGHT = 16;
	
	static Random r = new Random();
	
	public BasicEnemy(int x, int y, ID id) {
		super(x, y, id);
		
		velX = r.nextInt(10) - 5;
		velY = r.nextInt(10) - 5;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if (x <= 0 || x >= Game.WIDTH - BasicEnemy.WIDTH - 16) velX *= -1;
		if (y <= 0 || y >= Game.HEIGHT - BasicEnemy.HEIGHT - 38) velY *= -1;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, BasicEnemy.WIDTH, BasicEnemy.HEIGHT);
	}
}
