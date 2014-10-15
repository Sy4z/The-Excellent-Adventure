package tile;

import java.io.File;
import java.net.URL;

public class OpenDoor extends DoorTile {

	public OpenDoor(URL imgPath, Character key) {
		super(imgPath, key);
		this.canMove =true;
	}

}
