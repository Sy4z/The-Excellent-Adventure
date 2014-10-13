package runGame;

import gameWorld.World;

public class TurnWatcher {
public World world;

	public TurnWatcher(World world){
		this.world = world;
	}

	public boolean turn(){
		world.startTurn();

		while(world.checkPlayerStatus()){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
}
