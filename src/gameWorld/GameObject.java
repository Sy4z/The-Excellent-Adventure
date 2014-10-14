package gameWorld;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 *
 * @author ChrisMcIntosh
 *
 */
public abstract class GameObject implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 8896684741524936435L;
	protected BufferedImage img;
	protected Point curLocation;


	abstract void draw(Graphics2D g, int dx, int dy);

	public GameObject(Point p){
		curLocation = p;
	}

	public GameObject(int x, int y){
		curLocation = new Point(x, y);
	}


}
