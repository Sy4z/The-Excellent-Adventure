package gameWorld;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
import javax.imageio.ImageIO;

/**
 *
 * @author ChrisMcIntosh
 *
 */
public abstract class Unit extends GameObject {
	protected Point curLocation;
	protected File filePath;
	protected UnitCommand currentOrders; // That I was just following #didnothingwrong

	public Unit(Point loc) {
		currentOrders = null;
		curLocation = loc;

	}

	/**
	 * Updates a Entities information of its location. This will be used my the
	 * move methods in Control which will update all other information related
	 * to the move and ensure it is a valid move
	 */
	public void move(Point destination) {
		curLocation = destination;
	}

	public Point getLocation() {
		return curLocation;

	}

	public boolean takeAction(){
		if(currentOrders == null)
			return false;
		currentOrders.takeAction();
		return true;
	}


	public void newOrder(UnitCommand newOrder) {
		currentOrders = newOrder;
	}

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
