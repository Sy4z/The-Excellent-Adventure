package clientServer;


import gameWorld.UnitPlayer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * SERVERSIDE CLASS
 * FOR EACH CLIENTTHREAD, CREATE SERVERTHREAD TO DEAL WITH IT
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
	public UnitPlayer clientPlayer; //Local player from client is stored here, gets updated every tick

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
			//BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream())); //Needed a buffered reader to keep the input stream open

			//DataOutputStream output = new DataOutputStream(socket.getOutputStream()); 



			//Base this on ticks (Turns) - Send to every client on every turn. Whether you as a local player will have moved or not is based on game logic
			while(true){
				ObjectInputStream charFromClient = new ObjectInputStream(socket.getInputStream()); //for recieving the character from the connected clientThread
				ObjectOutputStream charsToClient = new ObjectOutputStream(socket.getOutputStream()); //for outputting all the other characters to the server

				//Receive Information from the client
				//Player Character
				try {
					clientPlayer = (UnitPlayer)charFromClient.readObject(); //Reads the UnitPlayer from the Client and stores it
				} catch (ClassNotFoundException e) {

					e.printStackTrace();
				}
				//Deal with adding to the list of clientPlayers here

				
				
				
				//Array of LogicalTiles - Just Update the server Copy
				
				
				
				
				
				//Send Data to Client
				
				//Player Characters
				
				
				
				
				
				
				
				//Server Copy of Logical Tiles
				//Send
				
				
				
				
				//Wrap up and wait for next looparound
				
				if(socket == null){ //If client DC's? Im not sure how to check for this.
					socket.close();
				}

			}



			//String sentence = "Hello";
			//output.writeBytes(sentence + '\n');
			//System.out.println(fromClient.readLine()); 

		} catch (IOException e) {
			System.out.println("There was a problem with input/output to/from the server");
			e.printStackTrace();
		}


		


	}











}