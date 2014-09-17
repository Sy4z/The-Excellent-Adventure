package gameRender;

	import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
	/**
	 * Testing Canvas, for isometric tiles.
	 * 
	 * @author gmos
	 *
	 */
	public class IsoCanvas extends JPanel{
		private int[][] tiles  = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
								  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
								  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
								  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
								  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
								  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
								  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
								  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
								  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
								  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
								  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
								  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
								  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,},
								  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,}};	
		private int WIDTH = 800;
		private int HEIGHT = 600;
		private int TILE_WIDTH = 52;
		private int TILE_HEIGHT = 52;
		private int OFFSET_X = (TILE_WIDTH/2)*(tiles.length);
		private int OFFSET_Y = 0;// HEIGHT/2;
		
		public void paint(Graphics g){
			Image testTile = null;
			try {
				testTile = ImageIO.read(new File("src/gameRender/tile.PNG"));
			} catch (IOException e) {
				System.out.println("unable to Load image");
				e.printStackTrace();
				System.exit(-1);
			}
			
			for(int y = 0; y <tiles.length;y++){
				for(int x = 0; x< tiles[y].length;x++){
					Point p = toIso((x*(TILE_WIDTH/2)),(y*(TILE_WIDTH/2)));
					g.drawImage(testTile,p.x+OFFSET_X ,p.y+OFFSET_Y ,null);
					
				}
			}
		
		}
		
		
		public Dimension getPreferredSize(){
			return new Dimension(WIDTH,HEIGHT);
		}
		public Point toCart(int x, int y){
			return new Point ((2 * x + y) / 2,(2 * x - y) / 2);
		}
		private Point toIso(int x, int y){
			return new Point (x - y,(x + y) / 2);
		}
	}
