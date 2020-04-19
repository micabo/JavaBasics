package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject {
	static final int WIDTH = 32;
	static final int HEIGHT = 32;
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - Player.WIDTH - 16);
		y = Game.clamp(y, 0, Game.HEIGHT - Player.HEIGHT - 	38); // where is the padding coming from? => frame!
		
		//if (x < 0 || x >= Game.WIDTH - Player.WIDTH) velX = 0;
		//if (y < 0 || y >= Game.HEIGHT - Player.HEIGHT) velY = 0;
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);		
		g.fillRect(x, y, Player.WIDTH, Player.HEIGHT);
	}
}
