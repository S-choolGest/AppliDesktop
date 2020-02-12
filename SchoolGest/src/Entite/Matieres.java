/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author saghir
 */
public class Matieres {
    private int idMatiere;
    private String nomMatiere;
    private int coefMatiere;
    private List<Cours> cours;
    

    public Matieres() {
        this.cours = new ArrayList<>();
    }

    public Matieres( String nomMatiere, int coefficient, List<Cours> cours) {
        
        this.nomMatiere = nomMatiere;
        this.coefMatiere = coefficient;
        this.cours = cours;
    }

    public Matieres(String nomMatiere, int coefficient) {
        this.nomMatiere = nomMatiere;
        this.coefMatiere = coefficient;
    }

    

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public int getId() {
        return idMatiere;
    }

    public void setId(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public int getCoefMatiere() {
        return coefMatiere;
    }

    public void setCoefMatiere(int coefficient) {
        this.coefMatiere = coefficient;
    }

    @Override
    public int hashCode() {
        int hash=0;
        hash = this.idMatiere;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Matieres other = (Matieres) obj;
        if (this.idMatiere != other.idMatiere) {
            return false;
        }
        if (!Objects.equals(this.nomMatiere, other.nomMatiere)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Matieres{" + "\nidMatiere=" + idMatiere + ", \nnomMatiere=" + nomMatiere + ", \ncoefficient=" + coefMatiere +'}';
    }
    
    
}
