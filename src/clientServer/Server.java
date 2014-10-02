package clientServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
private static int numPlayers;
public Server(int numberPlayers){
	this.numPlayers = numberPlayers;
}

	/**
	 * This method is in charge of initialising the server
	 */
	public void runServer(String addr, int port){
		ServerSocket serverSock = null;
		Socket accept = null;
		try{
			ServerThread[] serverThreads = new ServerThread[numPlayers];
			serverSock = new ServerSocket(port);
			while(true){
				System.err.println("I didnt accept connection because i suck");
				accept = serverSock.accept();
				System.out.println("Accepted Connection from: " + accept.getInetAddress());
				ServerThread server = new ServerThread(accept);
				server.start();

			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		try {
			serverSock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





}
