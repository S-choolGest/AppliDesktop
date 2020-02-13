/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite.Formulaire;

import java.util.Date;

/**
 *
 * @author Ahmed
 */
public class Formulaire {
    private int idFormulaire;
    private String description;
    private String objet;
    private Date dateEnvoi;

    public Formulaire(int idFormulaire, String description, String objet, Date dateEnvoi) {
        this.idFormulaire = idFormulaire;
        this.description = description;
        this.objet = objet;
        this.dateEnvoi = dateEnvoi;
    }

    public int getIdFormulaire() {
        return idFormulaire;
    }

    public String getDescription() {
        return description;
    }

    public String getObjet() {
        return objet;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setIdFormulaire(int idFormulaire) {
        this.idFormulaire = idFormulaire;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    @Override
    public String toString() {
        return "Formulaire{" + "idFormulaire=" + idFormulaire + ", description=" + description + ", objet=" + objet + ", dateEnvoi=" + dateEnvoi + '}';
    }
    
    
}
