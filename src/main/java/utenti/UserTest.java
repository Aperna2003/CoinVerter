package utenti;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class UserTest {

	@Test
	public void testRicaricaPortafoglioVuoto() {
		//set up
		User user = new User();
		String valuta = "moneta";
		Double valore = 1.23;
		
		//esecuzione
		user.ricaricaPortafoglio(valuta, valore);
	
		//controllo
		HashMap<String,Double> portafoglio = user.getPortafoglio();
		assertTrue(portafoglio.containsKey(valuta));
		assertTrue(portafoglio.containsValue(valore));
		assertEquals(portafoglio.get(valuta),valore);	
	}


	@Test
	public void testRicaricaPortafoglioNuovaValuta() {
		//set up
		User user = new User();
		String valuta = "moneta";
		String valuta2 = "moneta2";
		Double valore = 1.23;
		Double valore2 = 2.10;
		user.ricaricaPortafoglio(valuta, valore);
		
		//esecuzione
		user.ricaricaPortafoglio(valuta2, valore2);
	
		//controllo
		HashMap<String,Double> portafoglio = user.getPortafoglio();
		assertEquals(portafoglio.size(), 2);
		assertTrue(portafoglio.containsKey(valuta));
		assertTrue(portafoglio.containsKey(valuta2));
		assertTrue(portafoglio.containsValue(valore));
		assertTrue(portafoglio.containsValue(valore2));
		assertEquals(portafoglio.get(valuta),valore);
		assertEquals(portafoglio.get(valuta2),valore2);
		
	}
}
