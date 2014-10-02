package tile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Implements the the multiton design pattern to efficiently define each type of tile
 * @author macdondyla1
 *
 */
public class TileMultiton {

	//A map of each tile, for 1-to-1 access
	static Map<type,Tile> tiles = new HashMap<type, Tile>();

	//enum of tile types
	public static enum type{BLUE, FLOOR};

	/**
	 * Takes an enum from TileMultiton.type, returns the associated tile
	 *
	 * @param type
	 * @return the static tile which represents the game
	 */
	public static Tile getTile(type type){
		if(tiles.get(type) == null){
			switch(type){
			case FLOOR: tiles.put(type, new FloorTile(
					new File("src" + File.separator + "tile" +
							File.separator + "tile.png"),'f'));
				break;
			case BLUE:  tiles.put(type , new BlueTile(
					new File("src" + File.separator + "tile" +
							File.separator + "tower.png"),'b'));
				break;
			}
		}

		return tiles.get(type);
	}
}
