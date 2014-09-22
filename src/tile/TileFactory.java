package tile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TileFactory {
	static Map<type,Tile> tiles = new HashMap<type, Tile>();
	public static enum type{ BLUE, FLOOR};

	public static Tile getTile(type type){
		if(tiles.get(type) == null){
			switch(type){
			case FLOOR: tiles.put(type, new FloorTile(
					new File("src" + File.separator + "tile" +
							File.separator + "tile.png")));
				break;
			case BLUE:  tiles.put(type , new BlueTile(
					new File("src" + File.separator + "tile" +
							File.separator + "orb.png")));
				break;
			}
		}

		return tiles.get(type);
	}
}
