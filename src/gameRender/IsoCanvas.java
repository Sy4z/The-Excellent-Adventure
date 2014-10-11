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
import java.awt.image.BufferedImageFilter;
import java.awt.image.BufferedImageOp;
import java.util.ArrayDeque;
import java.util.ArrayList;

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
	private DrawMap render;
	private Unit entity;
	private int width;
	private int height;
	public int tile_width = 64;
	public int tile_height = 32;
	public int veiwport_x = 0;
	public int veiwport_y = 0;
	private String orientation;
	private ArrayList<Point> HIGHLIGHTED_TILES;
	private UnitCursor  cursor;
	private BufferedImage highLight;
	/**
	 *
	 */
	public IsoCanvas(int Width, int Height){
		cursor = new UnitCursor(new Point(3,3), 1234);
		Tuple t = Data.testSet(null);
		map = t.tiles;
		this.width = Width;
		this.height = Height;
		this.render = new DrawMapNorth(this.tile_width,this.tile_height,this.width,this.height,map.length);
	}
	/**
	 *
	 */
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(50,50,60));
		g2d.fillRect(0,0,width,height);
		render.draw(g2d, map, entity, cursor);
		//g2d.fillRect((width/2)-5,(height/2-5),10,10); //center of canvas.
		
	}
	/**
	 *
	 * @param updatedMap
	 */
	public void update(Tuple t){
		this.map = t.tiles;
		//entities = t.units;
		this.repaint();
	}

	
	/**
	 *
	 * @param unit
	 */

	public void moveUnit(UnitPlayer unit, ArrayDeque<Point> arrayDeque){
		int i = 0;
		this.entity = unit;
		while(!arrayDeque.isEmpty()){
			Point p = arrayDeque.pop();
			System.out.println("IsoCanvas.MoveUnit(), StackPoint "+"("+i+"):"+p.x+","+p.y);
			entity.upDateLocation(p);
			this.repaint();
			i++;
		}
	}
	public void moveCursor(UnitCursor cursor, int x, int y){
		this.cursor = cursor;
		this.repaint();
	}

	public void highlight(ArrayList<Point> tiles){
		this.HIGHLIGHTED_TILES = tiles;
	}

	public void initGameWorld(GameObject[][] obs, Units[][] units){

	}
	public void initEntity(Unit u){
		this.entity = u;
	}
	public void spawnObject(GameObject ob){

	}
	public void getMapLocation(int x, int i){

	}
	
	public void north(){
		this.render = new DrawMapNorth(this.tile_width,this.tile_height,this.width,this.height,map.length);
		this.repaint();
	}
	public void west(){
		this.render = new DrawMapWest(this.tile_width,this.tile_height,this.width,this.height,map.length);
		this.repaint();
	}
	
	public interface DrawMap {
		public int center_offset_x = 0;
		public int center_offset_y = 0;
		public int tile_width = 64;
		public int tile_height = 32;
		public void calculateOffset(int tile_width, int tile_height, int canvasWidth,int canvasHeight, int mapSize);
		public Point toIso(int x, int y);
		public void draw(Graphics2D g2d, TileMultiton.type[][] map, Unit entity, UnitCursor cursor);
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
