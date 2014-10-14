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
	public void calculateOffset(int tile_width, int tile_height, int canvasWidth,int canvasHeight, int veiwportSize) {
		this.center_offset_y = (int)((canvasHeight/2) - (tile_height)*1.5)+tile_height;
		this.center_offset_x = (int)((canvasWidth/2) - ((tile_width)*veiwportSize)/2);//-tile_width;//this too.

	}

	public Point toIso(int x, int y){
		Point isoPoint = new Point();
		int tileOffsetX = x*(tile_width/2);
		int tileOffsetY = y*(tile_height);
		isoPoint.x = ((tileOffsetX + tileOffsetY))+center_offset_x;
		isoPoint.y = ((tileOffsetX - tileOffsetY)/2)+center_offset_y;
		return isoPoint;
	}

	private TileMultiton.type[][] flipArray(TileMultiton.type[][] map){
		TileMultiton.type[][] fliped = new TileMultiton.type[map.length][map[0].length];
		int newX;
		int newY =0;
		for(int y = map.length-1;y>=0 ;y--){
			newX = 0;
			for(int x = map.length-1;x >=0;x--){
				fliped[newY][newX] = map[y][x];
				System.out.println(newY+":"+newX+"<--"+y+":"+x);
				newX++;
			}
			newY++;	
		}
		return fliped;
	}
	
	@Override
	public void draw(Graphics2D g2d, TileMultiton.type[][] visibleTiles, Unit entity, UnitCursor cursor) {
		//System.out.println("DrawMapNorth.draw");
		TileMultiton.type[][] map = visibleTiles;
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
		for(int y = map.length-1;y>=0;y--){
			for(int x = 0;x<map[y].length;x++){
				tilePos = toIso(x,y);//
				tile = TileMultiton.getTile(map[y][x]);
				tX = (tilePos.x);
				tY = (tilePos.y);
				//System.out.println("DrawMapNorth.draw current tile : " +"("+ tX+":"+ tY+")");
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
						entityPos = toIso(x,y);
						entity.draw(g2d, entityPos.x, entityPos.y);
					}
				}
			
			}
		
		}

	}

}
