package clientServer;


import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/**
 * SERVERSIDE CLASS
 * @author syaz
 * Server class dealing with all server calculations
 * Running via TCP, as the game is now turn based.
 * Currently Sends data from client to server and server Echos the data. 
 * So the connection is working currently.
 * Works via RunNetwork.java
 * TODO: Game Logic integration
 */

public class Server extends Thread{
	Socket clientSock;
	
	public Server(Socket clientSocket){
		this.clientSock = clientSocket;
		start();
		
	}
	
	
	
	
	
	
	
	
	
	
	
}