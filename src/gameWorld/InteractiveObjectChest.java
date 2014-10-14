package gameWorld;

import gameWorld.Inventory.itemTypes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

/**
 * This is a chest, If a player ends their turn on one of these they will get
 * all the items in it added to their inventory.
 *
 * @author mcintochri1
 *
 */
public class InteractiveObjectChest extends InteractiveObject{

	private int[] contents;

	/**
	 * Constructs a chest from given contents
	 * @param contents
	 * @param p
	 */
	public InteractiveObjectChest(int[] contents, Point p){
		super(p);
		this.contents = contents;
	}

	/**
	 * Constructs a chest with randomized contents
	 * @param p
	 */
	public InteractiveObjectChest(Point p){
		super(p);
		int[] itms = new int[itemTypes.values().length];
		for(int i = 0; i < itms.length; i++)
			itms[i] = (int) Math.max(0, Math.random()*15 -8);
		contents = itms;
	}

	@Override
	void draw(Graphics2D g, int dx, int dy) {
		// TODO Auto-generated method stub

	}

	/**
	 * Returns the contents of the chest
	 * @return The Chests Contents
	 */
	public int[] getContents() {
		return contents;
	}





}
