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
    public static Tile[][] load(File fi){
    	Tile[][] t = {	{new FloorTile(0, 0), new FloorTile(1, 0), new FloorTile(2, 0)},
    					{new FloorTile(0, 0), new BlueTile(1, 1) , new FloorTile(2, 1)},
    					{new FloorTile(0, 2), new FloorTile(1, 2), new FloorTile(2, 2)}};
    	return t;
    }
}
