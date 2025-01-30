package utenti;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IUsersDAO<User> {
	void doSave(User user) throws SQLException;

	boolean doDelete(String name) throws SQLException;

	 User doRetrieveByName(String name) throws SQLException;
	
	 ArrayList<User> doRetrieveAll(String order) throws SQLException;

	 void doUpdate(User user) throws SQLException;
	
	// boolean doFlipAdmin(User user) throws SQLException;
	
}
