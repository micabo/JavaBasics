package jgame;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Driver {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Shooter");
		//frame.setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
		//frame.setMaximumSize(new Dimension(Game.WIDTH, Game.HEIGHT));
		frame.setMinimumSize(new Dimension(Game.WIDTH + 16, Game.HEIGHT + 38));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		Game game = new Game();
		
		frame.add(game);
		frame.setVisible(true);
		game.setFocusable(true);
		game.start();
	}
	
}
