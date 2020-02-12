/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entite;

import java.io.File;

/**
 *
 * @author saghir
 */
public class Cours {
    private int idCours;
    private File chapitre;
    private String nomChapitre;

    public Cours() {
    }

    public Cours(File chapitre, String nomChapitre) {
        this.chapitre = chapitre;
        this.nomChapitre = nomChapitre;
    }

    public File getChapitre() {
        return chapitre;
    }

    public void setChapitre(File chapitre) {
        this.chapitre = chapitre;
    }

    public String getNomChapitre() {
        return nomChapitre;
    }

    public void setNomChapitre(String nomChapitre) {
        this.nomChapitre = nomChapitre;
    }
    
    
    
}
