package userInterface;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * This class represents the main window of the game.
 * 
 * @author Venkata Peesapati
 * 
 */
public class MainFrame extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	private ImageIcon background = new ImageIcon("post-apoc.jpg");
	private static ImageIcon loadBackground = new ImageIcon("robot.png");

	public MainFrame() {
		super.addWindowListener(this);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);// Potentially
																			// change
																			// this
																			// to
																			// JFrame.EXIT_ON_CLOSE
		super.setLayout(new BorderLayout());

		JPanel backgroundPanel = createMainPanel();
		MainMenuPanel mainPanel = new MainMenuPanel(this, backgroundPanel);
		backgroundPanel.setLayout(null);
		mainPanel.setBounds(100, 100, 300, 350); // Position the main panel at
													// (100,100) with width 300
													// and height 350.
		backgroundPanel.add(mainPanel);

		super.getContentPane().add(backgroundPanel, BorderLayout.CENTER);
		super.setSize(background.getIconWidth(), background.getIconHeight());
		super.setLocationRelativeTo(null); // Center the frame.
		super.setResizable(false);
		super.setVisible(true);
	}

	/**
	 * This method creates the main panel for the frame which displays the main
	 * menu of the game.
	 * 
	 * @return the mainPanel for the game.
	 */
	private JPanel createMainPanel() {
		// Resize the image of the crashed spaceship for display purposes.
		Image resizedImage = background.getImage().getScaledInstance(1150, 850,
				Image.SCALE_SMOOTH);
		background = new ImageIcon(resizedImage);

		JPanel panel = new JPanel() {
			// Displays the background image on the panel.
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(background.getImage(), 0, 0, null);
			}
		};

		return panel;
	}

	/**
	 * This method is used to display a loading window just before the game
	 * starts up. Image from http://grabcad.com/library/crazy-robot.
	 * 
	 * @return the loading frame of the game.
	 */
	private static JFrame createLoadingFrame() {
		JFrame frame = new JFrame();
		Image resizedImage = loadBackground.getImage().getScaledInstance(500,
				300, Image.SCALE_SMOOTH);
		loadBackground = new ImageIcon(resizedImage);
		JPanel panel = new JPanel() {
			// Displays the background image on the panel.
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(loadBackground.getImage(), 0, 0, null);
			}
		};
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		frame.setUndecorated(true);
		frame.setSize(500, 300);
		frame.setLocationRelativeTo(null);
		return frame;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		//
	}

	@Override
	public void windowClosing(WindowEvent e) {
		int confirmed = JOptionPane
				.showConfirmDialog(null, "Are you sure you want to quit?", "",
						JOptionPane.YES_NO_OPTION);
		if (confirmed == JOptionPane.YES_OPTION) {
			dispose();
			System.exit(0);
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	public static void main(String[] args) {
		JFrame loadFrame = createLoadingFrame();
		loadFrame.setVisible(true);

		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		loadFrame.dispose();

		MainFrame mainFrame = new MainFrame();
	}
}
