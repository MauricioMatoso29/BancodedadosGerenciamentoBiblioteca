package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

            //fabrica de conexão//
public class ConnectionFactory {
    
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/biblioteca", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}

