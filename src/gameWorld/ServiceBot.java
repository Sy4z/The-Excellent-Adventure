package gameWorld;

import Tile.Tile;

/**
 * This is a generic robot and is the base unit type that the player will
 * control.
 *
 * @author mcintochri1
 *
 */
public class ServiceBot implements Entity {
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

}
