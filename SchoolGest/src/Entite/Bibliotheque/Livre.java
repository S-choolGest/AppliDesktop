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
    private String categorie;
    private String dateSortie;
    private int taille;
    private int quantite;

    public Livre(int id, String titre, String editeur, String auteur, String categorie, String dateSortie, int taille, int quantite) {
        this.id = id;
        this.titre = titre;
        this.editeur = editeur;
        this.auteur = auteur;
        this.categorie = categorie;
        this.dateSortie = dateSortie;
        this.taille = taille;
        this.quantite = quantite;
    }

    public Livre(String titre, String editeur, String auteur, String categorie, String dateSortie, int taille, int quantite) {
        this.titre = titre;
        this.editeur = editeur;
        this.auteur = auteur;
        this.categorie = categorie;
        this.dateSortie = dateSortie;
        this.taille = taille;
        this.quantite = quantite;
    }

    public Livre(int id) {
        this.id = id;
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

    public String getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Livre{" + "id=" + id + ", titre=" + titre + ", editeur=" + editeur + ", auteur=" + auteur + ", categorie=" + categorie + ", dateSortie=" + dateSortie + ", taille=" + taille + ", quantite=" + quantite + '}';
    }
    
}
