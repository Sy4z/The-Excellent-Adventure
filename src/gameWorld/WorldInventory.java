package gameWorld;

import java.util.ArrayList;
import java.util.List;

public class WorldInventory extends Inventory {
	private int x;
	private int y;
	private List<GameObject> objs;
	private InteractiveObject interactive;

	public WorldInventory(int x, int y){
		super();
		this.x = x;
		this.y = y;
		objs = new ArrayList<GameObject>();
	}

	public boolean removeObject(GameObject obj) {
		return objs.remove(obj);
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
			if(interactive == null){
				return true;
			}
		}
		return false;
	}

	/**
	 * Add a gameObject to the tile
	 *
	 * @param newObject The object to be added
	 * @return True if the object was added, false otherwise
	 */
	public boolean addObject(GameObject newObject){
		if(gameObjectIsValid(newObject)){
			if(newObject instanceof InteractiveObject){
				interactive = (InteractiveObject) newObject;
			}

			if(objs.add(newObject)){
				return true;
			}
		}
		return false;
	}
}
