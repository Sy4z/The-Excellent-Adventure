package userInterface;

import gameRender.IsoCanvas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * This class contains all the necessary buttons for the main menu of the game.
 * 
 * @author Venkata Peesapati
 * 
 */
public class MainMenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JFrame currentFrame;
	JLabel gameName;
	JButton newGameButton;
	JButton loadButton;
	JButton storyButton;
	JButton controlsButton;
	JButton optionsButton;
	JButton exitButton;

	/**
	 * The main menu has 6 buttons: New Game, Load, Story, Controls, Options and
	 * Exit.
	 * 
	 * @param currentFrame
	 *            Takes the reference for the frame that the MainMenuPanel is
	 *            contained in so that its content pane can be changed when
	 *            required.
	 */
	public MainMenuPanel(JFrame currentFrame) {
		this.currentFrame = currentFrame;
		setLayout(new GridLayout(7, 1));
		gameName = new JLabel("Robot Mania");
		gameName.setFont(gameName.getFont().deriveFont(40.0f));
		gameName.setHorizontalAlignment(JLabel.CENTER);

		// This button starts a new game and replaces the content pane with a
		// new canvas.
		newGameButton = new JButton("New Game");
		newGameButton.setOpaque(false);
		newGameButton.setContentAreaFilled(false);
		newGameButton.setBorderPainted(false);
		newGameButton.setFont(new Font("Arial", Font.PLAIN, 35));
		newGameButton.setFocusPainted(false);
		newGameButton.setForeground(Color.black);
		newGameButton.addMouseListener(new HoverButtonListener());
		newGameButton.addActionListener(new NewGameButtonListener());

		// The load button allows the user to load a game they have saved
		// before.
		loadButton = new JButton("Load Game");
		loadButton.setOpaque(false);
		loadButton.setContentAreaFilled(false);
		loadButton.setBorderPainted(false);
		loadButton.setFont(new Font("Arial", Font.PLAIN, 35));
		loadButton.setFocusPainted(false);
		loadButton.setForeground(Color.black);
		loadButton.addMouseListener(new HoverButtonListener());
		loadButton.addActionListener(new LoadButtonListener());

		// Opens a window describing the background story of the game.
		storyButton = new JButton("Story");
		storyButton.setOpaque(false);
		storyButton.setContentAreaFilled(false);
		storyButton.setBorderPainted(false);
		storyButton.setFont(new Font("Arial", Font.PLAIN, 35));
		storyButton.setFocusPainted(false);
		storyButton.setForeground(Color.black);
		storyButton.addMouseListener(new HoverButtonListener());

		// The controls for the mouse and keyboard can be changed by pressing
		// this button.
		controlsButton = new JButton("Controls");
		controlsButton.setOpaque(false);
		controlsButton.setContentAreaFilled(false);
		controlsButton.setBorderPainted(false);
		controlsButton.setFont(new Font("Arial", Font.PLAIN, 35));
		controlsButton.setFocusPainted(false);
		controlsButton.setForeground(Color.black);
		controlsButton.addMouseListener(new HoverButtonListener());

		// Set game options.
		optionsButton = new JButton("Options");
		optionsButton.setOpaque(false);
		optionsButton.setContentAreaFilled(false);
		optionsButton.setBorderPainted(false);
		optionsButton.setFont(new Font("Arial", Font.PLAIN, 35));
		optionsButton.setFocusPainted(false);
		optionsButton.setForeground(Color.black);
		optionsButton.addMouseListener(new HoverButtonListener());

		// Quit the game.
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
			currentFrame.getContentPane().add(
					new IsoCanvas(currentFrame.getWidth(),
							currentFrame.getHeight()), BorderLayout.CENTER); // isocanvas
																				// was
																				// changed?
																				// I
																				// added
																				// some
																				// arbitrary
																				// width
																				// and
																				// height
																				// values

			currentFrame.getContentPane().validate();
			currentFrame.getContentPane().repaint();
		}

	}

	/**
	 * This is the listener class used for the loadButton. It opens a list of
	 * the games saved by the user and allows them to select one.
	 * 
	 * @author Venkata Peesapati
	 * 
	 */
	class LoadButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			final JDialog d = new JDialog(currentFrame, "Load Game", true);
			d.setSize(400, 300);
			d.setLayout(new BorderLayout());
			
			DefaultListModel<String> model = new DefaultListModel<String>(); 
			JList<String> list = new JList<String>(model);
			JScrollPane scrollPane = new JScrollPane(list);
			d.add(scrollPane, BorderLayout.CENTER);
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout());
			buttonPanel.add(new JButton("OK"));
			buttonPanel.add(new JButton("Cancel"));
			buttonPanel.add(new JButton("Delete"));
			d.add(buttonPanel, BorderLayout.SOUTH);
			
			d.setLocationRelativeTo(null);
			d.setVisible(true);
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
