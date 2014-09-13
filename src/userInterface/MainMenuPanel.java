package userInterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel {
	
	JLabel gameName;
	JButton newGameButton;
	JButton loadButton;
	JButton storyButton;
	JButton controlsButton;
	JButton optionsButton;
	JButton exitButton;
	
	public MainMenuPanel() {
		setLayout(new GridLayout(7, 1));
		gameName = new JLabel("Robot Mania");
		gameName.setFont(gameName.getFont().deriveFont(23.0f));
		gameName.setHorizontalAlignment(JLabel.CENTER);
		
		newGameButton = new JButton("New Game");
		newGameButton.setOpaque(false);
		newGameButton.setContentAreaFilled(false);
		newGameButton.setBorderPainted(false);
		
		loadButton = new JButton("Load Game");
		loadButton.setOpaque(false);
		loadButton.setContentAreaFilled(false);
		loadButton.setBorderPainted(false);
		
		storyButton = new JButton("Story");
		storyButton.setOpaque(false);
		storyButton.setContentAreaFilled(false);
		storyButton.setBorderPainted(false);
		
		controlsButton = new JButton("Controls");
		controlsButton.setOpaque(false);
		controlsButton.setContentAreaFilled(false);
		controlsButton.setBorderPainted(false);
		
		optionsButton = new JButton("Options");
		optionsButton.setOpaque(false);
		optionsButton.setContentAreaFilled(false);
		optionsButton.setBorderPainted(false);
		
		exitButton = new JButton("Exit");
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
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
	
	class ExitButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
		
	}

}
