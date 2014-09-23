package gameWorld;

public abstract class UnitCommand {
	protected boolean complete;

	/**
	 * Returns true if the UnitCommand has Completed it's task
	 */
	public boolean terminate(){
		return this.complete;
	}
	/**
	 * The unit executes within one tick worth of it's current command
	 */
	public abstract void takeAction();
}
