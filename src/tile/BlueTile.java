package tile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import gameWorld.GameObject;
/**
 * Testing tile, unless somehow it's liked
 * @author Dylan
 *
 */
public class BlueTile extends Tile{

	public BlueTile(int dx, int dy, String type,
			List<GameObject> objectsOnTile, File imgPath) {
		super(dx, dy, type, objectsOnTile, imgPath);
	}
}
