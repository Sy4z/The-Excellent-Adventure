package gameWorld;

import java.awt.Point;
import java.util.Stack;

public class LogicalTile {
	private boolean canTouchThis; //dananana
	private Stack<Point> path;
	private int movesRequired;

	public LogicalTile(boolean canTouchThis){
		this.canTouchThis = canTouchThis;
		movesRequired = -1;
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
	 * @return the distance
	 */
	public int getMovesRequired() {
		return movesRequired;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setMovesRequired(int movesRequired) {
		this.movesRequired = movesRequired;
	}

	/**
	 * Clears all movment infomation
	 */
	public void clearMoveInfo(){
		path = null;
		movesRequired = -1;
	}


}
