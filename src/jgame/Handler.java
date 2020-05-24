package jgame;

import static jgame.GameObjectFactory.createGameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;


public class Handler {
	// static variables and methods -> singleton
	private static Handler instance = null;
	private static Random R = new Random();
	
	public static synchronized Handler getInstance() {
		if (instance == null) instance = new Handler();
		return instance;
	}
	
	public static synchronized void freeInstance() {
		instance = null;
	}
	
	// behavior of THE (one, singleton) instance
	LinkedList<GameObject> object;
	Player player;
	
	private Handler() {
		object = new LinkedList<>();
	}
	
	public void clear() {
		object.clear();
		player = null;
	}
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			object.get(i).tick();
		}
		player.tick();
	}
	
	public void render(Graphics g) {
		for (GameObject go : object) {
			go.render(g);
		}
		player.render(g);
	}
	
	public void detectCollision() {
		Rectangle playerBounds = player.getBounds();
		for (GameObject gameObject : object) {
			if (gameObject.id == ID.Enemy &&
				playerBounds.intersects(gameObject.getBounds())) {
				int damage = (int) gameObject.getVelocity();
				player.inflictDamage(damage);
			}
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void spawnPlayer() {
		if (player == null)
			player = (Player) createGameObject(GameObjectType.PLAYER, 100.0f, 100.0f);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public boolean playerIsDead() {
		return player.isDead();
	}
	
	public void spawnEnemy(GameObjectType type) {
		int x = R.nextInt(Game.WIDTH);
		int y = R.nextInt(Game.HEIGHT);
		addObject(createGameObject(type, x, y));
		SoundPlayer.playSound();
	}
	
	public void addTrail(float x, float y, Color color) {
		addObject(createGameObject(GameObjectType.TRAIL, x, y, color));
	}
}
