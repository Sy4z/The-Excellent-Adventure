package gameWorld;

import gameWorld.Inventory.itemTypes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * This is a chest, If a player ends their turn on one of these they will get
 * all the items in it added to their inventory.
 *
 * @author mcintochri1
 *
 */
public class InteractiveObjectChest extends InteractiveObject{

	private int[] contents;
	private int heightOffSet;

	/**
	 * Constructs a chest from given contents
	 * @param contents
	 * @param p
	 */
	public InteractiveObjectChest(int[] contents, Point p){
		super(p);
		this.contents = contents;
		try {
			BufferedImageHolder.addImage(ImageIO.read(new File("src/tile/box.png")),"Player");
		} catch (IOException e) {
			e.printStackTrace();
		}

		heightOffSet = BufferedImageHolder.getimage("tentacle").getHeight();
		heightOffSet = Math.max(32 - heightOffSet, heightOffSet -32);

	}

	/**
	 * Constructs a chest with randomized contents
	 * @param p
	 */
	public InteractiveObjectChest(Point p){
		super(p);
		int[] itms = new int[itemTypes.values().length];
		for(int i = 0; i < itms.length; i++)
			itms[i] = (int) Math.max(0, Math.random()*15 -8);
		contents = itms;
		try {
			BufferedImageHolder.addImage(ImageIO.read(new File("src/tile/box.png")),"Player");
		} catch (IOException e) {
			e.printStackTrace();
		}

		heightOffSet = BufferedImageHolder.getimage("tentacle").getHeight();
		heightOffSet = Math.max(32 - heightOffSet, heightOffSet -32);

	}

	@Override
	public void draw(Graphics2D g, int dx, int dy) {
		g.setColor(new Color(155,144,255));
		g.drawImage(BufferedImageHolder.getimage("box"), dx, dy-heightOffSet, null);

	}

	/**
	 * Returns the contents of the chest
	 * @return The Chests Contents
	 */
	public int[] getContents() {
		return contents;
	}





}
