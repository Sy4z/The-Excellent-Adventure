package tile;

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
		objs = new GameObject[10];
		type = "blue_tile";
	}
}
