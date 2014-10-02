package gameWorld;

import java.awt.Graphics2D;

public class StationaryObjectHatStand extends StationaryObject{

	@Override
	void draw(Graphics2D g, int dx, int dy) {
		// TODO Auto-generated method stub

	}

	public String toString(){
		return toString("");
	}

	@Override
	public String toString(String append) {
		return "This is a Hat stand, if I had a hat I could put it on here.";
	}


}
