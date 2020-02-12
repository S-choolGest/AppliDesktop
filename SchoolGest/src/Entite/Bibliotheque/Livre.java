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
public class Livre {
    private int id;
    private String titre;
    private String editeur;
    private String auteur;
    private Date dateSortie;
    private int taille;

    public Livre(int id, String titre, String editeur, String auteur, Date dateSortie, int taille) {
        this.id = id;
        this.titre = titre;
        this.editeur = editeur;
        this.auteur = auteur;
        this.dateSortie = dateSortie;
        this.taille = taille;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    @Override
    public String toString() {
        return "Livre{" + "id=" + id + ", titre=" + titre + ", editeur=" + editeur + ", auteur=" + auteur + ", dateSortie=" + dateSortie + ", taille=" + taille + '}';
    }
    
    
}
