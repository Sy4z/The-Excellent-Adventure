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
	private ObjectOutputStream boardToServer;
	private ObjectInputStream boardFromServer;
	private UnitPlayer localPlayer;
	GameObject[][] localGameMap;
	public ClientThread(Socket socket){
		this.sock = socket;
		System.out.println("Client is Constructed");
	}


	public void run(){
		System.out.println("Trying Connection");
		try {
			boardFromServer = new ObjectInputStream(sock.getInputStream());
			boardToServer = new ObjectOutputStream(sock.getOutputStream());
			//fromServer = new DataInputStream(sock.getInputStream());
			System.out.println("Client Socket connected on " + sock.getInetAddress() + ":" + sock.getPort());



			/**
			 * The following block of code is where information can be sent to/from the server.
			 * Helper methods incoming
			 */
			while(true){ //Main Loop



				if(Main.tw.isTurn()== true){

					//Receive the GameBoard from the Server and update current game world
					try {
						String isMyTurn = "myturn";
						boardToServer.writeObject(isMyTurn); //Tells the serverThread its this clients turn
						Object gameBoardGeneric = boardFromServer.readObject(); //Read into  Generic Object Type
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

					//Receive LogicalTiles Here






					
					//Set endOfEndPhase on
					//Wait for end turn phase then send server local gameBoard
					while(Main.tw.startOfEndPhase == false); //Loop around, then when it is not false, go to the instruction


					//End Phase started so:

					boardToServer.writeObject(Main.world.getGameBoard());//Send current state of Local GameBoard to Server
					//Send the Array of LogicalTiles to the server - Can Just Override the servers version




					Main.tw.endOfEndPhase = true;//End end Phase







				}







				boardToServer.flush();

				if(boardToServer == null){ //Placeholder for now
					break;
				}


			}


			sock.close(); //Closes Socket - Important to do this but this closes the client thread completely, which might be a bad idea until the data beings being sent via a loop w/ exit clause
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}











