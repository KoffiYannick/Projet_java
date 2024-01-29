package org.reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Les détails de connexion
    private static final String URL = "jdbc:mysql://localhost:8083/gestion_salle";
    private static final String USER = "root";
    private static final String PASSWORD = "bdd_password";

    public static Connection getConnection() throws SQLException {
        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.jdbc.Driver");
            
            // Établir la connexion
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Le pilote MySql n'a pas pu être chargé.", e);
        }
    }

/*     public static void main(String[] args) {
        try {
            // Tester la connexion
            Connection connection = getConnection();
            System.out.println("Connexion réussie !");
            
            // Fermer la connexion (à faire dans une section 'finally' dans une application réelle)
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}