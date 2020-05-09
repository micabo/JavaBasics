package jgame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Rectangle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {
	Player player;
	float x_position = 100.0f;
	float y_position = 0.0f;
	float tolerance = 1e-2f;

	@BeforeEach
	void setUp() throws Exception {
		player = new Player(x_position, y_position, null);
	}

	@Test
	void testCreation() {
		assertEquals(100.0f, player.x, tolerance);
		assertEquals(0.0f, player.vx, tolerance);
	}
	
	@Test
	void testBoundaryEncounter() {
		player.vx = 2_000.0f;
		player.vy = 2_000.0f;
		player.tick();
		assertEquals(Game.WIDTH - Player.WIDTH, player.x, tolerance);
		assertEquals(Game.HEIGHT - Player.HEIGHT, player.y, tolerance);
		assertEquals(0.0f, player.vx, tolerance);
		assertEquals(0.0f, player.vy, tolerance);
		
		player.vx = -2_000.0f;
		player.vy = -2_000.0f;
		player.tick();
		assertEquals(0.0f, player.x, tolerance);
		assertEquals(0.0f, player.y, tolerance);
		assertEquals(0.0f, player.vx, tolerance);
		assertEquals(0.0f, player.vy, tolerance);
	}
	
	@Test
	void testMovement() {
		player.move(Direction.UP);
		assertEquals(-Player.MAXVEL, player.vy);
	}
	
	@Test
	void testGetBounds() {
		Rectangle r = player.getBounds();
		assertEquals(r.width, Player.WIDTH);
		assertEquals(r.height, Player.HEIGHT);
		assertEquals(r.x, (int) x_position);
		assertEquals(r.y, (int) y_position);
	}
	
	@Test
	void testDamage() {
		player.inflictDamage(49);
		assertEquals(51, player.getHealth());
		assertEquals(false, player.isDead());
		
		player.inflictDamage(100);
		assertEquals(0, player.getHealth());
		assertEquals(true, player.isDead());
	}

}
