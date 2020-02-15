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
    private String fichier;
    private boolean validation;
    private Date dateEnvoi;
    

    public Formulaire() {
    }

    public Formulaire(String objet, String description, String fichier, boolean validation) {
        this.objet = objet;
        this.description = description;
        this.fichier = fichier;
        this.validation = validation;
    }

    public Formulaire(int idFormulaire, String objet, String description, String fichier, boolean validation, Date dateEnvoi) {
        this.idFormulaire = idFormulaire;
        this.objet = objet;
        this.description = description;
        this.fichier = fichier;
        this.validation = validation;
        this.dateEnvoi = dateEnvoi;
    }

    public int getIdFormulaire() {
        return idFormulaire;
    }

    public String getObjet() {
        return objet;
    }

    public String getDescription() {
        return description;
    }

    public String getFichier() {
        return fichier;
    }

    public boolean isValidation() {
        return validation;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setIdFormulaire(int idFormulaire) {
        this.idFormulaire = idFormulaire;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    @Override
    public String toString() {
        return "Formulaire{" + "idFormulaire=" + idFormulaire + ", objet=" + objet + ", description=" + description + ", fichier=" + fichier + ", validation=" + validation + ", dateEnvoi=" + dateEnvoi + '}';
    }

    

    

    
    
    
    
    
}
