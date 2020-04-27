package jgame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {
	Player player;
	float tolerance = 0.01f;

	@BeforeEach
	void setUp() throws Exception {
		player = new Player(100.0f, 0.0f, null);
	}

	@Test
	void testCreation() {
		assertEquals(100.0f, player.x, tolerance);
		assertEquals(0.0f, player.vx, tolerance);
	}
	
	@Test
	void testBoundaryEncounter() {
		player.vx = 1000.0f;
		player.vy = 1000.0f;
		player.tick();
		assertEquals(608.0f, player.x, tolerance); // 640 - 32
		assertEquals(445.0f, player.y, tolerance); // why not 480 - 32?!
		assertEquals(0.0f, player.vx, tolerance);
		assertEquals(0.0f, player.vy, tolerance);
		
		player.vx = -1000.0f;
		player.tick();
		assertEquals(0.0f, player.x, tolerance);
		assertEquals(0.0f, player.vx, tolerance);
	}
	
	@Test
	void testMovement() {
		player.move(Direction.UP);
		assertEquals(-Player.MAXVEL, player.vy);
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
