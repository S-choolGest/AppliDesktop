/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite.Education;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author saghir
 */
public class Matiere {

    private int idMatiere;
    private int idProf;
    private int idClasse;
    private int idModule;
    private String nomMatiere;
    private int coefMatiere;
    private ObservableList<Cours> cours;

    public Matiere() {
        this.cours = FXCollections.observableArrayList();
    }

    public ObservableList<Cours> getCours() {
        return cours;
    }

    public void setCours(ObservableList<Cours> c) {
        this.cours = c;
    }

    public Matiere(String nomMatiere, int coefficient) {
        this.cours = FXCollections.observableArrayList();

        this.nomMatiere = nomMatiere;
        this.coefMatiere = coefficient;
    }

    public Matiere(int idMatiere, int idProf, int idClasse, int idModule, String nomMatiere, int coefMatiere) {
        this.cours = FXCollections.observableArrayList();
        this.idMatiere = idMatiere;
        this.idProf = idProf;
        this.idClasse = idClasse;
        this.idModule = idModule;
        this.nomMatiere = nomMatiere;
        this.coefMatiere = coefMatiere;
    }

    public Matiere(int idMatiere, int idModule, String nomMatiere, int coefMatiere) {
        this.cours = FXCollections.observableArrayList();
        this.idMatiere = idMatiere;
        this.idModule = idModule;
        this.nomMatiere = nomMatiere;
        this.coefMatiere = coefMatiere;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public int getIdModule() {
        return idModule;
    }

    public void setIdModule(int idModule) {
        this.idModule = idModule;
    }

    public int getCoefMatiere() {
        return coefMatiere;
    }

    public void setCoefMatiere(int coefficient) {
        this.coefMatiere = coefficient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
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
        final Matiere other = (Matiere) obj;
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
        return "Matieres{" + "\nidMatiere=" + idMatiere + ", \nnomMatiere=" + nomMatiere + ", \ncoefficient=" + coefMatiere + '}';
    }

}
