package gameWorld;

import gameRender.IsoCanvas;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

import dataStorage.Data;
import tile.Tile;

/**
 *
 * @author ChrisMcIntosh
 *
 */
public class World {
	private Unit[] units;
	private File defaultNewGameState = null;
	private GameObject[][] gameBoard;
	private LogicalTile[][] worldMap;
	private Unit activePlayer;

	public void turn() {
		while (true)
			for (Unit u : units) {
				u.activate();
				activePlayer = u;
				while (u.isActive()) {
					calculatePossibleMovments(u.curLocation);
				}
			}
	}



	/**
	 * Load Constructor
	 *
	 * @return
	 */
	public World(File save, int width, int height) {
		// worldObjects = dlynPlz();
		//TODO
	}

	/**
	 * New Game Control
	 *
	 */
	public World(int width, int height) {
		// worldObjects = dlynPlz();
		//TODO
	}


	private void calculatePossibleMovments(Point curLocation) {
		// TODO Auto-generated method stub

	}

	/**
	 * Gives a unit a new movement order by calling the find path method
	 *
	 * @param ID
	 * @param destination
	 * @return
	 */
	public int move(int x, int y) {
		if (!inBounds(x, y))
			return 0;
		if(worldMap[x][y].isCanTouchThis())
			if(activePlayer.avilableMoves()>= worldMap[x][y].getMovesRequired()){
				moveUnit(activePlayer, worldMap[x][y].getPath());
				activePlayer.depleateMoves(worldMap[x][y].getMovesRequired());
				gameBoard[x][y] = activePlayer;
				gameBoard[activePlayer.getLocation().x][activePlayer.getLocation().y] = null;
			}


	}



	/**
	 * Finds a path through the game world that can be triversed by a unit and
	 * returns it as a stack of single square movments.
	 *
	 * @param startX
	 * @param smaptartY
	 * @param x
	 * @param y
	 * @return
	 */
	private Stack<Point> findPath(int startX, int startY, int x, int y) {
		Stack<Point> path = new Stack<Point>();
		int curX = startX;
		int curY = startY;

		while (true) {
			if(curX == x && curY == y) break;
			if (curX < x && accesable[curX + 1][curY]){
				path.add(new Point(++curX, curY));
				continue;
			}
			if (curY < y && accesable[curX][curY+1]){
				path.add(new Point(curX, ++curY));
				continue;
			}
			if (curX > x && accesable[curX - 1][curY]){
				path.add(new Point(--curX, curY));
				continue;
			}
			if (curY > y && accesable[curX][curY-1]){
				path.add(new Point(curX, --curY));
				continue;
			}

			break;
		}
		//Beter versions of pathfinding will give things in the oposite order of traversal so a stack
		//is used thus untill pathfinding is improved we need to reverse the stack;
		Stack<Point> rPath = new Stack<Point>();
		while(!path.isEmpty())
			rPath.add(path.pop());
		return rPath;

	}

	private boolean inBounds(int x, int y) {
		if (x >= gameBoard.length)
			return false;
		if (y >= gameBoard[0].length)
			return false;
		if (x < 0)
			return false;
		if (y < 0)
			return false;
		return true;
	}
}
