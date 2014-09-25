package gameWorld;

import java.awt.Point;
import java.util.Stack;

public class LogicalTile {
	private boolean canTouchThis; //dananana
	private Stack<Point> path;
	private boolean reachable;

	public LogicalTile(boolean canTouchThis){
		this.canTouchThis = canTouchThis;
		reachable = false;
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
	public boolean isReachable() {
		return reachable;
	}

	/**
	 * @param reachable the reachable to set
	 */
	public void setReachable(boolean reachable) {
		this.reachable = reachable;
	}

	/**
	 * Clears all movment infomation
	 */
	public void clearMoveInfo(){
		path = null;
		reachable = false;
	}


}
