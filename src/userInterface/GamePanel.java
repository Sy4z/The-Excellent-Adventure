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

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

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
	
	private void addKeyBindings() {
		if (moveType.equals("arrows")) {
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "back");
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
	        this.getActionMap().put("up", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	//world.moveFromKeyBoard(0);
	                System.out.println("test");
	            }
	        });
	        this.getActionMap().put("down", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	world.moveFromKeyBoard(1);
	            }
	        });
	        this.getActionMap().put("left", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	world.moveFromKeyBoard(2);
	            }
	        });
	        this.getActionMap().put("right", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	world.moveFromKeyBoard(3);
	            }
	        });
		}
		else {
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "up");
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "back");
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "left");
			this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "right");
	        this.getActionMap().put("up", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	//world.moveFromKeyBoard(0);
	                System.out.println("test");
	            }
	        });
	        this.getActionMap().put("down", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	world.moveFromKeyBoard(1); 
	            }
	        });
	        this.getActionMap().put("left", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	world.moveFromKeyBoard(2);
	            }
	        });
	        this.getActionMap().put("right", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	world.moveFromKeyBoard(3);
	            }
	        });
		}
	}

}
