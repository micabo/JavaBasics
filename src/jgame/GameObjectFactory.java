package jgame;

import java.awt.Color;

enum GameObjectType {
	PLAYER,
	BASICENEMY,
	SMARTENEMY,
	TRAIL;
}

public class GameObjectFactory {
	
	private static Handler handler = Handler.getInstance();

	public static GameObject createGameObject(GameObjectType type, float x, float y) {
		GameObject g;
		switch (type) {
		case PLAYER:
			g = new Player(x, y, handler);
			break;
		case BASICENEMY:
			g = new BasicEnemy(x, y, handler);
			break;
		case SMARTENEMY:
			g = new SmartEnemy(x, y, handler);
			break;
		case TRAIL:
			g = createGameObject(type, x, y, Color.WHITE);
			break;
		default:
			g = null;
		}
		return g;
	}
	
	public static GameObject createGameObject(GameObjectType type, float x, float y, Color color) {
		GameObject g;
		switch (type) {
		case PLAYER:
		case BASICENEMY:
		case SMARTENEMY:
			g = createGameObject(type, x, y);
			break;
		case TRAIL:
			g = new Trail(x, y, color, handler);
			break;
		default:
			g = null;
		}
		return g;
	}
	
}
