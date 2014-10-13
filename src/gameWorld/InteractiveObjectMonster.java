package gameWorld;

import java.awt.Graphics2D;

public class InteractiveObjectMonster extends InteractiveObject{

	private int[] contents;
	private int str;
	
	/**
	 * Fight a monster if your power is greater you get it's loot
	 * Otherwise null is returned and the player will deal 
	 * with a loss accordingly.
	 * 
	 * @param Power
	 * @return
	 */
	public  int[] fight(int Power){
		if(Power > str){
			return contents;
		}
		return null;
	}
	@Override
	void draw(Graphics2D g, int dx, int dy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString(String append) {
		// TODO Auto-generated method stub
		return null;
	}

}
