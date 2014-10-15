package gameRender;

import gameWorld.GameObject;
import gameWorld.Unit;
import gameWorld.UnitCursor;

import java.awt.Graphics2D;
import java.awt.Point;

import tile.Tile;
import tile.TileMultiton;

public class DrawMapSouth implements IsoCanvas.DrawMap{
	private int center_offset_y;
	private int center_offset_x;
	public int tile_width = 64;
	public int tile_height = 32;
	/**
	 * Contructor for DrawMapSouth
	 * @param tile_width width of standard floorTile
	 * @param tile_height height of standard floorTile
	 * @param canvasWidth width of canvas
	 * @param canvasHeight height of canvas
	 * @param mapSize Size of area to be rendered.
	 */
	public DrawMapSouth(int tile_width, int tile_height, int canvasWidth,int canvasHeight, int mapSize){
		calculateOffset(tile_width,tile_height,canvasWidth, canvasHeight, mapSize);
	}
	/**
	 * Calculates offset required to draw map in the centre of the canvas.
	 */
	@Override
	public void calculateOffset(int tile_width, int tile_height, int canvasWidth,int canvasHeight, int mapSize) {
		this.center_offset_y = (int)((canvasHeight/2) - (tile_height)*1.5)+tile_height;//spread this calculation out.
		this.center_offset_x = (int)((canvasWidth/2) - ((tile_width)*mapSize)/2);//this too.
	}
	/**
	 *Flips a 2d array such that the 
	 *y is reversed and so is the x.
	 * @param map array to be fliped
	 * @return new array of with x and y reversed.
	 */
	private TileMultiton.type[][] flipArray(TileMultiton.type[][] map){
		TileMultiton.type[][] fliped = new TileMultiton.type[map.length][map[0].length];
		int newX;
		int newY =0;
		for(int y = map.length-1;y>=0 ;y--){
			newX = 0;
			for(int x = map.length-1;x >=0;x--){
				fliped[newY][newX] = map[y][x];
				//System.out.println(newY+":"+newX+"<--"+y+":"+x);
				newX++;
			}
			newY++;	
		}
		return fliped;
	}
	
	/**
	 * Converts a coordinate in cartesian space
	 * into a coordinate in isometric space such that
	 * coordinate (0,0) is at the left corner of 
	 * the isometric diamond.
	 */
	public Point toIso(int x, int y){
		Point isoPoint = new Point();
		int tileOffsetX = x*(tile_width/2);
		int tileOffsetY = y*(tile_height);
		isoPoint.x = ((tileOffsetX + tileOffsetY))+center_offset_x;
		isoPoint.y = ((tileOffsetX - tileOffsetY)/2)+center_offset_y;
		return isoPoint;
	}
	/**
	 *Render loop for drawing map South.
	 *Loops through a 2d array of visibleTiles converts x and y array coordinates to isometric
	 *and draws each tile, if the cursor also ocupies the current x and y it is drawn, then if 
	 *the entity ocupies the current x and y it is drawn.
	 *To render the map north correctly the x for loop reads forward and the y loop  reads 
	 *backwards, but the visibleTiles array is fliped before reading occurs.
	 */
	@Override
	public void draw(Graphics2D g2d, TileMultiton.type[][] visibleTiles,GameObject[][] visibleObjects, Unit entity, UnitCursor cursor) {
		TileMultiton.type[][] map = flipArray(visibleTiles);
		Tile tile;
		Point tilePos;
		Point entityPos;
		Point cursorPos;
		int tileX;
		int tileY;
		int entityX;
		int entityY;
		int cursorX;
		int cursorY;
		for(int y = map.length-1;y >=0;y--){
			for(int x = 0;x<map[y].length;x++){
				tilePos = toIso(x,y);//
				tile = TileMultiton.getTile(map[y][x]);
				tileX = (tilePos.x);
				tileY = (tilePos.y);
				tile.draw(g2d, tileX,tileY);
				
				if(cursor != null){
					cursorX = cursor.getLocation().x;
					cursorY = cursor.getLocation().y;
					if(cursorX == x && cursorY == y){
					cursorPos = toIso(x,y);
					cursor.draw(g2d, cursorPos.x, cursorPos.y);
				}
				}
				if(visibleObjects[y][x]!= null){
					Point objPoint = toIso(x,y);
					visibleObjects[y][x].draw(g2d,objPoint.x,objPoint.y);
				}
				if(entity != null){
					entityX = entity.getLocation().x;
					entityY = entity.getLocation().y;
					if(entityX==x && entityY==y){
						entityPos = toIso(entityX,entityY);
						entity.draw(g2d, entityPos.x, entityPos.y);
					}
				}
			}
		}
		
	}

}
