package prodotti;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//import org.omg.CORBA.Request;

import connection.ConPool;
import utenti.User;

public class ProductDaoDataSource implements IProductDAO<ProductBean> {

	private String selectSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME + " WHERE ID_prodotto = ?";
	
	private static DataSource ds;
	
	  public static void setDataSource(DataSource dataSource) {
	        ds = dataSource;
	    }
	    
	    public static DataSource getDataSource() {
	        return ds;
	    }
	

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/coinverter");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "prodotto";
	
	
	
	@Override
	public synchronized void doSave(ProductBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		String insertNewSQL = "INSERT INTO " + ProductDaoDataSource.TABLE_NAME
				+ " (nome, valore, prezzo, quantità, tipo, foto) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = ConPool.getConnection();
			preparedStatement = connection.prepareStatement(insertNewSQL);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setDouble(2, product.getValue());
			preparedStatement.setDouble(3, product.getPrice());
			preparedStatement.setInt(4, product.getQuantity());
			preparedStatement.setString(5, product.getType());
			preparedStatement.setString(6, product.getFoto());
			preparedStatement.executeUpdate();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}
	
	@Override
	public void doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String DeleteSQL = "DELETE FROM " + ProductDaoDataSource.TABLE_NAME + " WHERE ID_prodotto = ?";
		try {
			connection = ConPool.getConnection();
			preparedStatement = connection.prepareStatement(DeleteSQL);
			preparedStatement.setInt(1, code);
			preparedStatement.executeUpdate();
	
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}

	}
	
	
	@Override
	public void doUpdate(ProductBean product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean oldBean = new ProductBean();

		String updateSQL = "UPDATE " + ProductDaoDataSource.TABLE_NAME + 
		" SET nome = ?, tipo = ?, valore = ?, prezzo = ?, foto = ? where ID_prodotto = ?";

		try {
			connection = ConPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, product.getCode());

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				oldBean.setCode(rs.getInt("ID_prodotto"));
				oldBean.setName(rs.getString("nome"));
				oldBean.setType(rs.getString("tipo"));
				oldBean.setFoto(rs.getString("foto"));
				oldBean.setValue(rs.getDouble("valore"));
				oldBean.setPrice((float)rs.getDouble("prezzo"));
				
				
			preparedStatement = connection.prepareStatement(updateSQL);
			if(product.getName()== null) {preparedStatement.setString(1, oldBean.getName());}
			else preparedStatement.setString(1, product.getName());
			
			if(product.getType()== null) {preparedStatement.setString(2, oldBean.getType());}
			else preparedStatement.setString(2, product.getType());
			
			if(product.getPrice()< 0) {preparedStatement.setDouble(3, oldBean.getValue());}
			else preparedStatement.setDouble(3, product.getValue());
			
			if(product.getPrice()< 0) {preparedStatement.setDouble(4, oldBean.getPrice());}
			else preparedStatement.setDouble(4, product.getPrice());
			
			if(product.getFoto()== null) {preparedStatement.setString(5, oldBean.getFoto());}
			else preparedStatement.setString(5, product.getFoto());
			
			preparedStatement.setInt(6, oldBean.getCode());
			preparedStatement.execute();//sto codice è scritto da 2 coglioni pt3 - coders
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
	}
		




	@Override
	public synchronized ProductBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean bean = null;

		//String selectSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME + " WHERE ID_prodotto = ?";

		try {
			connection = ConPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.isBeforeFirst()) {
				bean = new ProductBean();
				while (rs.next()) {
					bean.setCode(rs.getInt("ID_prodotto"));
					bean.setName(rs.getString("nome"));
					bean.setType(rs.getString("tipo"));
					bean.setFoto(rs.getString("foto"));
					bean.setValue(rs.getDouble("valore"));
					bean.setPrice((float)rs.getDouble("prezzo"));
				}
			}else throw new SQLException();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}

	@Override
	public synchronized boolean doRemove(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "UPDATE " + ProductDaoDataSource.TABLE_NAME + " SET disponibile = FALSE WHERE ID_prodotto = ?";

		try {
			connection = ConPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	
	
	
	
	  @Override public ArrayList<ProductBean> doRetrieveByName(String name) throws
	  SQLException { Connection connection = null; PreparedStatement
	  preparedStatement = null;
	  
	  ArrayList<ProductBean> beanz = new ArrayList<ProductBean>();
	  
	  String selectNameSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME +
	  " WHERE nome LIKE ?";
	  
	  
	  try { connection = ConPool.getConnection(); preparedStatement =
	  connection.prepareStatement(selectNameSQL); preparedStatement.setString(1,"%" + name + "%");
	  
	  ResultSet rs = preparedStatement.executeQuery();
	  
	  while (rs.next()) { ProductBean bean = new ProductBean();
	  bean.setCode(rs.getInt("ID_prodotto")); 
	  bean.setName(rs.getString("nome"));
	  bean.setType(rs.getString("tipo")); 
	  bean.setFoto(rs.getString("foto"));
	  bean.setValue(rs.getDouble("valore"));
	  bean.setPrice((float)rs.getDouble("prezzo")); 
	  beanz.add(bean);
	  
	  }
	  
	  } finally { try { if (preparedStatement != null) preparedStatement.close(); }
	  finally { if (connection != null) connection.close(); } } return beanz; }
	 
	
	
	
	@Override
	public ArrayList<ProductBean> doRetrieveByCategory(String cat) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ProductBean> beanz = new ArrayList<ProductBean>();

		String selectNameSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME + " WHERE tipo = ?";

		
		try {
			connection = ConPool.getConnection();
			preparedStatement = connection.prepareStatement(selectNameSQL);
			preparedStatement.setString(1, cat);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();
				bean.setCode(rs.getInt("ID_prodotto"));
				bean.setName(rs.getString("nome"));
				bean.setType(rs.getString("tipo"));
				bean.setFoto(rs.getString("foto"));
				bean.setValue(rs.getDouble("valore"));
				bean.setPrice((float)rs.getDouble("prezzo"));
				beanz.add(bean);
			
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return beanz;
	}



	@Override
	public synchronized ArrayList<ProductBean> doRetrieveAvailable() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ProductBean> products = new ArrayList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME + " WHERE disponibile = TRUE";
		connection = ConPool.getConnection();
		try {
		
		
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				String tp = rs.getString("tipo");
				if(tp.equals("ricarica")) bean.setQuantity(rs.getInt("quantità"));
				
				bean.setCode(rs.getInt("ID_prodotto"));
				bean.setType(tp);
				bean.setName(rs.getString("nome"));
				bean.setValue(rs.getDouble("valore"));
				bean.setPrice(rs.getFloat("prezzo"));
				bean.setFoto(rs.getString("foto"));
				bean.setAvailable(rs.getBoolean("disponibile"));
				
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return products;
	}

	@Override
	public synchronized ArrayList<ProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<ProductBean> products = new ArrayList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductDaoDataSource.TABLE_NAME;
		connection = ConPool.getConnection();
		try {
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY ?";
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, order);
		}else {
			preparedStatement = connection.prepareStatement(selectSQL);
		}

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				String tp = rs.getString("tipo");
				
				if(tp.equals("ricarica"))bean.setQuantity(rs.getInt("quantità"));
				
				bean.setCode(rs.getInt("ID_prodotto"));
				
				bean.setType(tp);
				
			
				bean.setName(rs.getString("nome"));
				bean.setValue(rs.getDouble("valore"));
				bean.setPrice(rs.getFloat("prezzo"));
				bean.setFoto(rs.getString("foto"));
				bean.setAvailable(rs.getBoolean("disponibile"));
				
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return products;
	}

	@Override
	public void /*Dubai*/doBuy(ArrayList<ProductBean> products,User u) throws SQLException {
		String Ordinesql = "INSERT INTO ordine(data_acquisto, email) VALUES(?,?)";
		String Prodottisql = "INSERT INTO acquisto(ID_ordine,q_acquisto,nome,tipo,prezzo) VALUES(?,?,?,?,?)";
		String RecoverWallet = "SELECT nome,valore FROM valuta WHERE email = ?";
		
		String Wallet1sql = "INSERT INTO valuta(email,nome,valore) VALUES(?,?,?)";
		String Wallet2sql = "UPDATE valuta SET valore = ? WHERE email = ? AND nome = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedPortafoglio = null;
		connection = ConPool.getConnection();
		try {
			//creo l'ordine
			preparedStatement = connection.prepareStatement(Ordinesql,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setDate(1, Date.valueOf(java.time.LocalDate.now()));
			preparedStatement.setString(2, u.getEmail());
			preparedStatement.execute();
			ResultSet id = preparedStatement.getGeneratedKeys();
			int generatedId = -1;
			if (id.next()) {generatedId = id.getInt(1); }
			else System.out.println("ProductDaoDataSource + ERRORE KEY");
			System.out.println("ProductDaoDataSource: Ordine eseguito +" + generatedId);
			preparedStatement.close();
					
			//aggiungo i prodotti acquistati all'ordine
			preparedStatement = connection.prepareStatement(Prodottisql);
			preparedStatement.setInt(1, generatedId);
			HashMap<String,Double> dbData = new HashMap<String, Double>();
			
			preparedPortafoglio = connection.prepareStatement(RecoverWallet);
			preparedPortafoglio.setString(1,u.getEmail());
			ResultSet rs = preparedPortafoglio.executeQuery();
			while (rs.next()) dbData.put(rs.getString("nome"), rs.getDouble("valore"));
			preparedPortafoglio.close();
	
			 preparedPortafoglio = connection.prepareStatement(Wallet1sql);
			 PreparedStatement preparedPortafoglio2 = connection.prepareStatement(Wallet2sql);

			 for(ProductBean p: products){
				 if(p.getType().equals("valuta")|| p.getType().equals("crypto")) {
					 if (!dbData.containsKey(p.getName())) {
						 System.out.println(p.getName() + " non c'è");
             
						 preparedPortafoglio.setString(1,u.getEmail());
						 preparedPortafoglio.setString(2,p.getName());
						 preparedPortafoglio.setDouble(3,p.getValue()*p.getQuantity());
						 preparedPortafoglio.addBatch();
					 }else {
						 System.out.println(p.getName() + " c'è");
              
						 preparedPortafoglio2.setDouble(1,dbData.get(p.getName()) + p.getValue() * p.getQuantity());
						 preparedPortafoglio2.setString(2, u.getEmail());
						 preparedPortafoglio2.setString(3, p.getName());
						 preparedPortafoglio2.addBatch();
            
					 }				
            
				}   
				 
				 preparedStatement.setInt(2, p.getQuantity());
				 preparedStatement.setString(3, p.getName());
				 preparedStatement.setString(4, p.getType());
				 preparedStatement.setDouble(5, (p.getPrice() * p.getQuantity()));
				 preparedStatement.addBatch();
			 }
		// creo la "ricevuta"
			preparedStatement.executeBatch();
			System.out.println("ProductDaoDataSource: Batch ORdine eseguita");
			
			//salvo il portafoglio
			preparedPortafoglio.executeBatch();
			preparedPortafoglio2.executeBatch();
			
		}catch(BatchUpdateException e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (preparedPortafoglio != null)
					preparedPortafoglio.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
	}

	
}