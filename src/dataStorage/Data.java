package dataStorage;

import gameRender.IsoCanvas;
import gameWorld.ServiceBot;
import gameWorld.Unit;

import java.awt.Point;
import java.io.File;
import java.lang.reflect.Field;

import javax.swing.JFrame;

import sun.reflect.*;
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
	static int b = 0;
	public Data(){}
	public static TileMultiton.type[][] load(){

		return null;
	}

	public static boolean save(){
		return false;
	}
	/**
	 * -----STANDIN WHILE I STUDY XML------
	 * @param fi Use null the File shall be ignored
	 *
	 * Returns a 2D array of Tiles for testing purposes, feel free to mess with
	 * @return A 2D Array of Tile
	 * @throws FileNotFoundException
	 */
	public static Tuple testSet(File fi){

		int sizeX = 10;
		int sizeY = 10;
		int entityX = 0;
		int entityY = 0;

		TileMultiton.type[][] t = new TileMultiton.type[sizeY][sizeX];
		//Creates an array of tiles sizeX by sizeY if statement specifys what coordinate entity will be placed.
		for(int y = 0;y<sizeY;y++){
			for(int x = 0;x<sizeX;x++){
				if(x == y + b){
					t[y][x] = TileMultiton.type.BLUE;
				}
				else{
					t[y][x] = TileMultiton.type.FLOOR;
				}
			}
		}
		Unit[] u = new ServiceBot[7];

		for(int i = 0; i < 7; i++){
			u[i] = new ServiceBot(new Point(i,i));
		}
		b = (b + 1 )%9;
		if(b == 0){
			b = -9;
		}
		return new Tuple(t,u);
	}

	public static void main(String args[]){
		JFrame j = new JFrame();
		j.setBounds(30, 20, 1000, 1000);
		IsoCanvas c = new IsoCanvas(j.getWidth(), j.getHeight());
		j.add(c);
		j.setVisible(true);
		while(true){
			c.update(testSet(null));
			try{
			Thread.sleep(300);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}


	}
}
