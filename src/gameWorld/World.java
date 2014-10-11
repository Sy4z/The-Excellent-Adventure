package gameWorld;

import gameRender.IsoCanvas;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import tile.TileMultiton.type;
import dataStorage.Data;
import dataStorage.Tuple;


/**
 *
 * @author ChrisMcIntosh
 *
 */
public class World {
	private boolean isActive;
	private GameObject[][] gameBoard;
	private LogicalTile[][] worldMap;
	private UnitPlayer avatar;
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
		//units = (UnitPlayer[]) t.units;
		worldMap = new LogicalTile[t.tiles.length][t.tiles[0].length];
		gameBoard = new GameObject[t.tiles.length][t.tiles[0].length];
		populateWorldMape(t.tiles);
		avatar = (UnitPlayer) t.units[0];
		cursor = new UnitCursor(avatar.curLocation, -1);
		checkPlayerStatus();

	}

	/**
	 * This is very niaeve and may break will be fixed after discussing the Tuple class
	 * with group members
	 * @param tiles
	 */
	private void populateWorldMape(type[][] tiles) {
		for(int x = 0; x < tiles.length; x++)
			for(int y = 0; y < tiles[0].length; y++){
				//if(tiles[x][y].DOOR)
				//	worldMap[x][y] = new LogicalTileDoor(tiles[x][y] != null);
			//	else
					worldMap[x][y] = new LogicalTile(tiles[x][y] != null);
			}
	}


	/**
	 *Updates active player
	 *Then re calculates possible movements
	 */
	public boolean checkPlayerStatus() {
		//Return false if
		if(!avatar.isNotTurnEnd()){
			return isActive = false;
		}
		//Otherwise refresh movment and return true
		calculatePossibleMovments(avatar.curLocation);
		return true;
	}

	public void startTurn(){
		avatar.activate();
		calculatePossibleMovments(avatar.curLocation);
	}


	/**
	 *	This is unlikely to function due to time constraints :(
	 */
	public void intepretMouseCommand(Point coords){

		int x = coords.x;
		int y = coords.y;

		// x = canvas.toCart(x, y).x; // Sorry Chris this dosn't work yet
		// y = canvas.toCart(x, y).y;
		if (move(x, y))
			return;
		if (gameBoard[x][y] instanceof InteractiveObject)
			if(nextTo(x,y,avatar.curLocation.x, avatar.curLocation.y))
				interactWith(x,y);
	}

	/**
	 * Handles interaction with InteractiveObjects
	 * @param x
	 * @param y
	 */
	private void interactWith(int x, int y) {
		//If a player does not have a standard action left they may not interact with an object
		if(!avatar.getStandardAction())
			return;
		if(gameBoard[x][y] instanceof InteractiveObjectChest){
			avatar.addToInventory(((InteractiveObjectChest)gameBoard[x][y]).takeContents());

		}

	}

	public void moveFromKeyBoard(int i) {
		// 0 is up
		// 1 is down
		// 2 is left
		// 3 is right

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
				// If it's a door only a player with a key can go through
				if (worldMap[x][y] instanceof LogicalTileDoor)
					if (!avatar.hasKey())
						return;
				avatar.useKey();
				// If the XY is within one movement of the active player
				if (worldMap[x][y].isReachableByActive()) {
					cursor.setLocation(x,y);
					canvas.moveCursor(cursor);

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
	private void refresh() {
		for(int x = 0; x < worldMap.length; x++)
			for(int y =0; y < worldMap[0].length; y++){
				worldMap[x][y].setPath(null);
				worldMap[x][y].setReachableByActive(false);
			}

		checkPlayerStatus();
	}




	private void calculatePossibleMovments(Point curLocation) {
		checkMoveFrom(curLocation.x, curLocation.y, 6, new ArrayDeque<Point>());
		canvas.highlight(tilesToHightlight());

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
				canvas.moveUnit(avatar, worldMap[x][y].getPath());
				avatar.depleateMoves();
				gameBoard[x][y] = avatar;
				gameBoard[avatar.getLocation().x][avatar.getLocation().y] = null;
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
		if(move(cursor.curLocation)){
			refresh();
			return true;
		}
		return false;
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

	private void calculatePossibleMovments() {
		calculatePossibleMovments(avatar.getLocation());
	}



	//Networking Methods--------------------------------------------------------------------------------------------

}
