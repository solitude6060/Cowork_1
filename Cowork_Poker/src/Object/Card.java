package Object;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import Frame.GameObject;
import Frame.ObjectId;
import Frame.SpriteSheet;

public class Card extends GameObject {

	public final static int DEFAULT_WIDTH = 125, DEFAULT_HEIGHT = 181;
	
	private int number, suit; 

	private BufferedImage img;


	public Card(float x, float y, ObjectId id, int number, int suit, SpriteSheet ss) {
		super(x, y, id);
		this.number = (number + 12) % 13 + 1;
		this.suit = suit;
		this.img = ss.grabImage((number + 11) % 13 + 1, (4 - (suit + 2) % 4) % 4 + 1, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	
	}
	
	public int getNumber() {return number;}
	public int getSuit() {return suit;}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, (int)x, (int)y, (int)(DEFAULT_WIDTH * 0.8), (int)(DEFAULT_HEIGHT * 0.8), null);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
