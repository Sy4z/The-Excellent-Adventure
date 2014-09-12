package clientServer;

import java.io.IOException;
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
	
	
	
	public server(){
		
		try {
			sock = new ServerSocket(port); //Create server socket and broadcast on port
			clientSock = sock.accept(); //Accept client connection and store in socket clientSock
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}




}
