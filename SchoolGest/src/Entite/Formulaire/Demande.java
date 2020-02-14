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
public class Demande extends Formulaire{
    
    private int idDemande;

    public Demande(int idDemande, int idFormulaire, String description, String objet, Date dateEnvoi) {
        super( description, objet);
        this.idDemande = idDemande;
    }

    public int getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(int idDemande) {
        this.idDemande = idDemande;
    }

    @Override
    public String toString() {
        return super.toString()+"Demande{" + "idDemande=" + idDemande + '}';
    }
    
    
    
}
