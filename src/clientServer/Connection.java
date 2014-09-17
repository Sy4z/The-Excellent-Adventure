package clientServer;

import java.net.DatagramPacket;
import java.net.InetAddress;



/**
 * SERVERSIDE CLASS
 * @author syaz
 * A Connection is a specific connected user. 
 * This class Connection is based on the view of the server. A connection makes itself known
 * to the server by sending a packet to the servers' IP address. The server then stores
 * the connection in a collection of known clients.
 */

public class Connection{
	InetAddress clientIP;
	int port;

	public Connection(InetAddress ip, int port){
		this.clientIP = ip;
		this.port = port;

	}

/**
 * This method creates a new user for each unique IP coming in via a packet.
 * @param packet DatagramPacket sent by user
 * @return new client with unique identification data based on a user.
 */
	public Connection identifyClient(DatagramPacket packet){
		return(new Connection(packet.getAddress(), packet.getPort()));
}



	/**
	 * This class takes a packet and handles the message specific to the client.
	 */
	public void handleMessage(DatagramPacket packet){

	}

}
