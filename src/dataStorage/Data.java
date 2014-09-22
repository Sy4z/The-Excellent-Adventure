package dataStorage;

import java.io.File;
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
	public static TileFactory.type[][] load(File fi){

		int sizeX = 1000;
		int sizeY = 1000;
		int entityX = 0;
		int entityY = 0;

		TileFactory.type[][] t = new TileFactory.type[sizeY][sizeX];
		//Creates an array of tiles sizeX by sizeY if statement specifys what coordinate entity will be placed.
		for(int y = 0;y<sizeY;y++){
			for(int x = 0;x<sizeX;x++){
				if(y == x && x == y){
					t[y][x] = TileFactory.type.BLUE;
				}
				else{
					t[y][x] = TileFactory.type.FLOOR;
				}
			}
		}
		return t;
	}
}
