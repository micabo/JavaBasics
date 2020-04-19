package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<GameObject> object = new LinkedList<>();
	
	public void tick() {
		for (GameObject go : object) {
			go.tick();
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
}
