package gameWorld;

import java.io.IOException;

/**
 *
 * @author ChrisMcIntosh
 *
 */
public class Inventory {
	private final int defultMaxSize = 7;
	private int maxSize;
	private Item[] items;
	private int itemsHeld = 0;// Current number of items in inventory

	/**
	 * Constructor for basic inventory
	 */
	public Inventory() {
		this.maxSize = defultMaxSize;
		this.items = new Item[maxSize];
	}

	/**
	 * Constructor for inventory with starting items or non standard size
	 *
	 * @param itemList
	 * @param maxSize
	 */
	public Inventory(Item[] itemList, int maxSize) {
		if (items.length == maxSize)
			this.items = itemList;
		else {
			items = new Item[maxSize];
			int j = 0;
			for (int i = 0; i < itemList.length; i++) {
				if (itemList[i] != null)
					try {
						this.items[j++] = itemList[i];
					} catch (IndexOutOfBoundsException e) {
						throw new IllegalArgumentException("Attempting to create invintory with more items than it's lenght");
					}
			}
		}

		this.maxSize = maxSize;
	}

	/**
	 * Adds an item to an inventory
	 *
	 * @param item
	 * @return
	 */
	public boolean add(Item item) {
		if (itemsHeld == maxSize)
			return false;
		else
			items[itemsHeld++] = item;
		return true;
	}

	/**
	 * Removes an item from an inventory and returns the removed item so it can
	 * be placed in the game environment
	 *
	 * @param idx
	 * @return
	 */
	public Item drop(int idx) {
		if (!validIdx(idx) || items[idx] == null)
			// This may have to be dealt with in some way
			// but that will be done once the code that
			// calls this method is written
			return null;
		Item itm = items[idx];
		shunt(idx);
		itemsHeld--;
		return itm;
	}
	/**
	 * Uses an item from an inventory.
	 * Items control how they are used
	 * @param idx
	 * @return
	 * May require extension for items used with other GameObjects or consumables.
	 */
	public boolean use(int idx) {
		if (validIdx(idx) && items[idx] != null) {
			return items[idx].use();
		}
		return false;
	}

	/**
	 * Moves all inventory items down by one place after the given index
	 *
	 * @param idx
	 */
	private void shunt(int idx) {
		if (validIdx(idx)) {
			for (int i = idx; i < maxSize - 1; i++)
				items[i] = items[i + 1];
			return;
		}
		try {
			throw new IOException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean validIdx(int idx) {
		if (idx < 0)
			return false;
		if (idx >= maxSize)
			return false;
		return true;
	}

	public String toString(){
		return toString("");
	}

	public String toString(String append){
		StringBuilder s = new StringBuilder("Inventory{");
		append += "|\t";
		if(items[0] != null){
			for(Item i: items){
				s.append("\n" + append + i.toString(append));
			}
		}
		return s+"\n"+append.substring(2)+"}";
	}

	public boolean hasKey() {
		for(Item itm: items)
			if(itm instanceof ItemKey)
				return true;
		return false;
	}
}
