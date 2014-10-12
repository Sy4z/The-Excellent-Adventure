package gameWorld;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 *
 * @author ChrisMcIntosh
 *
 */
public abstract class GameObject implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 8896684741524936435L;
	protected BufferedImage img;

	abstract void draw(Graphics2D g, int dx, int dy);

	public abstract String toString(String append);

}
