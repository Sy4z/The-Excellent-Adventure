package gameWorld;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import sun.net.util.IPAddressUtil;

/**
 *
 * @author ChrisMcIntosh
 *
 */
public abstract class Unit extends GameObject {
	protected Inventory inventory;
	protected Point curLocation;
	private boolean isActiveUnit;
	private boolean standardAction;
	private boolean moveAction;

	protected int ID;
	public Unit(Point loc, int ID, Inventory inventory) {
		curLocation = loc;
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

	public Point getLocation() {
		return curLocation;

	}


	public abstract void draw(Graphics2D g, int dx, int dy);

	public void activate() {
		moveAction = true;
		standardAction = true;
		this.isActiveUnit = true;

	}

	public boolean isActive() {
		return isActiveUnit;
	}

	public boolean getStandardAction(){
		return standardAction;
	}

	public boolean getMoveAction(){
		return moveAction;

	}

	public int getAvilableMoves() {
		int moves = 0;
		if(standardAction) moves++;
		if(moveAction) moves++;
		return moves;
	}

	public void depleateMoves() {

		if(moveAction == false)
			standardAction = false;
		else moveAction = false;
	}


	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}


	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}

	public boolean hasKey() {
		return inventory.hasKey();
	}








}
