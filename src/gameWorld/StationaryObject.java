package gameWorld;

import java.awt.Point;

/**
 *
 * @author ChrisMcIntosh
 *
 */
/**
 * This is an object in the world that a player can not interact with it exists
 * to be a object on the map that the player can not pass throug
 *
 * @author mcintochri1
 *
 */
public abstract class StationaryObject extends GameObject{
	/**
	 * Constuctor for a stationaryObject given it's location
	 * @param p
	 */
	public StationaryObject(Point p) {
		super(p);
	}

}
