package userInterface;

import gameRender.IsoCanvas;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class contains the main canvas displaying the gameplay and other
 * controls required for playing the game.
 * 
 * @author Venkata Peesapati
 * 
 */
public class GamePanel extends JPanel {
	
	private JFrame currentFrame;

	public GamePanel(JFrame frame) {
		currentFrame = frame;
		setLayout(null);
		
		JButton pl = new JButton("Test");
		pl.setBounds(20, 20, 100, 40);
		add(pl);
		
		IsoCanvas canvas = new IsoCanvas(currentFrame.getWidth(), currentFrame.getHeight());
		canvas.setBounds(0, 0, currentFrame.getWidth(), currentFrame.getHeight());
		add(canvas);
	}

}
