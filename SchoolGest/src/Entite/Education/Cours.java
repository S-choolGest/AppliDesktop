/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entite.Education;

import java.io.File;

/**
 *
 * @author saghir
 */
public class Cours {
    private int idCours;
    private File chapitre;
    private String nomCours;

    public Cours() {
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
