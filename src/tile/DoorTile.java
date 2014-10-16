package tile;

import java.io.File;
import java.net.URI;
import java.net.URL;

/**
 * Testing tile, unless somehow it's liked
 * @author Dylan
 *
 */
public class DoorTile extends Tile{

	public DoorTile( URL imgPath,Character key) {
		super(imgPath,key);
		type = "Door_Tile";
		this.canMove = false;
	}
}
