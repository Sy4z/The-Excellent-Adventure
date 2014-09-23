package gameWorld;

import java.awt.Graphics2D;

/**
 *
 * @author ChrisMcIntosh
 *
 */
public abstract class GameObject {

	abstract void draw(Graphics2D g, int dx, int dy, int dx2, int dy2, int sx, int sy,
			int sx2, int sy2);

}
