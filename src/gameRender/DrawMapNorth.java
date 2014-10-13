package gameRender;

import gameWorld.Unit;
import gameWorld.UnitCursor;

import java.awt.Graphics2D;
import java.awt.Point;

import tile.Tile;
import tile.TileMultiton;

public class DrawMapNorth implements IsoCanvas.DrawMap{
	private int center_offset_y;
	private int center_offset_x;
	public int tile_width = 64;
	public int tile_height = 32;
	public int veiwport_origin_x = 5;
	public int veiwport_origin_y = 2;
	public int veiwport_size = 13;
	public DrawMapNorth(int tile_width, int tile_height, int canvasWidth,int canvasHeight, int mapSize){
		calculateOffset(tile_width,tile_height,canvasWidth, canvasHeight, mapSize);
	}

	@Override
	public void calculateOffset(int tile_width, int tile_height, int canvasWidth,int canvasHeight, int mapSize) {
		this.center_offset_y = (int)((canvasHeight/2) - (tile_height)*2);
		this.center_offset_x = (int)((canvasWidth/2) - ((tile_width)*veiwport_size)/2);//-tile_width;//this too.

	}

	public Point toIso(int x, int y){
		Point isoPoint = new Point();
		int tileOffsetX = x*(tile_width/2);
		int tileOffsetY = y*(tile_height);
		isoPoint.x = ((tileOffsetX + tileOffsetY))+center_offset_x;
		isoPoint.y = ((tileOffsetX - tileOffsetY)/2)+center_offset_y;
		return isoPoint;
	}


	@Override
	public void draw(Graphics2D g2d, TileMultiton.type[][] map, Unit entity, UnitCursor cursor) {
		//System.out.println("DrawMapNorth.draw");
		Tile tile;
		Point tilePos;
		Point entityPos;
		Point cursorPos;
		int tX;
		int tY;
		int eX;
		int eY;
		int cX;
		int cY;
		int mapX; 
		int mapY=veiwport_size-1; 
		for(int y = (veiwport_size+veiwport_origin_y)-1;y >=veiwport_origin_y;y--){
			mapX = 0;
			for(int x = veiwport_origin_x;x<veiwport_size+veiwport_origin_x;x++){
				tilePos = toIso(mapX,mapY);//
				tile = TileMultiton.getTile(map[y][x]);
				tX = (tilePos.x);
				tY = (tilePos.y);
				//System.out.println("DrawMapNorth.draw current tile : " +"("+ tX+":"+ tY+")");
				tile.draw(g2d, tX,tY);
				if(cursor != null){
					cX = cursor.getLocation().x;
					cY = cursor.getLocation().y;
					if(cX == mapX && cY == mapY){
						cursorPos = toIso(mapX,mapY);
						cursor.draw(g2d, cursorPos.x, cursorPos.y);
					}
				}
				if(entity != null){
					eX = entity.getLocation().x;
					eY = entity.getLocation().y;
					if(eX==mapX && eY==mapY){
						entityPos = toIso(mapX,mapY);
						entity.draw(g2d, entityPos.x, entityPos.y);
					}
				}
			mapX++;
			}
		mapY--;
		}

	}

}
