package coin;


import admin.ModifyProductServlet;
import connection.ConPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import prodotti.ProductDaoDataSource;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderDaoDataSourceTest {

	@Mock
	PreparedStatement ps;

	@InjectMocks
	OrderDaoDataSource ODDS;

	@Test
	public void testDoRetrieveAll() throws Exception {
		// Crea una lista reale che sarà popolata
		List<Ordine> realList = new ArrayList<>();

		// Intercetta la connessione al database
		try (MockedStatic<ConPool> mockedConPool = mockStatic(ConPool.class)) {

			// Mock della connessione
			Connection c = mock(Connection.class);
			mockedConPool.when(ConPool::getConnection).thenReturn(c);

			PreparedStatement ps = mock(PreparedStatement.class);
			when(c.prepareStatement(anyString())).thenReturn(ps);

			ResultSet rs = mock(ResultSet.class);
			when(ps.executeQuery()).thenReturn(rs);

			// Simula il risultato della query con due ordini
			when(rs.next()).thenReturn(true, true, false);
			//when(rs.getInt("id")).thenReturn(1, 2);
			//when(rs.getString("name")).thenReturn("Ordine1", "Ordine2");

			ArgumentCaptor<Ordine> ordineCaptor = ArgumentCaptor.forClass(Ordine.class);

			realList = ODDS.doRetrieveAllOrders();

			assertEquals(2, realList.size());
		}
	}

	@Test
	public void testDoRetrieveByDateFilter() throws Exception {
		// Crea una lista reale per raccogliere i risultati
		List<Ordine> realList = new ArrayList<>();

		// Intercetta la connessione al database
		try (MockedStatic<ConPool> mockedConPool = mockStatic(ConPool.class);
			 MockedConstruction<ProductDaoDataSource> mockedDAO = mockConstruction(ProductDaoDataSource.class)) {

			// Mock della connessione
			Connection c = mock(Connection.class);
			mockedConPool.when(ConPool::getConnection).thenReturn(c);

			PreparedStatement ps = mock(PreparedStatement.class);
			when(c.prepareStatement(anyString())).thenReturn(ps);

			ResultSet rs = mock(ResultSet.class);
			when(ps.executeQuery()).thenReturn(rs);

			// Simula il risultato della query con due ordini
			when(rs.next()).thenReturn(true, true, false);
			//when(rs.getInt("id")).thenReturn(1, 2);
			//when(rs.getString("data")).thenReturn("1-1-1", "2-2-2");

			String data1 = "1-1-1";
			String data2 = "2-2-2";

			// Esegui il metodo da testare
			realList = ODDS.doRetrieveByDateFilter(data1, data2);

			assertEquals(2, realList.size());
		}
	}

	@Test
	public void testDoRetrieveByNameFilter() throws Exception {
		// Crea una lista reale per raccogliere i risultati
		List<Ordine> realList = new ArrayList<>();

		// Intercetta la connessione al database
		try (MockedStatic<ConPool> mockedConPool = mockStatic(ConPool.class);
			 MockedConstruction<ProductDaoDataSource> mockedDAO = mockConstruction(ProductDaoDataSource.class)) {

			// Mock della connessione
			Connection c = mock(Connection.class);
			mockedConPool.when(ConPool::getConnection).thenReturn(c);

			PreparedStatement ps = mock(PreparedStatement.class);
			when(c.prepareStatement(anyString())).thenReturn(ps);

			ResultSet rs = mock(ResultSet.class);
			when(ps.executeQuery()).thenReturn(rs);

			// Simula il risultato della query con due ordini
			when(rs.next()).thenReturn(true, true, false);
			//when(rs.getInt("id")).thenReturn(1, 2);
			//when(rs.getString("name")).thenReturn("nome1", "nome2");

			String nome = "nome";

			// Esegui il metodo da testare
			realList = ODDS.doRetrieveByNameFilter(nome);

			// Verifica che gli ordini siano stati aggiunti alla lista
			assertEquals(2, realList.size());
		}
	}

}
