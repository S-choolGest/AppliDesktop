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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    public boolean update(Formulaire f) throws SQLException {
        ste = con.createStatement();
        String req = "update formulaire set objet = '"
                +f.getObjet()
                +"',descriptionFormulaire='"
                +f.getDescription()
                +"',fichier='"
                +f.getFichier()
                +"',dateEnvoi='"
                +f.getDateEnvoi()
                +"' where idFormulaire = '"
                +f.getIdFormulaire()+"'";
        if(ste.executeUpdate(req)==1){
            System.out.println("modification effectué");
            return  true;
        };
        System.out.println("modification échoué");
        return false;
        
    }
        
    

    //affichage de la liste
    @Override
    public ObservableList<Formulaire> readall() throws SQLException {
        ObservableList<Formulaire> arr = FXCollections.observableArrayList();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from formulaire");
        while (rs.next()) {
            int idFormulaire = rs.getInt(1);
            String objet = rs.getString("objet");
            String description = rs.getString("descriptionFormulaire");
            String fichier = rs.getString("fichier");
            boolean validation = rs.getBoolean(5);
            Timestamp dateEnvoi = rs.getTimestamp(6);
            Formulaire f = new Formulaire(idFormulaire, objet, description, fichier, validation, dateEnvoi);
            arr.add(f);
        }
        return arr;
    }
    
    public Formulaire getFormulaire(int idFormulaire) {
        Statement st;
        try {
            st = con.createStatement();
            String req = "select * from Formulaire where id = '" + idFormulaire + "'";
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                return new Formulaire(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getTimestamp(6));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
    
    
    
    public List<Formulaire> search(String t) throws SQLException {
        List<Formulaire> formulaire = new ArrayList<>();
        formulaire = readall();
        List<Formulaire> fo = formulaire.stream()
                .filter(a -> a.getObjet().contains(t) || a.getDescription().contains(t) || a.getDateEnvoi().contains(t))
                .collect(Collectors.toList());
        return fo;
    }
    

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
                Timestamp dateEnvoi = rs.getTimestamp(6);
                Formulaire f = new Formulaire(idFormulaire, objet, description, fichier, validation, (Timestamp) dateEnvoi);
                arr.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return arr;
    }
    
    // afficher les formulaires non confirmé 
    public List<Formulaire> getSimpleFormulairesConfirmes() {
        Statement st;
        List<Formulaire> arr = new ArrayList();
        try {
            st = con.createStatement();
            String req = "select * from Formulaire where validation=true";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                int idFormulaire = rs.getInt(1);
                String objet = rs.getString("objet");
                String description = rs.getString("descriptionFormulaire");
                String fichier = rs.getString("fichier");
                boolean validation = rs.getBoolean(5);
                Timestamp dateEnvoi = rs.getTimestamp(6);
                Formulaire f = new Formulaire(idFormulaire, objet, description, fichier, validation, dateEnvoi);
                arr.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return arr;
    }


}
