package clientServer;


import gameWorld.GameObject;
import gameWorld.UnitPlayer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
	Socket curPlayer = null;
	private int playerIndex = 0;


	public ServerThread(Socket clientSocket){
		System.out.println("New Server Thread Created");//debugging info
		this.socket = clientSocket;
		Main.server.playerList.add(socket.getInetAddress());//Add this Client to the List of Clients in server - This means the Logic for starting turns can be addressed easily this way
		//for receiving the character from the connected clientThread

	}



	/**
	 * This is called when the thread is started in server();
	 */
	public void run(){
		try {





			//Base this on ticks (Turns) - Send to every client on every turn. Whether you as a local player will have moved or not is based on game logic
			while(true){
				curPlayer = Server.players.get(playerIndex);
				while(curPlayer == null){
					curPlayer = Server.players.get(0);
					continue;
				}

				boardToClient = new ObjectOutputStream(curPlayer.getOutputStream()); //for outputting all the other characters to the server
				boardFromClient = new ObjectInputStream(curPlayer.getInputStream());

				boardToClient.writeObject("yourturn");//Let Client Know its now his turn
				try {
					System.out.println("Has sent turn number to client");
					while(true){
						tempStringTurn = boardFromClient.readObject(); //Wait for Incoming Token

						if(tempStringTurn != null){
							break;
						}

					}
					System.out.println("Has recieved input from client");
				}
				catch (ClassNotFoundException e1) {
					System.out.println("Was a problem with server waiting for incoming token from client, to start logic loop");
					e1.printStackTrace();
				}
				String isMyTurn = (String)tempStringTurn; //Cast the input String
				tempStringTurn = null;
				System.out.println(isMyTurn + " : This is the printed input from client");
				if(isMyTurn instanceof String && isMyTurn.equals("myturn")){//Check if its the connected clients turn (Will receive notification from client)

					System.out.println("ServerThread says Its My Turn : " + socket.getInetAddress().getHostAddress() +  " Is the IP");
					//Send GameBoard current State to Client
					gameBoardServer = Main.server.getMainGameBoard(); //Get the most updated recent copy of the gameBoard

					boardToClient.writeObject(gameBoardServer);//Write the gameboard to the client

					// Recieve updated GameBoard from client at end of turn - Should sit here waiting for the token to come in so no logic needed here
					try {
						Object tempBoard;
						while(true){
							tempBoard = boardFromClient.readObject();
							if(tempBoard != null){
								break;
							}
						}
						boardToClient.flush();//Flush output buffer, making sure we dont get overloaded with info
						gameBoardServer = (GameObject[][]) tempBoard; //Reads the UnitPlayer from the temp object, casting it as correct type and stores it
						tempBoard = null;
						Main.server.setMainGameBoard(gameBoardServer);//Update main board in Server so all threads can see it

						boardToClient.writeObject(new String("finishedmap"));
					} catch (ClassNotFoundException e) {
						System.out.println("Problem In Serverthread: Something went wrong when receiving the gameboard from the client");
						e.printStackTrace();
					}

					/**Array of LogicalTiles - Receive Here



					*
					 */
					while(true){
						try {
							Object receivedEnd = boardFromClient.readObject();
							String endTurnReceived = (String) receivedEnd;
//							Main.server.setNextTurn();
							if(receivedEnd != null){
								break;
							}


						} catch (ClassNotFoundException e) {
							e.printStackTrace();
							break;

						}
					}

					//Set next turn on

					System.out.println("Server Logic Finished");
					playerIndex = playerIndex  + 1 % Server.players.size()-1;
				}
				//Wrap up and wait for next looparound

				if(boardFromClient == null){ //If client DC's? Im not sure how to check for this.
					System.out.println("Should NOT get to here");
					return;
				}
				boardToClient.flush();
			}





		} catch (IOException e) {
			System.out.println("There was a problem with input/output to/from the server");
			e.printStackTrace();
		}





	}











}