/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite.Bibliotheque;

/**
 *
 * @author william
 */
public class Emprunteur extends Emprunt{
	private String nom;
	private String prenom;
	private int tel;
	private String email;
	private String img;

	public Emprunteur(String nom, String prenom, int tel, String email, int id, int idEmprunteur, int idLivre, Etat etat, String dateEmprunt, String dateConfirmation, String dateRendu, String img) {
		super(id, idEmprunteur, idLivre, etat, dateEmprunt, dateConfirmation, dateRendu);
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.email = email;
		this.img = img;
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

	public int getTel() {
		return tel;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
}
