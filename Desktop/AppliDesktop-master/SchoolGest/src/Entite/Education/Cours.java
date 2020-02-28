/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite.Education;

import java.io.File;
import javafx.collections.ObservableList;

/**
 *
 * @author saghir
 */

public class Cours {

    private int idCours;
    private int idMatiere;
    private String nomCours;
    private File chapitre;

    public Cours() {
    }

    public Cours(int idCours, int idMatiere, String nomCours) {
        this.idCours = idCours;
        this.idMatiere = idMatiere;
        this.nomCours = nomCours;
    }

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public Cours(int idCours, int idMatiere, String nomCours, File chapitre) {
        this.idCours = idCours;
        this.idMatiere = idMatiere;
        this.nomCours = nomCours;
        this.chapitre = chapitre;
    }
    
    public Cours(int idCours, String nomCours, File chapitre) {
        this.idCours = idCours;
        this.nomCours = nomCours;
        this.chapitre = chapitre;
    }

    public Cours(File chapitre, String nomChapitre) {
        this.chapitre = chapitre;
        this.nomCours = nomChapitre;
    }

    public File getChapitre() {
        return chapitre;
    }

    public void setChapitre(File chapitre) {
        this.chapitre = chapitre;
    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomChapitre) {
        this.nomCours = nomChapitre;
    }

}
