package gameRender;

import gameWorld.GameObject;
import gameWorld.Unit;
import gameWorld.UnitCursor;
import gameWorld.UnitPlayer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.ArrayList;
import javax.swing.JPanel;
import dataStorage.*;
import sun.management.counter.Units;
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
	private GameObject[][] objectMap = null;
	private DrawMap renderStratagy;
	private Unit entity;
	private int width;
	private int height;
	private int tile_width = 64;
	private int tile_height = 32;
	private int veiwport_x = 0;
	private int veiwport_y = 0;
	private int veiwport_size = 13;
	private ArrayList<Point> HIGHLIGHTED_TILES;
	private UnitCursor  cursor;
	private BufferedImage highLight;
	
	/**
	 * Initialise the IsoCanvas.
	 * @param Width Width of canvas
	 * @param Height height of canvas
	 */
	public IsoCanvas(int Width, int Height){
		cursor = null;
		highLight = null;
		HIGHLIGHTED_TILES = null;
		Tuple t = Data.testSet(null);
		map = t.tiles;
		this.width = Width;
		this.height = Height;
		objectMap = new GameObject[map.length][map[0].length];
		//System.out.println("width :" +width+"height :"+height);//canvas debug
		this.renderStratagy = new DrawMapNorth(this.tile_width,this.tile_height,this.width,this.height,veiwport_size);
	}


	/**
	 * Initialise the Isocanvas from a loaded map
	 * @param Width The width of the map
	 * @param Height The height of the map
	 * @param Tiles The array of tiles to draw the map from
	 */
	public IsoCanvas(int Width, int Height, TileMultiton.type[][] Tiles){
		cursor = null;
		highLight = null;
		HIGHLIGHTED_TILES = null;
		map = Tiles;
		this.width = Width;
		this.height = Height;
		//System.out.println("width :" +width+"height :"+height);//canvas debug
		this.renderStratagy = new DrawMapNorth(this.tile_width, this.tile_height, this.width, this.height, veiwport_size);
	}

	/**
	 *Starts the rendering for a frame, draws the
	 *background calls for the calculation of visible tiles and
	 *passes rendering data to the renderStratagy
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(50,50,60));
		g2d.fillRect(0,0,width,height);
		TileMultiton.type[][] visibleTiles = loadVisibleTiles();
		renderStratagy.draw(g2d, visibleTiles, entity, cursor);
		//g2d.fillRect((width/2)-5,(height/2-5),10,10); //debug center of canvas.
		//g2d.drawRect(0,0,width,height);// debug draw rectangle around canvas
	}
	/**
	 * Takes the origin x and y coordinates and size of the area 
	 * to be drawn to screen(veiwport) and loads the elements
	 * of the map array within those bounds into a new array.
	 * @return the subset of the map that is visible to be passed to the render
	 * strategy.
	 */
	private TileMultiton.type[][] loadVisibleTiles(){
		TileMultiton.type[][] visableTiles = new TileMultiton.type[veiwport_size][veiwport_size];
		int mapX;
		int mapY=0;
		for(int y = veiwport_y;y<(veiwport_size+veiwport_y);y++){
			mapX = 0;
			for(int x = veiwport_y;x<veiwport_size+veiwport_x;x++){
				visableTiles[mapY][mapX] = map[y][x];
			mapX++;
			}
			mapY++;
		}
		return visableTiles;
	}
	/**
	 * Takes a unit and a queue of points,
	 * updates the location of the unit and
	 * repaints canvas.
	 * @param unit unit to be moved.
	 * @param arrayDeque path unit shall be moved along
	 */
	public void moveUnit(UnitPlayer unit, ArrayDeque<Point> arrayDeque){
		int i = 0;
		this.entity = unit;
		while(!arrayDeque.isEmpty()){
			Point p = arrayDeque.pop();
			entity.upDateLocation(p);
			this.repaint();
			i++;
		}
	}
	/**
	 * Takes a cursor and draws it on the canvas
	 * using its internal location.
	 * repaints canvas.
	 * @param cursor
	 */
	public void moveCursor(UnitCursor cursor){
		this.cursor = cursor;
		this.repaint();
	}
	/**
	 * Takes an ArrayList of points.
	 * highlights these tiles by changing their
	 * color.
	 * @param tiles
	 */
	public void highlight(ArrayList<Point> tiles){
		this.HIGHLIGHTED_TILES = tiles;
		repaint();
	}

	public void updateGameBoardGraphics(ArrayList<GameObject> gObs) {
		

	}
	/**
	 * 
	 * @param u
	 */
	public void initEntity(Unit u){
		this.entity = u;
	}
	/**
	 * 
	 * @param ob
	 */
	public void spawnObject(GameObject ob){

	}
	/**
	 * If there is a closed door at x,y in the map
	 * then it is changed to an opendoor.
	 * @param x x coordinite of door to be opened
	 * @param y y coordinite of door to be opened
	 */
	public void openDoor(int x, int y){
		if(map[x][y].equals(TileMultiton.type.CLOSEDOOR)){
			this.map[x][y] = TileMultiton.type.OPENDOOR;
		}
	}
	/**
	 * If there is an open door at x,y in the map
	 * then it is changed to an closeddoor.
	 * @param x x coordinite of door to be closed
	 * @param y y coordinite of door to be closed
	 */
	public void closeDoor(int x, int y){
		if(map[x][y].equals(TileMultiton.type.OPENDOOR)){
			this.map[x][y] = TileMultiton.type.CLOSEDOOR;
		}
	}
	/**
	 * Switches renderStratagy to DrawMapNorth,
	 * this will draw the top right edge of the isometric diamond
	 * as north.
	 */
	public void north(){
		this.renderStratagy = new DrawMapNorth(this.tile_width,this.tile_height,this.width,this.height,veiwport_size);
		this.repaint();
	}
	/**
	 * Switches renderStratagy to DrawMapWest,
	 * this will draw the top right edge of the isometric diamond
	 * as West.
	 */
	public void west(){
		this.renderStratagy = new DrawMapWest(this.tile_width,this.tile_height,this.width,this.height,veiwport_size);
		this.repaint();
	}
	/**
	 * Switches renderStratagy to DrawMapEast,
	 * this will draw the top right edge of the isometric diamond
	 * as East.
	 */
	public void east(){
		this.renderStratagy = new DrawMapEast(this.tile_width,this.tile_height,this.width,this.height,veiwport_size);
		this.repaint();
	}
	/**
	 * Switches renderStratagy to DrawMapSouth,
	 * this will draw the top right edge of the isometric diamond
	 * as South.
	 */
	public void south(){
		this.renderStratagy = new DrawMapSouth(this.tile_width,this.tile_height,this.width,this.height,veiwport_size);
		this.repaint();
	}
	/**
	 * RenderStrategy InterFace
	 * This is were the calculations for converting a 2d array to 
	 * isometric cordinates occures and the rendering to screen.
	 * @author gmos
	 *
	 */
	public interface DrawMap {
		public int center_offset_x = 0;
		public int center_offset_y = 0;
		public int tile_width = 64;
		public int tile_height = 32;
		/**
		 * Calculates the offset needed to draw the map in the
		 * center of the canvas.
		 * @param tile_width width of standard floor tile
		 * @param tile_height height of standard floor tile
		 * @param canvasWidth width of canvas
		 * @param canvasHeight height of canvas
		 * @param mapSize size of area to be rendered.
		 */
		public void calculateOffset(int tile_width, int tile_height, int canvasWidth,int canvasHeight, int mapSize);
		/**
		 * Converts a coordinate in cartesian space
		 * into a coordinate in isometric space
		 * @param x cartesian x coordinate to me converted
		 * @param y cartesian x coordinate to me converted
		 * @return Point containing isometric conversion of x and y.
		 */
		public Point toIso(int x, int y);
		/**
		 * Main rendering loop.
		 * @param g2d graphics context.
		 * @param map 2d array of tiles to be drawn.
		 * @param entity player charachter to be drawn.
		 * @param cursor player cursor to be drawn.
		 */
		public void draw(Graphics2D g2d, TileMultiton.type[][] map, Unit entity, UnitCursor cursor);
	}
	/**
	 * 
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
