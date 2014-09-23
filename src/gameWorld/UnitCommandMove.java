package gameWorld;

import java.awt.Point;
import java.util.Stack;

public class UnitCommandMove extends UnitCommand{

	private Stack<Point> path;

	public UnitCommandMove(Stack<Point> path){
		this.path = path;
	}
	@Override
	public void takeAction() {
		// TODO Auto-generated method stub


		if(path.isEmpty())
			this.complete = true;

	}

}
