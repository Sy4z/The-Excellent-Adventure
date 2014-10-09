package gameRender;

import gameWorld.Unit;

import java.awt.Graphics2D;
import java.awt.Point;

import tile.Tile;
import tile.TileMultiton;

public class DrawMapWest implements IsoCanvas.DrawMap {
	private int center_offset_y;
	private int center_offset_x;
	public int tile_width = 64;
	public int tile_height = 32;
	
	public DrawMapWest(int tile_width, int tile_height, int canvasWidth,int canvasHeight, int mapSize){
		calculateOffset(tile_width,tile_height,canvasWidth, canvasHeight, mapSize);
	}
	
	@Override
	public void draw(Graphics2D g2d, TileMultiton.type[][] map, Unit entity) {
		System.out.println("DrawMapNorth.draw");
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
				System.out.println("DrawMapNorth.draw current tile : " + tile );
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
	@Override
	public Point toIso(int x, int y) {
		Point point = new Point();
		int dx = x*(this.tile_width/2);
		int dy = y*(this.tile_height);
		point.x = ((dx - dy))+center_offset_x;
		point.y = ((dx + dy)/2)+center_offset_y;
		return point;
	}
	@Override
	public void calculateOffset(int tile_width, int tile_height, int canvasWidth,int canvasHeight, int mapSize) {
		this.center_offset_x = (int)((canvasWidth/2) - (tile_width)*1.5)+tile_width;//spread this calculation out.
		this.center_offset_y = (int)((canvasHeight/2) - ((tile_height)*mapSize)/2);//+tile_height;//this too.
		
	}

}
