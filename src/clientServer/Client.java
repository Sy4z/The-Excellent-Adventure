package clientServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * CLIENTSIDE CLASS
 * @author syaz
 * This class creates packets, and sends them to the server for processing.
 * This class can also recieve packets back from the server, and perform actions accordingly.
 */
public class Client {
	byte[] sendingData = new byte[65508]; //The maximum size that can be sent via a packet, 65.508KB
	byte[] receivedData = new byte[65508]; //The data stored here is what was received from the server
	private InetAddress address; //Ip Address to send packet to - Set at localhost for now, for testing
	DatagramPacket packet; //Packet client will send to the server
	private int port = 80; //Port server will listen on - UDP port (UDP and TCP ports are different)
	DatagramSocket socket;;


	public Client(){

		try{
			address = InetAddress.getLocalHost(); //The address of which to send the packet to
			socket = new DatagramSocket(port);
		}
		catch(UnknownHostException e){
			e.printStackTrace();
		}
		catch(SocketException f){
			f.printStackTrace();
		}

	}

	/**
	 * Method which creates a packet with what is in the bytearray
	 * After creation, sends the packet to the server immediately
	 * TODO: Will put argument to push new bytearray into this method eventually
	 */
	public void createPacket(){
		//May have to make sure sendingData is not Empty/Null.
		byte[] buffer = sendingData; //Buffer for the DatagramPacket. Contains a copy of sendingData.
		packet = new DatagramPacket(buffer, buffer.length, address, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			System.out.println("Client: Socket did not send packet correctly");
			e.printStackTrace();
		}

	}

	/**
	 * Method which receives packet from server
	 */
	public void receivePacket(){
		byte[] buffer = new byte[10]; //Buffer for the DatagramPacket.
		packet = new DatagramPacket(buffer, buffer.length); //We don't need address or port here, as we are receiving
		try {
			socket.receive(packet);
			receivedData = packet.getData();//Get data out of packet buffer and store in Local Buffer
		} catch (IOException e) {
			System.out.println("Client: There was an error with the reception of a packet");
			e.printStackTrace();
		}
	}	
	
	
/**
 * Method which sets the ip for the client to send to the server
 * int? or InetAddress?
 * @param ip
 */
	public void setIP(int ip){

	}
	
	/**
	 * Method which sets the UDP port the server will connect on
	 * @param port The port number the server is listening on
	 */
	public void setPort(int port){
		this.port = port;
	}

}





