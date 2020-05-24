package jgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		if (game.isRunningOrPaused())
			return;
		if (playBtn.clicked(e))
			game.startGame();
		if (quitBtn.clicked(e))
			System.exit(0);
	}
	
	public void enterActive() {
		if (game.isRunningOrPaused())
			return;
		if (playBtn.isActive())
			game.startGame();
		if (quitBtn.isActive())
			System.exit(0);
	}
	
	public void selectionToggle() {
		if (game.isRunningOrPaused())
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
