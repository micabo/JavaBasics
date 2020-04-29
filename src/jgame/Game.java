package jgame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1550691097823471818L;
	
	static final int WIDTH = 640;
	static final int HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private HUD hud;
	
	public Game() {
		handler = Handler.getInstance();
		
		handler.spawnPlayer();
		handler.spawnEnemy(GameObjectType.BASICENEMY);
		
		hud = new HUD(handler);
		this.addKeyListener(new KeyInput(handler));
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		
		// game loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1_000_000_000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while (delta >= 1.0) {
				tick();
				delta--;
			}
			
			render();
			
			frames++;
			
			// print FPS every second
			if (System.currentTimeMillis() - timer > 1_000) {
				timer += 1_000;
				System.out.println("FPS: " + frames);
			}
			
			running = ! handler.playerIsDead();
		}
		render();
		stop();
	}
	
	private void tick() {
		handler.tick();
		handler.detectCollision();
		hud.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		hud.render(g);
		
		if (handler.playerIsDead()) {
			g.setColor(Color.WHITE);
			g.drawString("Game Over", Game.WIDTH/2, Game.HEIGHT/2);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int value, int min, int max) {
		if (value >= max) return max;
		if (value <= min) return min;
		return value;
	}
	
	public static float clamp(float value, float min, float max) {
		if (value >= max) return max;
		if (value <= min) return min;
		return value;
	}
}
