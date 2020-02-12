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
    private Date dateEmprunt;
    private Date dateConfirmation;
    private Date dateRendu;

    public Emprunt(int idEmprunteur, int idLivre, Etat etat, Date dateEmprunt, Date dateConfirmation, Date dateRendu) {
        this.idEmprunteur = idEmprunteur;
        this.idLivre = idLivre;
        this.etat = etat;
        this.dateEmprunt = dateEmprunt;
        this.dateConfirmation = dateConfirmation;
        this.dateRendu = dateRendu;
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

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Date getDateConfirmation() {
        return dateConfirmation;
    }

    public void setDateConfirmation(Date dateConfirmation) {
        this.dateConfirmation = dateConfirmation;
    }

    public Date getDateRendu() {
        return dateRendu;
    }

    public void setDateRendu(Date dateRendu) {
        this.dateRendu = dateRendu;
    }
    
}
