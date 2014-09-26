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
public class Client extends Thread {
	Socket sock;
	int port;
	String serverIp;
	public Client(Socket socket){
		this.sock = socket;
		System.out.println ("Attemping to connect to host " +
				serverIp + " on port " + port);


		PrintWriter toServer = null;
		BufferedReader fromServer = null;


		//TODO Information for client goes here
	}
	
	
	public void run(){
		
	}

}











