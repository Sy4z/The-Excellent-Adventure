package gameWorld;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 * This is a stationaryObject that is a hat stand. It sits in the world and acts
 * like a wall, though it will be draw as a hatstand.
 *
 * @author mcintochri1
 *
 */
public class StationaryObjectHatStand extends StationaryObject{

	/**
	 * Constructs a hat stand given it's location
	 * @param p
	 */
	public StationaryObjectHatStand(Point p){
		super(p);
	}

	@Override
	void draw(Graphics2D g, int dx, int dy) {
		// TODO Auto-generated method stub

	}

}
