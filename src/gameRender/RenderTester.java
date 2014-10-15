package gameRender;

import gameWorld.UnitCursor;
import gameWorld.UnitPlayer;
import gameWorld.Unit;
import gameWorld.World;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayDeque;
import java.util.Stack;

import javax.swing.JFrame;

import dataStorage.Data;
import dataStorage.Tuple;
import tile.Tile;

public class RenderTester extends JFrame implements KeyListener{
	private Unit entity;
	private IsoCanvas testCanvas;
	private World w;
	Point c = new Point(1,0);
	public RenderTester(int width, int height){
		super("Render Tester");
		System.out.println("Intitialising RenderTester..");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width,height);
		testCanvas = new IsoCanvas(this.getWidth(),this.getHeight());
		add(testCanvas);
		
		setResizable(false);

		setVisible(true);
		addKeyListener(this);
		this.entity = new UnitPlayer(new Point(25,8), 32);
		testCanvas.initEntity(entity);
		 //w = new World("",-1,-1, testCanvas);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		switch(e.getKeyCode()){
		case 37://left
			//Point pL = new Point(entity.getLocation().x-1,entity.getLocation().y-1);
			//ArrayDeque<Point> left = new ArrayDeque<Point>();
			//left.add(pL);
			//testCanvas.moveUnit(entity,left);
			//c.setLocation(c.getX()-1, c.getY());
			//UnitCursor cur = new UnitCursor(c, 1234);
			//testCanvas.moveCursor(cur);
			//w.moveFromKeyBoard(2);
			testCanvas.east();
			break;
		case 38://up
		//Point pU = new Point(entity.getLocation().x-1,entity.getLocation().y+1);
		//ArrayDeque<Point> up = new ArrayDeque<Point>();
		//up.add(pU);
		//testCanvas.moveUnit(entity,up);
			testCanvas.north();
		//	w.moveFromKeyBoard(0);
		break;
		case 40://down
			//Point pD = new Point(entity.getLocation().x+1,entity.getLocation().y-1);
			//Stack<Point> down = new Stack<Point>();
			//down.add(pD);
			//testCanvas.moveUnit(entity,down);
			//w.moveFromKeyBoard(1);
			testCanvas.south();
			break;


		case 39: //right
			//Point pR = new Point(entity.getLocation().x+1,entity.getLocation().y+1);
			//ArrayDeque<Point> right = new ArrayDeque<Point>();
			//right.add(pR);
			//testCanvas.moveUnit(entity,right);
				testCanvas.west();
			//c.setLocation(c.getX()+1, c.getY());
			//UnitCursor curR = new UnitCursor(c, 1234);
			//testCanvas.moveCursor(curR);
			//w.moveFromKeyBoard(3);
			break;
		case 10: 
		w.moveToCursor();
		break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	public static void main(String args[]){
		RenderTester rt = new RenderTester(800,600);
	}

}
