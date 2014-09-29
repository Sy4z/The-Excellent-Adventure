package wasteLandEditor;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * THE APOCOLYPSE MANIA WASTELAND EDITOR
 * 
 * @author 	Greg Oswald
 * 			300081254
 * 			oswald.gm@gmail.com
 */
public class AMWE {
	private WasteUnit[][] WASTELAND;
	private AMWEFrame WASTE_FRAME; 
	public AMWE(){
		System.out.println("Welcome to AMWE..");
		AMWEFrame wasteFrame = new AMWEFrame(800,600);
		WASTELAND = new WasteUnit[30][30];
	}
	
	public static void main(String[] args){
		AMWE amwe = new AMWE();
	}
	public WasteUnit[][] wasteLand(){
		return WASTELAND;
	}
	public void addUnit(int x, int y, WasteUnit toAdd){
		this.WASTELAND[y][x] = toAdd; 
	}
}
