package gameWorld;

import java.awt.Graphics2D;

public class ItemKey extends Item{

	@Override
	//A Key can not be used by itself once a text pane is implemented an output explaing that will be added to this method.
	public boolean use() {
		return false;
	}

	@Override
	public Object[] save() {
		Object[] data = new Object[1];
		data[0] = this;
		return data;
	}

	@Override
	void draw(Graphics2D g, int dx, int dy, int dx2, int dy2, int sx, int sy,
			int sx2, int sy2) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "This is a Key, you could use it to open something. Alternitivly it looks tasty.";
	}
	

}
