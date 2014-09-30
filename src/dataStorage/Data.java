package dataStorage;

import gameRender.IsoCanvas;
import gameWorld.Item;
import gameWorld.ServiceBot;
import gameWorld.Unit;

import java.awt.Point;
import java.io.File;

import javax.swing.JFrame;

import org.jdom2.Attribute;
import org.jdom2.Comment;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import tile.*;
import tile.TileMultiton.type;

/**
 * handles all data saving and loading needs.
 * @author 	Dylan Macdonald,
 * 			macdondyla1
 * 			300282068
 * 			dylan4823@gmail.com
 *
 */
public class Data {
	static int b = 0;
	public Data(){}

	public static Tuple load(File f){
		return new Tuple(null, null);
	}

	/**
	 * Converts the game data into an XML format, and saves that to the local directory
	 *
	 * @param types Your map of the world
	 * @param units An array containing all of the units the are to be saved
	 * @param items An array containing all of the units that are to be saved
	 * @return True is successful, else false.
	 */
	public static boolean save(TileMultiton.type[][] types, Unit[] units, Item[] items){
		assert(types != null);
		assert(units != null);
		assert(items != null);

		//Initialise the document
		Document document = new Document();
		//initialise the root node
		Element root = new Element("World");

		//-------Handle Tiles--------
		//create the tile root
		Element subRoot = new Element("Tiles");
		//give the subroot the dimensions of the tile array
		subRoot.setAttribute("X", Integer.toString(types.length));
		subRoot.setAttribute("Y", Integer.toString(types[1].length));

		Element elem;
		String tileMap = "";
		//For each element in tiles, if the tiles are not already in the tree, add them,
		//else add the char for the tile to the tileMap, and continue onwards
		char tileRepresentation;
		for(type[] j : types){
			for(type i : j){
				tileRepresentation = TileMultiton.getTile(i).getRepresentation();
				//if the child is in the XML, jump it do not add it, otherwise create a new element
				if(subRoot.getChild(tileRepresentation+"") == null){
					elem = new Element(tileRepresentation+"");
					elem.setAttribute("Type",i.name());
					subRoot.addContent(elem);
				}
				//Add the tile key to the map
				tileMap += tileRepresentation + " ";
			}
			tileMap += "\n";
		}
		root.addContent(subRoot);


		//---------Handle Units---------
		subRoot = new Element("Units");
		subRoot.setAttribute("Size", Integer.toString(units.length));
		//for each unit, create a new element, tie everything about the unit to it, and add it to the tree
		String[] test = {"curLocation", "filePath", "isActiveUnit",
				"standardAction","moveAction","swiftAction"};
		int i = 0;
		for(Unit e : units){
			elem = new Element(e.getClass().getSimpleName());
			for(Object o : e.save()){
				System.out.println(elem.toString() + "\n\t" + o);
				if(o == null){
					elem.setAttribute(test[i], "NULL");
				}
				else{

					elem.setAttribute(o.getClass().getSimpleName(), o + "");
				}
				i++;
			}
			i=0;
			subRoot.addContent(elem);
		}
		root.addContent(subRoot);


		//----------Handle Items--------
		subRoot = new Element("Items");
		subRoot.setAttribute("Size", Integer.toString(items.length));

		for(Item e : items){
			elem = new Element(e.getClass().getSimpleName());
			for(Object o : e.save()){
				elem.setAttribute(o.getClass().getSimpleName(), o.toString());
			}
			subRoot.addContent(elem);
		}
		root.addContent(subRoot);

		//----------Output the XML--------
		XMLOutputter xmlOut = new XMLOutputter();
		xmlOut.setFormat(Format.getPrettyFormat());
		document.addContent(root);

		System.out.println(xmlOut.outputString(document));
		System.out.println("Saving over");
		return true;
	}

	/**
	 * -----STANDIN WHILE I STUDY XML------
	 * @param fi Use null the File shall be ignored
	 *
	 * Returns a 2D array of Tiles for testing purposes, feel free to mess with
	 * @return A 2D Array of Tile
	 * @throws FileNotFoundException
	 */
	public static Tuple testSet(File fi){

		int sizeX = 11;
		int sizeY = 11;
		int entityX = 0;
		int entityY = 0;

		TileMultiton.type[][] t = new TileMultiton.type[sizeY][sizeX];
		//Creates an array of tiles sizeX by sizeY if statement specifys what coordinate entity will be placed.
		for(int y = 0;y<sizeY;y++){
			for(int x = 0;x<sizeX;x++){
				if(x == y + b){
					t[y][x] = TileMultiton.type.BLUE;
				}
				else{
					t[y][x] = TileMultiton.type.FLOOR;
				}
			}
		}

		Unit[] u = new ServiceBot[7];

		for(int i = 0; i < 7; i++){
			u[i] = new ServiceBot(new Point(i,i));
		}
		if(b++ > 9){
			b = -9;
		}
		return new Tuple(t,u);
	}

	public static void main(String args[]){
//		System.out.println("Beginning test");
//		Tuple t = testSet(null);
//		save(t.tiles, t.units, new Item[0]);
		RenderingTest();
	}

	private static void RenderingTest(){
		JFrame j = new JFrame();
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setBounds(30, 20, 600	, 480);
		IsoCanvas c = new IsoCanvas(j.getWidth(), j.getHeight());
		j.add(c);
		j.setVisible(true);
		while(true){
			c.update(testSet(null));
			try{
			Thread.sleep(300);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
