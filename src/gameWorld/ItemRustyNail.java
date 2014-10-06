package gameWorld;

import java.awt.Graphics2D;

public class ItemRustyNail extends Item{

	@Override
	public boolean use() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return toString("");
	}

	@Override
	void draw(Graphics2D g, int dx, int dy) {
		// TODO Auto-generated method stub

	}

	public String toString(String append){
		StringBuilder s = new StringBuilder("Rusty Nail{");
		append = "|\n" + append;

		return s + "\n" + append.substring(2) + "}";
	}
}
