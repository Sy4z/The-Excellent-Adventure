package gameWorld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This is a stationaryObject that is a hat stand. It sits in the world and acts
 * like a wall, though it will be draw as a hatstand.
 *
 * @author mcintochri1
 *
 */
public class StationaryObjectHatStand extends StationaryObject{
	private int heightOffSet;
	/**
	 * Constructs a hat stand given it's location
	 * @param p
	 */
	public StationaryObjectHatStand(Point p){
		super(p);
		try {
			BufferedImageHolder.addImage(ImageIO.read(this.getClass().getResource("/tile/tentacle.png")),"hasStand");
		} catch (IOException e) {
			e.printStackTrace();
		}

		heightOffSet = BufferedImageHolder.getimage("hatStand").getHeight();
		heightOffSet = Math.max(32 - heightOffSet, heightOffSet -32);

	}

	@Override
	public void draw(Graphics2D g, int dx, int dy) {
		g.setColor(new Color(155,144,255));
		g.drawImage(BufferedImageHolder.getimage("hatStand"), dx, dy-heightOffSet, null);


	}

}
