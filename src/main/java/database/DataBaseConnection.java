package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static Connection connection;

    public void createConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiculos", "root", "");

    }
    public Connection getConnection() {

        if (connection == null) {
            try {
                createConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
