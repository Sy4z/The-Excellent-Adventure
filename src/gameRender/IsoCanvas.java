package gameRender;


import gameWorld.GameObject;
import gameWorld.Unit;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JPanel;

import dataStorage.*;
import sun.management.counter.Units;
import tile.Tile;
import tile.TileMultiton;

	/**
	 * Main game Canvas.
	 *
	 * @author 	Greg Oswald,
	 * 			300081254
	 *			oswald.gm@gmail.com
	 */
	public class IsoCanvas extends JPanel{

		private static final long serialVersionUID = -1838809788973263253L;
		private TileMultiton.type[][] map = null;
		private Unit entities[];
		private int width;
		private int height;
		private int tile_width = 64;
		private int tile_height = 32;

		private int center_offset_x;
		private int center_offset_y;

		private ArrayList<Point> HIGHLIGHTED_TILES;


		/**
		 *
		 */
		public IsoCanvas(int Width, int Height){
			Tuple t = Data.testSet(null);
			map = t.tiles;
			entities = t.units;
//			mapDebug();

			this.width = Width;
			this.height = Height;
			calculateOffset();
//			conversionDebug();
		}
		/**
		 *
		 */
		public void paint(Graphics g){
			Graphics2D g2d = (Graphics2D) g;
			g.fillRect(0,0,width,height);
			Tile tile;
			for(int y = map.length-1;y >=0;y--){
				for(int x = 0;x<map[y].length;x++){
					Point p = toIso((x*(tile_width/2)),(y*(tile_height)));//
					tile = TileMultiton.getTile(map[y][x]);
					//top left vertex
					int x1 = (p.x);
					int y1 = (p.y);
					//bottom right vertex
					int x2 = (p.x+(tile_width));
					int y2 = (p.y+(tile_height));
					if(tile.getType().equals("Blue_Tile")){
						tile.draw(g2d, x1,y1-32);
					}
					else{
						tile.draw(g2d, x1,y1);
					}
				}
			}
			moveUnit(g2d, null, null);
		}
		/**
		 *
		 * @param updatedMap
		 */
		public void update(Tuple t){
			this.map = t.tiles;
			entities = t.units;
			this.repaint();
		}
		/**
		 *
		 */

		/**
		 *
		 * Returns the Cartesian representation of a isometric point
		 * @param x
		 * @param y
		 * @return
		 */
		public Point toCart(int x, int y){
			Point point = new Point();
			point.x =  0;
			point.y =  0;
			return point;
		}
		/**
		 *Returns the Isometric representation of a Cartesian point
		 * @param x
		 * @param y
		 * @return
		 */
		private Point toIso(int x, int y){
			Point point = new Point();
			point.x = ((x + y))+center_offset_x;
			point.y = ((x - y)/2)+center_offset_y;
			return point;
		}
		/**
		 * Calculates the offsets needed to draw the map centered on the canvas.
		 */
		private void calculateOffset(){
			center_offset_y = (int)((height/2) - (tile_height)*1.5)+tile_height;//spread this calculation out.
			center_offset_x = (int)((width/2) - ((tile_width)*map.length)/2)-tile_height;//this too.
		}
		/**
		 *
		 * @param unit
		 */
		public void moveUnit( Graphics2D g,Unit unit, Stack<Point> cordinates){
			for(Unit i: entities){
				i.draw(g,0,0,0,0,0,0,0,0);
			}
		}
		public void highlight(ArrayList<Point> tiles){
			this.HIGHLIGHTED_TILES = tiles;
		}
		public void initGameWorld(GameObject[][] obs, Units[][] units){

		}
		public void addObject(GameObject ob){

		}
		public void getMapLocation(int x, int i){

		}
		private void conversionDebug(){
			int x = 0;
			int y = 0;
			int numberOfTests = 5;
			while(y < numberOfTests){
				x = 0;
				while(x < numberOfTests){
					System.out.println(center_offset_x+":"+center_offset_y);
					Point iso = toIso(x,y);
					Point cart =  toCart(iso.x,iso.y);

					System.out.println("********************************************************");
					System.out.println("XY in cart:"+"("+x+","+y+")");
					System.out.println("");
					System.out.println("XY in iso:"+"("+iso.x+","+iso.y+")");
					System.out.println("XY converted back to cart:"+"("+cart.x+","+cart.y+")");
					System.out.println("");
					System.out.println("********************************************************");
				    x++;
				}
				y++;
			}
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
