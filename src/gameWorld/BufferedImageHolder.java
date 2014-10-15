package gameWorld;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author macdondyla1
 *
 */
public class BufferedImageHolder {
	static Map<String,BufferedImage> tiles = new HashMap<String, BufferedImage>();

	public static BufferedImage getimage(String img){
		return tiles.get(img);
	}

	public static void addImage(BufferedImage newImg, String key){
		tiles.put(key, newImg);
	}

}
