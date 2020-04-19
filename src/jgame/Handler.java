package jgame;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<GameObject> object = new LinkedList<>();
	
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject go = object.get(i);
			go.tick();
		}
	}
	
	public void render(Graphics g) {
		for (var go : object) {
			go.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
}
