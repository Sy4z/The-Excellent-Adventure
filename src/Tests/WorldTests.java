package Tests;
import static org.junit.Assert.*;

import java.awt.Canvas;

import org.junit.*;

import gameRender.IsoCanvas;
import gameWorld.*;

public class WorldTests {
	@Test
	/**
	 * Some Basic start up tests
	 */
	public void test_001(){
		World w = new World(null, -1, -1, new IsoCanvas(500, 500));
		assertTrue(w.checkPlayerStatus());
		assertTrue(w.getInventory()[0] == 0);
		assertTrue(w.getInventory()[1] == 0);
		assertTrue(w.getInventory()[2] == 0);
		assertTrue(w.getInventory()[3] == 0);
		assertTrue(w.isTurn());
		assertTrue(w.getAvatar().getMoveAction());
		assertTrue(w.getAvatar().getStandardAction());
	}
	@Test
	public void testMovement_001(){
		World w = new World(null, -1, -1, new IsoCanvas(500, 500));
		assertTrue(w.checkPlayerStatus());
		assertTrue(w.isTurn());
		assertTrue(w.getAvatar().getMoveAction());
		assertTrue(w.getAvatar().getStandardAction());

		w.getAvatar().depleateMoves();

		assertTrue(w.checkPlayerStatus());
		assertTrue(w.isTurn());
		assertFalse(w.getAvatar().getMoveAction());
		assertTrue(w.getAvatar().getStandardAction());

		w.getAvatar().depleateMoves();

		assertFalse(w.checkPlayerStatus());
		assertFalse(w.isTurn());
		assertFalse(w.getAvatar().getMoveAction());
		assertFalse(w.getAvatar().getStandardAction());

	}

	@Test
	public void testLogicalTile_001(){
		LogicalTile t = new LogicalTile(false);
		assertFalse(t.isIsTile());
		t.setIsTile(true);
		assertTrue(t.isIsTile());
		t.setReachableByActive(true);
		assertTrue(t.isReachableByActive());
		t.clearMoveInfo();
		assertFalse(t.isReachableByActive());
	}
}
