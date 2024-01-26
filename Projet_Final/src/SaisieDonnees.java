//import java.util.Calendar;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.Time;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class SaisieDonnees {

    static Scanner scanner = new Scanner(System.in);


    public static int Menu() {
        // Menu du programme
        System.out.println();
        System.out.println("Que voulez vous faire ?\n");
        System.out.println("-Taper 1 pour ajouter une salle:");
        System.out.println("-Taper 2 pour modifier une salle:");
        System.out.println("-Taper 3 pour supprimer une salle:");
        System.out.println("-Taper 4 pour ajouter une reservation:");
        System.out.println("-Taper 5 pour afficher les creneaux libres sur une plage donnee:");
        System.out.println("-Taper 6 pour afficher la liste des reservations sur une plage donnee:");
        System.out.println("-Taper 7 afficher les details d'une reservation choisie:");
        System.out.println("-Taper 8 pour supprimer une reservation:");
        System.out.println("-Taper 0 pour fermer le programme:");
        int choix = scanner.nextInt();
        return choix;
    }

    public static void EntreeInfos() {
        String batiment;
        int etage;				
        int numero;
        int date;				
        String heuredebut;				
        String heurefin;

        try {
            // Tester la connexion
            Connection connection = DatabaseConnection.getConnection();
            System.out.println("Connexion reussie !");
            int choix = 1;
            while (choix!=0) {
                choix = Menu();
                switch (choix) {
                    case 0:
                        break;
                    case 1:
                        // Ajout de salle
                        System.out.println("Taper le nom du batiment.");
                        batiment = scanner.next();
                        System.out.println("Taper le numero de l'etage.");
                        etage = scanner.nextInt();				
                        System.out.println("Taper le numero de salle.");
                        numero = scanner.nextInt();
                        Statement statement = connection.createStatement();
                        int  resultSet = statement.executeUpdate("insert into salle (batiment,etage,numero) values (\""+ batiment +"\", "+ etage +", "+ numero +");");
                        // Vérifions le nombre de lignes affectées
                        if (resultSet > 0) {
                            System.out.println("La salle a ete ajoutee avec succes !");
                        } else {
                            System.out.println("Erreur lors de l'ajout de la salle.");
                        }
                        break;
                    case 2:
                        // Modif salle

                        // Affichage des salles disponibles avec leurs ID
                        System.out.println("Liste des salles disponibles :");
                        afficherSalles(connection);

                        // Demandons à l'utilisateur de saisir l'ID de la salle à modifier
                        System.out.println("Veuillez saisir l'ID de la salle que vous voulez modifier :");
                        int salleId = scanner.nextInt();

                        // Demandons les nouvelles informations
                        System.out.println("Taper le nouveau nom du batiment :");
                        String newBatiment = scanner.next();
                        System.out.println("Taper le nouveau numero de l'etage :");
                        int newEtage = scanner.nextInt();
                        System.out.println("Taper le nouveau numero de salle :");
                        int newNumero = scanner.nextInt();

                        // Modifions la salle
                        modifierSalle(salleId, newBatiment, newEtage, newNumero, connection);
                        break;

                        case 3:
                        // Suppression de salle
                    
                        try {
                            // Affichons les salles disponibles avec leurs ID
                            System.out.println("Liste des salles disponibles :");
                            afficherSalles(connection);
                    
                            // Demandons à l'utilisateur de saisir l'ID de la salle à supprimer
                            System.out.println("Veuillez saisir l'ID de la salle que vous voulez supprimer : ");
                            int salId = scanner.nextInt();
                    
                            // Appel de la fonction pour supprimer la salle en utilisant son ID
                            supprimerSalle(salId, connection);
                    
                        } catch (SQLException e) {
                            System.out.println("Une erreur SQL s'est produite : " + e.getMessage());
                        }
                        break;
                    
                    case 4:
                        // Ajout reservation
                        System.out.println("Taper le nom du batiment.");
                        batiment = scanner.next();				
                        System.out.println("Taper le numero de l'etage.");
                        etage = scanner.nextInt();				
                        System.out.println("Taper le numero de salle.");
                        numero = scanner.nextInt();				
                        System.out.println("Taper la date AAMMJJ.");
                        date = scanner.nextInt();				
                        System.out.println("Taper l'heure de debut au format (HH:mm).");
                        heuredebut = scanner.next();				
                        System.out.println("Taper l'heure de fin au format (HH:mm).");
                        heurefin = scanner.next();
                        System.out.println("Taper le nom de la promo.");
                        String promo = scanner.next();
                        System.out.println("Taper le nom du responsable.");
                        String responsable = scanner.next();
                        
                        // Déclarons le format pour l'heure
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                        
                        try {
                            // on va convertir la chaîne d'heure de début et de fin en objet Time
                            java.util.Date parsedTimeDebut = timeFormat.parse(heuredebut);
                            Time timeDebut = new Time(parsedTimeDebut.getTime());
                        
                            java.util.Date parsedTimeFin = timeFormat.parse(heurefin);
                            Time timeFin = new Time(parsedTimeFin.getTime());
                        
                            // Récupération de l'ID de la salle
                            int sallId = getSalleId(batiment, etage, numero, connection);
                        
                            if (sallId != -1) {
                                // Insérons la réservation avec l'ID de la salle
                                Statement state = connection.createStatement();
                                state.executeUpdate("INSERT INTO reservation (id_salle, date, heure_debut, heure_fin, promo, responsable) VALUES (" +
                                        sallId + ", " + date + ", '" + timeDebut + "', '" + timeFin + "', '" + promo + "', '" + responsable + "')");
                        
                                System.out.println("La reservation a ete ajoutee avec succes !");
                            } else {
                                System.out.println("La salle n'a pas ete trouvee. Verifiez les valeurs saisies.");
                            }
                        } catch (ParseException | SQLException e) {
                            // Gérer les exceptions liées à la conversion de date/heure ou à l'exécution de la requête SQL
                            e.printStackTrace();
                        }
                        break;
                        case 8:
                        // Suppression de réservation
                    
                        int idReservation;
                    
                        do {
                            // Récupérons l'ID de la réservation
                            System.out.println("Saisissez l'ID de la reservation que vous voulez supprimer, si vous ne l'avez pas, retournez au menu et lancez la methode 6 qui vous fournira l'ID de chaque reservation : ");
                            
                            if (scanner.hasNextInt()) {
                                idReservation = scanner.nextInt();
                                scanner.nextLine(); // Pour consommer le saut de ligne
                                break; // Sortir de la boucle si la saisie est un entier
                            } else {
                                System.out.println("Saisie incorrecte. Veuillez entrer un entier.");
                                scanner.nextLine(); // Pour consommer le saut de ligne
                            }
                        } while (true);
                    
                        try {
                            if (idReservation != -1) {
                                // Supprimons la réservation en utilisant l'ID
                                Statement etat = connection.createStatement();
                                int rowsAffected = etat.executeUpdate("DELETE FROM reservation WHERE id=" + idReservation);
                    
                                if (rowsAffected > 0) {
                                    System.out.println("La reservation a ete supprimee avec succes !");
                                } else {
                                    System.out.println("Aucune reservation trouvee avec les informations specifiees.");
                                }
                            } else {
                                System.out.println("Aucune reservation trouvee avec les informations specifiees.");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    
                        break;
                    
                    case 5:

                        Scanner scan = new Scanner(System.in);	
                        // on demande à l'utilisateur de saisir la date
                        System.out.println("Veuillez saisir la date (au format AAMMJJ) :");
                        date = scanner.nextInt();

                        try {
                            // Saisie de l'heure de début au format "hh:mm:ss"
                            System.out.print("Veuillez saisir l'heure de debut au format \"hh:mm:ss\": ");
                            Time heureDebut = saisirHeure(scan);
                
                            // Saisir l'heure de fin au format "hh:mm:ss"
                            System.out.print("Veuillez saisir l'heure de fin au format \"hh:mm:ss\": ");
                            Time heureFin = saisirHeure(scan);
                
                            // Appel de la fonction afficher_libre avec les heures saisies
                            afficher_libre(heureDebut, heureFin, date, connection);
                        } catch (ParseException | SQLException e) {
                            System.out.println("Une erreur s'est produite : " + e.getMessage());
                        } 
                        break;
                    case 6:
                        Scanner scann = new Scanner(System.in);
                    
                        int dt = -1; // Initialisons à une valeur par défaut
                        while (true) {
                            try {
                                // Saisir la date
                                System.out.println("Veuillez saisir la date (au format AAMMJJ) :");
                    
                                if (scann.hasNextInt()) {
                                    dt = scann.nextInt();
                                    break; 
                                } else {
                                    System.out.println("Saisie incorrecte. Veuillez entrer un entier.");
                                    scann.nextLine();
                                    System.out.println();
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Saisie incorrecte. Veuillez entrer un entier.");
                                scann.nextLine();
                                System.out.println();
                            }
                        }
                    
                        try {
                            // Saisie de l'heure de début au format "hh:mm:ss"
                            Scanner sc = new Scanner(System.in);
                            System.out.print("Veuillez saisir l'heure de debut au format \"hh:mm:ss\": ");
                            Time heureDebut = saisirHeure(sc);
                            System.out.println("Heure de debut saisie : " + heureDebut);

                            // Saisie de l'heure de fin au format "hh:mm:ss"
                            System.out.print("Veuillez saisir l'heure de fin au format \"hh:mm:ss\": ");
                            Time heureFin = saisirHeure(sc);
                        
                            // Appel de la fonction afficher_libre avec les heures saisies
                            liste_reservation(heureDebut, heureFin, dt, connection);
                        } catch (ParseException e) {
                            System.out.println("Erreur lors de la saisie de l'heure : " + e.getMessage());
                        } catch (SQLException e) {
                            System.out.println("Une erreur SQL s'est produite : " + e.getMessage());
                        }                        
                    
                        break;
                    
                    
                    case 7:
                        // Détails reservation
                        Scanner scn = new Scanner(System.in);
                        
                        int reservationId = -1; // Initialiser à une valeur par défaut
                        
                        while (true) {
                            try {
                                // Saisir l'id de la réservation
                                System.out.println("Veuillez saisir l'ID de la reservation dont vous voulez voir les details : ");
                        
                                if (scn.hasNextInt()) {
                                    reservationId = scn.nextInt();
                                    break; 
                                } else {
                                    System.out.println("Saisie incorrecte. Veuillez entrer un entier.");
                                    scn.nextLine();
                                    System.out.println();
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Saisie incorrecte. Veuillez entrer un entier.");
                                scn.nextLine();
                                System.out.println();
                            }
                        }
                        
                        try {
                            // Affichage des détails de la réservation
                            afficherDetailsReservation(reservationId, connection);
                        } catch (SQLException e) {
                            System.out.println("Une erreur SQL s'est produite : " + e.getMessage());
                        }
                        break;

                    default:
                        System.out.println("Saisie invalide");
                    
                }
            }
            scanner.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour récupérer l'ID de la salle
    /**
     * @param batiment
     * @param etage
     * @param numero
     * @return id 
     * @throws SQLException
     */
    private static int getSalleId(String batiment, int etage, int numero,Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id FROM salle WHERE batiment = '" + batiment + "' AND etage = " + etage + " AND numero = " + numero);

        if (resultSet.next()) {
            return resultSet.getInt("id");
        } else {
            return -1;
        }
    }

    // Fonction pour saisir une heure
    private static Time saisirHeure(Scanner scanner) throws ParseException {
        String heureSaisie = scanner.next().trim();
        SimpleDateFormat formatHeure = new SimpleDateFormat("HH:mm:ss");
        formatHeure.setLenient(false);

        java.util.Date date = formatHeure.parse(heureSaisie);
        // Conversion de java.util.Date à java.sql.Date
        return new Time(date.getTime());
    }
    
    /**
     * @param dateInt
     * @return date convertie
     */
    public static String convertIntToDate(int dateInt) {
        // Extraire l'année, le mois et le jour de l'entier
        int year = dateInt / 10000;
        int month = (dateInt % 10000) / 100;
        int day = dateInt % 100;

        // Formater la chaîne de date au format "YYYY-MM-DD"
        return String.format("%04d-%02d-%02d", 2000 + year, month, day);
    }

    // Fonction pour récupérer les informations d'une salle par son ID
    /**
     * @param idSalle
     * @param connection
     * @return renvoie les infos recuperees grace a la requete
     * @throws SQLException
     */
    private static String[] recupererInfoSalle(int idSalle, Connection connection) throws SQLException {
        // Requête SQL pour récupérer les informations de la salle
        String query = "SELECT batiment, etage, numero FROM Salle WHERE id = ?";
        String[] infosSalle = new String[3];

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Paramètre de la requête (ID de la salle)
            statement.setInt(1, idSalle);

            // Exécution de la requête
            try (ResultSet resultSet = statement.executeQuery()) {
                // Vérification si une salle a été trouvée
                if (resultSet.next()) {
                    // Remplissage du tableau avec les informations récupérées
                    infosSalle[0] = resultSet.getString("batiment");
                    infosSalle[1] = String.valueOf(resultSet.getInt("etage"));
                    infosSalle[2] = String.valueOf(resultSet.getInt("numero"));
                    return infosSalle;
                }
            }
        }

        return null;
    }

    /**
     * @param heure_init
     * @param heure_final
     * @param dat
     * @param connection
     * @throws SQLException
     */
    public static void afficher_libre(Time heure_init, Time heure_final, int dat, Connection connection) throws SQLException  {
        List<Integer> liste_salle = new ArrayList<>() ; // créer une liste vide
        List<Time> liste_heure_debut = new ArrayList<>() ; // créer une liste vide
        List<Time> liste_heure_fin = new ArrayList<>() ; // créer une liste vide
        Time heurea;
        Time heureb;
        int id;
        String query = "SELECT id FROM Salle";
        String dateString = convertIntToDate(dat);

        ////////////requete sql pour récupéré les id de la table salle//////////////
        try (PreparedStatement preparedStatem = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatem.executeQuery()) {
                while(resultSet.next()){
                    id = resultSet.getInt("id");
                    liste_salle.add(id);
                }
            }
        }
        
        for(int i: liste_salle) {
            String[] infosSalle = recupererInfoSalle(i, connection);
            System.out.println();
            System.out.println("Les horaires disponibles pour la salle " + infosSalle[2] + " a l'etage " + infosSalle[1] + " au batiment " + infosSalle[0] + " le " + dateString + " sont :");
                        
            String query2 = "SELECT heure_debut,heure_fin FROM reservation WHERE id_salle="+i+" AND date=" + dat ;
            ////////requete sql pour récupéré heure debut et fin de reservation avec la idsalle =i///////
            try (PreparedStatement statement = connection.prepareStatement(query2)){                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while(resultSet.next()){
                        Time heuredeb = resultSet.getTime("heure_debut");
                        Time heurefin = resultSet.getTime("heure_fin");
                        liste_heure_debut.add(heuredeb);
                        liste_heure_fin.add(heurefin);
                    }
                }

            }
            
            heurea=heure_init;
            while (heurea.compareTo(heure_final)<0){
                heureb=heure_final;
                for(int j =0; j< liste_heure_debut.size();j++){
                    if (liste_heure_debut.get(j).compareTo(heurea)<=0 && heurea.compareTo(liste_heure_fin.get(j)) < 0){
                        heurea=liste_heure_fin.get(j);
                    }
                }
                for(int j =0; j< liste_heure_debut.size();j++){
                    if (heurea.compareTo(liste_heure_debut.get(j)) < 0  && liste_heure_debut.get(j).compareTo(heureb) < 0){
                        heureb=liste_heure_debut.get(j);
                    }
                }
                if(heurea.compareTo(heureb)<0) {
                    System.out.println( "de "+ heurea+" a " +heureb);
                }
                heurea = heureb ;
            }
        }

        return ;
    }

    /**
     * @param heure_init
     * @param heure_final
     * @param dat
     * @param connection
     * @throws SQLException
     */
    public static void liste_reservation(Time heure_init, Time heure_final, int dat, Connection connection) throws SQLException  {
        List<Integer> liste_salle = new ArrayList<>(); // créer une liste vide
        int i;
        int a;
        boolean reservationsTrouvees = false; // Variable de contrôle
    
        String query = "SELECT id FROM Salle";
        String dateString = convertIntToDate(dat);
    
        ////////////requete sql pour récupéré les id de la table salle//////////////
        try (PreparedStatement preparedStatem = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatem.executeQuery()) {
                while (resultSet.next()) {
                    i = resultSet.getInt("id");
                    liste_salle.add(i);
                }
            }
        }
    
        for (int j : liste_salle) {
            String[] infosSalle = recupererInfoSalle(j, connection);
            a = 1;
            String query2 = "SELECT id, date, heure_debut, heure_fin, promo, responsable FROM reservation WHERE id_salle = ? AND date = ? AND heure_debut <= ? AND heure_fin >= ?";
            ////////requete sql pour récupéré heure debut et fin de reservation avec la idsalle =i///////
            try (PreparedStatement statement = connection.prepareStatement(query2)) {
                statement.setInt(1, j);                 // Remplacez le 1er paramètre avec la valeur de i
                statement.setDate(2, convertIntToDateSQL(dat));    // Remplacez le 2ème paramètre avec la valeur de dat
                statement.setTime(3, heure_final);      // Remplacez le 3ème paramètre avec la valeur de heure_final
                statement.setTime(4, heure_init);       // Remplacez le 4ème paramètre avec la valeur de heure_init
    
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        if (a == 1) {
                            System.out.println("\nLes horaires des reservations pour la salle " + infosSalle[2] + " a l'etage " + infosSalle[1] + " au batiment " + infosSalle[0] + " le " + dateString + " sont :");
                            a = 0;
                        }
                        System.out.println();
                        Time heuredeb = resultSet.getTime("heure_debut");
                        Time heurefin = resultSet.getTime("heure_fin");
                        int id = resultSet.getInt("id");
                        System.out.println("De " + heuredeb + " a " + heurefin + "\t ( ID = " + id + ")");
                        System.out.println();
                        reservationsTrouvees = true; // Marquer que des réservations ont été trouvées
                    }
                }
            }
        }
    
        // Afficher le message si aucune réservation n'est trouvée
        if (!reservationsTrouvees) {
            System.out.println();
            System.out.println("Aucune reservation trouvee pour la plage horaire specifiee.");
        }
    
        return;
    }
    
    
    // Méthode pour convertir un int en java.sql.Date
    /**
     * @param dat
     * @return date convertie
     */
    private static Date convertIntToDateSQL(int dat){
        try {
            // Supposons que dat soit un entier au format YYMMDD
            String dateString = String.valueOf(dat);
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyMMdd");
            java.util.Date parsedDate = inputFormat.parse(dateString);

            // Convertir en java.sql.Date
            return new Date(parsedDate.getTime());
        } catch (ParseException e) {
            // Gérer l'exception ici (afficher un message d'erreur, journalisation, etc.)
            e.printStackTrace(); // À ajuster selon vos besoins
            return null; // Ou renvoyer une valeur par défaut, selon vos besoins
        }
    }

    /**
     * @param idReservation
     * @param connection
     * @throws SQLException
     */
    public static void afficherDetailsReservation(int idReservation, Connection connection) throws SQLException {
        // Requête SQL pour récupérer les détails de la réservation
        String query = "SELECT id, date, heure_debut, heure_fin, promo, responsable FROM reservation WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Remplace le paramètre dans la requête par l'ID de la réservation
            preparedStatement.setInt(1, idReservation);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Vérifie si la réservation existe
                if (resultSet.next()) {
                    // Récupère les détails de la réservation à partir du résultat de la requête
                    int id = resultSet.getInt("id");
                    String date = resultSet.getString("date");
                    String heureDebut = resultSet.getString("heure_debut");
                    String heureFin = resultSet.getString("heure_fin");
                    String promo = resultSet.getString("promo");
                    String responsable = resultSet.getString("responsable");

                    // Affiche les détails de la réservation
                    System.out.println("Details de la reservation " + id + " :");
                    System.out.println("Date: " + date);
                    System.out.println("Heure de debut: " + heureDebut);
                    System.out.println("Heure de fin: " + heureFin);
                    System.out.println("Promo: " + promo);
                    System.out.println("Responsable: " + responsable);
                    System.out.println();

                } else {
                    // La réservation n'existe pas
                    System.out.println("La reservation avec l'ID " + idReservation + " n'existe pas.");
                    System.out.println();
                }
            }
        }
    }
    
    // Fonction pour afficher les salles disponibles avec leurs ID
    /**
     * @param connection
     * @throws SQLException
     */
    public static void afficherSalles(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, batiment, etage, numero FROM salle");

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String batiment = resultSet.getString("batiment");
            int etage = resultSet.getInt("etage");
            int numero = resultSet.getInt("numero");

            System.out.println("ID: " + id + "\tBatiment: " + batiment + "\tEtage: " + etage + "\tNumero: " + numero);
        }

        // Fermer le ResultSet et la Statement
        resultSet.close();
        statement.close();
    }

    // Fonction pour modifier une salle en utilisant son ID
    /**
     * @param salleId
     * @param newBatiment
     * @param newEtage
     * @param newNumero
     * @param connection
     * @throws SQLException
     */
    public static void modifierSalle(int salleId, String newBatiment, int newEtage, int newNumero, Connection connection) throws SQLException {
        // Vérifier si la salle avec l'ID spécifié existe
        if (salleExists(salleId, connection)) {
            // Exécution de la requête UPDATE
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate("UPDATE salle SET batiment='" + newBatiment + "', etage=" + newEtage + ", numero=" + newNumero + " WHERE id=" + salleId);

            // Vérifie le nombre de lignes affectées
            if (rowsAffected > 0) {
                System.out.println("La salle a ete modifiee avec succes !");
            } else {
                System.out.println("Aucune salle n'a ete modifiee. Vérifiez les valeurs saisies.");
            }

            // Fermer la Statement
            statement.close();
        } else {
            System.out.println("Aucune salle trouvee avec l'ID specifie.");
        }
    }

    // Fonction pour vérifier si une salle avec l'ID spécifié existe
    /**
     * @param salleId
     * @param connection
     * @return
     * @throws SQLException
     */
    public static boolean salleExists(int salleId, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM salle WHERE id = ?");
        preparedStatement.setInt(1, salleId);
        ResultSet resultSet = preparedStatement.executeQuery();

        boolean exists = resultSet.next();

        // Fermer le ResultSet et la PreparedStatement
        resultSet.close();
        preparedStatement.close();

        return exists;
    }

    // Fonction pour supprimer une salle en utilisant son ID
    /**
     * @param salleId
     * @param connection
     * @throws SQLException
     */
    public static void supprimerSalle(int salleId, Connection connection) throws SQLException {
        // Vérifier si la salle avec l'ID spécifié existe
        if (salleExists(salleId, connection)) {
            // Exécution de la requête DELETE
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate("DELETE FROM salle WHERE id=" + salleId);

            // Vérifie le nombre de lignes affectées
            if (rowsAffected > 0) {
                System.out.println("La salle a ete supprimee avec succes !");
            } else {
                System.out.println("Aucune salle n'a ete supprimee. Verifiez l'ID de la salle.");
            }

            // Fermer la Statement
            statement.close();
        } else {
            System.out.println("Aucune salle trouvee avec l'ID specifie.");
        }
    }

}

