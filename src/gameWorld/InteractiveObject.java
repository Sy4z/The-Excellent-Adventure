package gameWorld;

import java.awt.Point;

/**
 *These are objects that the player can interact with by standing on.
 *Such as chests and Monsters
 * @author ChrisMcIntosh
 *
 */
public abstract class InteractiveObject extends GameObject{
	
	public InteractiveObject(Point p) {
		super(p);
	}

}
