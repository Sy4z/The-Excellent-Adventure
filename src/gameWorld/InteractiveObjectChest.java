package gameWorld;

import gameWorld.Inventory.itemTypes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

public class InteractiveObjectChest extends InteractiveObject{

//	private ArrayList<Item> contents = new ArrayList<Item>();
	private int[] contents;

	public InteractiveObjectChest(int[] contents, Point p){
		super(p);
		this.contents = contents;
	}

	public InteractiveObjectChest(Point p){
		super(p);
		int[] itms = new int[itemTypes.values().length];
		for(int i = 0; i < itms.length; i++)
			itms[i] = (int) Math.max(0, Math.random()*15 -8);
		contents = itms;
	}

	public int[] takeContents(){
		int[]  tempContents = contents;
		contents = null;
		return tempContents;
	}
	@Override
	void draw(Graphics2D g, int dx, int dy) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return toString("");
	}

	public String toString(String append){
		StringBuilder s = new StringBuilder("Chest{");
		append = "|\t" + append;

		return s + "\n" + append.substring(2) + "}";
	}

	public int[] getContents() {
		return contents;
	}





}
