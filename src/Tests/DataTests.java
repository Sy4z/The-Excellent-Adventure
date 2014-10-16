/**
 *
 */
package Tests;

import java.rmi.UnexpectedException;

import gameWorld.World;

import org.junit.Test;

import dataStorage.Data;

/**
 * @author Dylan, Jarred
 * Data is very robust and hard to test
 *
 */
public class DataTests {

	public @Test void testSave1(){
		World w = utils.createWorld();

		try {
			Data.save(null);
		} catch (UnexpectedException | IllegalArgumentException e) {

			assert(e instanceof IllegalArgumentException);
		}
	}
	public @Test void testSave2(){

	}

	public @Test void testLoadNull(){


		try {
			Data.load(null);
		} catch (UnexpectedException | IllegalArgumentException e) {

			assert(e instanceof IllegalArgumentException);
		}
	}

	public @Test void testLoad(){
		World w = utils.createWorld();
		String test = new String("hi");
		try {
			Data.load(test);
		} catch (UnexpectedException e) {

			e.printStackTrace();
		}
	}

	public @Test void testSet(){
		Data.testSet(null);
	}
}
