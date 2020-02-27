/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite.Utilisateur;

import javafx.scene.image.ImageView;

/**
 *
 * @author william
 */
public class Utilisateur {
	private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String cin;
    private int numTel;
    private String dateNaissance;
    private String adresse;
    private int type;
	private String profil;

	public Utilisateur(int id, String nom, String prenom, String email, String password, String cin, int numTel, String dateNaissance, String adresse, int type) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.cin = cin;
		this.numTel = numTel;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.type = type;
	}

	public Utilisateur(String nom, String prenom, String email, String password, String cin, int numTel, String dateNaissance, String adresse, int type, String profil) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.cin = cin;
		this.numTel = numTel;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.type = type;
		this.profil = profil;
	}

	public Utilisateur(int id, String nom, String prenom, String email, String password, String cin, int numTel, String dateNaissance, String adresse, int type, String profil) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.cin = cin;
		this.numTel = numTel;
		this.dateNaissance = dateNaissance;
		this.adresse = adresse;
		this.type = type;
		this.profil = profil;
	}
        public Utilisateur(int id, String nom, String prenom, String email, String password, String cin,int type) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.cin = cin;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public int getNumTel() {
		return numTel;
	}

	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}

	@Override
	public String toString() {
		return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", cin=" + cin + ", numTel=" + numTel + ", dateNaissance=" + dateNaissance + ", adresse=" + adresse + ", type=" + type + ", profil=" + profil + '}';
	}
	
}
