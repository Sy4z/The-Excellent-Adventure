package tile;

import java.io.File;

/**
 * Testing tile, unless somehow it's liked
 * @author Dylan
 *
 */
public class DoorTile extends Tile{

	public DoorTile( File imgPath,Character key) {
		super(imgPath,key);
		type = "Door_Tile";
	}
}
