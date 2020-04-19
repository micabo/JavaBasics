package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;

public class BasicEnemy extends GameObject {
	static final int WIDTH = 16;
	static final int HEIGHT = 16;
	
	public BasicEnemy(int x, int y, ID id) {
		super(x, y, id);
		
		velX = 5;
		velY = 5;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if (x <= 0 || x + WIDTH >= Game.WIDTH) {
			velX *= -1;
		}
		if (y <= 0 || y + HEIGHT >= Game.HEIGHT) {
			velY *= -1;
		}

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
}
