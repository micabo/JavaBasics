package jgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

enum Direction {
	UP,
	DOWN,
	LEFT,
	RIGHT,
	HORIZONTAL_ZERO,
	VERTICAL_ZERO;
}

public class KeyInput extends KeyAdapter {
	
	private Player player;
	private boolean[] keyDown = {false, false, false, false};
	
	private Menu menu;
	
	public KeyInput(Handler handler, Menu menu) {
		this.player = handler.getPlayer();
		this.menu = menu;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		// escape exits the game
		if (key == KeyEvent.VK_ESCAPE) System.exit(0);
		
		if (key == KeyEvent.VK_ENTER) menu.enterActive();
		
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) menu.selectionToggle();
		
		// keyEvents to steer the player
		if (key == KeyEvent.VK_W) {
			player.move(Direction.UP);
			keyDown[0] = true;
		}
		if (key == KeyEvent.VK_S) {
			player.move(Direction.DOWN);
			keyDown[1] = true;
		}
		if (key == KeyEvent.VK_A) {
			player.move(Direction.LEFT);
			keyDown[2] = true;
		}
		if (key == KeyEvent.VK_D) {
			player.move(Direction.RIGHT);
			keyDown[3] = true;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W) keyDown[0] = false;
		if (key == KeyEvent.VK_S) keyDown[1] = false;
		if (key == KeyEvent.VK_A) keyDown[2] = false;
		if (key == KeyEvent.VK_D) keyDown[3] = false;
		
		if (!keyDown[0] && !keyDown[1]) player.move(Direction.VERTICAL_ZERO);
		if (!keyDown[2] && !keyDown[3]) player.move(Direction.HORIZONTAL_ZERO);
	}
}
