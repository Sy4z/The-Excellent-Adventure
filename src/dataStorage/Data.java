package dataStorage;

import gameRender.IsoCanvas;
import gameWorld.GameObject;
import gameWorld.Inventory;
import gameWorld.Item;
import gameWorld.UnitPlayer;
import gameWorld.Unit;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import org.jdom2.Attribute;
import org.jdom2.Comment;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import sun.reflect.Reflection;
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

	private static void error(String s){
//		System.err.println(s);
	}

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
	 * @throws UnexpectedException
	 */
	public static boolean save(String fileName, TileMultiton.type[][] types, Unit[] units, Item[] items) throws UnexpectedException{
		assert(types 	!= 	null);
		assert(units 	!= 	null);
		assert(items 	!= 	null);
		assert(fileName != 	null);

		//Initialise the document
		Document document = new Document();

		//initialise the root node
		Element root = new Element("World");
		File savePath = new File("saves" + File.separatorChar + fileName);

		System.out.println(savePath);

		if(!savePath.exists()){
			error("Making directory");
			savePath.mkdirs();
		}

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

		File tileMapPath = new File(savePath.toString() + File.separator + "map" );
		PrintStream print = null;
		try {
			print = new PrintStream(tileMapPath);
			//print.print(tileMap);
		} catch (FileNotFoundException e) {
			try {
				tileMapPath.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		finally{
			print.close();
		}

		//---------Handle Units---------
		subRoot = new Element("Units");
		subRoot.setAttribute("Size", Integer.toString(units.length));
		//for each unit, create a new element, tie everything about the unit to it, and add it to the tree
		Scanner scan = null;
		Scanner curLine = null;
		boolean accesible = false;

//		Class[] classList = new Class[10];
		Element fieldElem = null;
		List<Class> classList = new ArrayList<Class>();
		for(Unit e : units){
			elem = new Element(e.getClass().getSimpleName());
			classList.add(e.getClass());

			while(classList.get(classList.size()-1).getSuperclass() != (Object.class)){
				classList.add(classList.get(classList.size()-1).getSuperclass());
			}

			for(Class c : classList){
				error("---Iterating " + c.getCanonicalName()+ "---");
				for(Field f : c.getDeclaredFields()){
					fieldElem = null;
					accesible = f.isAccessible();
					f.setAccessible(true);
					Object o = f;
//					error(f.getName() +" " + f.getType().getSimpleName());
					switch(f.getType().getSimpleName()){
					case "BufferedImage": break;
					case "int": fieldElem = handleIntField(f,e); break;
					case "Inventory": fieldElem = handleInventoryField(f,e);break;
					case "Point": fieldElem = handlePointField(f,e); break;
					case "File": fieldElem = handleFileField(f,e); break;
					case "boolean":fieldElem = handlebooleanField(f,e); break;
					case "String" : fieldElem = handleStringField(f,e); break;
					default:throw new UnexpectedException("SAVING: Unexpected field type in unit: +"+ f.getName() + " " + f.getType().getSimpleName());
					}
					if(fieldElem != null){
						elem.addContent(fieldElem);
					}

					f.setAccessible(accesible);
				}
			}
		}
		root.addContent(subRoot);


		//----------Handle Items--------
//		subRoot = new Element("Items");
//		subRoot.setAttribute("Size", Integer.toString(items.length));
//
//		for(Item e : items){
//			elem = new Element(e.getClass().getSimpleName());
//			for(Object o : e.save()){
//				elem.setAttribute(o.getClass().getSimpleName(), o.toString());
//			}
//			subRoot.addContent(elem);
//		}
//		root.addContent(subRoot);

		//----------Output the XML--------
		XMLOutputter xmlOut = new XMLOutputter();
		xmlOut.setFormat(Format.getPrettyFormat());
		document.addContent(root);

		System.out.println(xmlOut.outputString(document));
		System.out.println("Saving over");
		return true;
	}



	private static Element handleFileField(Field f, GameObject instance) {
		Element elem = new Element(f.getName());
		try {
			File fi = (File) f.get(instance);
			elem.addContent(fi.getPath());
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return elem;
	}

	private static Element handleStringField(Field f, GameObject instance) {
		Element elem = new Element(f.getName());
		try {
			elem.addContent(f.get(instance)+"");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return elem;
	}

	private static Element handlebooleanField(Field f,GameObject instance) {
		Element elem = new Element(f.getName());
		try {
			elem.addContent(f.getBoolean(instance)+"");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return elem;
	}

	private static Element handlePointField(Field f, GameObject instance) {
		Element elem = new Element(f.getName());
		try {
			Point p = (Point) f.get(instance);
			Element subElement = new Element("X");
			subElement.addContent(p.x+"");
			elem.addContent(subElement);

			subElement = new Element("Y");
			subElement.addContent(p.y+"");
			elem.addContent(subElement);

		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return elem;
	}

	private static Element handleInventoryField(Field f, GameObject instance) {
		Element elem = new Element(f.getName());
		try {
			Inventory i = (Inventory) f.get(instance);
			//TODO
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return elem;
	}

	private static Element handleIntField(Field f,GameObject instance) {
		Element elem = new Element(f.getName());
		try {
			elem.addContent(f.getInt(instance)+"");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return elem;
	}


	private static String parseUnitString(String unitdata) {
		Scanner scan = new Scanner(unitdata);
		Element e = new Element(scan.next());

		while(scan.hasNext()){
			switch(scan.next()){
			case "{":scan.next();continue;
			case "}":scan.next();continue;
			case ":":scan.next();continue;
			case "|":scan.next();continue;
			case "Inventory" : parseInventory(e,scan);continue;
			default: parseUnitLine(e,scan.nextLine());continue;
			}
		}
		return null;
	}

	private static void parseInventory(Element e, Scanner scan) {


	}

	private static void parseUnitLine(Element e, String ln){
		int idx = 0;
		int lookahead = 0;
		while(!Character.isAlphabetic(ln.charAt(idx))){
			idx++;
		}

		lookahead = idx;
		while(ln.charAt(lookahead) != ':'){
			lookahead++;
		}

		String attribName = ln.substring(idx, lookahead);
		lookahead += 2;
		idx = lookahead;

		while(ln.charAt(lookahead) != '\n'){
			lookahead++;
		}

		String attribValue = ln.substring(idx, lookahead);
		e.setAttribute(new Attribute(attribName, attribValue));
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
		error("Beginning test");
		TileMultiton.type[][] t = new TileMultiton.type[sizeY][sizeX];

		//Creates an array of tiles sizeX by sizeY if statement specifys what coordinate entity will be placed.

		for(int y = 0;y<sizeY;y++){
			for(int x = 0;x<sizeX;x++){
				if(y == x + b ){
					t[y][x] = TileMultiton.type.BLUE;
				}
				else{
					t[y][x] = TileMultiton.type.FLOOR;
				}
			}
		}

		Unit[] u = new UnitPlayer[7];

		for(int i = 0; i < 7; i++){
			u[i] = new UnitPlayer(new Point(i,i),i);
		}

		if(b++ > 9){
			b = -9;
		}
		return new Tuple(t,u);
	}

	public static void main(String args[]){
		Tuple t = testSet(null);
		try {
			save("test",t.tiles, t.units, new Item[0]);
		} catch (UnexpectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		RenderingTest();

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
