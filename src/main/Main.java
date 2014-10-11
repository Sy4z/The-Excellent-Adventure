package main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import userInterface.MainFrame;
import clientServer.Client;
import clientServer.ClientThread;
import clientServer.Server;
import clientServer.ServerThread;

/**
 * Main method, Ties the Program together
 * @author Syaz
 * honejarred@gmail.com
 */



public class Main {

	private static int numberOfPlayers = 3; //Variable for the number of players in the game
	private static boolean isServer = true; //Is this instance of the program a server
	private static String ipAddress = "127.0.0.1";

	/**
	 * Main Method to tie the whole program together.
	 * Using for networking currently.
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame loadFrame = MainFrame.createLoadingFrame();
		loadFrame.setVisible(true);

		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		loadFrame.dispose();
		MainFrame mainFrame = new MainFrame();

		Server server = new Server(numberOfPlayers);
		Client client = new Client();

		if(isServer == false){ //Sets whether this instance of the program is client or server from a boolean
			client.runClient(ipAddress, 29596);
		}
		else{
			server.runServer(ipAddress, 29596);


		}
	}


	public void setIP(String ip){
	this.ipAddress = ip;
	}



}







