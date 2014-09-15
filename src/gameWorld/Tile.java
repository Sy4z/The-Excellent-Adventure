/**
 * @name 		Dylan Macdonald
 * @author 		macdondyla1
 * @email  		dylan4823@gmail.com
 * @usercode	300282068
 *
 */

package gameWorld;

/**
 *
 * Interface for the Tiles
 * Every Tile should inherit this to make working with tiles not impossible
 *
 */
public interface Tile {

	/**
	 *
	 * @return a Location object which stores the x and y of the given tile
	 */
	public Location getLocation();
	/**
	 *
	 * @return A string denoting the type of tile that this represents
	 */
	public String getType();
	/**
	 *
	 * @return an array of objects that are currently located on this tile
	 */
	public GameObject[] getElements();

	/**
	 * Add a gameObject to the tile
	 *
	 * @param obj The object to be added
	 */
	public void addObject(GameObject obj);
}
