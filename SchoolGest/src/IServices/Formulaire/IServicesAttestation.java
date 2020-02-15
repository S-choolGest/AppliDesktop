/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices.Formulaire;

import Entite.Formulaire.Attestation;
import java.sql.SQLException;

/**
 *
 * @author Ahmed
 */
public interface IServicesAttestation {
    
    public void ajouterAttestation(Attestation at) throws SQLException;
    public void deleteAttestation(Attestation at) throws SQLException;
    public void updateAttestation(Attestation at) throws SQLException;
    public Attestation getAttestation(int idAttestation) throws SQLException;
    
    
}
