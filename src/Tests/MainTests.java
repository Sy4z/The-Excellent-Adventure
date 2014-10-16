package Tests;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

import clientServer.Server;
import runGame.Main;

/**
 * Tests for the Main Class
 * @author Jarred
 */


public class MainTests {





	public @Test void testSetPlayers(){

		Main.setNumberOfPlayers(4);
		assertTrue(Main.getNumberOfPlayers() == 4);
	}


	public @Test void testIsServer(){
		Main.isServer(false);
		assertTrue(Main.isServer == false);
	}

	public @Test void testStartServer(){
		try {
			InetAddress tempAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {

			e.printStackTrace();
		}
		Server server = new Server( 43252);
		try{
			Main.runServerMain();
		}
		catch(NullPointerException e){
			assert(e instanceof NullPointerException);
		}

	}

	public @Test void testSetip(){
		Main.setIP("127.0.0.1");
	}




}
