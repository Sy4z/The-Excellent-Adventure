package gameRender;


import gameWorld.Unit;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.*;

import dataStorage.Data;
import tile.Tile;
import tile.TileMultiton;
import tile.TileMultiton.type;
	/**
	 * Main game Canvas.
	 *
	 * @author Greg Oswald,
	 * 		   300081254
	 *
	 */
	public class IsoCanvas extends Canvas{
		private TileMultiton.type[][] map = null;
		private int WIDTH;
		private int HEIGHT;
		private int TILE_WIDTH = 64;//26
		private int TILE_HEIGHT = 64;//52
		private int OFFSET_X;
		private int OFFSET_Y;
		private int HALF_TILE = TILE_HEIGHT/2;
//		private boolean debug = true;

		/**
		 *
		 */
		public IsoCanvas(int Width, int Height){
			map = Data.testSet(null);
//			mapDebug();
			this.WIDTH = Width;
			this.HEIGHT = Height;
			calculateOffset();
		}
		/**
		 *
		 */
		public void paint(Graphics g){
			Graphics2D g2d = (Graphics2D) g;
			g.fillRect(0,0,WIDTH,HEIGHT);
			Tile tile;
			for(int y = 0; y <map.length;y++){
				for(int x = 0; x< map[y].length;x++){
					Point p = toIso((x*(TILE_WIDTH/2)),(y*(TILE_HEIGHT/2)));//why tile size/2 hmm
					tile = TileMultiton.getTile(map[y][x]);
					//top left vertex
					int x1 = (p.x+OFFSET_X);
					int y1 = (p.y+OFFSET_Y);
					//bottom right vertex
					int x2 = (p.x+(TILE_WIDTH))+OFFSET_X;
					int y2 = (p.y+(TILE_HEIGHT))+OFFSET_Y;
					tile.draw(g2d, x1,y1 ,x2 ,y2, 0, 0, TILE_WIDTH,TILE_HEIGHT);
					}
			}
		}
		/**
		 *
		 * @param updatedMap
		 */
		public void update(TileMultiton.type[][] updatedMap){
			this.map = updatedMap;
			this.repaint();
		}
		/**
		 *
		 */

		/**
		 *Returns the 2d representation of a isometric point
		 * @param x
		 * @param y
		 * @return
		 */
		public Point to2d(int x, int y){
			return new Point ((2 * x + y) / 2,(2 * x - y) / 2);
		}
		/**
		 *Returns the Isometric representation of a 2d point
		 * @param x
		 * @param y
		 * @return
		 */
		private Point toIso(int x, int y){
			return new Point ((x - y),(x + y) / 2);
		}
		/**
		 * Calculates the offsets needed to draw the map centered on the canvas.
		 */
		private void calculateOffset(){
			OFFSET_X = (int)((WIDTH/2) - (TILE_WIDTH)*1.5)+TILE_WIDTH;//spread this calculation out.
			OFFSET_Y = (int)((HEIGHT/2) - ((HALF_TILE)*map.length)/2)-HALF_TILE;//this too.
		}
		
		
		/**
		 * 
		 * @param unit
		 */
		public void moveUnit(Unit unit, Stack<Point> cordinates){
			
		}
		/**
		 * Was Reading some of the Commander keen source code
		 * that was recently released and they did this, awesome idea
		 * (the wrapper not debug statments they have always been so).
		 * Nicely wrapped debug statements related to the tile map
		 *                   WORK IN PROGRESS
		 */
		private void mapDebug(){
			System.out.println("=================Map debug====================");
			if(map == null){
				System.out.println("Somethinghasgoneterriblywrong : Map is null");
			}
			else{
				System.out.println("Map is: "+map[0].length+" x "+map.length+" tiles");
				System.out.println("Map contains:");
				for(int y = 0; y <map.length;y++){
					for(int x = 0; x< map[y].length;x++){
						System.out.println("Pos "+x+":"+y+" -> "+TileMultiton.getTile(map[y][x]).getType());
					}
				}
			}
			System.out.println("==============================================");
		}
	}
