package userInterface;

import gameRender.IsoCanvas;
import gameWorld.World;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
public class GamePanel extends JPanel implements MouseListener, KeyListener {

	private JFrame currentFrame;
	private JPanel oldPanel;
	private World world; // The game world.
	private IsoCanvas canvas;
	private String moveType;

	public GamePanel(JFrame frame, JPanel menuPanel, String moveType) {
		addMouseListener(this);
		currentFrame = frame;
		oldPanel = menuPanel;
		this.moveType = moveType;
		setLayout(null);

		// This code is used to set up the quit button to quit during game play.
		JButton quit = new JButton("Quit");
		quit.setOpaque(false);
		quit.setContentAreaFilled(false);
		quit.setBorderPainted(false);
		quit.setFont(new Font("Arial", Font.PLAIN, 35));
		quit.setFocusPainted(false);
		quit.setForeground(Color.green);

		quit.addActionListener(new QuitGameListener());
		quit.setBounds(1000, 20, 100, 40); // Sets the position of the quit
											// button on the canvas.
		add(quit);

		// Creates a canvas and a world to put the canvas into the world.
		canvas = new IsoCanvas(currentFrame.getWidth(),
				currentFrame.getHeight());
		world = new World("", currentFrame.getWidth(),
				currentFrame.getHeight(), canvas);

		canvas.setBounds(0, 0, currentFrame.getWidth(),
				currentFrame.getHeight());
		add(canvas);
	}

	/**
	 * This is the listener class used for the quit button. It returns to the
	 * main menu by replacing the game panel with the main menu's panel.
	 * 
	 * @author Venkata Peesapati
	 * 
	 */
	class QuitGameListener implements ActionListener {

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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP) {
			world.moveFromKeyBoard(0);
		} else if (key == KeyEvent.VK_DOWN) {
			world.moveFromKeyBoard(1);
		} else if (key == KeyEvent.VK_LEFT) {
			world.moveFromKeyBoard(2);
		} else if (key == KeyEvent.VK_RIGHT) {
			world.moveFromKeyBoard(3);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
