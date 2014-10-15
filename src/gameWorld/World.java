package gameWorld;

import gameRender.IsoCanvas;
import gameWorld.Inventory.itemTypes;

import java.awt.Point;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;

import com.sun.accessibility.internal.resources.accessibility;

import runGame.Main;
import sun.text.normalizer.UBiDiProps;
import tile.TileMultiton.type;
import dataStorage.Data;
import dataStorage.Tuple;


/**
 * This is the main class for controling what a player can do on their turn and
 * how they interact with the world.
 *
 * @author ChrisMcIntosh
 *
 */
public class World {
	private boolean isActive;
	//This contains all gameobjects in the world the player can interact with
	private GameObject[][] gameBoard;
	//This is the map of all the tile that make up the world
	private LogicalTile[][] worldMap;
	private UnitPlayer avatar;
	private UnitCursor cursor;
	private IsoCanvas canvas;

	/**
	 * Constructor
	 *
	 * @return
	 */
	public World(LogicalTile[][] tiles, GameObject[][] gameboard, int ID) {
		this.canvas = Main.cvs;
		this.gameBoard = gameboard;
		worldMap = tiles;
		LogicalTileNullHandler();
		UnitPlayer tempPlayer = null;
		for (int x = 0; x < gameboard.length; x++) {
			for (int y = 0; y < gameboard[0].length; y++) {
				if (gameboard[x][y] instanceof UnitPlayer) {
					if (((UnitPlayer) gameboard[x][y]).ID == ID) {
						avatar = (UnitPlayer) gameboard[x][y];
					}
				}
			}
		}
		if (avatar == null) {
			avatar = randomPositionAvatar();
		}

		cursor = new UnitCursor(avatar.curLocation, -1);
		checkPlayerStatus();
		startTurn();
		updateGameBoardGraphics();

	}

	/**
	 * Constructor
	 *
	 * @return
	 */
	public World( LogicalTile[][] tiles, GameObject[][] gameboard) {
		worldMap = tiles;
		LogicalTileNullHandler();
		this.canvas = Main.cvs;
		this.gameBoard = gameboard;
		avatar = randomPositionAvatar();
		cursor = new UnitCursor(avatar.curLocation, -1);
		checkPlayerStatus();
		startTurn();
		updateGameBoardGraphics();

	}

	/**
	 * Generates a new player at a random position on the map;
	 * @return The New Avatar
	 */
	private UnitPlayer randomPositionAvatar() {
		int id = -1;
		//Get the current Highest ID to set the new Avatars ID to one higher than that ID
		for (int x = 0; x < gameBoard.length; x++) {
			for (int y = 0; y < gameBoard[0].length; y++) {
				if (gameBoard[x][y] instanceof UnitPlayer) {
					id = Math.max(id, ((UnitPlayer) gameBoard[x][y]).getID());
				}
			}
		}
		//Generate random map positions on the map until one is free then put the player there.
		while(true){
			//Limited until map scrolling is implemented.
			int x = 5;//(int) (Math.random() * gameBoard.length);
			int y = 5;//(int) (Math.random() * gameBoard[0].length);
			if(gameBoard[x][y] == null){
				gameBoard[x][y] = new UnitPlayer(new Point(x,y), id+1);
				return (UnitPlayer) gameBoard[x][y];
			}
		}
	}

	/**
	 *Updates active player
	 *Then re calculates possible movements
	 */
	public boolean checkPlayerStatus() {
		//Return false if
		if(!avatar.isNotTurnEnd()){
			isActive = false;
			return isActive;
		}
		//Otherwise refresh moevment and return true
		calculatePossibleMovments();
		updateGameBoardGraphics();
		return true;
	}
	/**
	 * Resets turn information at the start of the turn
	 */
	public void startTurn(){
		avatar.activate();
		isActive = true;
		calculatePossibleMovments();
		updateGameBoardGraphics();

	}


