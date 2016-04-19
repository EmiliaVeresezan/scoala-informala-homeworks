package week9;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import week5.CKlasse;
import week5.Car;
import week5.Golf;
import week5.Passat;
import week5.SKlasse;

public class PriceCatalogTest {

	PriceCatalog priceCatalog;
	Map<String, Double> carPrices;
	Car car;
	Client client;
	ClientAccount clientAccount;
	Car car1, car2, car3;

	@Before
	public void init(){
		
		priceCatalog = new PriceCatalog();
		carPrices = new HashMap<String, Double>();
		
		car1 = new Golf("hsyb0018shdakshdasdjuwm", 2006);
		car2 = new CKlasse("nsku3728djamwpsqtsvgsj", 2012);
		
		car3 = new SKlasse("ansh97365askjhahdsbvoo", 2015);
		priceCatalog.addCarPrice(car3, 20000.0);
	}
	
	/**
	 * Checks that the HashMap carPrices in class PriceCatalog works correctly, meaning that it only takes 
	 * unique key values- car chasse number.
	 * If an existing key is added again with a different value, the initial value is overriden.
 	 */
	@Test
	public void hashMapTest() {
		car = new Golf(10, "abgd61001ndhlalxya");
		String key = car.getChasseNr();
		carPrices.put(key, 7000.0);
		//test that element was added
		assertEquals(1, carPrices.size());
		
		car = new Golf(20, "abgd61001ndhlalxya");
		carPrices.put(car.getChasseNr(), 7000.0);
		//tests that the same element cannot be added twice
		assertEquals(1, carPrices.size());
		
		//tests that if we add the same chasse number again the initial value is overriden
		carPrices.put(car.getChasseNr(), 8000.0);
		assertTrue(carPrices.containsKey(car.getChasseNr()));
		Double value = carPrices.get(car.getChasseNr());
		assertEquals(8000.0, value, 0.001);
	}
	
	/**
	 * Tests the method addCarPrice(Car car, Double price) in class PriceCatalog
	 */
	@Test
	public void addCarPriceTest(){
		priceCatalog.addCarPrice(car1, 9600.0);
		priceCatalog.addCarPrice(car2, 17000.0);
				
		assertEquals(9600, priceCatalog.getCarPrice(car1), 0.001);
		assertEquals(17000, priceCatalog.getCarPrice(car2), 0.001);
	}
	
	/**
	 * Tests the method isInPriceCatalog(Car car) from the class PriceCatalog.
	 */
	@Test
	public void isInPriceCatalogTest(){
		
		assertTrue(priceCatalog.isInPriceCatalog(car3));
		assertFalse(priceCatalog.isInPriceCatalog(car1));
	}
	
	/**
	 * Test method for the method remove(Car car) that removes a pair-chasseNr, price- from the price catalog.
	 */
	@Test
	public void removeTest(){
		Car car1 = new Golf(10, "abgd61001ndhlalxya");
		priceCatalog.getCarPrices().put(car1.getChasseNr(), 7000.0);
		
		Car car2 = new Passat(20, "smxu0171jdhtcboxyza");
		priceCatalog.getCarPrices().put(car2.getChasseNr(), 7000.0);
		
		priceCatalog.remove(car1);
		
		assertTrue(priceCatalog.getCarPrices().containsKey("smxu0171jdhtcboxyza"));
		assertFalse(priceCatalog.getCarPrices().containsKey("abgd61001ndhlalxya"));
		
	}
	
	/**
	 * Closes used variables.
	 */
	@After
	public void destroy(){
		priceCatalog = null;
		carPrices = null;
		car = null; car1=null; car2=null;
		client =null;
		clientAccount = null;
		
	}

}
