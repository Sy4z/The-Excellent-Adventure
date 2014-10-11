package gameWorld;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author ChrisMcIntosh
 *
 */
public abstract class GameObject {
	protected BufferedImage img;

	abstract void draw(Graphics2D g, int dx, int dy);

	public abstract String toString(String append);

}
