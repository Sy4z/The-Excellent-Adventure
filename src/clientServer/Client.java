package clientServer;

import java.net.DatagramPacket;
import java.net.InetAddress;



/**
 * @author syaz
 * A Client is a specific connected user. Will be stored in a map of known clients.
 */

public class Client {
	InetAddress clientIP;
	int port;

	public Client(InetAddress ip, int port){
		this.clientIP = ip;
		this.port = port;

	}

/**
 * This method creates a new user for each unique IP coming in via a packet.
 * @param packet datagrampacket sent by user
 * @return new client with unique identification data based on a user.
 */
	public Client identifyClient(DatagramPacket packet){
		return(new Client(packet.getAddress(), packet.getPort()));
}



	/**
	 * This class takes a packet and handles the message specific to the client.
	 */
	public void handleMessage(DatagramPacket packet){

	}

}
