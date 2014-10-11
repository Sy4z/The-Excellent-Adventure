package gameWorld;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class InteractiveObjectChest extends InteractiveObject{

//	private ArrayList<Item> contents = new ArrayList<Item>();
	private int[] contents;

	public int[] takeContents(){
		return contents = null;
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





}
