package gameWorld;

import java.awt.Graphics2D;

/**
 *
 * @author ChrisMcIntosh
 *Interface for game objects that can be added to a players inventory.
 */
public abstract class Item extends GameObject{
	//TODO boolean maybe?
	public abstract boolean use();

	void draw(Graphics2D g, int dx, int dy) {
		
		
	}
}