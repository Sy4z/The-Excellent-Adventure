package clientServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * CLIENTSIDE Class
 * This class is an instance of the client, which passes off new connections to a clientThread.
 * ClientThread does most of the client logic, this is just a wrapper to deal with creating the threads.
 * This class will start if this instance of the program is signified to be a client, rather than a server in the main method
 * This cannot run at the same time as a server.
 * @author Syaz
 *
 */
public class Client {


	public Client(){
		//Needs no constructor Logic currently
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


		} catch (IOException e) {

			e.printStackTrace();
		}


	}
}
