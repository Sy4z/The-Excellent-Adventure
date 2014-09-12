package userInterface;

import java.awt.GridLayout;

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
		
		newGameButton = new JButton("New Game");
		loadButton = new JButton("Load Game");
		storyButton = new JButton("Story");
		controlsButton = new JButton("Controls");
		optionsButton = new JButton("Options");
		exitButton = new JButton("Exit");
		
		add(gameName);
		add(newGameButton);
		add(loadButton);
		add(storyButton);
		add(controlsButton);
		add(optionsButton);
		add(exitButton);
	}
	
	// Action listeners for each of the buttons can go down here.

}
