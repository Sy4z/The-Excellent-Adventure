package tile;

import java.io.File;
import java.net.URL;

public class ClosedDoor extends DoorTile {

	public ClosedDoor(URL imgPath, Character key) {
		super(imgPath, key);
		this.canMove = false;
		type = "Closed_Door";
	}

}
