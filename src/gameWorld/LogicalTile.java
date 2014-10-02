package gameWorld;

import java.awt.Point;
import java.util.Stack;

public class LogicalTile {
	private boolean canTouchThis; //dananana
	private Stack<Point> path;
	private boolean isTile;

	public LogicalTile(boolean canTouchThis){
		this.canTouchThis = canTouchThis;
		isTile = false;
	}

	/**
	 * @return the canTouchThis
	 */
	public boolean isCanTouchThis() {
		return canTouchThis;
	}
	/**
	 * @param canTouchThis the canTouchThis to set
	 */
	public void setCanTouchThis(boolean canTouchThis) {
		this.canTouchThis = canTouchThis;
	}
	/**
	 * @return the path
	 */
	public Stack<Point> getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(Stack<Point> path) {
		this.path = path;
	}


	/**
	 * @return the reachable
	 */
	public boolean isTile() {
		return isTile;
	}

	/**
	 * @param isTile is used used for non rectangular
	 * rooms represented by retangular arrays of Logical tiles
	 */
	public void setIsTile(boolean isTile) {
		this.isTile = isTile;
	}

	/**
	 * Clears all movment infomation
	 */
	public void clearMoveInfo(){
		path = null;
		isTile = false;
	}


}
