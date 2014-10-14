package userInterface;

import gameRender.IsoCanvas;
import gameWorld.Inventory;
import gameWorld.World;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import java.rmi.UnexpectedException;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.sun.org.apache.xerces.internal.impl.xs.identity.Selector.Matcher;

import dataStorage.Data;
import runGame.Main;

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
	private String moveType;

	// Inventory fields.
	private JTable tableItems1;
	private JTable tableNums1;
	private JTable tableItems2;
	private JTable tableNums2;

	// This field stores the mode which is either 'Quit' or 'Save' depending on
	// whether the save dialog box opens when the user wants to quit or save
	// during the game.
	private String mode;

	public GamePanel(JFrame frame, JPanel menuPanel, String moveType) {
		currentFrame = frame;
		oldPanel = menuPanel;
		this.moveType = moveType;
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

		quit.addMouseListener(new HoverButtonListener());
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
		controls.addMouseListener(new HoverButtonListener());
		controls.addActionListener(new ControlsGameListener());
		add(controls);

		JButton saveGame = new JButton("Save Game");
		saveGame.setOpaque(false);
		saveGame.setContentAreaFilled(false);
		saveGame.setBorderPainted(false);
		saveGame.setFont(new Font("Arial", Font.PLAIN, 35));
		saveGame.setFocusPainted(false);
		saveGame.setForeground(Color.GREEN);

		saveGame.setBounds(10, 20, 250, 40);
		saveGame.addMouseListener(new HoverButtonListener());
		saveGame.addActionListener(new SaveGameListener());
		add(saveGame);

		setInventory();

		Main.cvs.setBounds(0, 0, currentFrame.getWidth(),
				currentFrame.getHeight());
		add(Main.cvs);
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

		tableItems1.setFocusable(false);
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

		int[] inventory = Main.world.getInventory();
		int katanas = inventory[Inventory.itemTypes.KATANA.ordinal()];
		int keys = inventory[Inventory.itemTypes.KEY.ordinal()];

		tableNums1.setValueAt(Integer.toString(katanas), 0, 0);
		tableNums1.setValueAt(Integer.toString(keys), 0, 1);

		tableNums1.setFocusable(false);
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

		tableItems2.setFocusable(false);
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

		tableNums2.setFocusable(false);
		tableNums2.setBounds(670, 700, 450, 15);
		add(tableNums2);
	}

	private void updateInventory() {
		int[] inventory = Main.world.getInventory();
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
	 * main menu by replacing the game panel with the main menu's panel. It also
	 * asks the user whether they want to save the game before quitting or not.
	 *
	 * @author Venkata Peesapati
	 *
	 */
	class QuitGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mode = "Quit";

			int option = JOptionPane.showConfirmDialog(null,
					"Do you want to save before quitting?", "Choose",
					JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				saveGame();
			} else {
				moveType = "arrows";
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().validate();
				currentFrame.getContentPane().repaint();

				currentFrame.getContentPane()
						.add(oldPanel, BorderLayout.CENTER);

				currentFrame.getContentPane().validate();
				currentFrame.getContentPane().repaint();
			}
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

	/**
	 * This is the listener class used for the Save Game button. It allows the
	 * user to save the game and continue playing. Note that the mode is 'Save'
	 * over here which means that the saveGame method will not quit after the
	 * saving is done.
	 *
	 * @author Venkata Peesapati
	 *
	 */
	class SaveGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mode = "Save";
			saveGame();
		}

	}

	/**
	 *
	 */
	public void saveGame() {
		final JDialog d = new JDialog(currentFrame, "Save Game", true);
		d.setSize(400, 300);
		d.setLayout(new BorderLayout());

		DefaultListModel<String> model = new DefaultListModel<String>(); // Add
		// saved
		// game
		// names
		// to
		// this
		// model.
		JList<String> list = new JList<String>(model);
		JScrollPane scrollPane = new JScrollPane(list);
		d.add(scrollPane, BorderLayout.CENTER);

		JPanel savePanel = new JPanel();
		savePanel.setLayout(new BorderLayout());

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout());
		namePanel.add(new JLabel("Name: "));
		final JTextField nameField = new JTextField();
		nameField.setPreferredSize(new Dimension(110, 20));
		namePanel.add(nameField);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				d.dispose();
			}
		});
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();

				Pattern alphaNumeric = Pattern.compile("^[a-zA-Z0-9]*$");
				java.util.regex.Matcher match1 = alphaNumeric.matcher(name);
				Pattern spaces = Pattern.compile("\\s");
				java.util.regex.Matcher match2 = spaces.matcher(name);
				if (match1.matches() && !match2.find()) {
					try {
						Data.save(name);
					} catch (UnexpectedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					d.dispose();
					if (mode.equals("Quit")) {
						moveType = "arrows";
						currentFrame.getContentPane().removeAll();
						currentFrame.getContentPane().validate();
						currentFrame.getContentPane().repaint();

						currentFrame.getContentPane().add(oldPanel,
								BorderLayout.CENTER);

						currentFrame.getContentPane().validate();
						currentFrame.getContentPane().repaint();
					}
				} else {
					JOptionPane.showMessageDialog(GamePanel.this,
							"Name must alphanumric with no spaces!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		buttonPanel.add(new JButton("Delete"));

		savePanel.add(namePanel, BorderLayout.CENTER);
		savePanel.add(buttonPanel, BorderLayout.SOUTH);
		d.add(savePanel, BorderLayout.SOUTH);

		d.setLocationRelativeTo(null);
		d.setVisible(true);
	}

	private void addKeyBindings() {
		this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");

		this.getActionMap().put("Enter", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.world.moveToCursor();
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
					Main.world.moveFromKeyBoard(0);
					updateInventory();
					repaint();
				}
			});
			this.getActionMap().put("down", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Main.world.moveFromKeyBoard(1);
					updateInventory();
					repaint();
				}
			});
			this.getActionMap().put("left", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Main.world.moveFromKeyBoard(2);
					updateInventory();
					repaint();
				}
			});
			this.getActionMap().put("right", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Main.world.moveFromKeyBoard(3);
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
					Main.world.moveFromKeyBoard(0);
					updateInventory();
					repaint();
				}
			});
			this.getActionMap().put("down", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Main.world.moveFromKeyBoard(1);
					updateInventory();
					repaint();
				}
			});
			this.getActionMap().put("left", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Main.world.moveFromKeyBoard(2);
					updateInventory();
					repaint();
				}
			});
			this.getActionMap().put("right", new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Main.world.moveFromKeyBoard(3);
					updateInventory();
					repaint();
				}
			});
		}
	}

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
			((JButton) e.getSource()).setForeground(Color.black);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			((JButton) e.getSource()).setForeground(Color.green);
		}

	}

}
