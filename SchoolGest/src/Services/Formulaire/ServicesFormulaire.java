/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Formulaire;

import Entite.Formulaire.Formulaire;
import IServices.Formulaire.IServicesFormulaire;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ahmed
 */
public class ServicesFormulaire implements IServicesFormulaire<Formulaire> {

    private Connection con;
    private Statement ste;

    public ServicesFormulaire() {
        con = DataBase.getInstance().getConnection();
    }

    //ajout formulaire 
    @Override
    public void ajouter(Formulaire f) {
        try {
            ste = con.createStatement();
            String requeteInsert = "INSERT INTO formulaire VALUES (NULL, '"+ f.getObjet()+ "', '"+ f.getDescription()+ "', '"+ f.getFichier()+ "', '"+ "0"+ "',current_timestamp);";
            ste.executeUpdate(requeteInsert);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }
    
    //suppression formulaire 
    @Override
    public void delete(int i) {
        try {
            String req = "delete from Formulaire where idFormulaire=" + i;
            PreparedStatement pt = con.prepareStatement(req);
            pt.executeUpdate(req);
            System.out.println("Formulaire supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //modification formulaire
    @Override
    public void update(Formulaire f) throws SQLException {
        try {
            String req = "UPDATE Formulaire SET 'objet'" + f.getObjet()+ ",'descriptionFormulaire'" + f.getDescription()+",'fichier'" + f.getFichier()+";";  
            PreparedStatement ste = con.prepareStatement(req);
            ste.executeUpdate();
            System.out.println("Formulaire modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //affichage de la liste
    @Override
    public List<Formulaire> readall() throws SQLException {
        List<Formulaire> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from formulaire");
        while (rs.next()) {
            int idFormulaire = rs.getInt(1);
            String objet = rs.getString("objet");
            String description = rs.getString("descriptionFormulaire");
            String fichier = rs.getString("fichier");
            boolean validation = rs.getBoolean(5);
            Date dateEnvoi = rs.getDate(6);
            Formulaire f = new Formulaire(idFormulaire, objet, description, fichier, validation, dateEnvoi);
            arr.add(f);
        }
        return arr;
    }
    
    /*
    //recherche simple
    public List<Formulaire> getSimpleFormilaireParMotCle(String motCle) {
        Statement st;
        List<Formulaire> ls = new ArrayList();
        try {
            st = con.createStatement();
            String req = "select * from Formulaire where objet like '%" + motCle + "%' or descriptionFormulaire like '%" + motCle + "%' or dateEnvoie like '%"+motCle+"%'";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                ls.add(new Formulaire(rs.getString(2), rs.getString(3), rs.getDate(6)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ls;
    }
    */

    //confirmer un formulaire
    @Override
    public void confirmerValidation(int idFormulaire) {
        try {
            String req = "update Formulaire set validation=1 where idFormulaire=?";
            PreparedStatement pt = con.prepareStatement(req);
            pt.setInt(1, idFormulaire);
            pt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    // afficher les formulaires non confirmé 
    public List<Formulaire> getSimpleFormulairesNonConfirmes() {
        Statement st;
        List<Formulaire> arr = new ArrayList();
        try {
            st = con.createStatement();
            String req = "select * from Formulaire where validation=false";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                int idFormulaire = rs.getInt(1);
                String objet = rs.getString("objet");
                String description = rs.getString("descriptionFormulaire");
                String fichier = rs.getString("fichier");
                boolean validation = rs.getBoolean(5);
                Date dateEnvoi = rs.getDate(6);
                Formulaire f = new Formulaire(idFormulaire, objet, description, fichier, validation, dateEnvoi);
                arr.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return arr;
    }


}
