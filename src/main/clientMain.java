package main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import clientServer.Client;

public class clientMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		runClient("127.0.0.1", 29596);

	}



	public static void runClient(String addr, int port){

		Socket clientSock = null;
		try {
			clientSock = new Socket(InetAddress.getByName(addr), port);
			Client client = new Client(clientSock);
			client.start();
		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}


	}

}
