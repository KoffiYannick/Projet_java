package org.reservation;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            // Tester la connexion
            Connection connection = DatabaseConnection.getConnection();
            System.out.println("Connexion réussie !");

            // Fermer la connexion (à faire dans une section 'finally' dans une application réelle)
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Test saisie
        SaisieDonnees.EntreeInfos();
    }
}
