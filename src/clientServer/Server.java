package clientServer;

import gameWorld.GameObject;
import gameWorld.UnitPlayer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import runGame.Main;
/**
 * SERVERSIDE Class
 * @author Jarred
 * This Class Deals with accepting connections from clients and passing them off to their own specific thread
 * This class is started by the main method if this instance of the program is signified to be a server
 * This class cannot run at the same time as a client
 */
public class Server {
	private static int numPlayers; //this is a static int for use in an array of threads
	boolean serverIsOn = true;
	static List<InetAddress> playerList = new ArrayList<InetAddress>(); //The list of players by their Unique IP. The order they are in tells the Server the turn order of Players
	static List<Socket> players = new ArrayList<Socket>();
	private static GameObject[][] mainGameBoard;
	private int currentTurn = 0; //Index of the player in the list who currently has a turn. Assume it starts at 1.
	ServerSocket serverSock;



	public Server(int numberPlayers){
		this.numPlayers = numberPlayers;
		this.setupInitialState();
		//Get the initial State of the gameboard for the first player to receive;
	}

	public void setupInitialState(){
		mainGameBoard = Main.world.getGameBoard();
	}


	/**
	 * This method is in charge of initialising the server - It creates a new thread every time a connection is accepted
	 */
	public void runServer(String addr, int port){
		System.out.println("Server Starting up");
		while(serverIsOn){
			try{

				serverSock = new ServerSocket(port);
				ServerThread server = new ServerThread();
				server.start();
				int i=0;
				while(true){
					System.err.println("Waiting for Connection");
					players.add(serverSock.accept());
					//Will only reach this point if the socket actually accepts a connection - considering accept() blocks until it receives input
					System.out.println("Accepted Connection: " + players.get(i++).getInetAddress());


					//playerList.add()



				}
			}
			catch(IOException e){
				System.out.println("SocketAccept(); Threw exception on server");
				e.printStackTrace();
			}
		}
		System.out.println("Server Stopped");
		try {

			serverSock.close();

		} catch (IOException e) {
			System.out.println("The problem is here");
			e.printStackTrace();
		}
	}



	/**
	 * This method starts the server if it is stopped
	 * Else, if it is already started, gives the user a message
	 */
	public void startServer(){

		if(serverIsOn){
			System.out.println("Server is Already Running");

		}
		else{
			serverIsOn = true; //sets the boolean to true
			//May need to actually call runServer - I'll get to this later after some testing
			System.out.println("Server is now started");
		}


	}

	/**
	 * This method stops the server if it is running
	 * If it is already stopped, gives the user a message
	 */
	public void stopServer(){

		if(serverIsOn){
			//Stopped message happens after loop closes anyway
			System.out.println("Stopping Server");
			serverIsOn = false;
		}
		else{
			System.out.println("Server is Already Stopped");
		}



	}
	/**
	 * Gets the current server version of the gameboard
	 * @return the  current server version of the gameboard
	 */
	public static GameObject[][] getMainGameBoard() {
		return mainGameBoard;
	}


	/**
	 * Sets the current server version of the gameboard
	 * @param mainGameBoard input a gameobject, and the server version will be set to it
	 */
	public static void setMainGameBoard(GameObject[][] mainGameBoard) {
		Server.mainGameBoard = mainGameBoard;
	}


	// ------------------Methods for Controlling and getting the current turn--------------------


	/**
	 * this method gets the current turn index
	 * @return int - the value of the current player who's turn it is
	 */
	public int currentTurn(){
		return currentTurn;
	}

	//	public void setNextTurn(){
	//		if((currentTurn) < playerList.size()){ //Make sure the next number doesnt tick over the max number of players
	//			currentTurn++; //Increment the current turn
	//		}
	//		else{
	//			currentTurn = 0; //Tick back around to 1, because we reached the end of the playerlist
	//		}
	//	}




}
