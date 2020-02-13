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

    public Attestation(int idAttestation, int idFormulaire, String description, String objet, Date dateEnvoi) {
        super(idFormulaire, description, objet, dateEnvoi);
        this.idAttestation = idAttestation;
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
