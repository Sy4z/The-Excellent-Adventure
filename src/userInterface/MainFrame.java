package userInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
 * @author peesapvenk
 * 
 */
public class MainFrame extends JFrame implements WindowListener {
	
	private ImageIcon background = new ImageIcon("Crashed_Spaceship.png");
	
	public MainFrame() {
		super.addWindowListener(this);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		super.setLayout(new BorderLayout());
		
		// Resize the image of the crashed spaceship for display purposes.
		Image resizedImage = background.getImage().getScaledInstance(1150, 850, Image.SCALE_SMOOTH);
		background = new ImageIcon(resizedImage);
		
		JPanel mainPanel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(background.getImage(), 0, 0, null);
			}
		};
		
		super.add(mainPanel, BorderLayout.CENTER);
		
		super.setSize(background.getIconWidth(), background.getIconHeight());
		super.setLocationRelativeTo(null); // Center the window.
		super.setResizable(false);
		super.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// 
	}

	@Override
	public void windowClosing(WindowEvent e) {
		int confirmed = JOptionPane.showConfirmDialog(null,
				"Are you sure you want to quit the game?", "",
				JOptionPane.YES_NO_OPTION);
		if (confirmed == JOptionPane.YES_OPTION) {
			dispose();
			System.exit(0);
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
	}
	
}
