package Tests;

import java.awt.Point;
import java.awt.image.BufferedImage;

import runGame.Main;
import tile.TileMultiton;
import dataStorage.Data;
import dataStorage.Tuple;
import gameRender.IsoCanvas;
import gameWorld.GameObject;
import gameWorld.LogicalTile;
import gameWorld.UnitPlayer;
import gameWorld.World;

public class utils {
	public static World createWorld(){
		Tuple t = Data.testSet(null);
		LogicalTile[][] logicTiles = new LogicalTile[t.tiles[1].length][t.tiles.length];
		GameObject[][] gameObjects = new GameObject[t.tiles[1].length][t.tiles.length];
		int id = 0;
		for(int i = 0; i < t.tiles[1].length;i++){
			for(int j = 0; j < t.tiles.length; j++){
				logicTiles[i][j] = new LogicalTile(TileMultiton.getTile(t.tiles[i][j]).getCanMove());
				if(i == j){
					gameObjects[i][j] = new UnitPlayer(new Point(i,j),id++);
				}
			}
		}


		Main.cvs = new IsoCanvas(0, 0, t.tiles);
		World world = new World(logicTiles, gameObjects);

		return world;
	}
}
