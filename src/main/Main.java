package main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import clientServer.Client;
import clientServer.ClientThread;
import clientServer.Server;
import clientServer.ServerThread;

/**
 * Main method, Ties the Program together
 * @author Syaz
 * honejarred@gmail.com
 */



public class Main {

	private static int numberOfPlayers = 3; //Variable for the number of players in the game
	private static boolean isServer = false; //Is this instance of the program a server


	/**
	 * Main Method to tie the whole program together.
	 * Using for networking currently.
	 * @param args
	 */
	public static void main(String[] args) {
		Server server = new Server(numberOfPlayers);
		Client client = new Client();

		if(isServer){
			client.runClient("127.0.0.1", 29596);
		}
		else{
			server.runServer("127.0.0.1", 29596);


		}
	}

}







	