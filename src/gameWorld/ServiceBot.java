package gameWorld;

import tile.Tile;

/**
 * This is a generic robot and is the base unit type that the player will
 * control.
 *
 * @author mcintochri1
 *
 */
public class ServiceBot implements Unit {
	private Inventory inventory;
	private Tile curLocation;

	public ServiceBot(Tile loc) {
		inventory = new Inventory();
		curLocation = loc;
	}

	@Override
	public void move(Tile destination) {
		curLocation = destination;
	}

	@Override
	public Tile getLocation() {
		return curLocation;

	}

	public boolean remove(){
		return curLocation.removeObject(this);
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


}
