package gameWorld;

import Tile.Tile;

/**
 *
 * @author ChrisMcIntosh
 *
 */
public interface Entity extends GameObject{
	/**
	 * Updates a Entities information of its location.
	 * This will be used my the move methods in Control which will update all
	 * other information related to the move and ensure it is a valid move
	 */
	public void move(Tile destination);
}