package gameWorld;

import java.awt.Graphics2D;

public class ItemRustyNail extends Item{

	@Override
	public boolean use() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString(String append) {
		return "This is a rusty nail";
	}

	@Override
	void draw(Graphics2D g, int dx, int dy) {
		// TODO Auto-generated method stub

	}
}
