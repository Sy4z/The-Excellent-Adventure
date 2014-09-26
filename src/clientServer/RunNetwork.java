package clientServer;


/**
 *@author syaz
 *Class which will create an instance of a server
 *If this program instance is an instance of a client, will not start server,
 *Will deal with client instead.
 *
 */


public class RunNetwork {


	public static void main(String[] args) {
		Client client = new Client();
	
		Server server = new Server(client.getSock());//possibly shouldnt be grabbing the client socket for this
	
		
	}

}
