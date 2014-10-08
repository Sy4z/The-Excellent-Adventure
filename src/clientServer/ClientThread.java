package clientServer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * CLIENTSIDE CLASS
 * @author syaz
 * honejarred@gmail.com
 * runs via TCP
 * This class creates packets, and sends them to the server for processing.
 * This class can also receive packets back from the server, and perform actions accordingly.
 */
public class ClientThread extends Thread {
	private final Socket sock;
	private DataOutputStream toServer;
	private DataInputStream fromServer;
	public ClientThread(Socket socket){
		this.sock = socket;
		System.out.println("Client is Constructed");
	}


	public void run(){
		System.out.println("Trying Connection");
		try {
			BufferedReader fromServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			toServer = new DataOutputStream(sock.getOutputStream());
			//fromServer = new DataInputStream(sock.getInputStream());
			System.out.println("Client Socket connected on " + sock.getInetAddress() + ":" + sock.getPort());
			
			
			
			/**
			 * The following block of code is where information can be sent to/from the server.
			 * Helper methods incoming
			 */
			String sentence = "Hi Server, From Client"; //Thus is the string that gets sent to the server
			toServer.writeBytes(sentence + '\n'); //Apparantly, writeBytes converts a string to bytes automatically
			//End Data Transfer Block
			
			
			
			toServer.flush();
			sock.close(); //Closes Socket - Important to do this but this closes the client thread completely, which might be a bad idea until the data beings being sent via a loop w/ exit clause
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}











