package gameWorld;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;

import sun.net.util.IPAddressUtil;

/**
 *
 * @author ChrisMcIntosh
 *
 */
public abstract class Unit extends GameObject {
	protected Point curLocation;
	protected File filePath;
	private boolean isActiveUnit;
	private boolean standardAction;
	private boolean moveAction;
	private boolean swiftAction;

	public Unit(Point loc) {
		curLocation = loc;
	}


	/**
	 * Updates a Entities information of its location. This will be used my the
	 * move methods in Control which will update all other information related
	 * to the move and ensure it is a valid move
	 */
	public void move(Point destination) {
		curLocation = destination;
	}

	public Point getLocation() {
		return curLocation;

	}


	public abstract void draw(Graphics2D g, int dx, int dy, int dx2, int dy2, int sx,
			int sy, int sx2, int sy2);

	public void activate() {
		moveAction = true;
		standardAction = true;
		swiftAction = true;
		this.isActiveUnit = true;

	}

	public boolean isActive() {
		return isActiveUnit;
	}

	public boolean getStandardAction(){
		return standardAction;
	}

	public boolean getMoveAction(){
		return moveAction;
	}

	public boolean getSwiftACtion(){
		return swiftAction;
	}

	public int getAvilableMoves() {
		int moves = 0;
		if(standardAction) moves++;
		if(moveAction) moves++;
		return moves;
	}

	public void depleateMoves() {

		if(moveAction == false)
			standardAction = false;
		else moveAction = false;
	}

}
