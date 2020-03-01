/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Utilisateur;

import Entite.Utilisateur.Utilisateur;
import Entite.Utilisateur.UtilisateurView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author william
 */
public class ServicesUtilisateurView {
	public UtilisateurView transform(Utilisateur u){
		String t;
		if(u.getType() == 0)
			t = "etudiant";
		else if(u.getType() == 1)
			t = "professeur";
		else if(u.getType() == 2)
			t = "scolarite";
		else if(u.getType() == 3)
			t = "Administration";
		else if(u.getType() == 4)
			t = "Parent";
		else if(u.getType() == 5)
			t = "Bibliothecaire";
		else
			t = "error";
		ImageView img = new ImageView(new Image(u.getProfil()));
		img.setFitHeight(50);
		img.setFitWidth(50);
		UtilisateurView v = new UtilisateurView(u.getId(), u.getNom(), u.getPrenom(), u.getEmail(), u.getPassword(), u.getCin(), u.getNumTel(), u.getDateNaissance(), u.getAdresse(), t, img);
		return v;
	}
}
