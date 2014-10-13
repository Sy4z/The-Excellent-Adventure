package runGame;

import gameWorld.World;


/**
 * Class which controls turns in the game. Network package controls when to start turns, and when to properly end them
 * @author Jarred and Chris
 *
 */



public class TurnWatcher {
	public World world;
	public static boolean endOfEndPhase = false; //Signifies end of end Phase, so the next turn can switch
	public TurnWatcher(World world){
		this.world = world;
	}


	/**
	 * Method which controls the gameturns. Starts the turn in gamelogic, and is controlled by network
	 * @return always returns true, will only return true when the entire turn is finished though.
	 *
	 */
	public boolean turn(){
		world.startTurn();

		//When this loop breaks, turn is over
		while(this.isTurn()){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		while(!endPhase()); //This is just to make the turn stop until the client decides to send the data to the server
		endOfEndPhase = false; //Change to false to that next time turn is called, its going to start the end phase again
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
		if(endOfEndPhase == true){
			try {
				Thread.sleep(1500); //This is the time given for the client to send the data to the server
			} catch (InterruptedException e) {
				System.out.println("Thread could not be slept");
				e.printStackTrace();
			}
			return true;

		}
		else{
			return false;
		}


	}


}
