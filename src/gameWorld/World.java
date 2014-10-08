package gameWorld;

import gameRender.IsoCanvas;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

import tile.TileMultiton.type;

import com.sun.jmx.remote.internal.ArrayQueue;
import com.sun.xml.internal.bind.v2.runtime.Coordinator;

import dataStorage.Data;
import dataStorage.Tuple;


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
	private UnitCursor cursor;
	private IsoCanvas canvas;


	/**
	 * Constructor
	 *
	 * @return
	 */
	public World(String save, int width, int height, IsoCanvas cvs) {
		Tuple t = Data.testSet(null);
		this.canvas = cvs;
		units = t.units;
		worldMap = new LogicalTile[t.tiles.length][t.tiles[0].length];
		gameBoard = new GameObject[t.tiles.length][t.tiles[0].length];
		populateWorldMape(t.tiles);
		activePlayer = units[0];
		cursor = new UnitCursor(activePlayer.curLocation, -1);
		checkPlayerStatus();

	}

	/**
	 * This is very niaeve and may break will be fixed affter discussing the Tuple class
	 * with group members
	 * @param tiles
	 */
	private void populateWorldMape(type[][] tiles) {
		for(int x = 0; x < tiles.length; x++)
			for(int y = 0; y < tiles[0].length; y++)
					worldMap[x][y] = new LogicalTile(tiles[x][y] != null);


	}


	/**
	 *
	 */
	public void checkPlayerStatus() {
		if(activePlayer == null){
			activePlayer = units[0];
			cursor = new UnitCursor(activePlayer.curLocation, -1);
		}
		else if(!activePlayer.isActive()){
			//activePlayer = units[nextPlayerID()]; //Removed while Testing is being done
			activePlayer.activate();
		}
		calculatePossibleMovments(activePlayer.curLocation);
		canvas.highlight(tilesToHightlight());


	}

	private int nextPlayerID() {
		if(activePlayer.getID() == units.length-1)
			return 0;
		return activePlayer.getID() +1;
	}

	/**
	 *	takes a mouse command and causes the active player to take the relevant action
	 */
	public void intepretMouseCommand(Point coords){
		//Currently just handles movement will be extended to interact with game objects

		int x = coords.x;
		int y = coords.y;

		// x = canvas.toCart(x, y).x; // Sorry Chris this dosn't work yet
		// y = canvas.toCart(x, y).y;
		if (move(x, y))
			return;
		if (gameBoard[x][y] instanceof InteractiveObject)
			if(nextTo(x,y,activePlayer.curLocation.x, activePlayer.curLocation.y))
				interactWith(x,y);



	}

	/**
	 * Handles interaction with InteractiveObjects
	 * @param x
	 * @param y
	 */
	private void interactWith(int x, int y) {
		//If a player does not have a standard action left they may not interact with an object
		if(!activePlayer.getStandardAction())
			return;
		if(gameBoard[x][y] instanceof InteractiveObjectDoor){
			if(activePlayer.hasKey()){
				//Maybe make keys used up but for now one key does everything
				//Hey Greg we should draw this
				gameBoard[x][y] = null;//Contemplating changing this to hold a container so we can do walkthrough able objects.
			}
		}

	}

	public void moveFromKeyBoard(int i){
		//0 is up
		//1 is down
		//2 is left
		//3 is right
//		if(i==0)
//			System.out.println("World.moveFromKeyBoard(): UP");
//			move(activePlayer.getLocation().x,activePlayer.getLocation().y+1);
//		if(i==1)
//			System.out.println("World.moveFromKeyBoard(): DOWN");
//			move(activePlayer.getLocation().x,activePlayer.getLocation().y-1);
//		if(i==2)
//			System.out.println("World.moveFromKeyBoard(): LEFT");
//			move(activePlayer.getLocation().x-1,activePlayer.getLocation().y);
//		if(i==3)
//			System.out.println("World.moveFromKeyBoard(): RIGHT");
//			move(activePlayer.getLocation().x+1,activePlayer.getLocation().y);

		int x = cursor.getLocation().x;
		int y = cursor.getLocation().y;

		if (i == 0)
			y++;
		if (i == 1)
			y--;
		if (i == 2)
			x--;
		if (i == 3)
			x++;

		if (inBounds(x, y))
			if (worldMap[x][y].isIsTile()) {
				if (worldMap[x][y].isReachableByActive()) {
					// canvas.moveCursor(x, y);
					ArrayDeque<Point> step = new ArrayDeque<Point>();
					step.add(new Point(x, y));
					canvas.moveUnit(cursor, step);
					// gameBoard[x][y] = activePlayer;
					// gameBoard[activePlayer.getLocation().x][activePlayer
					// .getLocation().y] = null;

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
				if(worldMap[x][y].isReachableByActive())
					highPoints.add(new Point(x, y));
		return highPoints;
	}



	/**
	 * Resets information about what can move where.
	 * @param u
	 */
	private void refresh(Unit u) {
		for(int x = 0; x < worldMap.length; x++)
			for(int y =0; y < worldMap[0].length; y++){
				worldMap[x][y].setPath(null);
				worldMap[x][y].setReachableByActive(false);
			}

	}




	private void calculatePossibleMovments(Point curLocation) {
		checkMoveFrom(curLocation.x, curLocation.y, 6, new ArrayDeque<Point>());

	}

	private void checkMoveFrom(int x, int y, int numMoves, ArrayDeque<Point> path){
		path.add(new Point(x, y));
		worldMap[x][y].setPath(path);
		worldMap[x][y].setReachableByActive(true);
		if(numMoves==0) return;

		if(validMove(x+1, y, path))
			checkMoveFrom(x+1, y, numMoves-1, path.clone());

		if(validMove(x-1, y, path))
			checkMoveFrom(x-1, y, numMoves-1, path.clone());

		if(validMove(x,y-1, path))
			checkMoveFrom(x,y-1, numMoves-1,path.clone());

		if(validMove(x,y+1, path))
			checkMoveFrom(x,y+1, numMoves-1,path.clone());
	}

	private boolean validMove(int x, int y, ArrayDeque<Point> path){
		if(!inBounds(x,y))
			return false;
		if(!worldMap[x][y].isIsTile())
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
	public boolean move(int x, int y) {
		if (!inBounds(x, y))
			return false;
		if(worldMap[x][y].isIsTile())
			if(worldMap[x][y].isReachableByActive()){
				canvas.moveUnit(activePlayer, worldMap[x][y].getPath());
				activePlayer.depleateMoves();
				gameBoard[x][y] = activePlayer;
				gameBoard[activePlayer.getLocation().x][activePlayer.getLocation().y] = null;
				calculatePossibleMovments(x,y);
				return true;
			}
		return false;
	}



	/**
	 * Helper method to allow move to be called with a point
	 */
	public boolean move(Point p){
		return move(p.x,p.y);
	}

	/**
	 * Moves the active player to the cursor
	 * @return
	 */
	public boolean moveToCursor(){
		return (move(cursor.curLocation));
	}




	/**
	 * @return the canvas
	 */
	public IsoCanvas getCanvas() {
		return canvas;
	}

	/**
	 * @param canvas the canvas to set
	 */
	public void setCanvas(IsoCanvas canvas) {
		this.canvas = canvas;
	}

	//Helper Methods Below this Point--------------------------------------------------------------------------

	/**
	 * Checks if two points are next to each other by
	 * checking if the absolute value of the change in X and Y is
	 * equal to 1
	 * @param p1
	 * @param p2
	 * @return
	 */
	private boolean nextTo(Point p1, Point p2){
		return ((Math.abs(p1.x-p2.x) + Math.abs(p1.y-p2.y)) == 1);
	}

	/**
	 * Calls method of same name with points instead of xy
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	private boolean nextTo(int x1, int y1, int x2, int y2){
		return nextTo(new Point(x1,y1), new Point(x2,y2));
	}

	/**
	 * Checks if a point is on the GameBoard
	 * @param x
	 * @param y
	 * @return
	 */
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

	private void calculatePossibleMovments(int x, int y) {
		calculatePossibleMovments(new Point(x,y));
	}

}
