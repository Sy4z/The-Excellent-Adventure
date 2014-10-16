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

	/**
	 * Constructor from point;
	 * @param p
	 */
	public GameObject(Point p){
		curLocation = p;
	}

	/**
	 * Constuctor from XY
	 * @param x
	 * @param y
	 */
	public GameObject(int x, int y){
		curLocation = new Point(x, y);
	}

	/**
	 * Returns the current location of this GameObject
	 * @return the location of this GameObject
	 */
	public Point getLocation(){
		return this.curLocation;
	}

	/**
	 * Compares if two game ojects are equal by comparing their location
	 * @param Other GameObject
	 * @return if this is equals to another gameObject
	 */
	public boolean equals(GameObject g){
		if(g == null)
			return false;
		if(this.getClass() != g.getClass())
			return false;
		if(this instanceof UnitPlayer && g instanceof UnitPlayer)
			return ((UnitPlayer)this).getID() == ((UnitPlayer)g).ID;
		return true;
	}


}
