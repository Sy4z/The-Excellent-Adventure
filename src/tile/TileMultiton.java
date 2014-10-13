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

	public static enum type{BLUE, FLOOR, DOOR, OPENDOOR, CLOSEDOOR};

	/**
	 * Takes an enum from TileMultiton.type, returns the associated tile
	 *
	 * @param type
	 * @return the static tile which represents the game
	 */
	public static Tile getTile(type type){
		/*To add a new tile to the game, add it's constructor to this switch statement with a unique ENUM and unique charactor
		 * 
		 */
		if(tiles.get(type) == null){
			switch(type){
			case FLOOR: tiles.put(type, new FloorTile(
					new File("src" + File.separator + "tile" +
							File.separator + "tileD1.png"),'f'));
				break;
			case BLUE:  tiles.put(type , new BlueTile(
					new File("src" + File.separator + "tile" +
							File.separator + "tower.png"),'b'));
				break;
			case DOOR:  tiles.put(type , new DoorTile(
					new File("src" + File.separator + "tile" +
							File.separator + "tower.png"),'d'));//This should get it's own picture
				break;
			}
		}

		return tiles.get(type);
	}
	
	public static Tile getByRepresentation(char ch){
		for(Tile t : tiles.values()){
			if(t.getRepresentation() == ch){
				return t;
			}
		}
		throw new IllegalArgumentException("Either an invalid char was entered or a tile was created with an invalid char type");
	}
}
