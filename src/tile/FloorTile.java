package tile;

import java.io.File;
import java.net.URL;

/**
 * STANDIN represents the floor
 * @author macdondyla1
 *
 */
public class FloorTile extends Tile{

	public FloorTile(URL imgPath, Character key) {
		super( imgPath, key);
		type = "Floor_Tile";
		this.canMove = true;
	}
}
