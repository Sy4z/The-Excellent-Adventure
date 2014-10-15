package gameWorld;

import java.io.IOException;
import java.io.Serializable;

/**
 * This class holds the count of all items of each type a player has and allows
 * the game to interact with it by adding or removing items.
 *
 * @author ChrisMcIntosh
 *
 */
public class Inventory implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 3278395666189208097L;

	//The types of item in the game
	public static enum itemTypes{KEY, RUSTY_NAIL, KATANA, PUPPY};
	//The number of each item a player has ordered by itemType Ordinal
	private int[] items;


	/**
	 * Constructor for basic inventory
	 */
	public Inventory() {
		items = new int[itemTypes.values().length];
	}


	/**
	 * Adds a number of items (numItems) to the player count of a given item
	 * type (type)
	 *
	 * @param type
	 * @param numItems
	 */
	public void add(itemTypes type, int numItems) {
		items[type.ordinal()] += numItems;
	}
	/**
	 * Returns the number of items of a given type a player has.
	 * @param ItemType type
	 * @return Number of items of itemType type
	 */
	public int numberOfItem(itemTypes type){
		return items[type.ordinal()];
	}
	/**
	 * Checks if the owner of this inventory has at least one key.
	 * @return If there is at least one key stored in inventory
	 */
	public boolean hasKey() {
		return items[itemTypes.KEY.ordinal()] > 0;
	}

	/**
	 * Adds a set of items to the inventory.
	 * @param chestContents
	 */
	public void add(int[] chestContents) {
		for(int i = 0; i < chestContents.length && i < items.length; i++)
			items[i] += chestContents[i];

	}

	/**
	 * Checks if a player has at least one key then uses one if there is one.
	 * @return If the player has a key.
	 */
	public boolean useKey() {
		boolean b = items[itemTypes.KEY.ordinal()] > 0;
		items[itemTypes.KEY.ordinal()] = Math.max(0, items[itemTypes.KEY.ordinal()]-1);
		return b;
	}
	/**
	 * Returns the array of counts of number of all item types in the inventory.
	 * @return
	 */
	public int[] getInventory(){
		return items;
	}

	/**
	 * If you lose a fight you lose all you puppies as the spoils of war.
	 */
	public void loseFight() {
		items[itemTypes.PUPPY.ordinal()] = 0;

	}


}
