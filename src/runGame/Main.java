package runGame;

import gameRender.IsoCanvas;
import gameWorld.*;

import java.awt.Point;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.UnexpectedException;

import javax.swing.JFrame;

import sun.security.util.Length;
import tile.TileMultiton.type;
import userInterface.MainFrame;
import clientServer.Client;
import clientServer.ClientThread;
import clientServer.Server;
import clientServer.ServerThread;
import dataStorage.Data;
import dataStorage.Tuple;

/**
 * Main method, Ties the Program together
 * @author Syaz
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



	/**
	 * Main Method to tie the whole program together.
	 * Using for networking currently.
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
		//The following block deals with starting the server
		server = new Server(numberOfPlayers);
		client = new Client();
		//tw = new TurnWatcher(mainFrame.mainPanel.gamePanel.getWorld());
		tw = new TurnWatcher(world);
		//runServer(server, client);


	}


	/**
	 * This Method sets the IP for the server taken from the input via the User Interface
	 * @param ip
	 */
	public static void setIP(String ip){
		ipAddress = ip;
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
		server.setupInitialState();
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

	public static int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public static void setNumberOfPlayers(int numberOfPlayers) {
		Main.numberOfPlayers = numberOfPlayers;
	}

}







