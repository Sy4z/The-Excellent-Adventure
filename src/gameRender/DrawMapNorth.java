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
	
	public DrawMapNorth(int tile_width, int tile_height, int canvasWidth,int canvasHeight, int mapSize){
		calculateOffset(tile_width,tile_height,canvasWidth, canvasHeight, mapSize);
	}
	
	@Override
	public void calculateOffset(int tile_width, int tile_height, int canvasWidth,int canvasHeight, int mapSize) {
		this.center_offset_y = (int)((canvasHeight/2) - (tile_height)*1.5)+tile_height;//-tile_height;//spread this calculation out.
		this.center_offset_x = (int)((canvasWidth/2) - ((tile_width)*mapSize)/2);//-tile_width;//this too.
		
	}

	public Point toIso(int x, int y){
		Point point = new Point();
		int dx = x*(tile_width/2);
		int dy = y*(tile_height);
		point.x = ((dx + dy))+center_offset_x;
		point.y = ((dx - dy)/2)+center_offset_y;
		return point;
	}


	@Override
	public void draw(Graphics2D g2d, TileMultiton.type[][] map, Unit entity, UnitCursor cursor) {
		System.out.println("DrawMapNorth.draw");
		Tile tile;
		Point tilePos;
		Point entityPos;
		Point cursorPos;
		int cx = 1;
		int cy = 0;
		
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
				//System.out.println("DrawMapNorth.draw current tile : " + tile );
				tile.draw(g2d, tX,tY);
				if(cx==x && cy==y){
					cursorPos = toIso(x,y);
					cursor.draw(g2d, cursorPos.x, cursorPos.y);
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

}
