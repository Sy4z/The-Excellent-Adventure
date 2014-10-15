package tile;

import java.io.File;

public class ClosedDoor extends DoorTile {

	public ClosedDoor(File imgPath, Character key) {
		super(imgPath, key);
		this.canMove = false;
		type = "Closed_Door";
	}

}
