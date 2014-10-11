package gameWorld;

import java.awt.Color;
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
public class ServiceBot extends Unit {
	private BufferedImage img;
	private int heightOffSet;

	public ServiceBot(Point loc, int ID) {
		super(loc, ID,new Inventory());
		this.filePath = null;//Replace with File once image is aqquired
		try {
			img = ImageIO.read(new File("src/tile/wastelander0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		heightOffSet = img.getHeight();
		heightOffSet = Math.max(32 - heightOffSet, heightOffSet -32);
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
	public void draw(Graphics2D g, int dx, int dy) {
		g.setColor(new Color(155,144,255));
		g.drawImage(img, dx, dy-heightOffSet, null);
	}

	public String toString(){
		return toString("");
	}

	public String toString(String append){
		StringBuilder s = new StringBuilder();
		append = "|\t" + append;
		s.append(ServiceBot.class.getSimpleName()+"{");

		s.append("\n"+append+"X: "+curLocation.x);
		s.append("\n"+append+"Y: "+curLocation.y);
		s.append("\n" +append + "ID: " + ID);
		s.append("\n"+append+"Image: " + ((filePath != null) ? filePath.toString() : "NULL") + "");
		s.append("\n"+append+"isActive: "+isActive());
		s.append("\n"+append+"StandardAction: "+getStandardAction());
		s.append("\n"+append+"MoveAction: "+getMoveAction());
		s.append("\n"+append+""+inventory.toString(append));

		return s +"\n" + append.substring(2) + "}";
	}



}
