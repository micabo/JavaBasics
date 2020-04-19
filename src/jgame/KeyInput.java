package jgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ESCAPE) System.exit(0);
		// System.out.println(key);
		
		for (var gameObject : handler.object) {
			if (gameObject.id == ID.Player) {
				// all actions of "Player"
				if (key == KeyEvent.VK_W) {
					gameObject.velY = -5;
					break;
				}
				if (key == KeyEvent.VK_S) {
					gameObject.velY = 5;
					break;
				}
				if (key == KeyEvent.VK_A) {
					gameObject.velX = -5;
					break;
				}
				if (key == KeyEvent.VK_D) {
					gameObject.velX = 5;
					break;
				}
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e) {}
}
