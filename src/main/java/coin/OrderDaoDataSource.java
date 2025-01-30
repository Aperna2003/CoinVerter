package coin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import connection.ConPool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrderDaoDataSource {
	
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/coinverter");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "ordine";
	
	
	public ArrayList<Ordine> doRetrieveAllOrders() throws SQLException {
		String query = "SELECT ordine.ID_ordine,data_acquisto,email,q_acquisto,nome,tipo,prezzo FROM ordine INNER JOIN acquisto ON ordine.ID_ordine = acquisto.ID_ordine";
		
		ArrayList<Ordine> ordini = new ArrayList<Ordine>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = ConPool.getConnection();
		try {
			//creo l'ordine
			preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Ordine o = new Ordine();
				o.setID_ordine(rs.getInt("ID_ordine"));
				o.setData_acquisto(rs.getDate("data_acquisto"));
				o.setEmail(rs.getString("email"));
				o.setQ_acquisto(rs.getInt("q_acquisto"));
				o.setNome_prodotto(rs.getString("nome"));
				o.setTipo_prodotto(rs.getString("tipo"));
				o.setPrezzo(rs.getDouble("prezzo"));
				ordini.add(o);
			}

		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
		return ordini;
	}
	
	public ArrayList<Ordine> doRetrieveByDateFilter(String data1,String data2) throws SQLException {
		String query = "SELECT ordine.ID_ordine,data_acquisto,email,q_acquisto,nome,tipo,prezzo FROM ordine INNER JOIN acquisto ON ordine.ID_ordine = acquisto.ID_ordine WHERE data_acquisto >= ? AND data_acquisto <= ?";
		
		ArrayList<Ordine> ordini = new ArrayList<Ordine>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = ConPool.getConnection();
		try {
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, data1);
			preparedStatement.setString(2, data2);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Ordine o = new Ordine();
				o.setID_ordine(rs.getInt("ID_ordine"));
				o.setData_acquisto(rs.getDate("data_acquisto"));
				o.setEmail(rs.getString("email"));
				o.setQ_acquisto(rs.getInt("q_acquisto"));
				o.setNome_prodotto(rs.getString("nome"));
				o.setTipo_prodotto(rs.getString("tipo"));
				o.setPrezzo(rs.getDouble("prezzo"));
				ordini.add(o);
			}

		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
		return ordini;
	}
	
	public ArrayList<Ordine> doRetrieveByNameFilter(String nome) throws SQLException {
		String query = "SELECT ordine.ID_ordine,data_acquisto,email,q_acquisto,nome,tipo,prezzo FROM ordine INNER JOIN acquisto ON ordine.ID_ordine = acquisto.ID_ordine WHERE email = ?";
		
		ArrayList<Ordine> ordini = new ArrayList<Ordine>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = ConPool.getConnection();
		try {
			//creo l'ordine
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, nome);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Ordine o = new Ordine();
				o.setID_ordine(rs.getInt("ID_ordine"));
				o.setData_acquisto(rs.getDate("data_acquisto"));
				o.setEmail(rs.getString("email"));
				o.setQ_acquisto(rs.getInt("q_acquisto"));
				o.setNome_prodotto(rs.getString("nome"));
				o.setTipo_prodotto(rs.getString("tipo"));
				o.setPrezzo(rs.getDouble("prezzo"));
				ordini.add(o);
			}

		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
		return ordini;
	}
	
	
}
