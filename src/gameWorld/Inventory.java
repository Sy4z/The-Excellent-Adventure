package gameWorld;

import java.io.IOException;

/**
 *
 * @author ChrisMcIntosh
 *
 */
public class Inventory {

	public static enum itemTypes{KEY, RUSTY_NAIL, KATANA, PUPPY};
	int[] items;


	/**
	 * Constructor for basic inventory
	 */
	public Inventory() {
		items = new int[itemTypes.values().length];
	}


	/**
	 * Adds an item to an inventory
	 *
	 * @param item
	 * @return
	 */
	public void add(itemTypes type, int numItems) {
		items[type.ordinal()] += numItems;
	}

	public int numberOfItem(itemTypes type){
		return items[type.ordinal()];
	}

	public boolean hasKey() {
		return items[itemTypes.KEY.ordinal()] > 0;
	}
}
