package coin;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import admin.ModifyProductServlet;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.junit.MockitoJUnitRunner;
import prodotti.ProductDaoDataSource;
import utenti.TestDataSource;
import utenti.User;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class OrdiniServletTest {

	@Mock
	HttpSession session;

	@Mock
	HttpServletRequest request;

	@Mock
	HttpServletResponse response;

	@InjectMocks
	OrdiniServlet servlet;

	@Before
	public void setup() {
		servlet = new OrdiniServlet();
	}

	@Test
	public void testDoGetAllOrders() throws Exception {
		//setup
		ArrayList<Ordine> O = mock(ArrayList.class);
		try (MockedConstruction<OrderDaoDataSource> mockedDAO = mockConstruction(OrderDaoDataSource.class, (mock, context) ->
		{
			when(mock.doRetrieveByDateFilter(anyString(),anyString())).thenReturn(O);
			when(mock.doRetrieveAllOrders()).thenReturn(O);

		})) {
			when(request.getSession()).thenReturn(session);
			when(request.getParameter("user")).thenReturn(null);
			when(request.getParameter("start")).thenReturn(null);
			when(request.getParameter("end")).thenReturn(null);

			//esecuzione
			servlet.doGet(request, response);

			//controllo

			verify(session).setAttribute("ordini", O);
			verify(response).sendRedirect("/admin/gindex.jsp");
			verify(mockedDAO.constructed().get(0)).doRetrieveAllOrders();
		}
	}

	@Test
	public void testDoGetDateFilter() throws Exception {
		//setup
		ArrayList<Ordine> O = mock(ArrayList.class);
		try (MockedConstruction<OrderDaoDataSource> mockedDAO = mockConstruction(OrderDaoDataSource.class, (mock, context) ->
		{
			when(mock.doRetrieveByDateFilter(anyString(),anyString())).thenReturn(O);
			when(mock.doRetrieveAllOrders()).thenReturn(O);

		})) {
			when(request.getSession()).thenReturn(session);
			when(request.getParameter("user")).thenReturn(null);
			when(request.getParameter("start")).thenReturn("1-1-1");
			when(request.getParameter("end")).thenReturn("2-2-2");

			//esecuzione
			servlet.doGet(request, response);

			//controllo

			verify(session).setAttribute("ordini", O);
			verify(response).sendRedirect("/admin/gindex.jsp");
			verify(mockedDAO.constructed().get(0)).doRetrieveByDateFilter("1-1-1", "2-2-2");
		}
	}

	@Test
	public void testDoGetNameFilter() throws Exception {
		//setup
		ArrayList<Ordine> O = mock(ArrayList.class);
		try (MockedConstruction<OrderDaoDataSource> mockedDAO = mockConstruction(OrderDaoDataSource.class, (mock, context) ->
		{
			when(mock.doRetrieveByNameFilter(anyString())).thenReturn(O);
			when(mock.doRetrieveAllOrders()).thenReturn(O);

		})) {
			when(request.getSession()).thenReturn(session);
			when(request.getParameter("user")).thenReturn("utente");


			//esecuzione
			servlet.doGet(request, response);

			//controllo

			verify(session).setAttribute("ordini", O);
			verify(response).sendRedirect("/admin/gindex.jsp");
			verify(mockedDAO.constructed().get(0)).doRetrieveByNameFilter("utente");
		}
	}

	@Test
	public void testDoPost() throws ServletException, IOException {
		OrdiniServlet serv = spy(new OrdiniServlet());
		doNothing().when(serv).doGet(request, response);

		serv.doPost(request, response);

		verify(serv).doGet(request, response);
	}

}
