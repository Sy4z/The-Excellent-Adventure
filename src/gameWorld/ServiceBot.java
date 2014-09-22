package gameWorld;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This is a generic robot and is the base unit type that the player will
 * control.
 *
 * @author mcintochri1
 *
 */
public class ServiceBot implements Unit {
	private Inventory inventory;
	private Point curLocation;
	private File filePath = null;

	public ServiceBot(Point loc) {
		inventory = new Inventory();
		curLocation = loc;
	}

	@Override
	public void move(Point destination) {
		curLocation = destination;
	}

	@Override
	public Point getLocation() {
		return curLocation;

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((curLocation == null) ? 0 : curLocation.hashCode());
		result = prime * result
				+ ((inventory == null) ? 0 : inventory.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ServiceBot))
			return false;
		ServiceBot other = (ServiceBot) obj;
		if (curLocation == null) {
			if (other.curLocation != null)
				return false;
		} else if (!curLocation.equals(other.curLocation))
			return false;
		if (inventory == null) {
			if (other.inventory != null)
				return false;
		} else if (!inventory.equals(other.inventory))
			return false;
		return true;
	}

	@Override
	public void draw(Graphics2D g, int dx, int dy, int dx2, int dy2, int sx,
			int sy, int sx2, int sy2) {

		BufferedImage img = null;
		try {
			img = ImageIO.read(filePath);
		} catch (IOException e) {

			System.err.println(e + "");
		}
		g.drawImage(img, dx, dy, dx2, dy2, sx, sy, sx2, sy2, null);


	}


}
