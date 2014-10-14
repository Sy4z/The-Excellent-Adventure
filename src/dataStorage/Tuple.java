package dataStorage;

import gameWorld.GameObject;
import gameWorld.Unit;
import tile.TileMultiton;


public class Tuple{
	public TileMultiton.type[][] tiles;
	public GameObject[] units;

	public Tuple(TileMultiton.type[][] t, GameObject[] u){
		tiles = t;
		units = u;
	}
}
