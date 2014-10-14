package gameWorld;

import java.awt.Graphics2D;
import java.awt.Point;

public class StationaryObjectHatStand extends StationaryObject{

	public StationaryObjectHatStand(Point p){
		super(p);
	}

	@Override
	void draw(Graphics2D g, int dx, int dy) {
		// TODO Auto-generated method stub

	}

	public String toString(){
		return toString("");
	}


	public String toString(String append){
		StringBuilder s = new StringBuilder("Hat Stand{");
		append = "|\n" + append;

		return s + "\n" + append.substring(2) + "}";
	}

}
