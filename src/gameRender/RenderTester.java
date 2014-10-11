package gameRender;

import gameWorld.ServiceBot;
import gameWorld.Unit;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

import javax.swing.JFrame;

import dataStorage.Data;
import dataStorage.Tuple;
import tile.Tile;

public class RenderTester extends JFrame implements KeyListener{
	private Unit entity;
	private IsoCanvas testCanvas;
	public RenderTester(int width, int height){
		super("Render Tester");
		System.out.println("Intitialising RenderTester..");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, width, height);
		testCanvas = new IsoCanvas(this.getWidth(),this.getHeight());
		add(testCanvas);
		pack();
		setResizable(true);
		setVisible(true);
		addKeyListener(this);
		this.entity = new ServiceBot(new Point(0,1), 32);
		testCanvas.initEntity(entity);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println(e.getKeyCode());
		switch(e.getKeyCode()){
		case 37://left
			Point pL = new Point(entity.getLocation().x-1,entity.getLocation().y-1);
			Stack<Point> left = new Stack<Point>();
			left.add(pL);
			testCanvas.moveUnit(entity,left);
		break;
		case 38://up
		Point pU = new Point(entity.getLocation().x-1,entity.getLocation().y+1);
		Stack<Point> up = new Stack<Point>();
		up.add(pU);
		testCanvas.moveUnit(entity,up);
		break;
		case 40://down
			Point pD = new Point(entity.getLocation().x+1,entity.getLocation().y-1);
			Stack<Point> down = new Stack<Point>();
			down.add(pD);
			testCanvas.moveUnit(entity,down);
			break;


		case 39: //right
			Point pR = new Point(entity.getLocation().x+1,entity.getLocation().y+1);
			Stack<Point> right = new Stack<Point>();
			right.add(pR);
			testCanvas.moveUnit(entity,right);
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
