package gameWorld;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 * This is a generic robot and is the base unit type that the player will
 * control.
 *
 * @author mcintochri1
 *
 */
public class ServiceBot extends Unit {
	private Inventory inventory;

	public ServiceBot(Point loc) {
		super(loc);
		inventory = new Inventory();
		this.filePath = null;//Replace with File once image is aqquired

	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((curLocation == null) ? 0 : curLocation.hashCode());
		result = prime * result
				+ ((inventory == null) ? 0 : inventory.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ServiceBot))
			return false;
		ServiceBot other = (ServiceBot) obj;
		if (curLocation == null) {
			if (other.curLocation != null)
				return false;
		} else if (!curLocation.equals(other.curLocation))
			return false;
		if (inventory == null) {
			if (other.inventory != null)
				return false;
		} else if (!inventory.equals(other.inventory))
			return false;
		return true;
	}


	@Override
	public void draw(Graphics2D g, int dx, int dy, int dx2, int dy2, int sx,
			int sy, int sx2, int sy2) {
		g.drawOval(curLocation.x, curLocation.y, 10, 10);
	}

	public String toString(){
		return toString("");
	}

	public String toString(String append){
		StringBuilder s = new StringBuilder();
		append = "|\t" + append;
		s.append("ServiceBot{");
		s.append("\n"+append+"X: "+curLocation.x);
		s.append("\n"+append+"Y: "+curLocation.y);
		s.append("\n"+append+"Image: " + ((filePath != null) ? filePath.toString() : "NULL") + "");
		s.append("\n"+append+"isActive: "+isActive());
		s.append("\n"+append+"StandardAction: "+getStandardAction());
		s.append("\n"+append+"MoveAction: "+getMoveAction());
		s.append("\n"+append+"SwiftAction: "+getSwiftACtion());
		s.append("\n"+append+""+inventory.toString(append) + "\n");
		return s + "}";
	}

	public static void main(String[] args){
		ServiceBot b = new ServiceBot(new Point(6,10));
		System.out.println(b.toString());

	}



}