	/**
	 * This is the method for handling mouse based movment however due to
	 * problems with translating mouse position to bored position all movement is
	 * now keyboard based and this is left in deprecated for if we are able to
	 * solve the problems with it and properly implement it.
	 */
	@Deprecated
	public void intepretMouseCommand(Point coords){

		int x = coords.x;
		int y = coords.y;

		// x = canvas.toCart(x, y).x; // Sorry Chris this dosn't work yet
		// y = canvas.toCart(x, y).y;
		if (move(x, y))
			return;
		if (gameBoard[x][y] instanceof InteractiveObject)
			if(nextTo(x,y,avatar.curLocation.x, avatar.curLocation.y))
				interactWith((InteractiveObject)gameBoard[x][y]);
	}

	/**
	 * Handles interaction with InteractiveObjects by calling to their internal
	 * methods
	 *
	 * @param x
	 * @param y
	 */
	private void interactWith(InteractiveObject obj) {
		if(obj instanceof InteractiveObjectChest)
			avatar.addToInventory(((InteractiveObjectChest)obj).getContents());
		if(obj instanceof InteractiveObjectMonster){
			//Fight the monster with all the Katanas you have
			int[] loot = ((InteractiveObjectMonster)obj).fight(avatar.getInventory()[itemTypes.KATANA.ordinal()]);
			if(loot == null)
				avatar.loseFight();
			else
				avatar.addToInventory(loot);
		}

	}
	/**
	 * Moves the Cursor based on keyboard imput and if the movment is valid.
	 * @param i
	 */
	public void moveFromKeyBoard(int i) {
		// 0 is up
		// 1 is down
		// 2 is left
		// 3 is right

		if(i > 3 || i < 0) return;
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
				if (worldMap[x][y] instanceof LogicalTileDoor) {
					if (!((LogicalTileDoor) worldMap[x][y]).isOpen()) {
						if (!avatar.hasKey())
							return;
						avatar.useKey();
						((LogicalTileDoor) worldMap[x][y]).setOpen(true);
					}
				}
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
			for(int y = 0; y < worldMap[0].length; y++){
				System.out.println(worldMap[x][y]);
				System.out.println(worldMap[x][y].isReachableByActive());
				System.out.println(x + " " + y);
				if(worldMap[x][y].isReachableByActive() && !(gameBoard[x][y] instanceof StationaryObject))
					highPoints.add(new Point(x, y));
			}
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



	/**
	 * Calculates all possible movements the player can make and saves them into
	 * tiles and then highlights squares reachable by one movement
	 *
	 * @param curLocation
	 */
	private void calculatePossibleMovements(Point curLocation) {
		checkMoveFrom(curLocation.x, curLocation.y, 6, new ArrayDeque<Point>());
		canvas.highlight(tilesToHightlight());

	}

	/**
	 * Recursivly calculates all movements that can be made from a given XY with
	 * remaining allotment of movement this is controled by the method
	 * calculatePossibleMovments
	 *
	 * @param x
	 * @param y
	 * @param numMoves
	 * @param path
	 */
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
	/**
	 * Checks if a tile is valid to move to
	 * @param x
	 * @param y
	 * @param path
	 * @return
	 */
	private boolean validMove(int x, int y, ArrayDeque<Point> path){
		//Return false if it out of bounds of the map array.
		if(!inBounds(x,y))
			return false;
		//Returns false if the tile does not correspond to a accessible square
		if(!worldMap[x][y].isIsTile())
			return false;
		//Return false if there is another player in that square
		if(gameBoard[x][y] instanceof UnitPlayer)
			return false;
		//Return true if there is no path yet calculated for that square
		if(worldMap[x][y].getPath() == null)
			return true;
		// Return false if the path calculated already for that square is more
		// efficient than this one
		if(worldMap[x][y].getPath().size() < path.size())
			return false;
		//Otherwise return true
		return true;
	}

	/**
	 * Moves the player avatar to the given XY
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
				GameObject currentContents = gameBoard[x][y];

				gameBoard[avatar.getLocation().x][avatar.getLocation().y] = null;
				canvas.moveUnit(avatar, worldMap[x][y].getPath());
				avatar.depleateMoves();
				gameBoard[x][y] = avatar;
				avatar.upDateLocation(new Point(x,y));
				calculatePossibleMovments(x,y);
				if(currentContents instanceof InteractiveObject)
					interactWith((InteractiveObject)currentContents);
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
	public boolean moveToCursor() {
		if (move(cursor.curLocation)) {
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

	/**
	 * Calls the method of the same name converting XY to the required point
	 * input
	 *
	 * @param x
	 * @param y
	 */
	private void calculatePossibleMovments(int x, int y) {
		calculatePossibleMovements(new Point(x,y));
	}
	/**
	 * Calculates all possible movments from the active players current location
	 */
	private void calculatePossibleMovments() {
		calculatePossibleMovements(avatar.getLocation());
	}

	/**
	 * Returns the [] of counts of items in the players inventory
	 * @return
	 */
	public int[] getInventory(){
		return avatar.getInventory();
	}
	/**
	 * If a logical tile is null replace it with an inaccessible  tile.
	 */
	private void LogicalTileNullHandler(){
		for(int x = 0; x < worldMap.length; x++)
			for(int y =0; y <worldMap[x].length; y++)
				if(worldMap[x][y] == null)
					worldMap[x][y] = new LogicalTile(false);
	}


	//Networking Methods--------------------------------------------------------------------------------------------

	/**
	 * Replaces the game board with an updated one given by the network
	 *
	 * @param updatedGameBoard
	 */
	public void setGameBoard(GameObject[][] updatedGameBoard){
		this.gameBoard = updatedGameBoard;
	}
	/**
	 * Returns the current game board
	 * @return
	 */
	public GameObject[][] getGameBoard(){
		return this.gameBoard;
	}
	/**
	 * Returns the current [][] of logical tiles.
	 * @return
	 */
	public LogicalTile[][] getWorldMap(){
		return worldMap;
	}
	/**
	 * Replaces the current world map with an updated one from the server
	 * @param updatedMap
	 */
	public void setWorldMap(LogicalTile[][] updatedMap){
		this.worldMap = updatedMap;
		LogicalTileNullHandler();
	}



	/**
	 * Returns UnitPlayer because i needed it - Sorry Chris
	 * @return Local Player
	 */
	public UnitPlayer getAvatar(){
		return avatar;
	}



//	public byte[] getGameBoard(){
//		try {
//			return toByteArray(gameBoard);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public byte[] getWorldMap(){
//		try {
//			return toByteArray(worldMap);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * Taken From: http://www.java2s.com/Code/Java/File-Input-Output/Convertobjecttobytearrayandconvertbytearraytoobject.htm
//	 * @param obj
//	 * @return
//	 * @throws IOException
//	 */
//	public static byte[] toByteArray(Object obj) throws IOException {
//		byte[] bytes = null;
//		ByteArrayOutputStream bos = null;
//		ObjectOutputStream oos = null;
//		try {
//			bos = new ByteArrayOutputStream();
//			oos = new ObjectOutputStream(bos);
//			oos.writeObject(obj);
//			oos.flush();
//			bytes = bos.toByteArray();
//		} finally {
//			if (oos != null) {
//				oos.close();
//			}
//			if (bos != null) {
//				bos.close();
//			}
//		}
//		return bytes;
//	}

	/**
	 * This method checks if player has finished the turn
	 * @return if player is still active
	 */
	public boolean isTurn() {
		return isActive;
	}

//Graphics methods
	/**
	 * Gets the canvas to redraw all GameObjects on the gameBoard
	 */
	public void updateGameBoardGraphics(){
		ArrayList<GameObject> t = new ArrayList<GameObject>();
		for(int x =0; x < gameBoard.length; x++)
			for(int y = 0; y < gameBoard[0].length; y++)
				if(gameBoard[x][y] != null)
					t.add(gameBoard[x][y]);
		canvas.updateGameBoardGraphics(t);
	}
}
