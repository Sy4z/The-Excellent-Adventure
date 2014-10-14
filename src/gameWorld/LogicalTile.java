package gameWorld;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Stack;
/**
 * This class is the base tile for the logic of the game. They are used to determine where a player can move.
 * @author mcintochri1
 *
 */
public class LogicalTile implements Serializable{

	private static final long serialVersionUID = -3331705665252979162L;
	//This holds if a logical tile is an accessible tile or part of an unaccessible part of the map
	private boolean isTile;
	//This is the plot of movement from the active player to this square.
	private ArrayDeque<Point> path;
	private boolean reachableByActive;

	/**
	 * This is a constructor that takes if a tile is a accessible tile or
	 * blankspace on the map such as a walled off area with no door or one that
	 * exists due to a non rectangular map
	 *
	 * @param isTile
	 */
	public LogicalTile(boolean isTile){
		this.isTile = isTile;
		reachableByActive = false;
	}

	/**
	 * This method returns if a tile is a real tile or unusable 'null space'
	 * @return isTile
	 */
	public boolean isIsTile() {
		return isTile;
	}


	/**
	 * This method returns the path from the active player to the square
	 * associated with this logical tile
	 *
	 * @return the path
	 */
	public ArrayDeque<Point> getPath() {
		return path;
	}
	/**
	 * This is used to set the path from the active player to this square
	 * @param path2 the path to set
	 */
	public void setPath(ArrayDeque<Point> path) {
		this.path = path;
	}


	/**
	 * Returns if this square is within one movment of the active player
	 * @return the reachable
	 */
	public boolean isReachableByActive() {
		return reachableByActive;
	}

	/**
	 * This method is used in movement calculation to indicate that this square
	 * is accessible by the active player
	 *
	 * @param reachable
	 */
	public void setReachableByActive(boolean reachable) {
		this.reachableByActive = reachable;
	}

	/**
	 * Clears all movement information
	 * This is used after each movement to reset movement calculations
	 */
	public void clearMoveInfo(){
		path = null;
		reachableByActive = false;
	}


}
