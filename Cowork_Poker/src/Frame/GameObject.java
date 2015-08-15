package Frame;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public abstract class GameObject {

	protected float x, y, velX, velY;
	protected ObjectId id;
	
	public GameObject(float x, float y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void keyPressed(KeyEvent e);
	
	public float getX() {return x;}
	public void setX(float x) {this.x = x;}

	public float getY() {return y;}
	public void setY(float y) {this.y = y;}

	public float getVelX() {return velX;}
	public void setVelX(float velX) {this.velX = velX;}

	public float getVelY() {return velY;}
	public void setVelY(float velY) {this.velY = velY;}

	public ObjectId getId() {return id;}
	
}
