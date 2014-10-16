package tile;

import java.io.File;
import java.net.URL;

/**
 * Testing tile, unless somehow it's liked
 * @author Dylan
 *
 */
public class BlueTile extends Tile{

	public BlueTile( URL imgPath,Character key) {
		super(imgPath,key);
		type = "Blue_Tile";
		this.canMove = false;
	}
}
