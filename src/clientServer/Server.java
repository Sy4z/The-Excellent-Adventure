package clientServer;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * SERVERSIDE CLASS
 * @author syaz
 * Server class dealing with all server calculations
 * Running via UDP, as TCP would be too slow and (i hope) we wont need every packet. 
 */

public class Server implements Runnable {
	int port = 32768;
	DatagramSocket sock;//Socket to recieve datagram packets

	boolean listening = true; //Boolean stating whether the server is listening

	public Server(){
		try {
			sock = new DatagramSocket();
		} catch (SocketException e) {

			e.printStackTrace();
		}
	}





	@Override
	public void run() {
		while(listening){
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
