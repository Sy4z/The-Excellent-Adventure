package gameWorld;

/**
 * This is a logical tile that is also a door, it holds if it is open to see if a key is needed to pass through it.
 * @author mcintochri1
 *
 */
public class LogicalTileDoor extends LogicalTile{

	private boolean open;
	/**
	 * This constructor creates a new closed door
	 * @param canTouchThis
	 */
	public LogicalTileDoor(boolean canTouchThis) {
		super(canTouchThis);
		this.open = false;
	}

	/**
	 * Checks if a door is open
	 * @return the open
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * This method opens or closes a door.
	 * @param open the open to set
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}



}
