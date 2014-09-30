package userInterface;

import gameRender.IsoCanvas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JPanel oldPanel;

	public GamePanel(JFrame frame, JPanel menuPanel) {
		currentFrame = frame;
		oldPanel = menuPanel;
		setLayout(null);

		JButton pl = new JButton("Test");
		pl.addActionListener(new TestListener());
		pl.setBounds(20, 20, 100, 40);
		add(pl);

		IsoCanvas canvas = new IsoCanvas(currentFrame.getWidth(),
				currentFrame.getHeight());
		canvas.setBounds(0, 0, currentFrame.getWidth(),
				currentFrame.getHeight());
		add(canvas);
	}

	class TestListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			currentFrame.getContentPane().removeAll();
			currentFrame.getContentPane().validate();
			currentFrame.getContentPane().repaint();

			currentFrame.getContentPane().add(oldPanel, BorderLayout.CENTER);

			currentFrame.getContentPane().validate();
			currentFrame.getContentPane().repaint();
		}

	}

}
