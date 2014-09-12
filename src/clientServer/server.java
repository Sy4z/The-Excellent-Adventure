package clientServer;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server class dealing with all server calculations (UDP?) 
 * If client is local, broadcast via Localhost, else via IP
 * @author syaz
 *
 */

public class server {
	int port = 32768;
	ServerSocket sock;
	Socket clientSock = null;
	DataInputStream input;
	PrintStream output;
	
	
	/**
	 * Will move most of the methods here into something that allows proper connections from clients
	 */
	public server(){
		
		try {
			sock = new ServerSocket(port); //Create server socket and broadcast on port
			clientSock = sock.accept(); //Accept client connection and store in socket clientSock
			input = new DataInputStream(clientSock.getInputStream());
			output = new PrintStream(clientSock.getOutputStream());
			output.close();
			input.close();
			clientSock.close();
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}




}
