package runGame;

import gameWorld.World;


/**
 * Class which controls turns in the game. Network package controls when to start turns, and when to properly end them
 * @author Jarred and Chris
 *
 */



public class TurnWatcher extends Thread{
	public World world;
	public static boolean endOfEndPhase = false; //Signifies end of end Phase, so the next turn can switch
	public static boolean startOfEndPhase = false;
	public static boolean isEndPhase = false;
	public TurnWatcher(World world){
		this.world = world;
	}

	public void run(){
		while(this.isTurn()){

			try {

				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			if(!isTurn()){
				System.out.println("Turn has ended but is not looping around a last time and is setting variable");
				isEndPhase = true;
			}
		}
		if(isEndPhase){
			System.out.println("End Phase Started");
		startOfEndPhase = true;
		while(!endPhase())//This is just to make the turn stop until the client decides to send the data to the server

		endOfEndPhase = false; //Change to null so that next time turn is called, its going to start the end phase again
		isEndPhase = false; //Makes this go bback to false
		}
		}
	/**
	 * Method which controls the gameturns. Starts the turn in gamelogic, and is controlled by network
	 * @return always returns true, will only return true when the entire turn is finished though.
	 *
	 */
	public boolean turn(){
		System.err.println("Start of turn");
		world.startTurn();

		//When this loop breaks, turn is over

		return true; //All the networking data has now been sent, the ClientThread has given the OK, end the turn properly.
	}


	/**
	 * Checker for whether it is currently the local players turn
	 * For calling without actually starting
	 * @return
	 */
	public boolean isTurn(){
		return world.isTurn();
	}


	/**
	 * Method to deal with the endPhase in which the client sends the data to the server
	 * @return true after a wait if the client has told this class it is sending data, false if the client has not begun sending yet
	 * @author syaz
	 */
	public boolean endPhase(){
//		System.err.println("Entered endPhase");
		if(endOfEndPhase == true){
			try {
				System.err.println("Sleeping in endPhase");
				Thread.sleep(1500); //This is the time given for the client to send the data to the server
			} catch (InterruptedException e) {
				System.out.println("Thread could not be slept");
				e.printStackTrace();
			}
			System.err.println("true");
			return true;

		}
		else{
			return false;
		}


	}


}
