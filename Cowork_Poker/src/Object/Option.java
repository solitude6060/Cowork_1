package Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Frame.GameObject;
import Frame.ObjectId;

public class Option extends GameObject {

	public static int DEFAULT_WIDTH = 300, DEFAULT_HEIGHT = 70;
	public static String[] OptionStatus = new String[]{"Black Jack", "Game2", "Game3"};
	public boolean selected;
	
	private int type;
	
	public Option(float x, float y, ObjectId id, int type) {
		super(x, y, id);
		
		this.type = type;
	}

	public static String getStatus (int index) {
		return OptionStatus[index];
	}
	
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		if(selected) 
			g.setColor(Color.red);
		else
			g.setColor(Color.blue);
		
		g.fillRect((int)x, (int)y, DEFAULT_WIDTH, DEFAULT_HEIGHT);

	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

}
