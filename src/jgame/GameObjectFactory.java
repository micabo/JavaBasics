package jgame;

import java.awt.Color;

enum GameObjectType {
	PLAYER,
	BASIC_ENEMY,
	SMART_ENEMY,
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
		case BASIC_ENEMY:
			g = new BasicEnemy(x, y, handler);
			break;
		case SMART_ENEMY:
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
		case BASIC_ENEMY:
		case SMART_ENEMY:
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
