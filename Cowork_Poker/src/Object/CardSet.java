package Object;

import java.awt.image.BufferedImage;
import java.util.Random;

import Frame.BufferedImageLoader;
import Frame.ObjectId;
import Frame.SpriteSheet;

public class CardSet {
	
	Card[] cards; 
	boolean[] used;
	private BufferedImageLoader loader = new BufferedImageLoader();
	private BufferedImage img;
	private SpriteSheet ss;
	
	public CardSet() {
		cards = new Card[52];
		used = new boolean[52];
		img = loader.loadImage("/Poker_Cards.gif");
		ss = new SpriteSheet(img);
		init();
	}
	
	private void init() {
		for(int i = 0; i < cards.length; i++) {
			cards[i] = new Card(0, 0, ObjectId.Card, (i + 1) % 13, i / 13, ss);	
		}
	}
	
	public Card getCard() {
		
		Random rand = new Random();
		int index;
	
		do {
			index = rand.nextInt(52);
		} while(used[index]);
		
		used[index] = true;
		return cards[index];
	}
	
	public BufferedImage getUserCardImage() {
		return ss.grabImage(1, 5, Card.DEFAULT_WIDTH, Card.DEFAULT_HEIGHT);
	}
	
}
