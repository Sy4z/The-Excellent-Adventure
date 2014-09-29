package main;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import clientServer;

public class Main {

	/**
	 * Main Method to tie the whole program together.
	 * Using for networking currently.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
		
			Socket sock = new Socket(addr, port);
		new clientServer.Client(sock).run();
		}
		
	
	
	
	/**
	 * This method is in charge of initialising the server
	 */
	private static void runServer(){
		
	}

}