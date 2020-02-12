/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entite.Bibliotheque;

import java.util.Date;

/**
 *
 * @author william
 */
public class Emprunt {
    private int id;
    private int idEmprunteur;
    private int idLivre;
    private Etat etat;
    private String dateEmprunt;
    private String dateConfirmation;
    private String dateRendu;

    public Emprunt(int idEmprunteur, int idLivre, Etat etat, String dateEmprunt, String dateConfirmation, String dateRendu) {
        this.idEmprunteur = idEmprunteur;
        this.idLivre = idLivre;
        this.etat = etat;
        this.dateEmprunt = dateEmprunt;
        this.dateConfirmation = dateConfirmation;
        this.dateRendu = dateRendu;
    }

    public Emprunt(int id, int idEmprunteur, int idLivre, Etat etat, String dateEmprunt, String dateConfirmation, String dateRendu) {
        this.id = id;
        this.idEmprunteur = idEmprunteur;
        this.idLivre = idLivre;
        this.etat = etat;
        this.dateEmprunt = dateEmprunt;
        this.dateConfirmation = dateConfirmation;
        this.dateRendu = dateRendu;
    }

    public Emprunt(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmprunteur() {
        return idEmprunteur;
    }

    public void setIdEmprunteur(int idEmprunteur) {
        this.idEmprunteur = idEmprunteur;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
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

    @Override
    public String toString() {
        return "Emprunt{" + "id=" + id + ", idEmprunteur=" + idEmprunteur + ", idLivre=" + idLivre + ", etat=" + etat + ", dateEmprunt=" + dateEmprunt + ", dateConfirmation=" + dateConfirmation + ", dateRendu=" + dateRendu + '}';
    }
    
}
