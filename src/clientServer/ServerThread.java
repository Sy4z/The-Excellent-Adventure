package clientServer;


import gameWorld.GameObject;
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

import runGame.Main;

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
	private Socket socket;
	public UnitPlayer clientPlayer; //Local player from client is stored here, gets updated every tick
	GameObject[][] gameBoardServer;
	Object tempStringTurn;
	static int turnNumber; //The number for the Connected Clients turnorder
	ObjectInputStream boardFromClient;
	ObjectOutputStream boardToClient;

	public ServerThread(Socket clientSocket){
		System.out.println("New Server Thread Created");//debugging info
		this.socket = clientSocket;
		Main.server.playerList.add(socket.getInetAddress());//Add this Client to the List of Clients in server - This means the Logic for starting turns can be addressed easily this way
		turnNumber = Main.server.playerList.size(); //Since the value added will be in the last place in the list, just get the size to set the value
	 //for receiving the character from the connected clientThread

	}


	/**
	 * This is called when the thread is started in server();
	 */
	public void run(){
		try {
			boardToClient = new ObjectOutputStream(socket.getOutputStream()); //for outputting all the other characters to the server
			boardFromClient = new ObjectInputStream(socket.getInputStream());



			//Base this on ticks (Turns) - Send to every client on every turn. Whether you as a local player will have moved or not is based on game logic
			while(true){
				if(Main.server.currentTurn() == this.turnNumber){

					String yourTurn = "yourturn";
					boardToClient.writeObject(yourTurn);//Let Client Know its now his turn
					try {
						tempStringTurn = boardFromClient.readObject(); //Wait for Incoming Token
					} catch (ClassNotFoundException e1) {
						System.out.println("Was a problem with server waiting for incoming token from client, to start logic loop");
						e1.printStackTrace();
					}
					String isMyTurn = (String)tempStringTurn; //Cast the input String
					if(isMyTurn instanceof String && isMyTurn.equals("myturn")){//Check if its the connected clients turn (Will receive notification from client)


						//Send GameBoard current State to Client
						gameBoardServer = Main.server.getMainGameBoard(); //Get the most updated recent copy of the gameBoard
						boardToClient.writeObject(gameBoardServer);//Write the gameboard to the client


						boardToClient.flush();//Flush output buffer, making sure we dont get overloaded with info


						// Recieve updated GameBoard from client at end of turn - Should sit here waiting for the token to come in so no logic needed here
						try {
							Object tempBoard = boardFromClient.readObject();
							gameBoardServer = (GameObject[][]) tempBoard; //Reads the UnitPlayer from the temp object, casting it as correct type and stores it
							Main.server.setMainGameBoard(gameBoardServer);//Update main board in Server so all threads can see it

						} catch (ClassNotFoundException e) {
							System.out.println("Problem In Serverthread: Something went wrong when receiving the gameboard from the client");
							e.printStackTrace();
						}





						//Array of LogicalTiles - Receive Here



















						//Set next turn on



					}
					//Wrap up and wait for next looparound

					if(socket == null){ //If client DC's? Im not sure how to check for this.
						socket.close();
					}
					boardToClient.flush();
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