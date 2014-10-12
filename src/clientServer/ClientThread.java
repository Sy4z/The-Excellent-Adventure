package clientServer;

import gameWorld.UnitPlayer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import runGame.Main;

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
	private ObjectOutputStream charToServer;
	private ObjectInputStream charFromServer;
	private UnitPlayer localPlayer;
	public ClientThread(Socket socket){
		this.sock = socket;
		System.out.println("Client is Constructed");
	}


	public void run(){
		System.out.println("Trying Connection");
		try {
			fromServer = new ObjectInputStream(sock.getInputStream());
			toServer = new ObjectOutputStream(sock.getOutputStream());
			//fromServer = new DataInputStream(sock.getInputStream());
			System.out.println("Client Socket connected on " + sock.getInetAddress() + ":" + sock.getPort());
			
			
			
			/**
			 * The following block of code is where information can be sent to/from the server.
			 * Helper methods incoming
			 */
			String sentence = "Hi Server, From Client"; //Thus is the string that gets sent to the server
			
			toServer.writeBytes(sentence + '\n'); //Apparantly, writeBytes converts a string to bytes automatically
			//End Data Transfer Block
			

			while(true){
				localPlayer = new UnitPlayer(Main.mainFrame.mainPanel.gamePanel.getWorld().avatar);
				
				
				
				
			}
			
			toServer.flush();
			sock.close(); //Closes Socket - Important to do this but this closes the client thread completely, which might be a bad idea until the data beings being sent via a loop w/ exit clause
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}











