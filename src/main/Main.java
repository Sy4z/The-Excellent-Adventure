package main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import clientServer.Client;
import clientServer.Server;

/**
 * Main method, Ties the Program together
 * @author Syaz
 * honejarred@gmail.com
 */



public class Main {

	private static int numberOfPlayers = 3; //Variable for the number of players in the game
	
	
	
	/**
	 * Main Method to tie the whole program together.
	 * Using for networking currently.
	 * @param args
	 */
	public static void main(String[] args) {

		runServer("127.0.0.1", 29596);
		//runClient("127.0.0.1", 29596);

	}


	/**
	 * This method controls a Multiplayer game
	 */
	private static void multiplayerGame(){

	}

	/**
	 * This method is in charge of initialising the client
	 * @param IP Address, Port Number
	 */
	private static void runClient(String addr, int port){

		Socket clientSock = null;
		try {
			clientSock = new Socket(InetAddress.getByName(addr), port);
			Client client = new Client(clientSock);
			client.start();
		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}


	}




	/**
	 * This method is in charge of initialising the server
	 */
	private static void runServer(String addr, int port){
		ServerSocket serverSock = null;
		Socket accept = null;
		try{
			Server[] serverThreads = new Server[numberOfPlayers];
			serverSock = new ServerSocket(port);
			while(true){
				System.err.println("I didnt accept connection because i suck");
				accept = serverSock.accept();
				System.out.println("Accepted Connection from: " + accept.getInetAddress());
				Server server = new Server(accept);
				server.start();

			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		try {
			serverSock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
