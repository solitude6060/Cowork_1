package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import Frame.BlackJack;
import Frame.BufferedImageLoader;
import Frame.GameObject;
import Frame.KeyInput;
import Object.BlackJackSelection;
import Object.Menu;
import Object.Player;

public class Game extends Canvas implements Runnable{
	public Game() {
	}

	private int GameStatus;
	private boolean running = false;
	Thread thread;
	
	public KeyInput<? extends GameObject> keyInput;
	private BufferedImageLoader loader = new BufferedImageLoader();
	
	//Object
	private Player player;
	private Menu menu;
	private BlackJack blackJack = null;
	//private BufferedImage cardPic =  null;
	
	
	public synchronized void start() {
		
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void init(int index) {
		switch(index) {
			case -2:
				initGame();
			case -1:
				initMenu();
				break;
			case 0:
				initBlackJack();
				break;
			default:
		}
		

	}
	
	
	@Override
	public void run() {
		
		init(-2);
		this.requestFocus();
		
		long lastTime = System.currentTimeMillis();
		long delta = 0;
		while(running) {
			long currentTime = System.currentTimeMillis();
			delta += currentTime - lastTime;
			lastTime = currentTime;
			
			if(delta >= 60) {
				delta = 0;
				tick();
				render();
			}
		}
		
	}

	private void tick() {
		switch(GameStatus) {
		case -1:
			break;
		case 0:
			blackJack.tick();
			break;
		default:
			
		}		
	}
	
	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		////
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		switch(GameStatus) {
			case -1:
				menu.render(g);
				break;
			case 0:
				blackJack.render(g);
				break;
			default:
				
		}
			
		
		////
		
		g.dispose();
		bs.show();
		
	}
	
	private void initGame() {

		menu = new Menu(this);
		player = new Player(1000);
		
	}
	
	private void initMenu() {
		
		GameStatus = -1;
		//keyInput = new KeyInput<Menu>(menu);
		this.addKeyListener(menu);
	}
	
	private void initBlackJack() {
		
		//cardPic = loader.loadImage("/Poker_Cards.gif");
		//BlackJackSelection BJmenu = new BlackJackSelection(this);
		//keyInput = new KeyInput<BlackJackSelection>(BJmenu);
		//this.addKeyListener();
		if(player.getCash() < 1000) {
			System.out.println("You have no enough money");
			return;
		}
		removeKeyListener(menu);
		blackJack = new BlackJack(this, player);
		setStatus(0);
		
	}
	
	public void setStatus(int status) {
		GameStatus = status;
	}
	
}
