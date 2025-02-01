package connection;


import java.sql.Connection;

import java.sql.SQLException;


import java.sql.*;
import java.util.*;

public class ConPool {
    private static final List<Connection> freeDbConnections;

    static {
        freeDbConnections = new LinkedList<Connection>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection createDBConnection() throws SQLException {
        Connection newConnection;
        String ip = "localhost";
        String port = "3306";
        String db = "coinverter";
        String username = "root";
        String password = "admin";
        newConnection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + db, username, password);
        newConnection.setAutoCommit(true);
        return newConnection;
    }

    public static synchronized Connection getConnection() throws SQLException {
        Connection connection;
        if (!freeDbConnections.isEmpty()) {
            connection = freeDbConnections.get(0);
            ConPool.freeDbConnections.remove(0);
            try {
                if (connection.isClosed()) {
                    connection = ConPool.getConnection();
                }
            } catch (SQLException e) {
                connection = ConPool.getConnection();
            }
        } else {
            connection = ConPool.createDBConnection();
        }
        return connection;
    }

    public static synchronized void releaseConnection(Connection connection) {
        ConPool.freeDbConnections.add(connection);
    }
}