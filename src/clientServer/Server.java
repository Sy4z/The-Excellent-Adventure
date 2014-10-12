package clientServer;

import gameWorld.UnitPlayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
/**
 * SERVERSIDE Class
 * This Class Deals with accepting connections from clients and passing them off to their own specific thread
 * This class is started by the main method if this instance of the program is signified to be a server
 * This class cannot run at the same time as a client
 */
public class Server {
private static int numPlayers; //this is a static int for use in an array of threads, however the array of threads system is currently unimplemented
boolean serverIsOn = true;
public Server(int numberPlayers){
	this.numPlayers = numberPlayers;
}

	/**
	 * This method is in charge of initialising the server - It creates a new thread every time a connection is accepted
	 */
	public void runServer(String addr, int port){
		ServerSocket serverSock = null;
		Socket accept = null;
		while(serverIsOn){
		try{
			ServerThread[] serverThreads = new ServerThread[numPlayers]; //Can use this for the number of possible players to accept, and store their threads. Unused currently
			serverSock = new ServerSocket(port);
			List<UnitPlayer> playerList = new ArrayList<UnitPlayer>();
			while(true){
				System.err.println("Waiting for Connection");
				accept = serverSock.accept();
				//Will only reach this point if the socket actually accepts a connection - considering accept() blocks until it receives input
				System.out.println("Accepted Connection from: " + accept.getInetAddress());
				playerList.add(arg0)
				ServerThread server = new ServerThread(accept);
				server.start();

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
			// TODO Auto-generated catch block
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
	





}
