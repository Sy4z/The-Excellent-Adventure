/**
 *
 */
package Tests;

import java.rmi.UnexpectedException;

import gameWorld.World;

import org.junit.Test;

import dataStorage.Data;

/**
 * @author macdondyla1
 *
 */
public class DataTests {

	public @Test void testSave1(){
		World w = utils.createWorld();

		try {
			Data.save(null);
		} catch (UnexpectedException | IllegalArgumentException e) {
			// TODO Auto-generated catch block
			assert(e instanceof IllegalArgumentException);
		}
	}
	public @Test void testSave2(){

	}
}
