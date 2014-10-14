package dataStorage;

import gameRender.IsoCanvas;
import gameWorld.GameObject;
import gameWorld.Inventory;
import gameWorld.LogicalTile;
import gameWorld.UnitPlayer;
import gameWorld.Unit;
import gameWorld.World;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;

import runGame.Main;
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

	private static void error(String s){
		System.err.println(s);
	}

	public static Tuple load(String f) throws UnexpectedException{
		//initialise the document
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		File xmlFile = new File(f+ File.separatorChar + "data");
		//load the XML file which represents the state of the game
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document w3cDoc = dBuilder.parse(xmlFile);

			DOMBuilder domBuilder = new DOMBuilder();
			doc = domBuilder.build(w3cDoc);
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("File not found");
		}


		Element root = doc.getRootElement();
		Element tilesNode = root.getChild("Tiles");
		for(Element e : tilesNode.getChildren()){

			TileMultiton.getTile(TileMultiton.getTypeByRepresentation(e.getName().charAt(0)));

		}

		int i = Integer.parseInt(tilesNode.getAttributeValue("X"));
		int j = Integer.parseInt(tilesNode.getAttributeValue("Y"));

		LogicalTile[][] lTiles = new LogicalTile[i][j];
		TileMultiton.type[][] tiles = new TileMultiton.type[i][j];
		GameObject[][] gObjs = new GameObject[i][j];

		i = 0;
		j = 0;

		try {
			Scanner tileMapScanner = new Scanner(new File(f + File.separatorChar + "map"));

			String curChar = "";
			TileMultiton.type curTile = null;

			while(tileMapScanner.hasNext()){
				curChar = tileMapScanner.next();
				while(curChar != ";"){
					curTile = TileMultiton.getTypeByRepresentation(curChar.charAt(0));

					tiles[i][j] = curTile;
					lTiles[i][j] = new LogicalTile(TileMultiton.getTile(curTile).getCanMove());
					j++;
					curChar = tileMapScanner.next();
				}
				i++;
				j = 0;
			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			return null;
		}

		for(Element e: root.getChild("GameObject").getChildren()){
			switch(e.getName()){
			case "UnitPlayer": HandleLoadUnitPlayer(e);break;
			case "StationaryObjectWall": HandleLoadStationaryObjectWall(e); break;
			case "StationaryObjectHatStand" : HandleLoadStationaryObjectHatStand(e);break;
			default:throw new UnexpectedException("unexpected child found in XML tree" + e.getName());
			}
		}

		return new Tuple(null, null);
	}

	private static void HandleLoadStationaryObjectHatStand(Element e) {
		// TODO Auto-generated method stub

	}

	private static void HandleLoadStationaryObjectWall(Element e) {
		// TODO Auto-generated method stub

	}

	private static GameObject HandleLoadUnitPlayer(Element e) {
		Point point = new Point(Integer.parseInt(e.getChild("curLocation").getChildText("X")),
							Integer.parseInt(e.getChild("curLocation").getChildText("Y")));
		UnitPlayer p = new UnitPlayer(point, Integer.parseInt(e.getChildText("ID")));



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
	public static boolean save(String fileName) throws UnexpectedException{
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

		//Get the types array from isoCanvas via reflection
		TileMultiton.type[][] types = null;

		try {
			Field tileMapField = IsoCanvas.class.getDeclaredField("map");
			tileMapField.setAccessible(true);
			types =(TileMultiton.type[][]) tileMapField.get(Main.cvs);
			tileMapField.setAccessible(false);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

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
			tileMap += ";"
					+ "";
		}
		root.addContent(subRoot);

		File tileMapPath = new File(savePath.toString() + File.separator + "map" );
		PrintStream print = null;
		try {
			print = new PrintStream(tileMapPath);
			print.print(tileMap);
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

		GameObject[][] gameObjs = null;
		World world = Main.world;
		try {
			Field gameObjsField = world.getClass().getDeclaredField("gameBoard");
			gameObjsField.setAccessible(true);
			gameObjs = (GameObject[][]) gameObjsField.get(world);
//			world.getClass().getDeclaredField("gameBoard").setAccessible(false);
		} catch (SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}


		//---------Handle GameObjects---------
		subRoot = new Element("GameObject");
		subRoot.setAttribute("Size",gameObjs.length+"");
		//for each unit, create a new element, tie everything about the unit to it, and add it to the tree
		Scanner scan = null;
		Scanner curLine = null;
		boolean accesible = false;

//		Class[] classList = new Class[10];
		Element fieldElem = null;
		List<Class> classList = new ArrayList<Class>();

		for(GameObject[] objs : gameObjs){
			for(GameObject e : objs){
//				error(e+"");

				if(e != null){
					elem = new Element(e.getClass().getSimpleName());
					classList.add(e.getClass());

					while(classList.get(classList.size()-1).getSuperclass() != (Object.class)){
						classList.add(classList.get(classList.size()-1).getSuperclass());
					}
					error(classList.toString());

					for(Class c : classList){
						error("---Iterating " + c.getCanonicalName()+ "---");
						for(Field f : c.getDeclaredFields()){
							fieldElem = null;
							accesible = f.isAccessible();
							f.setAccessible(true);

							switch(f.getType().getSimpleName()){

							case "BufferedImage": break;
							case "int": 		fieldElem = handleIntField(f,e); break;
							case "Inventory": 	fieldElem = handleInventoryField(f,e);break;
							case "Point": 		fieldElem = handlePointField(f,e); break;
							case "File": 		fieldElem = handleFileField(f,e); break;
							case "boolean":		fieldElem = handlebooleanField(f,e); break;
							case "String" : 	fieldElem = handleStringField(f,e); break;
							default:
								error("No saving controls found for objects of type: " + f.getType().getSimpleName() + "\n\tField will not be saved");
								break;
							}

							if(fieldElem != null){
								elem.addContent(fieldElem);
							}

							f.setAccessible(accesible);
						}


					}
					subRoot.addContent(elem);
					classList = new ArrayList<Class>();
				}
			}
		}
		root.addContent(subRoot);

		//----------Output the XML--------
		XMLOutputter xmlOut = new XMLOutputter();
		xmlOut.setFormat(Format.getPrettyFormat());
		document.addContent(root);

		System.out.println(xmlOut.outputString(document));
		File XMLPath = new File(savePath.toString() + File.separator + "data");
		try {
			print = new PrintStream(XMLPath);
			print.print(xmlOut.outputString(document));
		} catch (FileNotFoundException e) {
			try {
				XMLPath.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		finally{
			print.close();
		}
		System.out.println("Saving over");
		return true;
	}


	private static Element handleFieldField(Field f, GameObject instance){
		Element elem = new Element(f.getName());
		try {
			elem.addContent(f.get(instance) + "");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return elem;
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

			Element subElement = null;
			for(Inventory.itemTypes type : Inventory.itemTypes.values()){

				subElement = new Element(type.name());
				subElement.addContent(i.numberOfItem(type)+"");
				elem.addContent(subElement);

			}
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

	/**
	 * -----STANDIN WHILE I STUDY XML------
	 * @param fi Use null the File shall be ignored
	 *
	 * Returns a 2D array of Tiles for testing purposes, feel free to mess with
	 * @return A 2D Array of Tile
	 * @throws FileNotFoundException
	 */
	public static Tuple testSet(File fi){

		int sizeX = 100;
		int sizeY = 100;
		int entityX = 6;
		int entityY = 6;

		TileMultiton.type[][] t = new TileMultiton.type[sizeY][sizeX];

		//Creates an array of tiles sizeX by sizeY if statement specifys what coordinate entity will be placed.

		for(int y = 0;y<sizeY;y++){
			for(int x = 0;x<sizeX;x++){
				if(y == entityY && x == entityX  ){
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
}
