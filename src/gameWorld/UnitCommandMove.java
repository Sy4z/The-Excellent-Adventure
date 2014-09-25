package gameWorld;

import java.awt.Point;
import java.util.Stack;
@Deprecated
public class UnitCommandMove extends UnitCommand{
	private Stack<Point> path;

	public UnitCommandMove(Stack<Point> path){
		this.path = path;
	}
	@Override
	public void takeAction() {
		if(path.isEmpty())
			this.complete = true;
	}

}

