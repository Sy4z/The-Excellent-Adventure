package userInterface;

import gameRender.IsoCanvas;
import gameWorld.World;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
public class GamePanel extends JPanel implements MouseListener {

	private JFrame currentFrame;
	private JPanel oldPanel;
	private World world; // The game world.
	private IsoCanvas canvas;

	public GamePanel(JFrame frame, JPanel menuPanel) {
		addMouseListener(this);
		currentFrame = frame;
		oldPanel = menuPanel;
		setLayout(null);

		JButton pl = new JButton("Test");
		pl.addActionListener(new TestListener());
		pl.setBounds(20, 20, 100, 40);
		add(pl);
		//Create a world then get the canvas off it
		canvas = new IsoCanvas(currentFrame.getWidth(),
				currentFrame.getHeight());
		world = new World("", currentFrame.getWidth(), currentFrame.getHeight(), canvas);

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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println(e.getX() + " " + e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// TODO keyboard listener.

}
