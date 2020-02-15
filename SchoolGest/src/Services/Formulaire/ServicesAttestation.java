/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Formulaire;

import Entite.Formulaire.Attestation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ahmed
 */
public class ServicesAttestation {

    private Connection con;
    private Statement ste;

    public ServicesAttestation() {
    }

    public void ajouter(Attestation at) {
        try {
            ste = con.createStatement();
            String RequeteInsert = "INSERT INTO Attestation values (null, '" + at.getFichier() + ");";
            ste.executeUpdate(RequeteInsert);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void delete(int i) {

        try {
            String req = "delete from Attestation where idAttestation=" + i;
            PreparedStatement ste;
            ste = con.prepareStatement(req);
            ste.executeUpdate();
            System.out.println("Formulaire supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(ServicesAttestation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Attestation at) {
        try {
            String req = "UPDATE Attestation SET 'Fichier'" + at.getFichier() + ";";
            PreparedStatement ste = con.prepareStatement(req);
            ste.executeUpdate();
            System.out.println("attestation modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    

}
