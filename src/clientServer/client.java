package clientServer;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Client class, connects to server via socket. If no server can be found, connect via localhost.
 * @author syaz
 *
 */



public class client {

	Socket sock;
	int port = 32768;
	String addr = "localhost";
	DataInputStream input;
	PrintStream output; //Has only partial primitive data support, may want to use dataOutputStream ontop of this (Or instead of)

	/**
	 * Will move most of the calls in the constructor into methods
	 * @param port Port that the sockets connects through
	 * @param addr Address that the Socket connects through
	 */
	public client(int port, String addr){
		//this.port = port;
		//this.addr= addr;
		try {
			sock = new Socket(addr, port); //currently connecting at localhost, will need to be changed to server address
			//Maybe check for connection? Possibly might not need to for UDP
			input = new DataInputStream(sock.getInputStream());
			output = new PrintStream(sock.getOutputStream());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}













}
