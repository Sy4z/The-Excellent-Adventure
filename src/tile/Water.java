package tile;

import java.net.URL;

public class Water extends Tile {
	public Water(URL url, char key){
		super(url, key);
		this.canMove = false;
	}
}
