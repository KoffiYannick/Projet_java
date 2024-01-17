


public static void afficher_libre(int heure_init, int heure_final) {
	List<Integer> liste_salle = new ArrayList<>() ; // créer une liste vide
	List<Integer> liste_heure_debut = new ArrayList<>() ; // créer une liste vide
	List<Integer> liste_heure_fin = new ArrayList<>() ; // créer une liste vide
	int heurea;
	int heureb;
	
	////////////requete sql pour récupéré les id de la table salle//////////////
	//try (Connection connection = DriverManager.getConnection(url, utilisateur, motDePasse);
    //  PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM Salle");
    //	ResultSet resultSet = preparedStatement.executeQuery()){
	// 	while(resultSet.next()){
	// 		int id = resultSet.getInt("id");
	//		liste_salle.add(id);
	//	}
	//}
	for(int i: liste_salle) {
		//print("pour la salle" infos de la salle i "les horraires disponible sont");
		
		////////requete sql pour récupéré heure debut et fin de reservation avec la idsalle =i///////
		//try (Connection connection = DriverManager.getConnection(url, utilisateur, motDePasse);
	    //  PreparedStatement preparedStatement = connection.prepareStatement("SELECT heure_debut,heure_fin FROM reservation WHERE idsalle=i");
	    //	ResultSet resultSet = preparedStatement.executeQuery()){
		// 	while(resultSet.next()){
		// 		int heuredeb = resultSet.getInt("heure_debut");
		// 		int heurefin = resultSet.getInt("heure_fin");
		//		liste_heure_debut.add(heuredeb);
		//		liste_heure_fun.add(heurefin);
		//	}
		//}
		
		heurea=heure_init;
		while (heurea<heure_final){
			heureb=heure_final;
			for(int j =0; j< liste_heure_debut.size();j++){
				if (liste_heure_debut[j]<=heura < list_heure_fin[j]){
					heura=liste_heurefin[j];
				}
			}
			for(int j =0; j< liste_heure_debut.size();j++){
				if (heura < liste_heure_debut[j]<heureb){
					heurb=liste_heure_debut[j];
				}
			}
			if(heurea<heureb) {
				System.out.println( "de "+ heurea+" heure à " +heureb +" heure");
			}
	return ;
}
