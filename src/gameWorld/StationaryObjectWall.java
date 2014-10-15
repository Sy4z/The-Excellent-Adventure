package gameWorld;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 * This is a wall it is an object that the player can not move through and
 * provides no other interaction
 *
 * @author mcintochri1
 *
 */
public class StationaryObjectWall extends StationaryObject{
	/**
	 * Contructs a wall given it's location
	 * @param p
	 */
	public StationaryObjectWall(Point p) {
		super(p);
	}

	@Override
	public void draw(Graphics2D g, int dx, int dy) {
		// TODO Auto-generated method stub

	}

}
