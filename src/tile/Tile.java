/**
 * @name 		Dylan Macdonald
 * @author 		macdondyla1
 * @email  		dylan4823@gmail.com
 * @usercode	300282068
 *
 */

package tile;

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

	protected int x,y;
	protected String type;
	protected boolean hasInteractive = false;
	protected GameObject[] objs;
	protected int objIdx = 0;

	/**
	 *
	 * @return a Location object which stores the x and y of the given tile
	 */
	public Location getLocation(){
		return new Location(x,y);
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
	public GameObject[] getElements(){
		return objs;
	}

	/**
	 * Add a gameObject to the tile
	 *
	 * @param obj The object to be added
	 * @return True if the object was added, false otherwise
	 */
	public boolean addObject(GameObject obj){
		if(gameObjectIsValid(obj)){
			try{
				objs[objIdx++] = obj;
			}
			catch(IndexOutOfBoundsException excep){
				GameObject[] temp = new GameObject[objs.length*2];
				int i = 0;
				for(GameObject e : objs){
					temp[i] = e;
				}
				objs = temp;
			}
			return true;
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
}
