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
public class Reclamation extends Formulaire{
    
    private int idReclamation;

    public Reclamation(int idReclamation, int idFormulaire, String description, String objet, Date dateEnvoi) {
        super( description, objet);
        this.idReclamation = idReclamation;
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    @Override
    public String toString() {
        return super.toString()+"Reclamation{" + "idReclamation=" + idReclamation + '}';
    }
    
    
    
    
    
}
