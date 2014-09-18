package clientServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * SERVERSIDE CLASS
 * @author syaz
 * Class which deals with the logic of each different connected user.
 * This gets invoked when a user connects to the server,
 * as a seperate thread per connection.
 */


public class ServerThread extends Thread{
	DatagramSocket threadSocket;
	DatagramPacket serverPacket;
	boolean packetDealtWith = false; //Boolean telling this class whether the packet passed to the socket has been dealt with. Default false.

	public ServerThread(DatagramSocket sock, DatagramPacket packet) throws SocketException{
		super("ServerThread");
		this.threadSocket = sock;
		this.serverPacket = packet;
	}



	public void run(){
		while(true){
		
		}
	}
	
	
	/**
	 * Class which takes the from the thread, and deals with it.
	 * Can pass to sendMessage, which sends the message to the client, via the server.
	 */
	public void handleMessage(){
		
	}

	/**
	 * Class which sends the message after handleMessage() has dealt with the packet.
	 * Will return an error if there is no packet, or the packet has not been dealt with
	 */
	public void sendMessage(){
		
	}
	
	

}
