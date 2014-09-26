package clientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * CLIENTSIDE CLASS
 * @author syaz
 * runs via TCP
 * This class creates packets, and sends them to the server for processing.
 * This class can also receive packets back from the server, and perform actions accordingly.
 */
public class Client {
Socket sock;
	public Client(){
		String serverIp = new String ("127.0.0.1");//Localhost. Leave it on this for testing purposes
		int port = 10008; //Port. Server is currently listening at 10008

		System.out.println ("Attemping to connect to host " +
				serverIp + " on port " + port);

		sock = null; //this is the socket where information is sent/received
		PrintWriter toServer = null;
		BufferedReader fromServer = null;
		
		try {
			sock = new Socket(serverIp, port); //create a socket in sock with the desired details
		} catch (UnknownHostException e) { //Thrown if the host details lead to the host not being found
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		try {
			//toServer.close(); (Nullpointer Currently)
			//fromServer.close();
			sock.close();//Close this last
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		}
	
	
	/**
	 * Method which gets the socket from this class for the main network method to use
	 * @return Socket - Client main socket
	 */
	public Socket getSock(){
		return sock;
	}
	
	
	
	}




