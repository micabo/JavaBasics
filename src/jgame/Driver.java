package jgame;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Driver {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Shooter");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		Game game = new Game();
		game.setSize(new Dimension(Game.WIDTH, Game.HEIGHT));
		
		frame.add(game);
		frame.pack();
		frame.setVisible(true);
		
		game.setFocusable(true);
		game.start();
	}
	
}
