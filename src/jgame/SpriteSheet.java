package jgame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private BufferedImage sprite;
	
	private SpriteSheet(BufferedImage image) {
		this.sprite = image;
	}
	
	public static SpriteSheet createFromFile(String path) {
		SpriteSheet sheet = null;
		try {
			sheet = new SpriteSheet(ImageIO.read(new File(path)));
		}
		catch (IOException e) {
			System.err.println("Could not load requested image.");
		}
		return sheet;
	}
	
	public BufferedImage grabImage(int row, int col, int width, int height) {
		return sprite.getSubimage(32 * (row - 1) , 32 * (col - 1), width, height);
	}

}
