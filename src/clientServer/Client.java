package clientServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	
	public Client(){
		
	}
	
	
	
	
	/**
	 * This method is in charge of initialising the client
	 * @param IP Address, Port Number
	 */
	public void runClient(String addr, int port){

		Socket clientSock = null;
		try {
			clientSock = new Socket(InetAddress.getByName(addr), port);
			ClientThread client = new ClientThread(clientSock);
			client.start();
		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}


	}
}
