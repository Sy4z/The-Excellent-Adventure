package gameWorld;

import java.awt.Graphics2D;

public class ItemKey extends Item{

	@Override
	//A Key can not be used by itself once a text pane is implemented an output explaing that will be added to this method.
	public boolean use() {
		return false;
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
		StringBuilder s = new StringBuilder("Key{");
		append = "|\n" + append;

		return s + "\n" + append.substring(2) + "}";
	}


}
