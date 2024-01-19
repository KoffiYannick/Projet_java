package org.reservation;
import java.util.Calendar;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.Time;
import java.text.ParseException;
//import java.text.SimpleDateFormat;


public class SaisieDonnees {

    static Scanner scanner = new Scanner(System.in);

    public static int Menu() {
        // Menu du programme
        System.out.println("Que voulez vous faire ? ");
        System.out.println("-Taper 1 pour ajouter une salle:");
        System.out.println("-Taper 2 pour modifier une salle:");
        System.out.println("-Taper 3 pour supprimer une salle:");
        System.out.println("-Taper 4 pour ajouter une reservation:");
        System.out.println("-Taper 5 pour supprimer une reservation:");
        System.out.println("-Taper 6 pour afficher les créneaux libres:");
        System.out.println("-Taper 7 pour afficher les reservation:");
        System.out.println("-Taper 8 afficher le détail d'une reservation:");
        System.out.println("-Taper 0 pour fermer le programme:");
        int choix = scanner.nextInt();
        return choix;
    }

    public static void EntreeInfos() {
        String batiment;
        int etage;				
        int numero;
        int heure_init;
        int heure_final;
        int date;				
        String heuredebut;				
        String heurefin;

        try {
            // Tester la connexion
            Connection connection = DatabaseConnection.getConnection();
            System.out.println("Connexion réussie !");
            
            switch (Menu()) {
                case 0:
                    //System.out.println("Jeudi");
                    break;
                case 1:
                    // Ajout salle
                    System.out.println("Taper le nom du batiment.");
                    batiment = scanner.next();
                    System.out.println("Taper le numéro de l'étage.");
                    etage = scanner.nextInt();				
                    System.out.println("Taper le numéro de salle.");
                    numero = scanner.nextInt();
                    //ajouter_salle(batiment,etage,numero);
                    // Exécution de la requête
                    Statement statement = connection.createStatement();
                    int  resultSet = statement.executeUpdate("insert into salle (batiment,etage,numero) values (\""+ batiment +"\", "+ etage +", "+ numero +");");
                    // Vérifier le nombre de lignes affectées
                    if (resultSet > 0) {
                        System.out.println("La salle a été ajoutée avec succès !");
                    } else {
                        System.out.println("Erreur lors de l'ajout de la salle.");
                    }
                    break;
                case 2:
                    // Modif salle
                    System.out.println("Taper le nom du batiment actuel.");
                    batiment = scanner.next();				
                    System.out.println("Taper le numéro de l'étage actuel.");
                    etage = scanner.nextInt();				
                    System.out.println("Taper le numéro de salle actuel.");
                    numero = scanner.nextInt();				
                    System.out.println("Taper le nouveau nom du batiment .");
                    String newbatiment = scanner.next();				
                    System.out.println("Taper le nouveau numéro de l'étage.");
                    int newetage = scanner.nextInt();				
                    System.out.println("Taper le nouveau numéro de salle.");
                    int newnumero = scanner.nextInt();
                    //modifier_salle(batiment,etage,numero,newbatiment,newetage,newnumero);
                    Statement query1 = connection.createStatement();
                    ResultSet reslt_query1 = query1.executeQuery("select `id` from `gestion_salle`.`salle` where batiment=\""+batiment+"\" and etage="+etage+" and numero="+numero+";");
                    /*while (reslt_query1.next()) {
                        int id = reslt_query1.getInt(1);
                        query1.executeUpdate("UPDATE `gestion_salle`.`salle` SET `batiment`=\""+newbatiment+"\", `etage`="+newetage+", `numero`="+newnumero+" WHERE `id`="+id+";");
                    }*/
                    // Vérifier si la requête a trouvé une ligne
                    if (reslt_query1.next()) {
                        int id = reslt_query1.getInt(1);

                        // Ferme le ResultSet 1
                        reslt_query1.close();

                        // Exécution de la requête UPDATE
                        Statement query2 = connection.createStatement();
                        int rowsAffected = query2.executeUpdate("UPDATE `gestion_salle`.`salle` SET `batiment`=\"" + newbatiment + "\", `etage`=" + newetage + ", `numero`=" + newnumero + " WHERE `id`=" + id + ";");

                        // Vérifie le nombre de lignes affectées
                        if (rowsAffected > 0) {
                            System.out.println("La salle a été modifiée avec succès !");
                        } else {
                            System.out.println("Aucune salle n'a été modifiée. Vérifiez les valeurs saisies.");
                        }
                    } else {
                        System.out.println("Aucune salle trouvée avec les valeurs spécifiées.");
                    }
                    break;
                case 3:
                    // Suppr salle
                    System.out.println("Taper le nom du batiment de la salle à supprimer.");
                    batiment = scanner.next();				
                    System.out.println("Taper le numéro de l'étage de la salle à supprimer.");
                    etage = scanner.nextInt();				
                    System.out.println("Taper le numéro de salle à supprimer.");
                    numero = scanner.nextInt();
                    //supprimer_salle(batiment,etage,numero);
                    Statement query2 = connection.createStatement();
                    int reslt_query2 = query2.executeUpdate("DELETE FROM `gestion_salle`.`salle` WHERE  `batiment`=\"" + batiment + "\" AND `etage`=" + etage + " AND `numero`=" + numero + ";");
                    /*while (reslt_query2.next()) {
                        int id = reslt_query2.getInt(1);
                        query2.executeUpdate("DELETE FROM `gestion_salle`.`salle` WHERE  `id`="+id+";");
                    }*/
                    if (reslt_query2 > 0) {
                        System.out.println("La salle a été supprimée avec succès !");
                    } else {
                        System.out.println("Aucune salle n'a été supprimée. Vérifiez les valeurs saisies.");
                    }
                    break;
                case 4:
                    // Ajout reservation
                    System.out.println("Taper le nom du batiment.");
                    batiment = scanner.next();				
                    System.out.println("Taper le numéro de l'étage.");
                    etage = scanner.nextInt();				
                    System.out.println("Taper le numéro de salle.");
                    numero = scanner.nextInt();				
                    System.out.println("Taper la date AAMMJJ.");
                    date = scanner.nextInt();				
                    System.out.println("Taper l'heure de début au format (HH:mm).");
                    heuredebut = scanner.next();				
                    System.out.println("Taper l'heure de fin au format (HH:mm).");
                    heurefin = scanner.next();
                    System.out.println("Taper le nom de la promo.");
                    String promo = scanner.next();
                    System.out.println("Taper le nom du responsable.");
                    String responsable = scanner.next();
                    //ajouter_reservation(date,heuredebut,heurefin,promo, responsable,batiment,etage ,numero );
                    // Récupérer l'ID de la salle
                    //int salleId = getSalleId(batiment, etage, numero,connection);
                    
                    // Déclarer le format pour l'heure
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                    
                    // Vérifie si la date est dans le futur 
                    //SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy HH:mm");
                    //java.util.Date parsedDate = dateFormat.parse(String.format("%06d", date) + " " + heuredebut);
                    try {
                        // Convertir la chaîne d'heure de début en objet Time
                        java.util.Date parsedTimeDebut = timeFormat.parse(heuredebut);
                        Time timeDebut = new Time(parsedTimeDebut.getTime());
                    
                        // Convertir la chaîne d'heure de fin en objet Time
                        java.util.Date parsedTimeFin = timeFormat.parse(heurefin);
                        Time timeFin = new Time(parsedTimeFin.getTime());
                    
                        // Récupérer l'ID de la salle
                        int salleId = getSalleId(batiment, etage, numero, connection);
                    
                        if (salleId != -1) {
                            // Insérer la réservation avec l'ID de la salle
                            Statement state = connection.createStatement();
                            state.executeUpdate("INSERT INTO reservation (id_salle, date, heure_debut, heure_fin, promo, responsable) VALUES (" +
                                    salleId + ", " + date + ", '" + timeDebut + "', '" + timeFin + "', '" + promo + "', '" + responsable + "')");
                    
                            System.out.println("La réservation a été ajoutée avec succès !");
                        } else {
                            System.out.println("La salle n'a pas été trouvée. Vérifiez les valeurs saisies.");
                        }
                    } catch (ParseException | SQLException e) {
                        // Gérer les exceptions liées à la conversion de date/heure ou à l'exécution de la requête SQL
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    // Suppression de réservation
                    System.out.println("Taper le nom du batiment.");
                    batiment = scanner.next();
                    System.out.println("Taper le numéro de l'étage.");
                    etage = scanner.nextInt();
                    System.out.println("Taper le numéro de salle.");
                    numero = scanner.nextInt();
                    System.out.println("Taper la date AAMMJJ.");
                    date = scanner.nextInt();
                    System.out.println("Taper l'heure de début.");
                    heuredebut = scanner.next();

                    try {
                        // Récupérer l'ID de la réservation
                        int idReservation = getReservationId(batiment, etage, numero, date, heuredebut, connection);

                        if (idReservation != -1) {
                            // Supprimer la réservation en utilisant l'ID
                            Statement etat = connection.createStatement();
                            int rowsAffected = etat.executeUpdate("DELETE FROM reservation WHERE id=" + idReservation);

                            if (rowsAffected > 0) {
                                System.out.println("La réservation a été supprimée avec succès !");
                            } else {
                                System.out.println("Aucune réservation trouvée avec les informations spécifiées.");
                            }
                        } else {
                            System.out.println("Aucune réservation trouvée avec les informations spécifiées.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    break;
                case 6:
                    // Affich créneaux libres
                    System.out.println("De");
                    heure_init = scanner.nextInt();				
                    System.out.println("à");
                    heure_final = scanner.nextInt();				
                    //afficher_libre(heure_init, heure_final);
                    break;
                case 7:
                    // List reservations
                    System.out.println("De");
                    heure_init = scanner.nextInt();				
                    System.out.println("à");
                    heure_final = scanner.nextInt();				
                    //liste_reservation(heure_init, heure_final);
                    break;
                case 8:
                    // Détails reservation
                    System.out.println("Taper le nom du batiment.");
                    batiment = scanner.next();				
                    System.out.println("Taper le numéros de l'étage.");
                    etage = scanner.nextInt();				
                    System.out.println("Taper le numéros de salle.");
                    numero = scanner.nextInt();				
                    System.out.println("Taper la date JJMMAA.");
                    date = scanner.nextInt();				
                    System.out.println("Taper l'heure de début.");
                    heuredebut = scanner.next();					
                    // detail_reservation(date,heuredebut,batiment,etage,numero);
                    break;
                default:
                    System.out.println("Saisie invalide");
            }
            
            scanner.close();
            // Fermer la connexion (à faire dans une section 'finally' dans une application réelle)
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //scanner.close();

    // Méthode pour récupérer l'ID de la salle
    /**
     * @param batiment
     * @param etage
     * @param numero
     * @return
     * @throws SQLException
     */
    private static int getSalleId(String batiment, int etage, int numero,Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id FROM salle WHERE batiment = '" + batiment + "' AND etage = " + etage + " AND numero = " + numero);

        if (resultSet.next()) {
            return resultSet.getInt("id");
        } else {
            return -1; // Retourne -1 si la salle n'est pas trouvée
        }
    }

    public static int getReservationId(String batiment, int etage, int numero, int date, String heureDebut, Connection connection) throws SQLException {
        int reservationId = -1;

        String query = "SELECT id FROM reservation WHERE id_salle IN (SELECT id FROM salle WHERE batiment=? AND etage=? AND numero=?) AND date=? AND heure_debut=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, batiment);
            preparedStatement.setInt(2, etage);
            preparedStatement.setInt(3, numero);
            preparedStatement.setInt(4, date);
            preparedStatement.setString(5, heureDebut);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    reservationId = resultSet.getInt("id");
                }
            }
        }

        return reservationId;
    }

}


//public static int load(String file) { //Liste au lieux de int
//	int list = 0; //récupére les id dans une liste
//	return list; 