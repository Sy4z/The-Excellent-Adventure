package gameWorld;

/**
 *
 * @author ChrisMcIntosh
 *Interface for game objects that can be added to a players inventory.
 */
public abstract class Item extends GameObject{
	//TODO boolean maybe?
	public abstract void use();

	/**
	 * Method used to obtain all information about an object that is relevant to saving
	 * ie, type, name, filePath, damageValue, whether it extends grumpy cat
	 * @return An array of objects that
	 */
	public abstract Object[] save();
}