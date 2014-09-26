package userInterface;

import gameRender.IsoCanvas;

import java.awt.Color;

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
		
		IsoCanvas canvas = new IsoCanvas(currentFrame.getWidth(), currentFrame.getHeight());
		canvas.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
		add(canvas);
	}

}
