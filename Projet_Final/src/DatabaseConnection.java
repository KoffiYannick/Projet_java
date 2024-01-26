import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Les détails de connexion
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_salle";
    private static final String USER = "root";
    private static final String PASSWORD = "mdproot";

    public static Connection getConnection() throws SQLException {
        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Établir la connexion
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Le pilote MySql n'a pas pu être chargé.", e);
        }
    }

}
