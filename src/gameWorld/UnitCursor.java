package gameWorld;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This Unit is used for the player to use keyboard movment to decide where to move there Unit before finalizing the movment,
 * This allows us to use keyboard movement and still allow onl 2 distinct movments a turn.
 * @author mcintochri1
 *
 */
public class UnitCursor extends Unit{
	private float alpha_level = 0.4f;

	/**
	 * Contrucsts a Cursor with it's location and an ID (This will be an
	 * arbitrary ID as only one Cursor may exist at a time)
	 *
	 * @param loc
	 * @param ID
	 */
	public UnitCursor(Point loc, int ID) {
		super(loc, ID, null);
		try {
			BufferedImageHolder.addImage(ImageIO.read(new File("src/tile/cursor.png")),"Cursor");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Draw cursor with transparency specified by alpha_level
	 *
	 */
	@Override
	public void draw(Graphics2D g2d, int dx, int dy) {
		Composite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha_level);
		Composite initialAlpha = g2d.getComposite();
		g2d.setComposite(alpha);
		g2d.drawImage(BufferedImageHolder.getimage("Cursor"), dx, dy, null);
		g2d.setComposite(initialAlpha);
	}

	/**
	 * Moves the Cursor to a given location
	 * @param x
	 * @param y
	 */
	public void setLocation(int x, int y) {
		this.curLocation = new Point(x, y);
	}

}
