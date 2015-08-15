package Frame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput<T extends GameObject> extends KeyAdapter {

	T object;
	
	public KeyInput(T object) {
		this.object = object;
	}
	
	public void keyPressed(KeyEvent e) {
		//System.out.println(e.getKeyCode());
		object.keyPressed(e);
		
	}
	
	public void keyReleased() {
		
	}
}
