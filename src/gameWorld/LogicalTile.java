package gameWorld;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Stack;

public class LogicalTile implements Serializable{

	private static final long serialVersionUID = -3331705665252979162L;
	private boolean isTile;
	private ArrayDeque<Point> path;
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
	public ArrayDeque<Point> getPath() {
		return path;
	}
	/**
	 * @param path2 the path to set
	 */
	public void setPath(ArrayDeque<Point> path2) {
		this.path = path2;
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
