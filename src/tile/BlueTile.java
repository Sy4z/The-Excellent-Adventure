package tile;

import java.util.ArrayList;

import gameWorld.GameObject;
/**
 * Testing tile, unless somehow it's liked
 * @author Dylan
 *
 */
public class BlueTile extends Tile{
	public BlueTile(int dx,int dy){
		x = dx;
		y = dy;
		objs = new ArrayList<GameObject>();
		type = "blue_tile";
	}
}
