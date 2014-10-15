package gameWorld;

import gameWorld.Inventory.itemTypes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class InteractiveObjectMonster extends InteractiveObject{

	private int[] contents;
	//This is the combat power of a monster
	private int str;
	/**
	 * Makes a monster given a it's items held, combat power and location
	 * @param loot
	 * @param strength
	 * @param p
	 */
	public InteractiveObjectMonster(int[] loot, int strength, Point p){
		super(p);
		contents = loot;
		str = strength;
	}
	/**
	 * Makes a monster randomizing its items held and combat power
	 * @param p
	 */
	public InteractiveObjectMonster(Point p){
		super(p);
		int[] itms = new int[itemTypes.values().length];
		for(int i = 0; i < itms.length; i++)
			itms[i] = (int) Math.max(0, Math.random()*50 -25);
		contents = itms;
		str = (int) Math.max(50, Math.random() * 200);
	}

	/**
	 * Fight a monster if your power is greater you get it's loot
	 * Otherwise null is returned and the player will deal
	 * with a loss accordingly.
	 *
	 * @param Power
	 * @return
	 */
	public  int[] fight(int Power){
		if(Power > str){
			return contents;
		}
		return null;
	}
	@Override
	/**
	 * Draws itself at a given location
	 */
	void draw(Graphics2D g, int dx, int dy) {
		// TODO Auto-generated method stub

	}

	/**
	 *Returns the items hold by the monster
	 * @return The monsters loot
	 */
	public int[] getContents() {
		return contents;
	}

}
