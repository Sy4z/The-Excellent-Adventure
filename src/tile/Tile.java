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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import gameWorld.GameObject;
import gameWorld.InteractiveObject;
import gameWorld.Item;

/**
 *
 * Interface for the Tiles
 * Every Tile should inherit this to make working with tiles not impossible
 *
 */
public abstract class Tile {

	protected int x,y ;
	protected String type;
	protected boolean hasInteractive = false;
	protected List<GameObject> objs;
	protected int objIdx = 0;
	protected File imgPath;

	public Tile(int dx, int dy, String type, List<GameObject> objectsOnTile, File imgPath){
		this.type = type;
		objs = objectsOnTile;
		this.imgPath = imgPath;
	}

	/**
	 *
	 * @return a Point object which stores the x and y of the given tile
	 */
	public Point getLocation(){
		return new Point(x,y);
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
	public List<GameObject> getElements(){
		return objs;
	}

	/**
	 * Add a gameObject to the tile
	 *
	 * @param newObject The object to be added
	 * @return True if the object was added, false otherwise
	 */
	public boolean addObject(GameObject newObject){
		if(gameObjectIsValid(newObject)){
			if(objs.add(newObject)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Determine if a given object may be added to the array of GameObjects for this tile
	 *
	 * @param obj The GameObject
	 * @return True if the item may be added, else False
	 */
	private boolean gameObjectIsValid(GameObject obj){
		if(obj instanceof Item){
			return true;
		}
		if(obj instanceof InteractiveObject){
			if(!hasInteractive){
				hasInteractive = true;
				return true;
			}
		}
		return false;
	}

	public boolean canMove(Tile e){
		//XXX
		//TODO
		//FIXME
		return true;
	}

	public boolean draw(Graphics2D g, int dx, int dy, int dx2, int dy2,int sx, int sy, int sx2,int sy2){
		try {
			BufferedImage img = ImageIO.read(imgPath);

			g.drawImage(img, dx, dy, dx2, dy2, sx, sy, sx2, sy2, null);
			for(GameObject o : objs){
				o.draw(g,dx,dy,dx2,dy2,sx,sy,sx2,sy2);
			}
		} catch (IOException e) {
			//FORCES ALL TILES TO HAVE AN IMG
			e.printStackTrace();
		}
		return false;

	}

	public boolean removeObject(GameObject obj) {
		return objs.remove(obj);
	}
}
