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
import runGame.TurnWatcher;
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
	private static boolean testing = false;

	/**
	 * Error checking method, Prints the given string to Stderr
	 * @param s The string to be printed
	 */
	public static void error(String s){
		if(testing){
			System.err.println(s);
		}
	}
	/**
	 * Loads the game state from a given save location
	 *
	 * Builds a tileMap, logicalTileMap and GameObject array
	 *
	 * @param SaveFile the file to be loaded
	 * @throws UnexpectedException An unexpected exception is thrown when there is an unknown field in a class, this is mostly to remind the programmer that they need to update their load method
	 */
	public static void load(String SaveFile) throws UnexpectedException{
		//initialise the document
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		error("Loading has begun");
		File xmlFile = new File("saves"+ File.separatorChar+ SaveFile + File.separatorChar + "data");
		//load the XML file which represents the state of the game

		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document w3cDoc = dBuilder.parse(xmlFile);

			DOMBuilder domBuilder = new DOMBuilder();
			doc = domBuilder.build(w3cDoc);
			error("Doc built");
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("File not found");
		}

		error("XMLDoctree exists");

		//------Preload all tile types used in this map------
		Element root = doc.getRootElement();
		Element tilesNode = root.getChild("Tiles");

		error("Got Tiles");

		int playerID = 0;
		if(root.getChild("LocalID")!= null){
			playerID = Integer.parseInt(root.getChild("LocalID").getAttribute("ID").getValue());
		}


		for(Element e : tilesNode.getChildren()){
			error(e.getName().charAt(0)+"");
			TileMultiton.getTile(TileMultiton.getTypeByRepresentation(e.getName().charAt(0)));

		}

		error("Tiles loaded");

		//get the size of the map from the xml tree
		int i = Integer.parseInt(tilesNode.getAttributeValue("X"));
		int j = Integer.parseInt(tilesNode.getAttributeValue("Y"));

		//initialise the logicalTile, tile and gameobject arrays
		LogicalTile[][] lTiles = new LogicalTile[i][j];
		TileMultiton.type[][] tiles = new TileMultiton.type[i][j];
		GameObject[][] gameObjectArray = new GameObject[i][j];

		//--------read in the tilemap------
		i = 0;
		j = 0;

		error("Entering tileMap try-catch");
		try {
			//read in the file map located at save/#file#/map
			Scanner tileMapScanner = new Scanner(new File("saves" + File.separatorChar + SaveFile + File.separatorChar + "map"));

			String curChar = "";
			TileMultiton.type curTile = null;
			//for each char in the map, find the given representation in the tile multiton
			while(tileMapScanner.hasNext()){
				curChar = tileMapScanner.next();
				while(curChar.charAt(0) != ';'){
					curTile = TileMultiton.getTypeByRepresentation(curChar.charAt(0));
					tiles[i][j] = curTile;
					lTiles[i][j] = new LogicalTile(TileMultiton.getTile(curTile).getCanMove());
					j++;
					curChar = tileMapScanner.next();
				}
				i++;
				j = 0;
			}
			tileMapScanner.close();

		} catch (FileNotFoundException e1) {
			error("-----File Not found-----");
			return;
		}
		error("Tilemap built");

		//------Load in the game objects------
		GameObject tempObj = null;
		for(Element e: root.getChild("GameObject").getChildren()){

			switch(e.getName()){
			case "UnitPlayer": tempObj = HandleLoadUnitPlayer(e);break;//Load a player
			case "StationaryObjectWall": tempObj = HandleLoadStationaryObjectWall(e); break; //load a wall
			case "StationaryObjectHatStand" : tempObj = HandleLoadStationaryObjectHatStand(e);break; //load a hat stand
			default:throw new UnexpectedException("unexpected child found in XML tree" + e.getName()); //unexpected child
			}

			//--Find the correct position to place this is the array--
			Field tempField;
			try {
				//while tempClass != gameObject.class, climb the class tree
				Class tempClass = tempObj.getClass();
				for(;tempClass != GameObject.class;tempClass = tempClass.getSuperclass()){}

				tempField = tempClass.getDeclaredField("curLocation");
				tempField.setAccessible(true);
				//get the array coords from the point field inside of gameObject
				Point p = (Point) tempField.get(tempObj);
				tempField.setAccessible(false);

				gameObjectArray[p.x][p.y] = tempObj;
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		error("GameobjectArray populated");
		//Create the new IsoCavas
		Main.cvs = new IsoCanvas(Main.mainFrame.getWidth(), Main.mainFrame.getHeight(),tiles);
		World world = null;
		if(root.getChild("localID") == null){
		//create the new world object
			world = new World(lTiles, gameObjectArray,playerID);
		}
		//give the world the gameObject array
//		world.setGameBoard(gameObjectArray);
		//give the world the logical tile map
//		world.setWorldMap(lTiles);
		Main.world = world;
		//create a new turnWatcher
		Main.tw = new TurnWatcher(world);
		error("All files loaded");
	}

	/**
	 * parse the XML element hatstand
	 * @param e The XML Element Hatstand
	 * @return
	 */
	private static GameObject HandleLoadStationaryObjectHatStand(Element e) {
		//TODO
		return null;
	}

	/**
	 * Deletes the file denoted by the given string from the save folder
	 *
	 * @param s
	 * @return
	 */
	public static boolean deleteFile(String s){
			File fi = new File("saves" + File.separatorChar + s);
		try{
			for(File f : fi.listFiles()){
				f.delete();
			}
		}catch(NullPointerException e){

		}
		return fi.delete();
	}

	private static GameObject HandleLoadStationaryObjectWall(Element e) {
		// TODO Auto-generated method stub
		return null;
	}

	private static GameObject HandleLoadUnitPlayer(Element e) throws UnexpectedException {
		Point point = new Point(Integer.parseInt(e.getChild("curLocation").getChildText("X")),
							Integer.parseInt(e.getChild("curLocation").getChildText("Y")));

		UnitPlayer p = new UnitPlayer(point, Integer.parseInt(e.getChildText("ID")));

		for(Field f : p.getClass().getDeclaredFields()){
			f.setAccessible(true);
			try {
				switch (f.getName()){
				case "heightOffSet"		: f.setInt(p, Integer.parseInt(e.getChildText("heightOffSet")))				; break;
				case "inventory"   		: f.set(p, HandleLoadInventory(e.getChild("inventory")))					; break;
				case "notTurnEnd"  		: f.setBoolean(p, (e.getChildText("notTurnEnd"    )=="true" ? true : false)); break;
				case "standardAction"	: f.setBoolean(p, (e.getChildText("standardAction")=="true" ? true : false)); break;
				case "moveAction"		: f.setBoolean(p, (e.getChildText("moveAction"    )=="true" ? true : false)); break;
				case "ID"				: break;
				case "curLocation"		: break;
				default 				: throw new UnexpectedException("Unexpected field value in handleLoadUnitPlayer ");
				}
			} catch (IllegalArgumentException
					| IllegalAccessException | SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}


		for(Element childNode : e.getChildren()){

		}
		for(Field f : e.getClass().getDeclaredFields()){
			f.setAccessible(false);
		}
		return p;
	}
	/**
	 * Load the inventory from the XMLTree
	 * @param child
	 * @return
	 */
	private static Inventory HandleLoadInventory(Element child) {
		Inventory i = new Inventory();
		i.add(Inventory.itemTypes.KATANA,Integer.parseInt(child.getChildText("KATANA")));
		i.add(Inventory.itemTypes.KEY,Integer.parseInt(child.getChildText("KEY")));
		i.add(Inventory.itemTypes.PUPPY,Integer.parseInt(child.getChildText("PUPPY")));
		i.add(Inventory.itemTypes.RUSTY_NAIL,Integer.parseInt(child.getChildText("RUSTY_NAIL")));
		return i;
	}

	public static String[] getLoadFiles(){
		return new File("saves").list();
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
		if(fileName == null){
			throw new IllegalArgumentException("FileName was null");
		}

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
		Field playerAvatar;
		try {
			playerAvatar = Main.world.getClass().getDeclaredField("avatar");
			playerAvatar.setAccessible(true);
			UnitPlayer p = (UnitPlayer)playerAvatar.get(Main.world);
			elem = new Element("LocalID");
			elem.setAttribute("ID", p.getID()+"");
			root.addContent(elem);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}



		//----------Output the XML--------
		XMLOutputter xmlOut = new XMLOutputter();
		xmlOut.setFormat(Format.getPrettyFormat());
		document.addContent(root);

		error(xmlOut.outputString(document));
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
		error("Saving over");
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
	 * Test set of tiles for rendering and gameObjs
	 *
	 * @param fi Use null the File shall be ignored
	 *
	 * Returns a 2D array of Tiles for testing purposes, feel free to mess with
	 * @return A 2D Array of Tile
	 * @throws FileNotFoundException
	 */
	public static Tuple testSet(File fi){

		int sizeX = 10;
		int sizeY = 10;
		int entityX = 0;
		int entityY = 0;
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
