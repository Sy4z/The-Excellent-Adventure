package Tile;

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
		objs = new GameObject[10];
		type = "Floortile";
	}
}
