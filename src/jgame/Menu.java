package jgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import jgame.Game.GameState;

public class Menu extends MouseAdapter {
	
	private Game game;
	private MenuButton playBtn;
	private MenuButton quitBtn;
	
	public Menu(Game game) {
		this.game = game;
		
		playBtn = new MenuButton("Play", new Point(10, 100));
		playBtn.setActive(true);
		
		quitBtn = new MenuButton("Quit", new Point(10, 200));
	}
	
	public void mousePressed(MouseEvent e) {
		if (game.gameState == GameState.STARTED)
			return;
		if (playBtn.clicked(e))
			game.gameState = GameState.STARTED;
		if (quitBtn.clicked(e))
			System.exit(0);
	}
	
	public void enterActive() {
		if (game.gameState == GameState.STARTED)
			return;
		if (playBtn.isActive())
			game.gameState = GameState.STARTED;
		if (quitBtn.isActive())
			System.exit(0);
	}
	
	public void selectionToggle() {
		if (game.gameState == GameState.STARTED)
			return;
		playBtn.setActive(!playBtn.isActive());
		quitBtn.setActive(!quitBtn.isActive());
	}
	
	public void mouseReleased(MouseEvent e) {}
	
	public void tick() {}
	
	public void render(Graphics g) {
		Font fnt = new Font("courier", 1, 50);
		g.setFont(fnt);
		g.setColor(Color.WHITE);
		g.drawString("Menu", 250, 50);
		
		playBtn.render(g);
		quitBtn.render(g);
		
		g.setColor(Color.GRAY);
		g.drawString("Steer Player using WASD-keys", 10, 400);
	}
}
