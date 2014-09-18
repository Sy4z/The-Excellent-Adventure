package clientServer;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * SERVERSIDE CLASS
 * @author syaz
 * Server class dealing with all server calculations
 * Running via UDP, as TCP would be too slow and (i hope) we wont need every packet.
 * TODO: For every packet that gets sent, send back a confirmation it was received.
 * If the client does not receive confirmation, it will resend the packet until it receives confirmation
 * Will need to deal with duplicate packets because of the chance of the confirmation packet being lost
 * May not need confirmation for all packets. Just important ones. Frameskipping can be a thing too. 
 */

public class Server implements Runnable {
	int port = 32768;
	DatagramSocket sock;//Socket to recieve datagram packets

	boolean listening = false; //Boolean stating whether the server is listening. Default false, server must be started before it will begin listening.

	public Server(){
		try {
			sock = new DatagramSocket();
		} catch (SocketException e) {

			e.printStackTrace();
		}
	}





	@Override
	public void run() {
		while(true){
			if(listening){
				try {
					DatagramPacket packet = null; //Kinda hacky, I should probably watch out for nullPointers
					sock.receive(packet);
					new Thread(new ServerThread(sock, packet)).start();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}

	}



	/**
	 * Method which shuts down the server functionality
	 * Bearing in mind the instance of server is still instantiated
	 * This method just stops the server from listening for new connections
	 */
	public void shutdownServer(){
		listening = false;

	}


	/**
	 * Method which starts the server listening.
	 * This must be done before the server will start accepting connections.
	 */
	public void startServer(){
		listening = true;
	}




}
