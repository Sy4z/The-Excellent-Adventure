package tile;

import java.util.ArrayList;

import gameWorld.GameObject;
/**
 * STANDIN represents the floor
 * @author macdondyla1
 *
 */
public class FloorTile extends Tile{
	public FloorTile(int dx,int dy){
		x = dx;
		y = dy;
		objs = new ArrayList<GameObject>();
		type = "floor_tile";
	}
}
