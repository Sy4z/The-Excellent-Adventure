package gameRender;


import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import dataStorage.Data;
import tile.Tile;
	/**
	 * Main game Canvas.
	 *
	 * @author Greg Oswald,
	 * 		   300081254
	 *
	 */
	public class IsoCanvas extends Canvas{
		private Tile[][] map = null;
		private int WIDTH;//take this and height in as a parameter of the constructor.
		private int HEIGHT;
		private int TILE_WIDTH = 64;//26
		private int TILE_HEIGHT = 64;//52
		private int OFFSET_X;
		private int OFFSET_Y;
		
		/**
		 *
		 */
		public IsoCanvas(int Width, int Height){
			//do some usefull stuff.
			//some way of dynamically calculating offsets put this in a method??
			//set width height for canvas and tiles
			//set initial tilemap.
			map = Data.load(null);
			mapDebug();
			this.WIDTH = Width;
			this.HEIGHT = Height;
			OFFSET_X = (WIDTH/2) - (TILE_WIDTH)*(map[0].length)/2;//dont rely on this yet.
			OFFSET_Y = (HEIGHT/2) - (TILE_HEIGHT)*(map.length)/2; //   ^ ditto ^
			
		}
		/**
		 *
		 */
		public void paint(Graphics g){
			Graphics2D g2d = (Graphics2D) g;
			g.fillRect(0,0,WIDTH,HEIGHT);
			for(int y = 0; y <map.length;y++){
				for(int x = 0; x< map[y].length;x++){
					Point p = toIso((x*(TILE_WIDTH/2)),(y*(TILE_HEIGHT/2)));
					Tile tile = map[y][x]; 
					tile.draw(g2d, p.x+OFFSET_X, p.y+OFFSET_Y, (p.x+(TILE_WIDTH))+OFFSET_X,(p.y+(TILE_HEIGHT))+OFFSET_Y, 0, 0, 64,64);

				}
			}

		}
		/**
		 *
		 * @param updatedMap
		 */
		public void update(Tile[][] updatedMap){
			this.map = updatedMap;
			this.repaint();
		}
		/**
		 *
		 */
		public Dimension getPreferredSize(){
			return new Dimension(WIDTH,HEIGHT);
		}
		/**
		 *
		 * @param x
		 * @param y
		 * @return
		 */
		public Point toCart(int x, int y){
			return new Point ((2 * x + y) / 2,(2 * x - y) / 2);
		}
		/**
		 *
		 * @param x
		 * @param y
		 * @return
		 */
		private Point toIso(int x, int y){
			return new Point (x - y,(x + y) / 2);
		}
		/**
		 *
		 */
		private void loadImageTiles(){

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
						System.out.println("Pos "+x+":"+y+" -> "+map[y][x].getType());
					}
				}
			}
			System.out.println("==============================================");
		}
	}
