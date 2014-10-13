package gameWorld;

public class LogicalTileDoor extends LogicalTile{

	private boolean open;

	public LogicalTileDoor(boolean canTouchThis) {
		super(canTouchThis);
		this.open = false;
	}

	/**
	 * @return the open
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}



}
