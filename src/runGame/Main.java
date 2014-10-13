package runGame;

import gameWorld.World;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import userInterface.MainFrame;
import clientServer.Client;
import clientServer.ClientThread;
import clientServer.Server;
import clientServer.ServerThread;

/**
 * Main method, Ties the Program together
 * @author Syaz
 * honejarred@gmail.com
 */



public  class Main {

	private static int numberOfPlayers = 3; //Variable for the number of players in the game

	public static boolean isServer = true; //Is this instance of the program a server
	public static String ipAddress = "127.0.0.1";
	public static Server server;
	public static Client client;
	public static MainFrame mainFrame;
	public static World world;


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

		//The following block deals with starting the server
		server = new Server(getNumberOfPlayers());
		client = new Client();
		runServer(server, client);

	}

	private boolean turn(){
		world.startTurn();

		while(world.checkPlayerStatus()){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
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
	public static void runServer(Server runServer, Client runClient){

		if(isServer == false){ //Sets whether this instance of the program is client or server from a boolean
			runClient.runClient(ipAddress, 29599);
		}
		else{
			runServer.runServer(ipAddress, 29599);


		}
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







