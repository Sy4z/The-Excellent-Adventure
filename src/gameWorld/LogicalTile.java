package gameWorld;

import java.awt.Point;
import java.util.Stack;

public class LogicalTile {
	private boolean isTile;
	private Stack<Point> path;
	private boolean reachableByActive;

	public LogicalTile(boolean canTouchThis){
		this.isTile = canTouchThis;
		reachableByActive = false;
	}

	/**
	 * @return the canTouchThis
	 */
	public boolean isIsTile() {
		return isTile;
	}
	/**
	 * @param isTile the canTouchThis to set
	 */
	public void setIsTile(boolean isTile) {
		this.isTile = isTile;
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
	public boolean isReachableByActive() {
		return reachableByActive;
	}

	/**
	 * @param
	 */
	public void setReachableByActive(boolean reachable) {
		this.reachableByActive = reachable;
	}

	/**
	 * Clears all movment infomation
	 */
	public void clearMoveInfo(){
		path = null;
		reachableByActive = false;
	}


}
