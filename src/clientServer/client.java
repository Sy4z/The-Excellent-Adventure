package clientServer;

import java.io.DataInputStream;
import java.io.IOException;
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


	public client(int port, String addr){
		//this.port = port;
		//this.addr= addr;
		try {
			sock = new Socket(addr, port); //currently connecting at localhost, will need to be changed to server address

		} catch (IOException e) {

			e.printStackTrace();
		}
	}













}
