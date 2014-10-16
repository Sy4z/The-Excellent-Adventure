package runGame;

import gameRender.IsoCanvas;
import gameWorld.*;

import java.awt.Point;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import userInterface.MainFrame;
import clientServer.Client;
import clientServer.Server;
import dataStorage.Data;
import dataStorage.Tuple;

/**
 * Main method, Ties the Program together
 * @author Jarred
 * honejarred@gmail.com
 */



public  class Main {

	private static int numberOfPlayers = 3; //Variable for the number of players in the game

	public static boolean isServer = true; //Is this instance of the program a server
	public static String ipAddress;
	public static int port = 29597;
	public static Server server;
	public static Client client;
	public static MainFrame mainFrame;
	public static World world;
	public static IsoCanvas cvs;
	public static TurnWatcher tw;
	public static boolean onlineMode;


	/**
	 * Main Method to tie the whole program together.
	 * Several checks for problems within constructor also
	 * @param args
	 */
	public static void main(String[] args) {


		JFrame loadFrame = MainFrame.createLoadingFrame();
		loadFrame.setVisible(true);


		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		loadFrame.dispose();
		mainFrame = new MainFrame();

		cvs = new IsoCanvas(mainFrame.getWidth(), mainFrame.getHeight());
		Tuple t = Data.testSet(null);

		LogicalTile[][] lTiles = new LogicalTile[t.tiles[1].length][t.tiles.length];
		GameObject[][] gobjs = new GameObject[t.tiles[1].length][t.tiles.length];

		for(int i  =0; i < lTiles[1].length;i++){
			for(int j = 0; j < lTiles.length;j++){
				lTiles[i][j] = new LogicalTile(true);
			}
		}
		gobjs[1][1] = new UnitPlayer(new Point(1,1),0);
		world = new World(lTiles, gobjs);

		try {
			ipAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.out.println("Error getting local host ip");
			e.printStackTrace();
		}
		tw = new TurnWatcher(world);
		tw.start();
		//The following block deals with starting the server
		server = new Server(numberOfPlayers);
		client = new Client();
		//tw = new TurnWatcher(mainFrame.mainPanel.gamePanel.getWorld());

		//runServer(server, client);


	}


	/**
	 * This Method sets the IP for the server taken from the input via the User Interface
	 * @param ip
	 */
	public static void setIP(String ip){
		ipAddress = "130.195.4.155";
	}

	/**
	 * This method runs the server based on IP input from the User Interface
	 * It assumes localhost if no IP is input
	 * @param runServer - the Instance of Server
	 * @param runClient - the Instance of Client
	 */
	public static void runClientMain(){


			client.runClient(ipAddress, port );


	}

	public static void runServerMain(){

		server.runServer(ipAddress, port);
	}

	/**
	 * This method sets from the UI whether to start server or client
	 * @param serverTrue - True if this should start server, false if this should start client
	 */
	public static void isServer(boolean serverTrue){


	if(serverTrue){
		isServer = true;
	}
	else{
		isServer = false;
	}

	}


	/**
	 * Gets the number of players that can currently be in the game
	 * @return int - The number of players total that can be in the game at any one time
	 */
	public static int getNumberOfPlayers() {
		return numberOfPlayers;
	}


	/**
	 * Set the number of players that can be in the game at any one time
	 * @param numberOfPlayers - int The number of players you want to set
	 */
	public static void setNumberOfPlayers(int numberOfPlayers) {
		Main.numberOfPlayers = numberOfPlayers;
	}

}







