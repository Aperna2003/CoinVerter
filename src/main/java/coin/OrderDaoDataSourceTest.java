package coin;


import admin.ModifyProductServlet;
import connection.ConPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;
import prodotti.ProductDaoDataSource;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderDaoDataSourceTest {

	@Mock
	PreparedStatement ps;

	@InjectMocks
	OrderDaoDataSource ODDS;

	@Test
	public void testDoRetrieveAll() throws Exception {
		// Crea uno spy di un'ArrayList reale per evitare problemi di ricorsione
		List<Ordine> spyList = spy(new ArrayList<>());

		// Intercetta la creazione di una nuova ArrayList nel metodo testato
		try (MockedStatic<ConPool> mockedConPool = mockStatic(ConPool.class);
			 MockedConstruction<ArrayList> mockedList = mockConstruction(ArrayList.class,
					 (mock, context) -> when(mock.add(any())).thenCallRealMethod())) {

			// Simulazione della connessione e della query
			Connection c = mock(Connection.class);
			mockedConPool.when(ConPool::getConnection).thenReturn(c);  // Mock del metodo statico

			PreparedStatement ps = mock(PreparedStatement.class);
			when(c.prepareStatement(anyString())).thenReturn(ps);

			ResultSet rs = mock(ResultSet.class);
			when(ps.executeQuery()).thenReturn(rs);

			// Simula il risultato della query con due ordini
			when(rs.next()).thenReturn(true, true, false);
			when(rs.getInt("id")).thenReturn(1, 2);
			when(rs.getString("name")).thenReturn("Ordine1", "Ordine2");



			// Esegui il metodo da testare
			ODDS.doRetrieveAllOrders();

			// Verifica che gli ordini siano stati aggiunti alla lista
			verify(spyList, times(2)).add(any(Ordine.class));
		}
	}

	@Test
	public void testDoRetrieveByDateFilter() throws Exception {
		//setup
		try (MockedConstruction<ProductDaoDataSource> mockedDAO = mockConstruction(ProductDaoDataSource.class);
			 MockedConstruction<ArrayList> mockedAL = mockConstruction(ArrayList.class);
		) {
			OrderDaoDataSource source = new OrderDaoDataSource();
			Connection c = mock(Connection.class);
			when(ConPool.getConnection()).thenReturn(c);

			String data1 = mock(String.class); // Mocked string data1
			String data2 = mock(String.class); // Mocked string data2
			ResultSet rs = mock(ResultSet.class);
			when(ps.executeQuery()).thenReturn(rs);
			Ordine o = mock(Ordine.class);

			//esecuzione
			ODDS.doRetrieveByDateFilter(data1, data2);

			//verifica
			verify(mockedAL.constructed().get(0), times(2)).add(o);
		}
	}

	@Test
	public void testDoRetrieveByNameFilter() throws Exception {
		//setup
		try (MockedConstruction<ProductDaoDataSource> mockedDAO = mockConstruction(ProductDaoDataSource.class);
			 MockedConstruction<ArrayList> mockedAL = mockConstruction(ArrayList.class);
		) {
			OrderDaoDataSource source = new OrderDaoDataSource();
			Connection c = mock(Connection.class);
			when(ConPool.getConnection()).thenReturn(c);

			String nome = mock(String.class); // Mocked string data1
			ResultSet rs = mock(ResultSet.class);
			when(ps.executeQuery()).thenReturn(rs);
			Ordine o = mock(Ordine.class);

			//esecuzione
			source.doRetrieveByNameFilter(nome);

			//verifica
			verify(mockedAL.constructed().get(0), times(2)).add(o);
		}
	}

}
