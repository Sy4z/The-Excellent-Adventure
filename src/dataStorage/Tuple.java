package dataStorage;

import gameWorld.Unit;
import tile.TileMultiton;


public class Tuple{
	public TileMultiton.type[][] tiles;
	public Unit[] units;

	public Tuple(TileMultiton.type[][] t, Unit[] u){
		tiles = t;
		units = u;
	}
}
