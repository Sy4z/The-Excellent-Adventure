package runGame;

import gameWorld.World;


/**
 * Class which controls turns in the game. Network package controls when to start turns, and when to properly end them
 * @author Jarred and Chris
 *
 */



public class TurnWatcher {
public World world;
public boolean endOfEndPhase = false; //Signifies end of end Phase, so the next turn can switch
	public TurnWatcher(World world){
		this.world = world;
	}

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

		while(!endPhase()){
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				System.out.println("End phase Thread did not sleep");
				e.printStackTrace();
			}
		}

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


	public boolean endPhase(){
	if(endOfEndPhase == false){
		return false;

	}
	else{
		return true;
	}


	}


}
