package Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Frame.BlackJack;
import Frame.GameObject;
import Frame.ObjectId;
import Game.Game;

public class BlackJackSelection extends GameObject implements KeyListener{

	private Game game;
	private int currentSelected = 0;
	private BlackJack bj;
	
	public BlackJackSelection(Game game, BlackJack bj) {
		super(0, 0, ObjectId.Menu);
		this.game = game;
		this.bj = bj;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(bj.CountPoints(bj.getPlayerCards()) >= 21)
				currentSelected = 1;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.blue);
		g.fillRect(game.getWidth() - 150, game.getHeight() - 200, 100, 50);
		g.setColor(Color.red);
		g.fillRect(game.getWidth() - 150, game.getHeight() - 100, 100, 50);
		g.setColor(Color.white);
		if(currentSelected == 0)
			g.drawChars(new String("Deal").toCharArray(), 0, 4, game.getWidth() - 110, game.getHeight() - 175);
		else
			g.drawChars(new String("CallGame").toCharArray(), 0, 8, game.getWidth() - 125, game.getHeight() - 75);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(bj.CountPoints(bj.getPlayerCards()) < 21 && !bj.getGameStatus()) {
			if(currentSelected != 1 && key == KeyEvent.VK_DOWN)
				currentSelected++;
			else if(currentSelected != 0 && key == KeyEvent.VK_UP)
				currentSelected--;
		}

		
		if(key == KeyEvent.VK_ENTER)
			bj.makeSelection(currentSelected, bj.getPlayerCards());
	}
	
	public void setBJ(BlackJack bj) {
		this.bj = bj;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
