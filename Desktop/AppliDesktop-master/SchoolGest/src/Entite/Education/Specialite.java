/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entite.Education;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saghir
 */

public class Specialite {
    private int idSpecialite;
    private String nomSpecialite;
    private List<Matiere> matieres;

    public Specialite() {
        this.matieres = new ArrayList<Matiere>();
    }
//
    public Specialite(String nomSpecialite) {
        this.nomSpecialite = nomSpecialite;
    }

    public Specialite(int idSpecialite, String nomSpecialite) {
        this.idSpecialite = idSpecialite;
        this.nomSpecialite = nomSpecialite;
    }

    public Specialite(int idSpecialite, String nomSpecialite, List<Matiere> matieres) {
        this.matieres = matieres;
        this.idSpecialite = idSpecialite;
        this.nomSpecialite = nomSpecialite;
        
    }

    public int getIdSpecialite() {
        return idSpecialite;
    }

    public void setIdSpecialite(int idSpecialite) {
        this.idSpecialite = idSpecialite;
    }

    public String getNomSpecialite() {
        return nomSpecialite;
    }

    public void setNomSpecialite(String nomSpecialite) {
        this.nomSpecialite = nomSpecialite;
    }

    public List<Matiere> getMatieres() {
        return matieres;
    }

    public void setMatieres(List<Matiere> matieres) {
        this.matieres = matieres;
    }

    @Override
    public String toString() {
        String nomMatieres = "";
        for (Matiere matiere : (new Services.Education.ServiceMatiere()).getAllMatiere()) {
            nomMatieres+="NOM: "+matiere.getNomMatiere()+"\n";
        }
        return "Specialites{" + "idSpecialite=" + idSpecialite + ", nomSpecialite=" + nomSpecialite + "\n" +nomMatieres + '}';
    }
    
    
}
