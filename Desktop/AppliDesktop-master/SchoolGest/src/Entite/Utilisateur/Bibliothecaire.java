/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entite.Utilisateur;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import javafx.scene.image.ImageView;

/**
 *
 * @author william
 */
public class Bibliothecaire extends Utilisateur{

	public Bibliothecaire(int id, String nom, String prenom, String email, String password, String cin, int numTel, String dateNaissance, String adresse, int type) {
		super(id, nom, prenom, email, password, cin, numTel, dateNaissance, adresse, type);
	}
	
	public Bibliothecaire(int id, String nom, String prenom, String email, String password, String cin, int numTel, String dateNaissance, String adresse, int type, String profil) {
		super(id, nom, prenom, email, password, cin, numTel, dateNaissance, adresse, type, profil);
	}

	public Bibliothecaire(String nom, String prenom, String email, String password, String cin, int numTel, String dateNaissance, String adresse, int type, String profil) {
		super(nom, prenom, email, password, cin, numTel, dateNaissance, adresse, type, profil);
	}

	@Override
	public String toString() {
		return "Bibliothecaire{"+super.toString() + '}';
	}
    
}
