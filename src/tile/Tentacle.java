package tile;

import java.net.URL;

public class Tentacle extends Tile {
	public Tentacle(URL url, char key){
		super(url,key);
		this.canMove = false;
	}
}
