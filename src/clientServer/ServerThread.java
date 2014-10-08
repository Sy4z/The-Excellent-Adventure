package clientServer;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * SERVERSIDE CLASS
 * @author syaz
 * honejarred@gmail.com
 * Server class dealing with all server calculations
 * Running via TCP, as the game is now turn based.
 * Currently Sends data from client to server and server Echos the data. 
 * So the connection is working currently.
 * Works via RunNetwork.java
 * TODO: Game Logic integration
 */

public class ServerThread extends Thread{
	private final Socket socket;

	public ServerThread(Socket clientSocket){
		System.out.println("New Server Thread Created");//debugging info
		this.socket = clientSocket;
		//start(); Not sure that i need this - I don't

	}


	/**
	 * When start is called in the constructor, this runs.
	 */
	public void run(){
		try {
			BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream())); //Needed a buffered reader to keep the input stream open

			DataOutputStream output = new DataOutputStream(socket.getOutputStream());
			
			String sentence = "Hello";
			output.writeBytes(sentence + '\n');
			System.out.println(fromClient.readLine()); 
			socket.close();
		} catch (IOException e) {
			System.out.println("There was a problem with input/output to/from the server");
			e.printStackTrace();
		}


		//Succesful opening of both input and output streams, now write map state to output


	}











}