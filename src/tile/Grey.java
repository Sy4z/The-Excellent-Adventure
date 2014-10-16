package tile;

import java.net.URL;

public class Grey extends Tile {
	public Grey(URL url, char key){
		super(url,key);
		this.canMove = true;
	}
}
