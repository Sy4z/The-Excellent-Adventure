package tile;

import java.io.File;

/**
 * STANDIN represents the floor
 * @author macdondyla1
 *
 */
public class FloorTile extends Tile{

	public FloorTile(File imgPath, Character key) {
		super( imgPath, key);
		type = "Floor_Tile";
	}
}
