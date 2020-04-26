package jgame;

import java.awt.Graphics;
import java.util.LinkedList;


public class Handler {
	// static variables and methods -> singleton
	private static Handler instance = null;
	
	public static Handler getInstance() {
		if (instance == null) {
			instance = new Handler();
		}
		return instance;
	}
	
	public static void freeInstance() {
		instance = null;
	}
	
	// behavior of THE (one, singleton) instance
	LinkedList<GameObject> object;
	
	private Handler() {
		object = new LinkedList<>();
	}
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			object.get(i).tick();
		}
	}
	
	public void render(Graphics g) {
		for (GameObject go : object) {
			go.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public boolean playerIsDead() {
		Player player = (Player) object.getFirst();
		return player.isDead();
	}
}
