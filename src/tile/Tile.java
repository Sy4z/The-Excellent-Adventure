/**
 * @name 		Dylan Macdonald
 * @author 		macdondyla1
 * @email  		dylan4823@gmail.com
 * @usercode	300282068
 *
 */

package tile;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 *
 * Interface for the Tiles
 * Every Tile should inherit this to make working with tiles not impossible
 *
 */
public abstract class Tile {
	protected static boolean[] chars = new boolean[200]; //if this proves to be to few, extend it
	protected Character key;
	protected String type;

	protected BufferedImage img;
	protected int heightOffSet;


	public Tile(File imgPath, Character key){
		try {
			img = ImageIO.read(imgPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(key < chars.length && !chars[key]){
			this.key = key;
			chars[key] = true;
		}
		else{
			throw new IllegalArgumentException("Key already used, choose another up to char 200");
		}
		//-----Build the height offset-----
		heightOffSet = img.getHeight();
		heightOffSet = Math.max(32 - heightOffSet, heightOffSet -32);
	}


	/**
	 *
	 * @return A string denoting the type of tile that this represents
	 */
	public String getType(){
		return type;
	}
	/**
	 *
	 * @return an array of objects that are currently located on this tile
	 */
//	public List<GameObject> getElements(){
//		return objs;
//	}


	public boolean draw(Graphics2D g, int dx, int dy){
		return g.drawImage(img, dx, dy-heightOffSet,null);

	}

	public Character getRepresentation(){
		return key;
	}

}
