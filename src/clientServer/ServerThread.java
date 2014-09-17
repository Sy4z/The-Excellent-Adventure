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

	public ServerThread(DatagramSocket sock, DatagramPacket packet) throws SocketException{
		super("ServerThread");
		this.threadSocket = sock;
		this.serverPacket = packet;
	}



	public void run(){
		while(true){
		
		}
	}


}
