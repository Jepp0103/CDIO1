package automat;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BilletautomatTest {

	Billetautomat automat;
	
	@Before
	public void setUp() throws Exception {
		automat = new Billetautomat();
	}

	@After
	public void tearDown() throws Exception {
		automat = null;
	}

	
	@Test
	//default billetpris should be 10, checking if this is true
	public void testGetBilletpris() {
		int expected = 10;
		Billetautomat automat = new Billetautomat();
		int actual = automat.getBilletpris();
		assertEquals(expected, actual);
	}

	@Test
	//tests for overflow by adding values onto max value
	public void testIndsætPengeOverflow() {
		int expected = Integer.MAX_VALUE;
		automat.indsætPenge(Integer.MAX_VALUE);
		automat.indsætPenge(22);
		int actual = automat.getBalance();
	// since We will get a Failure if assert is true, we are expecting them to not match, since we add to the known max value. 
		assertNotEquals(expected, actual);
	}

	@Test
	//Puts 100 in machine, tests if balance is 100.
	public void testGetBalance() {
		automat.indsætPenge(100);
		int expected = 100;
		int actual = automat.getBalance();
		assertEquals(expected, actual);
		
	}

//	@Test
//	Will not be testing this case. 
//	public void testUdskrivBillet() {
//		fail("Not yet implemented");
//	}

	@Test
	//
	public void testReturpenge() {
		automat.indsætPenge(100);
		int expected = 90;
		automat.udskrivBillet();
//		automat.returpenge();
		int actual = automat.returpenge();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testMontørLogin() {
		//logs in as operator with keycode, checks if login is ture
		automat.montørLogin("1234");
		boolean result = automat.erMontør();
		
		assertTrue(result);
	}

	@Test
	//Puts money en machine. Buys three tickets. Since no custom value is assigned to tickets, their price must be 10, and therefore machine total should be 30. 
	public void testGetTotal() {
		automat.indsætPenge(200);
		automat.udskrivBillet();
		automat.udskrivBillet();
		automat.udskrivBillet();
		automat.montørLogin("1234");
		int expected = 30;
		int actual = automat.getTotal();
		assertEquals(expected, actual);
		
	}

	@Test
	//buys two tickets, logs in as operator with assigned keycode, checks if antalbilettersolgt is equal to ticekts sold
	public void testGetAntalBilletterSolgt() {
		int expected = 2;
		automat.montørLogin("1234");
		automat.indsætPenge(20);
		automat.udskrivBillet();
		automat.udskrivBillet();
		int actual = automat.getAntalBilletterSolgt();
		assertEquals(expected, actual);
	}

	@Test
	// logs in, sets new ticket price, looks up this price. 
	public void testSetBilletpris() {
		automat.montørLogin("1234");
		automat.setBilletpris(60);
		int expected = 60;
		int actual = automat.getBilletpris();
		assertEquals(expected, actual);
	}

	@Test
	// buys tickets, logs in as operator, resets, checks if sold tickets is 0
	public void testNulstil() {
		automat.indsætPenge(200);
		automat.udskrivBillet();
		automat.udskrivBillet();
		automat.udskrivBillet();
		automat.montørLogin("1234");
		automat.nulstil();
		int expected = 0;
		int actual = automat.getAntalBilletterSolgt();
		assertEquals(expected, actual);
	}

	@Test
	//logs in as operator, manually sets tickets sold, checks the actual value of tickets sold. 
	public void testSetAntalBilletterSolgt() {
		automat.montørLogin("1234");
		automat.setAntalBilletterSolgt(100);
		int expected = 100;
		int actual = automat.getAntalBilletterSolgt();
		assertEquals(expected, actual);
	}

	@Test
	//if no code is entered, operator status must be false
	public void testErMontørUdenKode() {
		
		boolean result = automat.erMontør();
		
		assertFalse(result);
	}
	
	@Test
	//if the correct code is entered, operator status must be true
	public void testErMontørmedKode() {
		
		automat.montørLogin("1234");
		boolean result = automat.erMontør();
		
		assertTrue(result);
	}

}
