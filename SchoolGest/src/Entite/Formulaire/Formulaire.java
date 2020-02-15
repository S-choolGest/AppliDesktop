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
    private String objet;
    private String description;
    private boolean validation;
    private Date dateEnvoi;

    public Formulaire() {
    }

    
    public Formulaire( String description, String objet) {
        this.description = description;
        this.objet = objet;
    }

    public Formulaire(int idFormulaire, String objet, String description, boolean validation, Date dateEnvoi) {
        this.idFormulaire = idFormulaire;
        this.objet = objet;
        this.description = description;
        this.validation = validation;
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

    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
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
        return "Formulaire{" + "idFormulaire=" + idFormulaire + ", objet=" + objet + ", description=" + description + ", validation=" + validation + ", dateEnvoi=" + dateEnvoi + '}';
    }

    
    
    
    
    
}
