package wasteLandEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class AMWEGraphics extends JPanel{
	private int scale;
	private int grid_size;
	private int width;
	private int height;
	private int pos_x;
	private int pos_y;
	private int veiw_port_x;
	private int veiw_port_y;
	private Color highlight_col;
	private Rectangle highlight_Box;
	public AMWEGraphics(int initWidth,int initHeight){
		this.width = initWidth;
		this.height = initHeight;
		System.out.println("..Done.");
			
	}
	public void paint(Graphics g){
		
	
	
	}
	private void calculateGrid(){
		
	}
	public void setScale(int s) {
		scale = s;
	}
	public void setWidth(int w) {
		width = w;
	}
	public void setHeight(int h) {
		height = h;
	}
	public void setPosX(int posX) {
		this.pos_x = posX;
	}
	public void setPosY(int posY) {
		this.pos_y = posY;
	}


}
