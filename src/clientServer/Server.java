package clientServer;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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

public class Server extends Thread{
	private final Socket socket;

	public Server(Socket clientSocket){
		System.out.println("New Server Thread Created");//debugging info
		this.socket = clientSocket;
		//start(); Not sure that i need this

	}


	/**
	 * When start is called in the constructor, this runs.
	 */
	public void run(){
		try {
			DataInputStream input = new DataInputStream(socket.getInputStream());
			DataOutputStream output = new DataOutputStream(socket.getOutputStream());
			output.writeUTF("Hi Client");
			output.close();
			System.out.println(input.readUTF()); //Reads UTF-8 String from the Input Stream
			socket.close();
		} catch (IOException e) {
			System.out.println("There was a problem with input/output to/from the server");
			e.printStackTrace();
		}


		//Succesful opening of both input and output streams, now write map state to output


	}
}