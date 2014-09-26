package clientServer;

import java.io.IOException;
import java.net.ServerSocket;


/**
 *@author syaz
 *Class which will create an instance of a server
 *If this program instance is an instance of a client, will not start server,
 *Will deal with client instead.
 *
 */


public class RunNetwork {


	public static void main(String[] args) {
		makeServer();
		Client client = new Client(); //Client will never be created for the server to accept. Will have to do this another way probably
	
		
	
		
	}
	
	
	public static void makeServer(){
		ServerSocket serverSock = null;
		try{
			serverSock = new ServerSocket(10008);
			System.out.println("Server Socket created at port: 10008");
			try{
				while(true){
					new Server(serverSock.accept());
				}
			}
			catch(IOException e){
				e.printStackTrace();
				
			}
		}
		catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		finally{
			try{
				serverSock.close();
			}
			catch(IOException e){
				e.printStackTrace();
				System.exit(1);
				
			}
		}
	}

}
