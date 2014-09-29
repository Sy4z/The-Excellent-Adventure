package wasteLandEditor;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AMWEFrame extends JFrame implements MouseListener{
	public AMWEFrame(int width, int height){
		super("  AMWE - Quadrent 10's most brutal map editor");	
		System.out.println("Intitialising..");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, width, height);
		AMWEGraphics wasteSpace = new AMWEGraphics(this.getWidth(),this.getHeight());
		add(wasteSpace);
		pack();
		setResizable(true); 
		setVisible(true); 
		addMouseListener(this);
	}

	public void mouseClicked(MouseEvent e) {

	}
	public void mouseEntered(MouseEvent e) {

	}
	public void mouseExited(MouseEvent e) {

	}
	public void mousePressed(MouseEvent e) {

	}
	public void mouseReleased(MouseEvent e) {

	}
}
