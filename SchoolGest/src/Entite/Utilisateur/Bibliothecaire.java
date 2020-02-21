/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entite.Utilisateur;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author william
 */
public class Bibliothecaire extends Utilisateur{
    private int id_bibliotheque;
	private String heure_debut;
	private String heure_arret;

	public Bibliothecaire(int id, String nom, String prenom, String email, String password, String cin, int numTel, String dateNaissance, String adresse, int type) {
		super(id, nom, prenom, email, password, cin, numTel, dateNaissance, adresse, type);
		this.id_bibliotheque = id_bibliotheque;
		this.heure_arret = heure_arret;
		this.heure_debut = heure_debut;
	}
	
	public Bibliothecaire(int id_bibliotheque, String heure_debut, String heure_arret, int id, String nom, String prenom, String email, String password, String cin, int numTel, String dateNaissance, String adresse, int type) {
		super(id, nom, prenom, email, password, cin, numTel, dateNaissance, adresse, type);
		this.id_bibliotheque = id_bibliotheque;
		this.heure_debut = heure_debut;
		this.heure_arret = heure_arret;
	}

	public Bibliothecaire(String nom, String prenom, String email, String password, String cin, int numTel, String dateNaissance, String adresse, int type) {
		super(nom, prenom, email, password, cin, numTel, dateNaissance, adresse, type);
	}
	
	public int getId_bibliotheque() {
		return id_bibliotheque;
	}

	public void setId_bibliotheque(int id_bibliotheque) {
		this.id_bibliotheque = id_bibliotheque;
	}

	public String getHeure_debut() {
		return heure_debut;
	}

	public void setHeure_debut(String heure_debut) {
		this.heure_debut = heure_debut;
	}

	public String getHeure_arret() {
		return heure_arret;
	}

	public void setHeure_arret(String heure_arret) {
		this.heure_arret = heure_arret;
	}

	@Override
	public String toString() {
		return "Bibliothecaire{"+ super.toString() + "id_bibliotheque=" + id_bibliotheque + ", heure_debut=" + heure_debut + ", heure_arret=" + heure_arret + '}';
	}
    
}
