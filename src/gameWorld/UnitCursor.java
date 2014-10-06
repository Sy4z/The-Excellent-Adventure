package gameWorld;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 * This Unit is used for the player to use keyboard movment to decide where to move there Unit before finalizing the movment,
 * This allows us to use keyboard movement and still allow onl 2 distinct movments a turn.
 * @author mcintochri1
 *
 */
public class UnitCursor extends Unit{

	public UnitCursor(Point loc, int ID) {
		super(loc, ID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g, int dx, int dy) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString(String append) {
		// TODO Auto-generated method stub
		return null;
	}

}
