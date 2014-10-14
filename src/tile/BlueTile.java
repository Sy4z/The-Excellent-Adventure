package tile;

import java.io.File;

/**
 * Testing tile, unless somehow it's liked
 * @author Dylan
 *
 */
public class BlueTile extends Tile{

	public BlueTile( File imgPath,Character key) {
		super(imgPath,key);
		type = "Blue_Tile";
		this.canMove = false;
	}
}
