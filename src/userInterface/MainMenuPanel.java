package userInterface;

import gameRender.IsoCanvas;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

/**
 * 
 * @author Venkata Peesapati
 * 
 */
public class MainMenuPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame currentFrame;
	JLabel gameName;
	JButton newGameButton;
	JButton loadButton;
	JButton storyButton;
	JButton controlsButton;
	JButton optionsButton;
	JButton exitButton;

	public MainMenuPanel(JFrame currentFrame) {
		this.currentFrame = currentFrame;
		setLayout(new GridLayout(7, 1));
		gameName = new JLabel("Robot Mania");
		gameName.setFont(gameName.getFont().deriveFont(40.0f));
		gameName.setHorizontalAlignment(JLabel.CENTER);

		newGameButton = new JButton("New Game");
		newGameButton.setOpaque(false);
		newGameButton.setContentAreaFilled(false);
		newGameButton.setBorderPainted(false);
		newGameButton.setFont(new Font("Arial", Font.PLAIN, 35));
		newGameButton.setFocusPainted(false);
		newGameButton.setForeground(Color.black);
		newGameButton.addMouseListener(new HoverButtonListener());
		newGameButton.addActionListener(new NewGameButtonListener());

		loadButton = new JButton("Load Game");
		loadButton.setOpaque(false);
		loadButton.setContentAreaFilled(false);
		loadButton.setBorderPainted(false);
		loadButton.setFont(new Font("Arial", Font.PLAIN, 35));
		loadButton.setFocusPainted(false);
		loadButton.setForeground(Color.black);
		loadButton.addMouseListener(new HoverButtonListener());

		storyButton = new JButton("Story");
		storyButton.setOpaque(false);
		storyButton.setContentAreaFilled(false);
		storyButton.setBorderPainted(false);
		storyButton.setFont(new Font("Arial", Font.PLAIN, 35));
		storyButton.setFocusPainted(false);
		storyButton.setForeground(Color.black);
		storyButton.addMouseListener(new HoverButtonListener());

		controlsButton = new JButton("Controls");
		controlsButton.setOpaque(false);
		controlsButton.setContentAreaFilled(false);
		controlsButton.setBorderPainted(false);
		controlsButton.setFont(new Font("Arial", Font.PLAIN, 35));
		controlsButton.setFocusPainted(false);
		controlsButton.setForeground(Color.black);
		controlsButton.addMouseListener(new HoverButtonListener());

		optionsButton = new JButton("Options");
		optionsButton.setOpaque(false);
		optionsButton.setContentAreaFilled(false);
		optionsButton.setBorderPainted(false);
		optionsButton.setFont(new Font("Arial", Font.PLAIN, 35));
		optionsButton.setFocusPainted(false);
		optionsButton.setForeground(Color.black);
		optionsButton.addMouseListener(new HoverButtonListener());

		exitButton = new JButton("Exit");
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		exitButton.setFont(new Font("Arial", Font.PLAIN, 35));
		exitButton.setFocusPainted(false);
		exitButton.setForeground(Color.black);
		exitButton.addMouseListener(new HoverButtonListener());
		exitButton.addActionListener(new ExitButtonListener());

		add(gameName);
		add(newGameButton);
		add(loadButton);
		add(storyButton);
		add(controlsButton);
		add(optionsButton);
		add(exitButton);

		// This helps make the panel transparent.
		setOpaque(false);
	}

	// Action listeners for each of the buttons can go down here.

	/**
	 * This is the listener class used for the newGameButton. It opens a new
	 * canvas.
	 * 
	 * @author Venkata Peesapati
	 * 
	 */
	class NewGameButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			currentFrame.getContentPane().removeAll();
			currentFrame.getContentPane().validate();
			currentFrame.getContentPane().repaint();
			currentFrame.getContentPane().add(new IsoCanvas(1150, 850),
					BorderLayout.CENTER);
			currentFrame.getContentPane().validate();
			currentFrame.getContentPane().repaint();
		}

	}

	/**
	 * This is the listener class used for the exitButton. It quits the program
	 * and closed the window.
	 * 
	 * @author Venkata Peesapati
	 * 
	 */
	class ExitButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	}

	/**
	 * This MouseListener class is used to change the color of the text when
	 * hovering over a button. It is used for all the buttons in the main menu.
	 * 
	 * @author Venkata Peesapati
	 * 
	 */
	class HoverButtonListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			((JButton) e.getSource()).setForeground(Color.red);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			((JButton) e.getSource()).setForeground(Color.black);
		}

	}

}
