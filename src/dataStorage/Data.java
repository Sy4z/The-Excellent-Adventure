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
    	File FT = new File("src" + File.separator + "tile" + File.separator + "t1.png");
    	File BT = new File("src" + File.separator + "tile" + File.separator + "t2.png");
    	Tile[][] t = {	{new FloorTile(0, 0, "floor_tile", new ArrayList<GameObject>(), FT), new FloorTile(1, 0, "floor_tile", new ArrayList<GameObject>(), FT), new FloorTile(2, 0, "floor_tile", new ArrayList<GameObject>(), FT)},
    					{new FloorTile(0, 0, "floor_tile", new ArrayList<GameObject>(), FT), new BlueTile(1, 1, "blue_tile", new ArrayList<GameObject>(), BT) , new FloorTile(2, 1, "floor_tile", new ArrayList<GameObject>(), FT)},
    					{new FloorTile(0, 2, "floor_tile", new ArrayList<GameObject>(), FT), new FloorTile(1, 2, "floor_tile", new ArrayList<GameObject>(), FT), new FloorTile(2, 2, "floor_tile", new ArrayList<GameObject>(), FT)}};
    	return t;
    }
}
