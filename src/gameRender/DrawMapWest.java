package gameRender;

import gameWorld.Unit;
import gameWorld.UnitCursor;

import java.awt.Graphics2D;
import java.awt.Point;

import tile.Tile;
import tile.TileMultiton;

public class DrawMapWest implements IsoCanvas.DrawMap {
	private int center_offset_y;
	private int center_offset_x;
	public int tile_width = 64;
	public int tile_height = 32;
	/**
	 * 
	 * @param tile_width
	 * @param tile_height
	 * @param canvasWidth
	 * @param canvasHeight
	 * @param mapSize
	 */
	public DrawMapWest(int tile_width, int tile_height, int canvasWidth,int canvasHeight, int mapSize){
		calculateOffset(tile_width,tile_height,canvasWidth, canvasHeight, mapSize);
	}
	/**
	 * 
	 */
	@Override
	public void draw(Graphics2D g2d, TileMultiton.type[][] visibleTiles, Unit entity, UnitCursor cursor) {
		//System.out.println("DrawMapWest.draw");
		Tile tile;
		Point tilePos;
		Point entityPos;
		Point cursorPos;
		int cX;
		int cY;
		int tX;
		int tY;
		int eX;
		int eY;
		for(int y = 0;y <visibleTiles.length;y++){
			for(int x = 0;x<visibleTiles[y].length;x++){
				tilePos = toIso((x),(y));//
				tile = TileMultiton.getTile(visibleTiles[y][x]);
				tX = (tilePos.x);
				tY = (tilePos.y);
				//System.out.println("DrawMapWest.draw current tile : " + tile );
				tile.draw(g2d, tX,tY);
				if(cursor != null){
					cX = cursor.getLocation().x;
					cY = cursor.getLocation().y;
					if(cX == x && cY == y){
						cursorPos = toIso(x,y);
						cursor.draw(g2d, cursorPos.x, cursorPos.y);
					}
				}
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
	 */
	@Override
	public Point toIso(int x, int y) {
		Point isoPoint = new Point();
		int tileOffsetX = x*(this.tile_width/2);
		int tileOffsetY = y*(this.tile_height);
		isoPoint.x = ((tileOffsetY - tileOffsetX))+center_offset_x;
		isoPoint.y = ((tileOffsetY + tileOffsetX)/2)+center_offset_y;
		return isoPoint;
	}
	/**
	 * 
	 */
	@Override
	public void calculateOffset(int tile_width, int tile_height, int canvasWidth,int canvasHeight, int mapSize) {
		this.center_offset_x = (int)((canvasWidth/2) - (tile_width)*1.5)+tile_width;//spread this calculation out.
		this.center_offset_y = (int)((canvasHeight/2) - ((tile_height)*mapSize)/2);//+tile_height;//this too.
		
	}

}
