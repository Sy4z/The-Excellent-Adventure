package clientServer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * CLIENTSIDE CLASS
 * @author syaz
 * runs via TCP
 * This class creates packets, and sends them to the server for processing.
 * This class can also receive packets back from the server, and perform actions accordingly.
 */
public class Client extends Thread {
	Socket sock;
	private DataOutputStream toServer;
	private DataInputStream fromServer;
	public Client(Socket socket){
		this.sock = socket;
	}


	public void run(){
		try {
			toServer = new DataOutputStream(sock.getOutputStream());
			fromServer = new DataInputStream(sock.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}











