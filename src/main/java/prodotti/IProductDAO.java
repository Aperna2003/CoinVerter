package prodotti;

import java.sql.SQLException;
import java.util.ArrayList;

import utenti.User;


public interface IProductDAO<T> {
	void doSave(T product) throws SQLException;

	boolean doRemove(int code) throws SQLException;
	
	void doUpdate(T product) throws SQLException;

	T doRetrieveByKey(int code) throws SQLException;
	
	ArrayList<T> doRetrieveByName(String name) throws SQLException;
	
	ArrayList<T> doRetrieveAvailable() throws SQLException;
	
	ArrayList<T> doRetrieveAll(String order) throws SQLException;

	ArrayList<T> doRetrieveByCategory(String cat) throws SQLException;
	
	void doBuy(ArrayList<T> products, User u) throws SQLException;

	

	void doDelete(int code) throws SQLException;


	
	
	
}
