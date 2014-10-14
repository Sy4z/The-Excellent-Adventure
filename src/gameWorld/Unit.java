package gameWorld;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import sun.net.util.IPAddressUtil;

/**
 * This is a Unit that is able to move around it the world, It has a resticted
 * amount of movement it can make per turn as well as keeping an inventory of
 * its items held and a unique ID.
 *
 * @author ChrisMcIntosh
 *
 */
public abstract class Unit extends GameObject {
	protected Inventory inventory;
	private boolean notTurnEnd;
	private boolean standardAction;
	private boolean moveAction;

	protected int ID;

	/**
	 * Constructs a unit given its location starting inventory and unique ID
	 *
	 * @param loc
	 * @param ID
	 * @param inventory
	 */
	public Unit(Point loc, int ID, Inventory inventory) {
		super(loc);
		this.ID = ID;
		this.inventory = inventory;
	}



	/**
	 * Updates a Entities information of its location. This will be used my the
	 * move methods in Control which will update all other information related
	 * to the move and ensure it is a valid move
	 */
	public void upDateLocation(Point newLocation){
		this.curLocation = newLocation;
	}
	/**
	 * Returns the current location of the unit.
	 * @return
	 */
	public Point getLocation() {
		return curLocation;

	}

	/**
	 * Draws the unit
	 */
	public abstract void draw(Graphics2D g, int dx, int dy);

	/**
	 * Resets the unit to have all of its actions, this method is used to reset
	 * a unit at the start of its turn
	 */
	public void activate() {
		moveAction = true;
		standardAction = true;
		notTurnEnd = true;

	}
	/**
	 * Checks if the Unit's turn is over yet.
	 * @return
	 */
	public boolean isNotTurnEnd() {
		return notTurnEnd;
	}

	/**
	 * Checks if the player has used it's standard action yet.
	 * @return
	 */
	public boolean getStandardAction(){
		return standardAction;
	}
	/**
	 * Checks if the player has used it's move action yet.
	 * @return
	 */
	public boolean getMoveAction(){
		return moveAction;

	}

	/**
	 * Returns the total number of movments a Unit could make this turn if it
	 * used all of its remaining actions
	 *
	 * @return
	 */
	public int getAvilableMoves() {
		int moves = 0;
		if(standardAction) moves++;
		if(moveAction) moves++;
		return moves;
	}

	/**
	 * Consumes one of a players actions and sets the the turn to be over if it was the last action.
	 */
	public void depleateMoves() {

		if(moveAction == false){
			standardAction = false;
			notTurnEnd = false;
		}
		else {
			moveAction = false;
		}
	}


	/**
	 * Returns the unique ID of the player
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}



	/**
	 * Checks if the player has at least one key, this is called before taking
	 * an action that would consume a key.
	 *
	 * @return
	 */
	public boolean hasKey() {
		return inventory.hasKey();
	}








}
