package gameWorld;

import gameRender.IsoCanvas;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Stack;


/**
 *
 * @author ChrisMcIntosh
 *
 */
public class World {
	private Unit[] units;
	private GameObject[][] gameBoard;
	private LogicalTile[][] worldMap;
	private Unit activePlayer;
	private IsoCanvas canvas;

	/**
	 * Keeps on running through the turn cycle.
	 */
	public void turn() {
		while (true)
			for (Unit u : units) {
				refresh(u);
				u.activate();
				activePlayer = u;
				while (u.isActive()) {
					calculatePossibleMovments(u.curLocation);
					canvas.highlight(tilesToHightlight());
				}
			}

	}


	/**
	 * Works out which tiles can be reached in one action.
	 * This is used to be passed to the renderer.
	 * @return
	 */
	private ArrayList<Point> tilesToHightlight() {
		ArrayList<Point> highPoints = new ArrayList<Point>();
		for(int x = 0; x < worldMap.length; x++)
			for(int y = 0; y < worldMap[0].length; y++)
				if(worldMap[x][y].isReachable())
					highPoints.add(new Point(x, y));

		return highPoints;
	}



	/**
	 * Resets infomation about what can move where.
	 * @param u
	 */
	private void refresh(Unit u) {
		for(int x = 0; x < worldMap.length; x++)
			for(int y =0; y < worldMap[0].length; y++){
				worldMap[x][y].setPath(null);
				worldMap[x][y].setReachable(false);
			}

	}



	/**
	 * Constructor
	 *
	 * @return
	 */
	public World(String save, int width, int height) {
		// worldObjects = dlynPlz();
		//TODO
	}


	private void calculatePossibleMovments(Point curLocation) {
		moveFrom(curLocation.x, curLocation.y, 6, new Stack<Point>());

	}

	private void moveFrom(int x, int y, int numMoves, Stack<Point> path){
		path.add(new Point(x, y));
		worldMap[x][y].setPath(path);
		worldMap[x][y].setReachable(true);
		if(numMoves==0) return;

		if(validMove(x+1, y, path))
			moveFrom(x+1, y, numMoves-1, path);

		if(validMove(x-1, y, path))
			moveFrom(x-1, y, numMoves-1, path);

		if(validMove(x,y-1, path))
			moveFrom(x,y-1, numMoves-1,path);

		if(validMove(x,y+1, path))
			moveFrom(x,y+1, numMoves-1,path);

	}

	private boolean validMove(int x, int y, Stack<Point> path){
		if(!inBounds(x,y))
			return false;
		if(!worldMap[x][y].isCanTouchThis())
			return false;
		if(worldMap[x][y].getPath() == null)
			return true;
		if(worldMap[x][y].getPath().size() < path.size())
			return false;
		return true;
	}

	/**
	 * Gives a unit a new movement order by calling the find path method
	 *
	 * @param ID
	 * @param destination
	 * @return
	 */
	public void move(int x, int y) {
		if (!inBounds(x, y))
			return;
		if(worldMap[x][y].isCanTouchThis())
			if(worldMap[x][y].isReachable()){
				//canvas.moveUnit(null, activePlayer, worldMap[x][y].getPath());
				activePlayer.depleateMoves();
				gameBoard[x][y] = activePlayer;
				gameBoard[activePlayer.getLocation().x][activePlayer.getLocation().y] = null;
			}


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
