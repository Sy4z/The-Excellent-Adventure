package tile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import dataStorage.Data;

/**
 * Implements the the multiton design pattern to efficiently define each type of tile
 * @author macdondyla1
 *
 */
public class TileMultiton {

	//A map of each tile, for 1-to-1 access
	static Map<type,Tile> tiles = new HashMap<type, Tile>();

	//enum of tile types

	public static enum type{BLUE, FLOOR, OPENDOOR, CLOSEDOOR, BOX, TENTACLE,GREY,WATER};
	private static enum typeChar{b,f,d,c,g,t,a,w};
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
					TileMultiton.class.getResource("/tile/openDoor.png"),'f'));
				break;
			case BLUE:  tiles.put(type , new BlueTile(
					TileMultiton.class.getResource("/tile/tower.png"),'b'));
				break;
			case OPENDOOR:  tiles.put(type , new OpenDoor(TileMultiton.class.getResource("/tile/openDoor.png"),'d'));//This should get it's own picture
				break;
			case CLOSEDOOR:  tiles.put(type , new ClosedDoor(
					TileMultiton.class.getResource("/tile/closedDoor.png"),'c'));//This should get it's own picture
				break;
			case BOX: 		tiles.put(type, new Box(
					TileMultiton.class.getResource("/tile/t2.png"),'g'));
						break;
			case TENTACLE:	tiles.put(type, new Tentacle(
					TileMultiton.class.getResource("/tile/tentacle.png"),'t'));
						break;
			case GREY: 		tiles.put(type, new Grey(
					TileMultiton.class.getResource("/tile/tile.png"),'a')); break;
			case WATER: 	tiles.put(type, new Water(
					TileMultiton.class.getResource("/tile/water.png"), 'w')); break;
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

	/**
	 * Retunr the relative type from a given char
	 * @param ch
	 * @return
	 */
	public static type getTypeByRepresentation(char ch){
		Data.error(ch+"");
		return type.values()[typeChar.valueOf(ch+"").ordinal()];
	}
}
