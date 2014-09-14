package gameWorld;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.IIOException;

public class Inventory {
	private final int defultMaxSize = 7;
	private int maxSize;
	private Item[] items;
	private int itemsHeld = 0;

	public Inventory() {
		this.maxSize = defultMaxSize;
		this.items = new Item[maxSize];
	}

	public Inventory(Item[] itemList, int maxSize) {
		this.maxSize = maxSize;
		this.items = itemList;
	}

	public boolean add(Item item) {
		if (itemsHeld == maxSize)
			return false;
		else
			items[itemsHeld++] = item;
		return true;
	}

	public Item drop(int idx) {
		if (!validIdx(idx) || items[idx] == null)
			// This may have to be dealt with in some way
			// but that will be done once the code that
			// calls this method is written
			return null;
		Item itm = items[idx];
		shunt(idx);
		return itm;
	}

	public boolean use(int idx) {
		if (validIdx(idx) && items[idx] != null) {
			items[idx].use();
			return true;
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
		if (idx > maxSize)
			return false;
		return true;
	}
}
