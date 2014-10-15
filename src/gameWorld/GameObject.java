package gameWorld;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 *This is what everything except tiles in the gameWorld will extend.
 *It mainly exists to allow for storing of all gameObjects together
 *as well as ensuring everything holds it's own XY
 * @author ChrisMcIntosh
 *
 */
public abstract class GameObject implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 8896684741524936435L;
	protected Point curLocation;


	public abstract void draw(Graphics2D g, int dx, int dy);

	public GameObject(Point p){
		curLocation = p;
	}

	public GameObject(int x, int y){
		curLocation = new Point(x, y);
	}

	public Point getLocation(){
		return this.curLocation;
	}


}
