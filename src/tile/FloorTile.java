package tile;

import java.io.File;

/**
 * STANDIN represents the floor
 * @author macdondyla1
 *
 */
public class FloorTile extends Tile{

	public FloorTile(File imgPath) {
		super( imgPath);
		type = "Floor_Tile";
	}
}
