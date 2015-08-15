package Frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Game.Game;
import Object.BlackJackSelection;
import Object.Card;
import Object.CardSet;
import Object.Player;

public class BlackJack extends GameObject {
	
	public static int cashLimit = 1000;
	
	private ArrayList<Card> playerCards, userCards ;
	private Game game;
	private BlackJackSelection menu;
	private Player player;
	private CardSet cardSet;
	private BufferedImage userCardImage;
	private boolean gameOver = false;
	
	

	public BlackJack(Game game, Player player) {
		super(0, 0, ObjectId.BlackJack);
		this.game = game;
		menu = new BlackJackSelection(this.game, this);
		this.game.addKeyListener(menu);
		this.player = player;
		
		cardSet = new CardSet();
		userCardImage = cardSet.getUserCardImage();
		
		playerCards = new ArrayList<Card>();
		userCards = new ArrayList<Card>();
		
		for(int i = 0; i < 2; i++) {
			/*Card card = cardSet.getCard();
			card.setX(playerCards.size() * ((int)(Card.DEFAULT_WIDTH * 0.8) + 5) + 5);
			card.setY(game.getHeight() - Card.DEFAULT_HEIGHT - 20);*/
			playerCards.add(setPlayerCard(cardSet.getCard()));
		}
		
		for(int i = 0; i < 2; i++) {
			userCards.add(setUserCard(cardSet.getCard()));
		}
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		menu.tick();
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.white);
		g.drawRect(game.getWidth() - 150, game.getHeight() - 300, 100, 50);
		String cash = new Integer(player.getCash()).toString();
		g.drawChars(cash.toCharArray(), 0, cash.length(), game.getWidth() - 110, game.getHeight() - 275);
		
		
		for(int i = 0; i < playerCards.size(); i++) 
			playerCards.get(i).render(g);
		for(int i = 0; i < userCards.size(); i++) {
			if(!gameOver) {
				int x = (int)userCards.get(i).getX();
				int y = (int)userCards.get(i).getY();
				g.drawImage(userCardImage, x, y, (int)(Card.DEFAULT_WIDTH * 0.8), (int)(Card.DEFAULT_HEIGHT * 0.8), null);				
			}else
				userCards.get(i).render(g);
		}
		menu.render(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void makeSelection(int s, ArrayList<Card> cards) {
		
		if(s == 0)
			deal(cards);
		else
			callGame();
		
		if(s == 0) {
			if(CountPoints(userCards) < 17) 
				deal(userCards);		
		}

	}
	
	private void deal(ArrayList<Card> cards) {
		
		if(CountPoints(cards) < 21) {
			Card card = cardSet.getCard();
			if(cards == playerCards)
				setPlayerCard(card);
			else
				setUserCard(card);
			cards.add(card);
		}else
			System.out.println("U can't");
		return;
	}
	
	private void callGame() {
		
		if(gameOver) {
			game.removeKeyListener(menu);
			game.init(-1);			
			return;
		}
			
		while(CountPoints(userCards) < 17)
			deal(userCards);
		gameOver = true;
		if(isWin(CountPoints(playerCards), CountPoints(userCards))) {
			System.out.println("You win!");
			player.addCash(100);
		}
		else {
			System.out.println("Yout lose!");
			player.addCash(-100);
		}
		//Quit BlackJack
		//game.removeKeyListener(menu);
		//game.init(-1);
	}
	
	private Card setPlayerCard(Card card) {
		card.setX(playerCards.size() * ((int)(Card.DEFAULT_WIDTH * 0.8) + 5) + 5);
		card.setY(game.getHeight() - Card.DEFAULT_HEIGHT - 20);
		
		return card;
	}
	private Card setUserCard(Card card) {
		card.setX(userCards.size() * ((int)(Card.DEFAULT_WIDTH * 0.8) + 5) + 5);
		card.setY(20);
		
		return card;
	}	
	
	
	
	public ArrayList<Card> getPlayerCards() {
		return playerCards;
	}
	
	public int CountPoints(ArrayList<Card> cards) {
		
		int point = 0;
		int totalAce = 0;
		for(int i = 0; i < cards.size(); i++) {
			if(cards.get(i).getNumber() >= 10)
				point += 10;
			else if(cards.get(i).getNumber() != 1)
				point += cards.get(i).getNumber();
			else
				totalAce++;
		}
		
		while(totalAce != 0) {
			if(point + 11 + (totalAce - 1) <= 21) {
				point += 11;
				totalAce--;
			}else {
				point += 1;
				totalAce--;
			}
		}
		return point;
	}
	
	private boolean isWin(int playerPoint, int userPoint) {
		
		System.out.println(playerPoint + " " + userPoint);
		if(playerPoint > 21) 
			//System.out.println("You lose");
			return false;
		else if(userPoint > 21 || playerPoint > userPoint)
			//System.out.println("You win");
			return true;
		return false;
	}
	
	public boolean getGameStatus() {
		return gameOver;
	}
	
}
