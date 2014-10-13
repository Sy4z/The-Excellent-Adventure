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
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

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
	GameObject[][] localGameMap;
	public ClientThread(Socket socket){
		this.sock = socket;
		System.out.println("Client is Constructed");
	}


	public void run(){
		System.out.println("Trying Connection");
		try {
			charFromServer = new ObjectInputStream(sock.getInputStream());
			charToServer = new ObjectOutputStream(sock.getOutputStream());
			//fromServer = new DataInputStream(sock.getInputStream());
			System.out.println("Client Socket connected on " + sock.getInetAddress() + ":" + sock.getPort());



			/**
			 * The following block of code is where information can be sent to/from the server.
			 * Helper methods incoming
			 */
			String sentence = "Hi Server, From Client"; //Thus is the string that gets sent to the server

			charToServer.writeBytes(sentence + '\n'); //Apparantly, writeBytes converts a string to bytes automatically
			//End Data Transfer Block


			while(true){



				if(Main.tw.isTurn()== true){

					//Receive the GameBoard from the Server and update current game world
					try {
						Object gameBoardGeneric = charFromServer.readObject(); //Read into  Generic Object Type
						if(gameBoardGeneric instanceof GameObject[][]){ //Check that the generic object is an arraylist - Cant check further than this because nested generics get erased at runtime but at least theres some safeguard to typechecking
							localGameMap = (GameObject[][])gameBoardGeneric; //Set the board as a local variable
							Main.world.setGameBoard(localGameMap); //Update the local copy of the gameMap
						}
						else{
							System.out.println("Client Expected the ArrayList of Players and Recieved Something that wasnt an ArrayList");
						}
					} catch (ClassNotFoundException e) {
						System.out.println("ClientThread: Could not read CharacterList from Server");
						e.printStackTrace();
					}

					//Wait for end turn phase then send server local gameBoard












				}



				//Send the Array of LogicalTiles to the server - Can Just Override the servers version



				charToServer.flush();

				if(charToServer == null){ //Placeholder for now
					break;
				}


			}


			sock.close(); //Closes Socket - Important to do this but this closes the client thread completely, which might be a bad idea until the data beings being sent via a loop w/ exit clause
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}











