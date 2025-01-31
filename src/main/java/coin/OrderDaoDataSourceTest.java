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
import java.sql.Date;
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
		// setup

		try (MockedConstruction<ArrayList> mockedAL = mockConstruction(ArrayList.class, (mock, context) ->
		{
		})) {


			Connection c = mock(Connection.class);
			try (MockedStatic<ConPool> conPool = mockStatic(ConPool.class)) {
				when(ConPool.getConnection()).thenReturn(c);
			}
			PreparedStatement ps = mock(PreparedStatement.class);
			when(c.prepareStatement(anyString())).thenReturn(ps);
			ResultSet rs = mock(ResultSet.class);
			when(ps.executeQuery()).thenReturn(rs);
			when(rs.next()).thenReturn(true).thenReturn(false);
			when(rs.getInt("ID_ordine")).thenReturn(1);
			Date d = mock(Date.class);
			when(rs.getDate("data_acquisto")).thenReturn(d);
			when(rs.getString("email")).thenReturn("test");
			when(rs.getInt("q_acquisto")).thenReturn(1);
			when(rs.getString("nome")).thenReturn("test");
			when(rs.getString("tipo")).thenReturn("moneta");
			when(rs.getDouble("ID_ordine")).thenReturn(1.0);

			//esecuzione
			ODDS.doRetrieveAllOrders();

			//controllo
			verify(mockedAL.constructed().get(0)).add(any(Ordine.class));

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
