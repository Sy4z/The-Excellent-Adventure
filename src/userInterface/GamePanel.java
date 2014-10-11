package userInterface;

import gameRender.IsoCanvas;
import gameWorld.World;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

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
	private String moveType;

	public GamePanel(JFrame frame, JPanel menuPanel, String moveType) {
		currentFrame = frame;
		oldPanel = menuPanel;
		this.moveType = moveType;
		addMouseListener(this);
		addKeyBindings();
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

		JButton controls = new JButton("Controls");
		controls.setOpaque(false);
		controls.setContentAreaFilled(false);
		controls.setBorderPainted(false);
		controls.setFont(new Font("Arial", Font.PLAIN, 35));
		controls.setFocusPainted(false);
		controls.setForeground(Color.green);

		controls.setBounds(965, 60, 170, 40);
		controls.addActionListener(new ControlsGameListener());
		add(controls);

		ImageIcon horn = new ImageIcon("horn.jpg");
		Image hornRe = horn.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		horn = new ImageIcon(hornRe);
		ImageIcon sword = new ImageIcon("sword.png");
		ImageIcon shuriken = new ImageIcon("shuriken.jpg");
		ImageIcon trident = new ImageIcon("trident.jpg");

		String[] columnNames = { "C1", "C2" };
		Object[][] data = {{horn, sword}, {sword, shuriken}};

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JTable table = new JTable(model){
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };

        table.setOpaque(false);
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
        	{
        		setOpaque(false);
        	}
        });

        table.setCellSelectionEnabled(true);
		table.setRowHeight(0, 100);
		table.setBounds(10, 550, 450, 1000);
		add(table);

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
			moveType = "arrows";
			currentFrame.getContentPane().removeAll();
			currentFrame.getContentPane().validate();
			currentFrame.getContentPane().repaint();

			currentFrame.getContentPane().add(oldPanel, BorderLayout.CENTER);

			currentFrame.getContentPane().validate();
			currentFrame.getContentPane().repaint();
		}

	}

	/**
	 * This is the listener class used for the controls button. It allows the
	 * user to change the keyboard controls during the gameplay.
	 *
	 * @author Venkata Peesapati
	 *
	 */
	class ControlsGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
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
					addKeyBindings(); // Resetting key bindings to match new
										// control settings.
					d.dispose();
				}
			});

			cancelButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					d.dispose();
				}
			});

			d.add(moveControls, BorderLayout.CENTER);
			d.add(buttonsPanel, BorderLayout.SOUTH);
			d.setLocationRelativeTo(null);
			d.setVisible(true);
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

	private void addKeyBindings() {
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");

		this.getActionMap().put("Enter", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				world.moveToCursor();
				repaint();
			}
		});

		if (moveType.equals("arrows")) {
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");// Hey
																			// chet
																			// I
																			// changed
																			// this
																			// to
																			// down
																			// so
																			// it
																			// works
																			// now
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");

			this.getActionMap().put("up", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					world.moveFromKeyBoard(0);
					// System.out.println("test");
					repaint();
				}
			});
			this.getActionMap().put("down", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					world.moveFromKeyBoard(1);
					repaint();
				}
			});
			this.getActionMap().put("left", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					world.moveFromKeyBoard(2);
					repaint();
				}
			});
			this.getActionMap().put("right", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					world.moveFromKeyBoard(3);
					repaint();
				}
			});
		} else {
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "up");
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "down");// Hey
																		// chet
																		// I
																		// changed
																		// this
																		// to
																		// down
																		// so it
																		// works
																		// now
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "left");
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "right");

			this.getActionMap().put("up", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					world.moveFromKeyBoard(0);
					System.out.println("test");
					repaint();
				}
			});
			this.getActionMap().put("down", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					world.moveFromKeyBoard(1);
					repaint();
				}
			});
			this.getActionMap().put("left", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					world.moveFromKeyBoard(2);
					repaint();
				}
			});
			this.getActionMap().put("right", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					world.moveFromKeyBoard(3);
					repaint();
				}
			});
		}
	}

}
