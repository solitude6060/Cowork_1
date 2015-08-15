package Frame;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage img;
	
	public SpriteSheet(BufferedImage img) {
		this.img = img;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height) {
		return img.getSubimage((col - 1) * width, (row - 1) * height, width, height);
	}
	
}
