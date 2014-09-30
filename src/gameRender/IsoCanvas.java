package gameRender;


import gameWorld.GameObject;
import gameWorld.Unit;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Stack;
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
	public class IsoCanvas extends Canvas{
		/**
		 *	Why does this need a java doc?
		 *	--DYLAN
		 */
		private static final long serialVersionUID = -1838809788973263253L;
		private TileMultiton.type[][] MAP = null;
		private Unit ENTITIES[]; //#Rebellion
		private int WIDTH;
		private int HEIGHT;
		private int TILE_WIDTH = 64;//26
		private int TILE_HEIGHT = 64;//52
		private int OFFSET_X;
		private int OFFSET_Y;
		private int HALF_TILE = TILE_HEIGHT/2;
		private ArrayList<Point> HIGHLIGHTED_TILES;
		private boolean debug = true;

		/**
		 *
		 */
		public IsoCanvas(int Width, int Height){
			Tuple t = Data.testSet(null);
			MAP = t.tiles;
			ENTITIES = t.units;
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
			for(int y = 0; y <MAP.length;y++){
				for(int x = 0; x< MAP[y].length;x++){
					Point p = toIso((x*(TILE_WIDTH/2)),(y*(TILE_HEIGHT/2)));//why tile size/2 hmm
					tile = TileMultiton.getTile(MAP[y][x]);
					//top left vertex
					int x1 = (p.x+OFFSET_X);
					int y1 = (p.y+OFFSET_Y);
					//bottom right vertex
					int x2 = (p.x+(TILE_WIDTH))+OFFSET_X;
					int y2 = (p.y+(TILE_HEIGHT))+OFFSET_Y;
					tile.draw(g2d, x1,y1 ,x2 ,y2, 0, 0, TILE_WIDTH,TILE_HEIGHT);
					}
			}

			moveUnit(g2d, null, null);
		}
		/**
		 *
		 * @param updatedMap
		 */
		public void update(Tuple t){
			this.MAP = t.tiles;
			ENTITIES = t.units;
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
			return new Point ((2 * x + y) / 2,(2 * x - y) / 2);
		}
		/**
		 *Returns the Isometric representation of a Cartesian point
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
			OFFSET_Y = (int)((HEIGHT/2) - ((HALF_TILE)*MAP.length)/2)-HALF_TILE;//this too.
		}


		/**
		 *
		 * @param unit
		 */
		public void moveUnit( Graphics2D g,Unit unit, Stack<Point> cordinates){
			for(Unit i: ENTITIES){
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
		/**
		 * Was Reading some of the Commander keen source code
		 * that was recently released and they did this, awesome idea
		 * (the wrapper not debug statments they have always been so).
		 * Nicely wrapped debug statements related to the tile map
		 *                   WORK IN PROGRESS
		 */
		private void mapDebug(){
			System.out.println("=================Map debug====================");
			if(MAP == null){
				System.out.println("Somethinghasgoneterriblywrong : Map is null");
			}
			else{
				System.out.println("Map is: "+MAP[0].length+" x "+MAP.length+" tiles");
				System.out.println("Map contains:");
				for(int y = 0; y <MAP.length;y++){
					for(int x = 0; x< MAP[y].length;x++){
						System.out.println("Pos "+x+":"+y+" -> "+TileMultiton.getTile(MAP[y][x]).getType());
					}
				}
			}
			System.out.println("==============================================");
		}
	}
