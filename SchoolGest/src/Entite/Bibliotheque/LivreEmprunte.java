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
public class LivreEmprunte extends Livre{
	private int id_emprunt;
	private Etat etat;
	private String dateEmprunt;
	private String dateConfirmation;
	private String dateRendu;

	public LivreEmprunte(int id_emprunt, Etat etat, String dateEmprunt, String dateConfirmation, String dateRendu, int id, int id_bibliotheque, String titre, String editeur, String auteur, String categorie, String dateSortie, int taille, int quantite, String img, String dateajout) {
		super(id, id_bibliotheque, titre, editeur, auteur, categorie, dateSortie, taille, quantite, img, dateajout);
		this.id_emprunt = id_emprunt;
		this.etat = etat;
		this.dateEmprunt = dateEmprunt;
		this.dateConfirmation = dateConfirmation;
		this.dateRendu = dateRendu;
	}
	
	public int getId_emprunt() {
		return id_emprunt;
	}

	public void setId_emprunt(int id_emprunt) {
		this.id_emprunt = id_emprunt;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public String getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(String dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public String getDateConfirmation() {
		return dateConfirmation;
	}

	public void setDateConfirmation(String dateConfirmation) {
		this.dateConfirmation = dateConfirmation;
	}

	public String getDateRendu() {
		return dateRendu;
	}

	public void setDateRendu(String dateRendu) {
		this.dateRendu = dateRendu;
	}

	
}
