package gameRender;


import gameWorld.GameObject;
import gameWorld.Unit;

import java.awt.Color;
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
	private Unit entity;
	private int width;
	private int height;
	private int tile_width = 64;
	private int tile_height = 32;
	private int center_offset_x;
	private int center_offset_y;
	private int veiwport_x;
	private int veiwport_y;
	private ArrayList<Point> HIGHLIGHTED_TILES;


	/**
	 *
	 */
	public IsoCanvas(int Width, int Height){
		Tuple t = Data.testSet(null);
		map = t.tiles;
		//entities = t.units;
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
		g.setColor(new Color(50,50,60));
		g.fillRect(0,0,width,height);
		Tile tile;
		Point tilePos;
		Point entityPos;
		int tX;
		int tY;
		int eX;
		int eY;
		for(int y = map.length-1;y >=0;y--){
			for(int x = 0;x<map[y].length;x++){
				tilePos = toIso((x),(y));//
				tile = TileMultiton.getTile(map[y][x]);
				//top left vertex
				tX = (tilePos.x);
				tY = (tilePos.y);
				tile.draw(g2d, tX,tY);
				if(entity != null){
					eX = entity.getLocation().x;
					eY = entity.getLocation().y;
					if(eX==x && eY==y){
						entityPos = toIso(eX,eY);
						entity.draw(g2d, entityPos.x, entityPos.y);
					}
				}
			}
		}
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
	 */

	/**
	 *
	 * Returns the Cartesian representation of a isometric point
	 * THIS DOSE NOT WORK YET
	 * @param x
	 * @param y
	 * @return
	 */
	private Point toCart(int x, int y){
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
		int dx = x*(tile_width/2);
		int dy = y*(tile_height);
		point.x = ((dx + dy))+center_offset_x;
		point.y = ((dx - dy)/2)+center_offset_y;
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
	public void moveUnit(Unit unit, Stack<Point> cordinates){
		int i = 0;
		this.entity = unit;
		while(!cordinates.empty()){
			Point p = cordinates.pop();
			System.out.println("IsoCanvas.MoveUnit(), StackPoint "+"("+i+"):"+p.x+","+p.y);
			entity.upDateLocation(p);
			this.repaint();
			i++;
		}
	}
	public void highlight(ArrayList<Point> tiles){
		this.HIGHLIGHTED_TILES = tiles;
	}
	public void initGameWorld(GameObject[][] obs, Units[][] units){

	}
	public void initEntity(Unit u){
		this.entity = u;
	}
	public void addObject(GameObject ob){

	}
	public void getMapLocation(int x, int i){

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
