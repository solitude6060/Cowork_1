package Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import Frame.GameObject;
import Frame.ObjectId;
import Game.Game;
import Object.*;

public class Menu extends GameObject implements KeyListener{
	
	LinkedList<Option> options;
	int currentSelected = 0;
	
	Game game;
	
	public Menu(Game game) {
		super(0, 0, ObjectId.Option);
		this.game = game;
		int posX = (game.getWidth() - Option.DEFAULT_WIDTH) / 2;
		
		options = new LinkedList<Option>();
		
		int posY = 150;
		
		for(int i = 0; i < 3; i++) {
			options.add(new Option(posX, posY, ObjectId.Option, i));
			posY += Option.DEFAULT_HEIGHT + 50;
		}
		
		options.get(0).selected = true;
		
	}
	
	public void render(Graphics g) {
		
		Option option;
		
		for(int i = 0; i < 3; i++) 
			options.get(i).render(g);
		
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		System.out.println("test");
		options.get(currentSelected).selected = false;
		if(currentSelected != 2 && key == KeyEvent.VK_DOWN)
			currentSelected++;
		if(currentSelected != 0 && key == KeyEvent.VK_UP)
			currentSelected--;
		options.get(currentSelected).selected = true;
		
		if(key == KeyEvent.VK_ENTER) {
			System.out.println(Option.getStatus(currentSelected));
			game.init(currentSelected);
			
		}
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
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
