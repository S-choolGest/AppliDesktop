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
public class Attestation extends Formulaire {
    
    private int idAttestation;
    private String fichier;

    public Attestation(int idAttestation, String fichier, String description, String objet) {
        super(description, objet);
        this.idAttestation = idAttestation;
        this.fichier = fichier;
    }

    

    

    public int getIdAttestation() {
        return idAttestation;
    }

    public void setIdAttestation(int idAttestation) {
        this.idAttestation = idAttestation;
    }

    @Override
    public String toString() {
        return super.toString()+"Attestation{" + "idAttestation=" + idAttestation + '}';
    }
    
    
    
}
