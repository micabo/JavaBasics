package jgame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;


public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1550691097823471818L;
	
	static final int WIDTH = 640;
	static final int HEIGHT = WIDTH / 12 * 9;
	
	static Random r = new Random();
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private HUD hud;
	
	public Game() {
		handler = new Handler();
		handler.addObject(new Player(100, 100, handler));
		
		for (int i = 0; i < 20; i++)
			handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT)));
		
		hud = new HUD();
		
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH, HEIGHT, "Let's build a Game", this);
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
			
			if (running) // is this needed?!
				render();
			
			frames++;
			
			// print FPS every second
			if (System.currentTimeMillis() - timer > 1_000) {
				timer += 1_000;
				// System.out.println("FPS: " + frames);
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
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
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if (var >= max) return max;
		if (var <= min) return min;
		return var;
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
