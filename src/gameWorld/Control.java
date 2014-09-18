package gameWorld;

import java.io.File;

import dataStorage.Data;
import tile.Location;
import tile.Tile;

/**
 *
 * @author ChrisMcIntosh
 *
 */
public class Control {
	private Tile[][] gameBoard;
	private Unit[] units;
	private File defaultNewGameState = null;

	public void tick() {
		while (true) {

		}
	}
	/**
	 * Load Constructor
	 * @return
	 */
	public Control(File save, int width, int height){
		gameBoard = Data.load(save);
	}

	/**
	 * New Game Control
	 *
	 */
	public Control(int width, int height){
		gameBoard = Data.load(defaultNewGameState);
	}

	public boolean move(int ID, Location destination){
		if(outOfBounds(destination))
			return false;

		int x = destination.getX();
		int y = destination.getY();

		Tile target = gameBoard[x][y];
		//add is valid Tile to move to check.

		units[ID].getLocation().removeObject();
		gameBoard[x][y].addObject(units[ID]);
		units[ID].move(target);

		return true;

	}
	private boolean outOfBounds(Location destination) {
		int x = destination.getX();
		int y = destination.getY();
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
