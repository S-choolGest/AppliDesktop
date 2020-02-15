/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Formulaire;

import Entite.Formulaire.Attestation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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

    public void ajouterAttestation(Attestation at) {
        try {
            ste = con.createStatement();
            String RequeteInsert = "INSERT INTO Attestation values (null, '" + at.getFichier() + ");";
            ste.executeUpdate(RequeteInsert);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void deleteAttestation(int i) {

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

    public void updateAttestation(Attestation at) {
        try {
            String req = "UPDATE Attestation SET 'Fichier'" + at.getFichier() + ";";
            PreparedStatement ste = con.prepareStatement(req);
            ste.executeUpdate();
            System.out.println("attestation modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //affichage par idAttestation
    /*
    public Attestation getAttestation(String objet){
        Statement st;
        try{
        st=con.createStatement();
        String req = "select * from Attestation where id= '"+ IdAttestation+"'";
        ResultSet rs=st.executeQuery(req);
        if(rs.next()){
            return new Attestation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    */
    
    /*
    public List<Attestation> getAttestationParMotCle(String motCle){
        
    }
    */
    
    
    /*etude
    stage 
    ecole
    encadrant academique
    encadrant profetionnel
    
    
    
    */
    

}
