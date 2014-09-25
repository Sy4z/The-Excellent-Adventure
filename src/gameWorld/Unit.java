package gameWorld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;

/**
 *
 * @author ChrisMcIntosh
 *
 */
public abstract class Unit extends GameObject {
	protected Point curLocation;
	protected File filePath;
	private boolean isActiveUnit;
	private boolean standardAction;
	private boolean moveAction;
	private boolean swiftAction;

	public Unit(Point loc) {
		curLocation = loc;
	}

	/**
	 * Updates a Entities information of its location. This will be used my the
	 * move methods in Control which will update all other information related
	 * to the move and ensure it is a valid move
	 */
	public void move(Point destination) {
		curLocation = destination;
	}

	public Point getLocation() {
		return curLocation;

	}


	public void draw(Graphics2D g, int dx, int dy, int dx2, int dy2, int sx,
			int sy, int sx2, int sy2) {
//
//		BufferedImage img = null;
//		try {
//			img = ImageIO.read(filePath);
//		} catch (IOException e) {
//
//			System.err.println(e + "");
//		}
//		g.drawImage(img, dx, dy, dx2, dy2, sx, sy, sx2, sy2, null);
		System.err.println("Drawing unit");
		g.setColor(Color.CYAN);
		g.fillOval(curLocation.x, curLocation.y, 10, 10);

	}

	public void activate() {
		 this.isActiveUnit = true;

	}

	public boolean isActive() {
		return isActiveUnit;
	}

	public int avilableMoves() {
		int moves = 0;
		if(standardAction) moves++;
		if(moveAction) moves++;
		return moves;
	}

	public void depleateMoves(int movesRequired) {
		//If 2 moves are used deplete both standard and move
		if(movesRequired == 2){
			standardAction = false;
			moveAction = false;
		}
		//If one move required try to use move first and if it is already used use standard
		else if(movesRequired ==1){
			if(moveAction == false)
				standardAction = false;
			else moveAction = false;
		}

	}

}
