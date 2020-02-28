 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Education;

import Entite.Education.*;
import IServices.Cours.IserviceCours;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author saghir
 */

public class ServiceModule implements IserviceCours<Object> {

    private final Connection con;
    private Statement ste;
    PreparedStatement pst;
    ResultSet rs;

    public ServiceModule() throws SQLException {
        DataBase mc = DataBase.getInstance();
        con = mc.getConnection();
        calculCoefModule();
    }

    @Override
    public void ajouter(Object t1) {
        Module t = (Module) t1;
        try {
            String requeteInsert = "INSERT INTO module(nomModule, coefModule) VALUES(?,?)";

            pst = con.prepareStatement(requeteInsert);

            pst.setString(1, t.getNomModule());
            pst.setInt(2, t.getCoefModule());
            pst.executeUpdate();
            System.out.println("Module ajouté");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceModule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(int id, Object t1) {
        Module t = (Module) t1;
        try {
            String requeteUpdate
                    = "UPDATE module SET nomModule = ?  " + "WHERE idModule='" + id + "'";

            pst = con.prepareStatement(requeteUpdate);

            pst.setString(1, t.getNomModule());

            pst.executeUpdate();
            System.out.println("Module modifié");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(Object t1) {
        if (t1 instanceof Module) {
            Module t = (Module) t1;
            try {
                String requeteDelete
                        = "DELETE FROM module where nomModule='" + t.getNomModule() + "'";
                ste = con.createStatement();
                ste.executeUpdate(requeteDelete);
                System.out.println("Module supprimée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (t1 instanceof Integer) {
            int t = (Integer) t1;
            try {
                String requeteDelete
                        = "DELETE FROM module where idModule='" + t + "'";
                ste = con.createStatement();
                ste.executeUpdate(requeteDelete);
                System.out.println("Module supprimée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (t1 instanceof String) {
            String t = (String) t1;
            try {
                String requeteDelete
                        = "DELETE FROM module where nomModule='" + t + "'";
                ste = con.createStatement();
                ste.executeUpdate(requeteDelete);
                System.out.println("Module supprimée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void afficherList() {

        String requete = "select * from module ";
        try {
            ste = con.createStatement();
            rs = ste.executeQuery(requete);
            while (rs.next()) {
                System.out.println("ID_MODULE: " + rs.getString(1) + " , NOM_MODULE: " + rs.getString(2) + " , COEFFICIENT_MODULE: " + rs.getString(3));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMatiere.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Module> getAllModule() {
        ObservableList<Module> list = FXCollections.observableArrayList();
        String requete = "select * from module ";
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(requete);
            while (rs.next()) {

//                    list.add(new User(rs.getString("nom"), rs.getString("prenom")));
                list.add(new Module(rs.getInt(1), rs.getInt(2),
                        rs.getString(3), rs.getInt(4)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSpecialite.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public void calculCoefModule() throws SQLException {
        ObservableList<Module> modules = getAllModule();
        ServiceMatiere svm = new ServiceMatiere();
String requete
                    = "UPDATE module SET coefModule = 0  where idModule!=0";

            pst = DataBase.getInstance().getConnection().prepareStatement(requete);
            pst.executeUpdate();
        for (Module module : modules) {

                    String requeteUpdate
                            = "UPDATE module SET module.coefModule = (select sum(matiere.coefMatiere) from matiere where matiere.idModule='"+module.getIdModule()+"')" + "WHERE module.idModule='" + module.getIdModule() + "'";
                    pst = DataBase.getInstance().getConnection().prepareStatement(requeteUpdate);
                    

                    pst.executeUpdate();
                }
            }

        }

    

