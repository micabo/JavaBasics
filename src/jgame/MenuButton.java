package jgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

public class MenuButton {
	public static int FONT_SIZE = 30;
	public static Font FNT;
	public static int WIDTH = 200;
	public static int HEIGHT = 64;
	
	static {
		FNT = new Font("courier", 1, 30);
	}
	
	private String label;
	private Point location;
	private boolean isActive;
	
	public MenuButton(String label, Point location) {
		this.label = label;
		this.location = location;
		this.isActive = false;
	}
	
	public boolean clicked(MouseEvent e) {
		Point p = e.getPoint();
		return p.x > location.x && p.x < location.x + WIDTH && p.y > location.y && p.y < location.y + HEIGHT;
	}
	
	public void setActive(boolean b) {
		isActive = b;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.drawRect(location.x, location.y,  WIDTH, HEIGHT);
		
		if (isActive) {
			g.setColor(new Color(0.5f, 0.5f, 0.5f, 0.5f));
			g.fillRect(location.x, location.y,  WIDTH, HEIGHT);
		}
		
		g.setFont(FNT);
		g.setColor(Color.WHITE);
		g.drawString(label, location.x + 10, location.y + HEIGHT/2 + FONT_SIZE/2);
	}

}
