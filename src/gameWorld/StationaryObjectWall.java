package gameWorld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This is a wall it is an object that the player can not move through and
 * provides no other interaction
 *
 * @author mcintochri1
 *
 */
public class StationaryObjectWall extends StationaryObject{
	private int heightOffSet;

	/**
	 * Contructs a wall given it's location
	 * @param p
	 */
	public StationaryObjectWall(Point p) {
		super(p);
		try {
			BufferedImageHolder.addImage(ImageIO.read(new File("src/tile/wall.png")),"wall");
		} catch (IOException e) {
			e.printStackTrace();
		}

		heightOffSet = BufferedImageHolder.getimage("wall").getHeight();
		heightOffSet = Math.max(32 - heightOffSet, heightOffSet -32);

	}

	@Override
	public void draw(Graphics2D g, int dx, int dy) {
		g.setColor(new Color(155,144,255));
		g.drawImage(BufferedImageHolder.getimage("wall"), dx, dy-heightOffSet, null);

	}

}
