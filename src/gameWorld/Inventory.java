package gameWorld;

import java.io.IOException;

/**
 *
 * @author ChrisMcIntosh
 *
 */
public class Inventory {

	public static enum itemTypes{KEY, RUSTY_NAIL, KATANA, PUPPY};
	private int[] items;


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


	public void add(int[] chestContents) {
		for(int i = 0; i < chestContents.length && i < items.length; i++)
			items[i] += chestContents[i];

	}


	public boolean useKey() {
		boolean b = items[itemTypes.KEY.ordinal()] > 0;
		items[itemTypes.KEY.ordinal()] = Math.max(0, items[itemTypes.KEY.ordinal()]-1);
		return b;
	}

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
