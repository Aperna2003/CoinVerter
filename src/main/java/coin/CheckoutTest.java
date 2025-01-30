package coin;

import admin.ModifyProductServlet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.MockitoJUnitRunner;
import prodotti.ProductBean;
import prodotti.ProductDaoDataSource;
import utenti.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutTest {

	@Mock
	ProductDaoDataSource source;

	@Mock
	HttpServletRequest request;

	@Mock
	HttpServletResponse response;

	@Mock
	HttpSession session;

	@Mock
	Carrello c;

	@InjectMocks
	Checkout servlet;


	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() throws Exception {
		// Crea una lista mock di prodotti
		ArrayList<ProductBean> list = new ArrayList<>();
		HashMap<String, Double> p = new HashMap<>();

		// Mock dell'utente e del carrello
		User u = mock(User.class);
		Carrello c = mock(Carrello.class);

		// Mock della classe ProductDaoDataSource e cattura dell'istanza mockata
		try (MockedConstruction<ProductDaoDataSource> mockedDAO = mockConstruction(ProductDaoDataSource.class,
				(mock, context) -> doNothing().when(mock).doBuy(list, u))) { // Stub di doBuy()

			// Setup delle dipendenze della servlet
			when(request.getSession()).thenReturn(session);
			when(session.getAttribute("user")).thenReturn(u);
			when(session.getAttribute("cart")).thenReturn(c);
			when(c.getProducts()).thenReturn(list);
			when(u.getPortafoglio()).thenReturn(p);

			// Esegui la servlet
			servlet.doGet(request, response);

			// Verifica che i metodi corretti siano stati chiamati
			verify(c).clearCart();
			verify(u).setPortafoglio(p);
			verify(session).setAttribute("cart", c);
			verify(session).setAttribute("user", u);
			verify(response).sendRedirect("index.jsp");

			// Recupera l'istanza mockata di ProductDaoDataSource e verifica la chiamata a doBuy()
			ProductDaoDataSource mockDAO = mockedDAO.constructed().get(0); // Ottiene l'istanza mockata
			verify(mockDAO).doBuy(list, u);
		}
	}


	@Test
	public void testDoPost() throws ServletException, IOException {
		Checkout serv = spy(new Checkout());
		doNothing().when(serv).doGet(request, response);

		serv.doPost(request, response);

		verify(serv).doGet(request, response);
	}

}