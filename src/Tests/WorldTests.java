//package Tests;
//import static org.junit.Assert.*;
//
//import java.awt.Canvas;
//import java.awt.Point;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
//import org.junit.*;
//
//import gameRender.IsoCanvas;
//import gameWorld.*;
//import gameWorld.Inventory.itemTypes;
//
//public class WorldTests {
////The following tests do not function due to changes to server.
////	@Test
////	/**
////	 * Some Basic start up tests
////	 */
////	public void test_001(){
////		World w = utils.createWorld();
////		assertTrue(w.checkPlayerStatus());
////		assertTrue(w.getInventory()[0] == 0);
////		assertTrue(w.getInventory()[1] == 0);
////		assertTrue(w.getInventory()[2] == 0);
////		assertTrue(w.getInventory()[3] == 0);
////		assertTrue(w.isTurn());
////		assertTrue(w.getAvatar().getMoveAction());
////		assertTrue(w.getAvatar().getStandardAction());
////		assertTrue(w.getAvatar() instanceof UnitPlayer);
////	}
////	@Test
////	public void testMovement_001(){
////		World w = utils.createWorld();
////		assertTrue(w.checkPlayerStatus());
////		assertTrue(w.isTurn());
////		assertTrue(w.getAvatar().getMoveAction());
////		assertTrue(w.getAvatar().getStandardAction());
////
////		w.getAvatar().depleateMoves();
////
////		assertTrue(w.checkPlayerStatus());
////		assertTrue(w.isTurn());
////		assertFalse(w.getAvatar().getMoveAction());
////		assertTrue(w.getAvatar().getStandardAction());
////
////		w.getAvatar().depleateMoves();
////
////		assertFalse(w.checkPlayerStatus());
////		assertFalse(w.isTurn());
////		assertFalse(w.getAvatar().getMoveAction());
////		assertFalse(w.getAvatar().getStandardAction());
////
////	}
//
//	@Test
//	public void constructorTest_001(){
//		World w = utils.createWorld();
//		World wrld = new World(w.getWorldMap(), w.getGameBoard(), w.getAvatar().getID());
//		assertTrue(w.getAvatar().equals(wrld.getAvatar()));
//		World world = new World(w.getWorldMap(), w.getGameBoard(), -7);
//		assertFalse(w.getAvatar().equals(world.getAvatar()));
//	}
//
//	@Test
//	public void testLogicalTile_001(){
//		LogicalTile t = new LogicalTile(false);
//		assertFalse(t.isIsTile());
//		t = new LogicalTile(true);
//		assertTrue(t.isIsTile());
//		t.setReachableByActive(true);
//		assertTrue(t.isReachableByActive());
//		t.clearMoveInfo();
//		assertFalse(t.isReachableByActive());
//	}
//
//	@Test
//	public void testInvientory_001(){
//		UnitPlayer p = new UnitPlayer(new Point(), 0);
//
//		assertTrue(p.getInventory()[0] == 0);
//		assertTrue(p.getInventory()[1] == 0);
//		assertTrue(p.getInventory()[2] == 0);
//		assertTrue(p.getInventory()[3] == 0);
//
//		int[] ints = {1,2,3,4};
//
//		p.addToInventory(ints);
//
//		assertTrue(p.getInventory()[0] == 1);
//		assertTrue(p.getInventory()[1] == 2);
//		assertTrue(p.getInventory()[2] == 3);
//		assertTrue(p.getInventory()[3] == 4);
//		assertTrue(p.hasKey());
//		p.useKey();
//
//		assertTrue(p.getInventory()[0] == 0);
//		assertTrue(p.getInventory()[1] == 2);
//		assertTrue(p.getInventory()[2] == 3);
//		assertTrue(p.getInventory()[3] == 4);
//		assertFalse(p.hasKey());
//
//		p.addToInventory(itemTypes.KEY, 5);
//
//		assertTrue(p.getInventory()[0] == 5);
//
//	}
//
//	@Test
//	public void testObjectInteaction_001(){
//
//		World w = utils.createWorld();
//		InteractiveObjectChest chst = new InteractiveObjectChest(new Point(7,9));
//		int keyIncreseC = chst.getContents()[itemTypes.KEY.ordinal()];
//		try {
//			Method m = null;
//			for(Method iterationM: w.getClass().getDeclaredMethods()){
//				if(iterationM.getName().equals("interactWith")){
//					m = iterationM;
//					break;
//				}
//			}
//			m.setAccessible(true);
//			m.invoke(w, chst);
//			assertTrue(w.getAvatar().numberOfItem(itemTypes.KEY)== keyIncreseC);
//		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//	@Test
//	public void testObjectInteaction_002(){
//
//		World w = utils.createWorld();
//		InteractiveObjectMonster mnstr = new InteractiveObjectMonster(null);
//		int keyIncreseM = mnstr.getContents()[itemTypes.KEY.ordinal()];
//		try {
//			Method m = null;
//			for(Method iterationM: w.getClass().getDeclaredMethods()){
//				if(iterationM.getName().equals("interactWith")){
//					m = iterationM;
//					break;
//				}
//			}
//			m.setAccessible(true);
//			m.invoke(w, mnstr);
//			assertTrue(w.getAvatar().numberOfItem(itemTypes.PUPPY)== 0);
//			w.getInventory()[itemTypes.KATANA.ordinal()] = 5000;
//			m.invoke(w, mnstr);
//			assertTrue(w.getAvatar().numberOfItem(itemTypes.PUPPY) == mnstr.getContents()[itemTypes.PUPPY.ordinal()]);
//
//		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//
//}
//
//
