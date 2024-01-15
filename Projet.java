package Projet;

import java.util.Scanner;

public class Projet{
//	public static void ajouter_salle(String batiment,int etage , int numero ) {
//        //Requete SQL  qui crée l'individus ayant les attirbut 
//        //batiment,etage;numero
//		return ; // 
//		
//	}
//	public static int recherche_salle(String batiment,int etage , int numero ) {
//		//rechercher la salle qui a batiment=batiment ;etage=etage;numero=numero
//	    int id = 0;//recupere l'id de la salle à la place du 0
//		
//		return id;
//	}
//	
//	
//	public static void modifier_salle(String batiment,int etage , int numero,String newbatiment,int newetage , int newnumero ) {
//		int id = recherche_salle(batiment,etage,numero);
//		//modifier les infos de l'individus salle ayant id = id
//        //batiment= newbatiment ,etage=newetage; numero=newnumero;
//        //
//		
//	}
//	public static int supprimer_salle(String batiment,int etage , int numero ) {
//		int id = recherche_salle(batiment,etage,numero);
//        //supprime la salle de la base de donnée
//		return id; //on retourne pour supprimer de la liste Salle 
//	}
//	
//	
//	public static void ajouter_reservation(int date,int heuredebut,int heurefin,String promo, String responsable, String batiment,int etage , int numero ) {
//		int ids = recherche_salle(batiment,etage,numero); 
//		
//		//Requete SQL  qui crée l'individus ayant les attirbut 
//        //date=date , heure=heure , promo=promo ,responsable=responsable, idsalle = ids
//		return  ; // on retourne id pour l'ajouter dans la liste Salle
//		
//	}
//	public static void supprimer_reservation(int date,int heuredebut, String batiment,int etage , int numero ) {
//		int ids = recherche_salle(batiment,etage,numero); 
//        
//		//Requete SQL  qui supprime l'individus ayant les attirbut 
//        //date=date , heure=heure , idsalle = ids
//		return ; // on retourne id pour l'ajouter dans la liste Salle
//		
//	}
//	//Plage_horraire_disponible(int ids,int a,int b){
//		//requete Sql qui envoie les id des reservation tel que attribut heuredebut <b ET heurefin>a ET ids=ids
//		//return a liste des id
//	//}
//	public static void afficher_libre(int heure_init, int heure_final) {
//		int liste_salle ; // créer une liste vide
//		int list_heuredebut;//
//		int list_heurefin;//
//		//requete sql pour récupéré les id de la table salle
//		liste_salle = 0; //ajouter les id des salle dans la liste
//		for(i in liste_salle):
//			//print("pour la salle" i "les horraires disponible sont");
//			//list_id=Plage_horraire_dispoible(i,heure_init,heure_final)
//			list_heuredebut = 0; //list_debut = l'ensemble des heuredebut des reservation contenue dans list_id
//			list_heurefin = 0; //list_debut = l'ensemble des heuredebut des reservation contenue dans list_id
//			//heurea=heure_init
//			//heureb=heure_final
//			//while heurea<heure_final{
//				//for j in range liste_id
//					// if liste_heuredebut[j]<=heura < list_heurefin[j]:
//						//a=liste_heurefin[j]
//				//for j in range liste_id
//					// if heura <= liste_heuredebut[j]<b
//						//b=liste_heuredebut[j]
//				//if heure<heureb
//					//println("de " heurea" heure à " heureb " heure")
//		return ;
//	}
//	
//	//liste_reservation(int heure_init, int heure_final){
//		//int liste_salle ; // créer une liste vide
//		//int list_heuredebut;//
//		//int list_heurefin;//
//		//requete sql pour récupéré les id de la table salle
//		//liste_salle = 0; //ajouter les id des salle dans la liste
//		//for(i in liste_salle):
//			//list_id=Plage_horraire_dispoible(i,heure_init,heure_final)
//			//for j in range list_id
//				//print("la salle " i " est reservé de " a "à" b" heure); //avec i infos de la salle en requete sql et a et b infos heure debut et fin en requete sql
//	//}
//	//detail_reservation(date,heuredebut,batiment,étage,numero){
//		//print infos avec requete SQL
//	//}
	public static void main(String[] args) {	
		Scanner scanner = new Scanner(System.in);
		int En_cours_dutilisation = 1;
		while(En_cours_dutilisation == 1){
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
			if (choix ==0) {En_cours_dutilisation = 0;}
			if (choix ==1) {
				System.out.println("Taper le nom du batiment.");
				String batiment = scanner.next();
				System.out.println("Taper le numéros de l'étage.");
				int etage = scanner.nextInt();				
				System.out.println("Taper le numéros de salle.");
				int numero = scanner.nextInt();
				//ajouter_salle(batiment,etage,numero);

			}
			if (choix ==2) {
				System.out.println("Taper le nom du batiment actuel.");
				String batiment = scanner.next();				
				System.out.println("Taper le numéros de l'étage actuel.");
				int etage = scanner.nextInt();				
				System.out.println("Taper le numéros de salle actuel.");
				int numero = scanner.nextInt();				
				System.out.println("Taper le nouveaux nom du batiment .");
				String newbatiment = scanner.next();				
				System.out.println("Taper le nouveaux numéros de l'étage.");
				int newetage = scanner.nextInt();				
				System.out.println("Taper le nouveaux numéros de salle.");
				int newnumero = scanner.nextInt();
				//modifier_salle(batiment,etage,numero,newbatiment,newetage,newnumero);
			}
			if (choix ==3) {
				System.out.println("Taper le nom du batiment à supprimer.");
				String batiment = scanner.next();				
				System.out.println("Taper le numéros de l'étage à supprimer.");
				int etage = scanner.nextInt();				
				System.out.println("Taper le numéros de salle à supprimer.");
				int numero = scanner.nextInt();
				//supprimer_salle(batiment,etage,numero);
			}
			if (choix ==4) {
				System.out.println("Taper le nom du batiment.");
				String batiment = scanner.next();				
				System.out.println("Taper le numéros de l'étage.");
				int etage = scanner.nextInt();				
				System.out.println("Taper le numéros de salle.");
				int numero = scanner.nextInt();				
				System.out.println("Taper la date JJMMAA.");
				int date = scanner.nextInt();				
				System.out.println("Taper l'heure de début.");
				int heuredebut = scanner.nextInt();				
				System.out.println("Taper l(heure de fin.");
				int heurefin = scanner.nextInt();
				System.out.println("Taper le nom de la promo.");
				String promo = scanner.next();
				System.out.println("Taper le nom du responsable.");
				String responsable = scanner.next();
				//ajouter_reservation(date,heuredebut,heurefin,promo, responsable,batiment,etage ,numero );
			}
			if (choix ==5) {
				System.out.println("Taper le nom du batiment.");
				String batiment = scanner.next();				
				System.out.println("Taper le numéros de l'étage.");
				int etage = scanner.nextInt();				
				System.out.println("Taper le numéros de salle.");
				int numero = scanner.nextInt();				
				System.out.println("Taper la date JJMMAA.");
				int date = scanner.nextInt();				
				System.out.println("Taper l'heure de début.");
				int heuredebut = scanner.nextInt();				
				//supprimer_reservation(date,heuredebut, batiment,etage , numero );
			}
			if (choix ==6) {
				System.out.println("De");
				int heure_init = scanner.nextInt();				
				System.out.println("à");
				int heure_final = scanner.nextInt();				
				//afficher_libre(heure_init, heure_final);
			}
			if (choix ==7) {
				System.out.println("De");
				int heure_init = scanner.nextInt();				
				System.out.println("à");
				int heure_final = scanner.nextInt();				
				//liste_reservation(heure_init, heure_final);
			}
			if(choix == 8) {
				System.out.println("Taper le nom du batiment.");
				String batiment = scanner.next();				
				System.out.println("Taper le numéros de l'étage.");
				int etage = scanner.nextInt();				
				System.out.println("Taper le numéros de salle.");
				int numero = scanner.nextInt();				
				System.out.println("Taper la date JJMMAA.");
				int date = scanner.nextInt();				
				System.out.println("Taper l'heure de début.");
				int heuredebut = scanner.nextInt();					
//				detail_reservation(date,heuredebut,batiment,etage,numero);
			}

				
		
			
		}
		scanner.close();
		
		
	}
}




























//public static int load(String file) { //Liste au lieux de int
//	int list = 0; //récupére les id dans une liste
//	return list; 
