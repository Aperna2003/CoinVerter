package coin;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.Random;
import java.util.ArrayList;

import org.junit.Test;

import prodotti.ProductBean;


public class CarrelloTest {

	@Test
	public void testAddProductCorretto() {
		//set up
		ProductBean prodotto = mock(ProductBean.class);
		Random random = new Random();
		int i =  random.nextInt(1000) + 1;
		Carrello carrello = new Carrello();
		
		//esecuzione
		carrello.addProduct(prodotto, i);
		
		//controllo
		ArrayList<ProductBean> PCarrello = carrello.getProducts();
		int NCarrello = carrello.getCount();
		assertTrue(PCarrello.contains(prodotto));
		assertTrue(NCarrello > 0);
		
	}
	
	@Test
	public void testAddProductQuantitàErrata() {
		//set up
		ProductBean prodotto = mock(ProductBean.class);
		int i = 0;
		Carrello carrello = new Carrello();
		
		//esecuzione
		carrello.addProduct(prodotto, i);
		
		//controllo
		ArrayList<ProductBean> PCarrello = carrello.getProducts();
		int NCarrello = carrello.getCount();
		assertFalse(PCarrello.contains(prodotto));
		assertEquals(NCarrello, 0);
	}
	
	@Test
	public void testAddProductProdottoErrato() {
		//set up
		ProductBean prodotto = null;
		Random random = new Random();
		int i =  random.nextInt(1000) + 1;
		Carrello carrello = new Carrello();
		
		//esecuzione
		carrello.addProduct(prodotto, i);
		
		//controllo
		ArrayList<ProductBean> PCarrello = carrello.getProducts();
		int NCarrello = carrello.getCount();
		assertFalse(PCarrello.contains(prodotto));
		assertEquals(NCarrello, 0);
		
	}

	@Test
	public void testDeleteProductCorretto() {
		//set up
		ProductBean prodotto = mock(ProductBean.class);
		Random random = new Random();
		int i =  random.nextInt(1000) + 1;
		Carrello carrello = new Carrello();
		carrello.addProduct(prodotto, i);
				
		//esecuzione
		carrello.deleteProduct(prodotto, i);
		
		//controllo
		ArrayList<ProductBean> PCarrello = carrello.getProducts();
		int NCarrello = carrello.getCount();
		assertTrue(PCarrello.isEmpty());
		assertEquals(NCarrello,0);
	}
	
	@Test
	public void testDeleteProductProdottoErrato() {
		//set up
		ProductBean prodotto = new ProductBean();
		prodotto.setCode(1);
		prodotto.setName("prod1");
		ProductBean prodotto2 = new ProductBean();
		prodotto2.setCode(2);
		prodotto2.setName("prod2");
		Random random = new Random();
		int i = random.nextInt(1000) + 1;
		int j = random.nextInt(1000) + 1;
		Carrello carrello = new Carrello();
		carrello.addProduct(prodotto, i);
				
		//esecuzione
		carrello.deleteProduct(prodotto2, j);
		
		//controllo
		ArrayList<ProductBean> PCarrello = carrello.getProducts();
		int NCarrello = carrello.getCount();
		assertTrue(NCarrello > 0);
		assertTrue(!PCarrello.isEmpty());

	}

}
