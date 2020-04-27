package jgame;

import java.awt.Color;
import java.util.Random;

enum GameObjectType {
	PLAYER,
	BASICENEMY,
	SMARTENEMY,
	TRAIL;
}

public class GameObjectFactory {

	public static GameObject createGameObject(GameObjectType type, Handler handler, float x, float y) {
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
			g = new Trail(x, y, Color.WHITE, handler);
			break;
		default:
			g = null;
		}
		return g;
	}
	
	public static GameObject createGameObject(GameObjectType type, Handler handler, float x, float y, Color color) {
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
			g = new Trail(x, y, color, handler);
			break;
		default:
			g = null;
		}
		return g;
	}
	
}
