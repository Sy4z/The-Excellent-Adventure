package gameWorld;

import gameRender.IsoCanvas;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

import dataStorage.Data;
import tile.Tile;
import tile.TileFactory;
import tile.TileFactory.type;

/**
 *
 * @author ChrisMcIntosh
 *
 */
public class Control {
	private TileFactory.type[][] gameBoard;
	private Unit[] units;
	private File defaultNewGameState = null;
	private GameObject[][] worldObjects;


	public void tick() {
		while (true) {

		}
	}
	/**
	 * Load Constructor
	 * @return
	 */
	public Control(File save, int width, int height){
		gameBoard = Data.testSet(save);
	}

	/**
	 * New Game Control
	 *
	 */
	public Control(int width, int height){
		gameBoard = Data.testSet(defaultNewGameState);
	}

	/**
	 * Moment class.
	 * Currently works as teleportation.
	 * Will be updated with a pathfinding alogritm and move one square per tick.
	 * @param ID
	 * @param destination
	 * @return
	 */
	public boolean move(int ID, int xDes, int yDes){
		if(!inBounds(xDes, yDes))
			return false;

		int startX = units[ID].getLocation().x;
		int startY = units[ID].getLocation().y;


		UnitCommandMove unitMovment = new UnitCommandMove(findPath(startX,startY,xDes,yDes));



		return true;

	}

	private Stack<Point> findPath(int startX, int startY, int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}
	private boolean inBounds(int x, int y) {
		if(x >= gameBoard.length)
			return false;
		if(y >= gameBoard[0].length)
			return false;
		if(x < 0)
			return false;
		if(y < 0)
			return false;
		return true;
	}
}
