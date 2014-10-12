package userInterface;

import gameRender.IsoCanvas;
import gameWorld.Inventory;
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

	private JTable tableItems1;
	private JTable tableNums1;
	private JTable tableItems2;
	private JTable tableNums2;

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

		// Creates a canvas and a world to put the canvas into the world.
		canvas = new IsoCanvas(currentFrame.getWidth(),
				currentFrame.getHeight());
		world = new World("", currentFrame.getWidth(),
				currentFrame.getHeight(), canvas);

		setInventory();

		canvas.setBounds(0, 0, currentFrame.getWidth(),
				currentFrame.getHeight());
		add(canvas);
	}

	/**
	 * The inventory
	 */
	private void setInventory() {
		ImageIcon katana = new ImageIcon("ImageKatana.jpg");
		katana = new ImageIcon(katana.getImage().getScaledInstance(250, 100,
				Image.SCALE_SMOOTH));
		ImageIcon key = new ImageIcon("imageKey.jpg");
		key = new ImageIcon(key.getImage().getScaledInstance(250, 100,
				Image.SCALE_SMOOTH));

		String[] columns1 = { "C1", "C2" };
		Object[][] data1 = { { katana, key } };

		DefaultTableModel model1 = new DefaultTableModel(data1, columns1) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tableItems1 = new JTable(model1) {
			// Returning the Class of each column will allow different
			// renderers to be used based on Class
			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};

		tableItems1.setOpaque(false);
		tableItems1.setDefaultRenderer(Object.class,
				new DefaultTableCellRenderer() {
					{
						setOpaque(false);
					}
				});

		tableItems1.setCellSelectionEnabled(false);
		tableItems1.setRowSelectionAllowed(false);
		tableItems1.setRowHeight(0, 100);
		tableItems1.setBounds(10, 600, 450, 1000);
		add(tableItems1);

		// Next

		String[] columns2 = { "C1", "C2" };
		Object[][] data2 = { { "1", "1" } };

		DefaultTableModel model2 = new DefaultTableModel(data2, columns2) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tableNums1 = new JTable(model2);
		tableNums1.setOpaque(false);

		int[] inventory = world.getInventory();
		int katanas = inventory[Inventory.itemTypes.KATANA.ordinal()];
		int keys = inventory[Inventory.itemTypes.KEY.ordinal()];

		tableNums1.setValueAt(Integer.toString(katanas), 0, 0);
		tableNums1.setValueAt(Integer.toString(keys), 0, 1);

		tableNums1.setCellSelectionEnabled(false);
		tableNums1.setRowSelectionAllowed(false);
		tableNums1.setBounds(10, 700, 450, 1000);
		add(tableNums1);

		// Next

		ImageIcon puppies = new ImageIcon("ImagePuppies07.jpg");
		puppies = new ImageIcon(puppies.getImage().getScaledInstance(250, 100,
				Image.SCALE_SMOOTH));
		ImageIcon nails = new ImageIcon("ImageRustyNails.jpg");
		nails = new ImageIcon(nails.getImage().getScaledInstance(250, 100,
				Image.SCALE_SMOOTH));

		String[] columns3 = { "C1", "C2" };
		Object[][] data3 = { { puppies, nails } };

		DefaultTableModel model3 = new DefaultTableModel(data3, columns3) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tableItems2 = new JTable(model3) {
			// Returning the Class of each column will allow different
			// renderers to be used based on Class
			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};

		tableItems2.setOpaque(false);
		tableItems2.setDefaultRenderer(Object.class,
				new DefaultTableCellRenderer() {
					{
						setOpaque(false);
					}
				});

		tableItems2.setCellSelectionEnabled(false);
		tableItems2.setRowSelectionAllowed(false);
		tableItems2.setRowHeight(0, 100);
		tableItems2.setBounds(670, 600, 450, 1000);
		add(tableItems2);

		// Next

		String[] columns4 = { "C1", "C2" };
		Object[][] data4 = { { "1", "1" } };

		DefaultTableModel model4 = new DefaultTableModel(data4, columns4) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tableNums2 = new JTable(model4);
		tableNums2.setOpaque(false);

		int pups = inventory[Inventory.itemTypes.PUPPY.ordinal()];
		int rustyNails = inventory[Inventory.itemTypes.RUSTY_NAIL.ordinal()];

		tableNums2.setValueAt(Integer.toString(pups), 0, 0);
		tableNums2.setValueAt(Integer.toString(rustyNails), 0, 1);

		tableNums2.setCellSelectionEnabled(false);
		tableNums2.setRowSelectionAllowed(false);
		tableNums2.setBounds(670, 700, 450, 15);
		add(tableNums2);
	}

	private void updateInventory() {
		int[] inventory = world.getInventory();
		int katanas = inventory[Inventory.itemTypes.KATANA.ordinal()];
		int keys = inventory[Inventory.itemTypes.KEY.ordinal()];

		tableNums1.setValueAt(Integer.toString(katanas), 0, 0);
		tableNums1.setValueAt(Integer.toString(keys), 0, 1);

		int pups = inventory[Inventory.itemTypes.PUPPY.ordinal()];
		int rustyNails = inventory[Inventory.itemTypes.RUSTY_NAIL.ordinal()];

		tableNums2.setValueAt(Integer.toString(pups), 0, 0);
		tableNums2.setValueAt(Integer.toString(rustyNails), 0, 1);
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
					KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");

			this.getActionMap().put("up", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					world.moveFromKeyBoard(0);
					updateInventory();
					repaint();
				}
			});
			this.getActionMap().put("down", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					world.moveFromKeyBoard(1);
					updateInventory();
					repaint();
				}
			});
			this.getActionMap().put("left", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					world.moveFromKeyBoard(2);
					updateInventory();
					repaint();
				}
			});
			this.getActionMap().put("right", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					world.moveFromKeyBoard(3);
					updateInventory();
					repaint();
				}
			});
		} else {
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "up");
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "down");
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "left");
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "right");

			this.getActionMap().put("up", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					world.moveFromKeyBoard(0);
					updateInventory();
					repaint();
				}
			});
			this.getActionMap().put("down", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					world.moveFromKeyBoard(1);
					updateInventory();
					repaint();
				}
			});
			this.getActionMap().put("left", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					world.moveFromKeyBoard(2);
					updateInventory();
					repaint();
				}
			});
			this.getActionMap().put("right", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					world.moveFromKeyBoard(3);
					updateInventory();
					repaint();
				}
			});
		}
	}

	/**
	 * This method gets world for the network code to deal with Written by
	 * Jarred - Sorry chet, i needed access to world
	 */
	public World getWorld() {
		return this.world;
	}

}
