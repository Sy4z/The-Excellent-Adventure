package gameWorld;

import gameWorld.Inventory.itemTypes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

/**
 *This is the base unit that a player will be controlling in the game
 *
 * @author mcintochri1
 *
 */

public class UnitPlayer extends Unit{

	private int heightOffSet;
	/**
	 * Contructs a UnitPlayer given it's starting location and unique ID
	 * @param loc
	 * @param ID
	 */
	public UnitPlayer(Point loc, int ID) {
		super(loc, ID,new Inventory());
		try {
			BufferedImageHolder.addImage(ImageIO.read(new File("src/tile/wastelander0.png")),"Player");
		} catch (IOException e) {
			e.printStackTrace();
		}
		heightOffSet = BufferedImageHolder.getimage("Player").getHeight();
		heightOffSet = Math.max(32 - heightOffSet, heightOffSet -32);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((curLocation == null) ? 0 : curLocation.hashCode());
		result = prime * result
				+ ((inventory == null) ? 0 : inventory.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UnitPlayer))
			return false;
		UnitPlayer other = (UnitPlayer) obj;
		if (curLocation == null) {
			if (other.curLocation != null)
				return false;
		} else if (!curLocation.equals(other.curLocation))
			return false;
		if (inventory == null) {
			if (other.inventory != null)
				return false;
		} else if (!inventory.equals(other.inventory))
			return false;
		return true;
	}


	@Override
	public void draw(Graphics2D g, int dx, int dy) {
		g.setColor(new Color(155,144,255));
		g.drawImage(BufferedImageHolder.getimage("Player"), dx, dy-heightOffSet, null);
	}



	/**
	 * This method adds a int[] of items to the players inventory adding the
	 * number stored in each index to the count of the itemType with the Ordinal
	 * mathing the index
	 *
	 * @param chestContents
	 */
	public void addToInventory(int[] chestContents) {
		inventory.add(chestContents);

	}

	/**
	 * This uses a key from the players inventory reducing the number of keys
	 * held by the player by one.
	 */
	public void useKey() {
		inventory.useKey();

	}
	/**
	 * Returns the contents of the players inventory
	 * @return
	 */
	public int[] getInventory(){
		return this.inventory.getInventory();
	}

	/**
	 * adds a number (i) of items of a given type (type) to the players
	 * inventory
	 *
	 * @param type
	 * @param i
	 */
	public void addToInventory(itemTypes type, int i) {
		this.inventory.add(type, i);

	}

	/**
	 * This is called when a player loses a fight, this makes the player lose
	 * all of their puppies as there opponent who has defeated them obsconds with them.
	 */
	public void loseFight() {
		inventory.loseFight();
	}

	/**
	 *Returns the number of items of a given type a player has.
	 * @param type
	 * @return
	 */
	public int numberOfItem(itemTypes type) {
		return inventory.numberOfItem(type);
	}


	public void endTurn() {
		while(this.getAvilableMoves() != 0){
			depleateMoves();

		}
	}


}
