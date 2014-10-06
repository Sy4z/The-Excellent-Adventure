package gameWorld;

import java.awt.Graphics2D;

public class InteractiveObjectChest extends InteractiveObject{

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
		append = "|\n" + append;

		return s + "\n" + append.substring(2) + "}";
	}

}
