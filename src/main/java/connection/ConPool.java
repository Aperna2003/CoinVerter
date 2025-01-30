package connection;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.TimeZone;

import java.sql.*;
import java.util.*;

public class ConPool {
    private static List<Connection> freeDbConnections;

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
        newConnection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + db + "", username, password);
        newConnection.setAutoCommit(true);
        return newConnection;
    }

    public static synchronized Connection getConnection() throws SQLException {
        Connection connection;
        if (!freeDbConnections.isEmpty()) {
            connection = (Connection) freeDbConnections.get(0);
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