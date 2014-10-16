package userInterface;

import gameRender.IsoCanvas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.UnexpectedException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import dataStorage.Data;
import runGame.Main;

/**
 * This class displays the main menu and contains all the necessary buttons for
 * the main menu of the game.
 *
 * @author Venkata Peesapati
 *
 */
public class MainMenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame currentFrame; // Reference to the MainFrame.
	private JPanel currentPanel; // Reference to current panel being used.
	private String moveType; // This field is to indicate which controls are
								// going to be used in the game.

	private JLabel gameName;
	private JButton newGameButton;
	private JButton serverButton;
	private JButton loadButton;
	private JButton storyButton;
	private JButton controlsButton;
	private JButton exitButton;
	public GamePanel gamePanel;

	private ImageIcon background = new ImageIcon(this.getClass().getResource("/userInterface/post-apoc.jpg"));

	/**
	 * The main menu has 6 buttons: New Game, Load, Story, Controls and Exit.
	 *
	 * @param currentFrame
	 *            Takes the reference for the frame that the MainMenuPanel is
	 *            contained in so that its content pane can be changed when
	 *            required.
	 */
	public MainMenuPanel(JFrame currentFrame, JPanel currentPanel) {
		this.currentFrame = currentFrame;
		this.currentPanel = currentPanel;
		moveType = "arrows";
		setLayout(new GridLayout(7, 1));
		gameName = new JLabel("Apocalypse");
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

		// This button starts a new game and replaces the content pane with a
		// new canvas.
		serverButton = new JButton("Start Server");
		serverButton.setOpaque(false);
		serverButton.setContentAreaFilled(false);
		serverButton.setBorderPainted(false);
		serverButton.setFont(new Font("Arial", Font.PLAIN, 35));
		serverButton.setFocusPainted(false);
		serverButton.setForeground(Color.black);
		serverButton.addMouseListener(new HoverButtonListener());
		serverButton.addActionListener(new ServerButtonListener());

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
		storyButton.addActionListener(new StoryButtonListener());

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
		controlsButton.addActionListener(new ControlsButtonListener());

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
		add(serverButton);
		add(loadButton);
		add(storyButton);
		add(controlsButton);
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

			final JDialog d = new JDialog(currentFrame, "Server Options", true);
			d.setSize(400, 300);
			d.setLayout(new BorderLayout());

			JPanel serverOptions = new JPanel();
			serverOptions.setLayout(new GridLayout(3, 1));

			JLabel serverLabel = new JLabel("Server Options:");
			serverLabel.setFont(serverLabel.getFont().deriveFont(15.f));

			JRadioButton server = new JRadioButton("Connect To Server");
			server.setActionCommand("server");
			JRadioButton localGame = new JRadioButton("Local Game");
			localGame.setActionCommand("local");
			localGame.setSelected(true);
			final ButtonGroup serverButtons = new ButtonGroup();
			serverButtons.add(server);
			serverButtons.add(localGame);

			serverOptions.add(serverLabel);
			serverOptions.add(server);
			serverOptions.add(localGame);

			JPanel buttonsPanel = new JPanel();
			buttonsPanel.setLayout(new FlowLayout());
			JButton okButton = new JButton("OK");
			buttonsPanel.add(okButton);

			okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String option = serverButtons.getSelection()
							.getActionCommand();
					d.dispose();

					if (option.equals("server")) {
						Main.onlineMode = true;
						String input = JOptionPane
								.showInputDialog("Enter IP Address:");
						Main.setIP(input);
					}
					Main.runClientMain();
				}
			});

			d.add(serverOptions, BorderLayout.CENTER);
			d.add(buttonsPanel, BorderLayout.SOUTH);
			d.setLocationRelativeTo(null);
			d.setVisible(true);

			currentFrame.getContentPane().removeAll();
			currentFrame.getContentPane().validate();
			currentFrame.getContentPane().repaint();
			gamePanel = new GamePanel(currentFrame, currentPanel, moveType);
			currentFrame.getContentPane().add(gamePanel, BorderLayout.CENTER);

			currentFrame.getContentPane().validate();
			currentFrame.getContentPane().repaint();
		}

	}

	class ServerButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Image resizedImage = background.getImage().getScaledInstance(400,
					300, Image.SCALE_SMOOTH);
			background = new ImageIcon(resizedImage);

			JPanel mainPanel = new JPanel() {
				// Displays the background image on the panel.
				@Override
				public void paintComponent(Graphics g) {
					g.drawImage(background.getImage(), 0, 0, null);
					Main.server.runServer(Main.ipAddress, Main.port);
				}
			};

			final JDialog d = new JDialog(currentFrame, "Server Running", true);
			d.setSize(400, 300);
			d.setLayout(new BorderLayout());

			JLabel label = new JLabel("Running Server...");
			JButton exit = new JButton("Exit");

			exit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Main.server.stopServer();
					d.dispose();
				}
			});

			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(label, BorderLayout.CENTER);
			mainPanel.add(exit, BorderLayout.SOUTH);
			d.add(mainPanel, BorderLayout.CENTER);

			d.setLocationRelativeTo(null);
			d.setVisible(true);

			Main.runServerMain();
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
			Image resizedImage = background.getImage().getScaledInstance(400,
					300, Image.SCALE_SMOOTH);
			background = new ImageIcon(resizedImage);

			JPanel mainPanel = new JPanel() {
				// Displays the background image on the panel.
				@Override
				public void paintComponent(Graphics g) {
					g.drawImage(background.getImage(), 0, 0, null);
				}
			};

			String[] loadNames = Data.getLoadFiles();

			final JDialog d = new JDialog(currentFrame, "Load Game", true);
			d.setSize(400, 300);
			d.setLayout(new BorderLayout());

			final DefaultListModel<String> model = new DefaultListModel<String>(); // Add
			// saved
			// game
			// names
			// to
			// this
			// model.
			for (String name : loadNames) {
				model.addElement(name);
			}

			final JList<String> list = new JList<String>(model);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Makes
			// sure
			// that
			// the
			// user
			// can
			// only
			// select
			// one
			// game
			// to
			// load.
			final JScrollPane scrollPane = new JScrollPane(list);


			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout());
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String name = list.getSelectedValue();
					try {
						Data.load(name);
					} catch (UnexpectedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					d.dispose();

					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().validate();
					currentFrame.getContentPane().repaint();
					gamePanel = new GamePanel(currentFrame, currentPanel,
							moveType);
					currentFrame.getContentPane().add(gamePanel,
							BorderLayout.CENTER);

					currentFrame.getContentPane().validate();
					currentFrame.getContentPane().repaint();
				}
			});
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					d.dispose();
				}
			});
			JButton deleteButton = new JButton("Delete");
			deleteButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int index = list.getSelectedIndex();
					String n = list.getSelectedValue();
					System.out.println(n + " = " + index);

					boolean i = Data.deleteFile(n);
					System.out.println(i);

					String[] modifiedList = Data.getLoadFiles();

					model.removeAllElements();

					for (String name : modifiedList) {
						model.addElement(name);
					}

					list.setModel(model);
					scrollPane.revalidate();
					scrollPane.repaint();

				}
			});
			buttonPanel.add(okButton);
			buttonPanel.add(cancelButton);
			buttonPanel.add(deleteButton);

			scrollPane.setOpaque(false);
			buttonPanel.setOpaque(false);

			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(scrollPane, BorderLayout.CENTER);
			mainPanel.add(buttonPanel, BorderLayout.SOUTH);
			d.add(mainPanel, BorderLayout.CENTER);

			d.setLocationRelativeTo(null);
			d.setVisible(true);
		}
	}

	/**
	 * This is the listener class used for the story button. It opens a text
	 * area which describes the background story of the game.
	 *
	 * @author Venkata Peesapati
	 *
	 */
	class StoryButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Image resizedImage = background.getImage().getScaledInstance(400,
					300, Image.SCALE_SMOOTH);
			background = new ImageIcon(resizedImage);

			JPanel mainPanel = new JPanel() {
				// Displays the background image on the panel.
				@Override
				public void paintComponent(Graphics g) {
					g.drawImage(background.getImage(), 0, 0, null);
				}
			};

			final JDialog d = new JDialog(currentFrame, "Story", true);
			d.setSize(400, 300);
			d.setLayout(new BorderLayout());

			JTextArea story = new JTextArea("Once upon a time...");
			story.setEditable(false);
			JScrollPane scrollPane = new JScrollPane(story);
			scrollPane
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout());
			JButton closeButton = new JButton("Close");
			closeButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					d.dispose();
				}
			});
			buttonPanel.add(closeButton);

			scrollPane.setOpaque(false);
			buttonPanel.setOpaque(false);

			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(scrollPane, BorderLayout.CENTER);
			mainPanel.add(buttonPanel, BorderLayout.SOUTH);
			d.add(mainPanel, BorderLayout.CENTER);

			d.setLocationRelativeTo(null);
			d.setVisible(true);
		}

	}

	/**
	 * This is the listener class used for the controls button. It allows the
	 * user to choose between two keyboard options for movement in the game. One
	 * is using the alphabet keys(W,A,S,D) and the other is using the arrow
	 * keys.
	 *
	 * @author Venkata Peesapati
	 *
	 */
	class ControlsButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Image resizedImage = background.getImage().getScaledInstance(400,
					300, Image.SCALE_SMOOTH);
			background = new ImageIcon(resizedImage);

			JPanel mainPanel = new JPanel() {
				// Displays the background image on the panel.
				@Override
				public void paintComponent(Graphics g) {
					g.drawImage(background.getImage(), 0, 0, null);
				}
			};

			final JDialog d = new JDialog(currentFrame, "Controls", true);
			d.setSize(400, 300);
			d.setLayout(new BorderLayout());

			JPanel moveControls = new JPanel();
			moveControls.setLayout(new GridLayout(3, 1));

			JLabel moveLabel = new JLabel("Player Move Controls:");
			moveLabel.setFont(moveLabel.getFont().deriveFont(15.f));

			JRadioButton arrows = new JRadioButton(
					"Use arrows to move the player.");
			arrows.setActionCommand("arrows");
			JRadioButton letters = new JRadioButton(
					"Use alphabets(W,A,S,D) to move the player.");
			letters.setActionCommand("letters");
			arrows.setSelected(true);
			final ButtonGroup moveButtons = new ButtonGroup();
			moveButtons.add(arrows);
			moveButtons.add(letters);

			moveControls.add(moveLabel);
			moveControls.add(arrows);
			moveControls.add(letters);

			JPanel buttonsPanel = new JPanel();
			buttonsPanel.setLayout(new FlowLayout());
			JButton okButton = new JButton("OK");
			JButton cancelButton = new JButton("Cancel");
			buttonsPanel.add(okButton);
			buttonsPanel.add(cancelButton);

			okButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					moveType = moveButtons.getSelection().getActionCommand();
					d.dispose();
				}
			});

			cancelButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					d.dispose();
				}
			});

			moveControls.setOpaque(false);
			buttonsPanel.setOpaque(false);

			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(moveControls, BorderLayout.CENTER);
			mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
			d.add(mainPanel, BorderLayout.CENTER);

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
			Main.server.stopServer();
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
