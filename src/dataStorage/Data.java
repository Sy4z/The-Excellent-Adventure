package dataStorage;

import gameWorld.GameObject;

import java.io.File;
import java.util.ArrayList;

import tile.*;

/**
 * handles all data saving and loading needs.
 * @author 	Dylan Macdonald,
 * 			macdondyla1
 * 			300282068
 * 			dylan4823@gmail.com
 *
 */
public class Data {

	public Data(){}

	/**
	 * -----STANDIN WHILE I STUDY XML------
	 * @param fi Use null the File shall be ignored
	 *
	 * Returns a 2D array of Tiles for testing purposes, feel free to mess with
	 * @return A 2D Array of Tile
	 * @throws FileNotFoundException
	 */
	public static Tile[][] load(File fi){
		int sizeX = 100;
		int sizeY = 100;
		int entityX = 0;
		int entityY = 0;
		File FT = new File("src" + File.separator + "tile" + File.separator + "tile.png");
		File BT = new File("src" + File.separator + "tile" + File.separator + "orb.png");
		Tile[][] t = new Tile[sizeY][sizeX];
		//Creates an array of tiles sizeX by sizeY if statement specifys what coordinate entity will be placed.
		for(int y = 0;y<sizeY;y++){
			for(int x = 0;x<sizeX;x++){
				//if(y == entityY && entityX == x){
					t[y][x] = new BlueTile(x, y, "blue_tile", new ArrayList<GameObject>(), BT);
//				}
//				else{
					t[y][x] = new FloorTile(x, y, "floor_tile", new ArrayList<GameObject>(), FT);
//				}
			}
		}
		return t;
	}
}
