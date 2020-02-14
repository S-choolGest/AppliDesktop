/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services.Cours;

import Entite.Module;
import Entite.Specialite;
import Utils.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author saghir
 */
public class ServiceSpecialite implements IServices.Cours.IserviceCours<Object> {
    private final Connection con;
    private Statement ste;
    PreparedStatement pst;

    public ServiceSpecialite() {
        DataBase mc=DataBase.getInstance();
        con=mc.getConnection();
    }

    @Override
    public void ajouter(Object t1) {
        Specialite t=(Specialite )t1;
        try {
            
            
            String requeteInsert = "INSERT INTO specialite(nomSpecialite) VALUES(?)";
            
            pst =con.prepareStatement(requeteInsert);

            pst.setString(1, t.getNomSpecialite());

            pst.executeUpdate();
            System.out.println("specialité ajouté");
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSpecialite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(int id, Object t1) {
        if(t1 instanceof Specialite){
        Specialite t=(Specialite )t1;
        try {
            String requeteUpdate =
                    "UPDATE specialite SET nomSpecialite = ?  "+"WHERE idSpecialite='"+id+"'";

            pst =con.prepareStatement(requeteUpdate);

            pst.setString(1, t.getNomSpecialite());
            
            pst.executeUpdate();
            System.out.println("specialité modifier");


        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }      }
        if(t1 instanceof String){
        String t=(String )t1;
        try {
            String requeteUpdate =
                    "UPDATE specialite SET nomSpecialite = ?  "+"WHERE idSpecialite='"+id+"'";

            pst =con.prepareStatement(requeteUpdate);

            pst.setString(1, t);
            
            pst.executeUpdate();
            System.out.println("specialité modifier");


        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }      }
    }

    @Override
    public void supprimer(Object t1) {
        if(t1 instanceof Specialite){
        Specialite t=(Specialite )t1;
    try {
        String requeteDelete =
                "DELETE FROM specialite where nomSpecialite='"+t.getNomSpecialite()+"'";
                ste =con.createStatement();
                ste.executeUpdate(requeteDelete);
                System.out.println("Specialité supprimée____  ");
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }      }
        else if(t1 instanceof String){
        String t=(String )t1;
    try {
        String requeteDelete =
                "DELETE FROM specialite where nomSpecialite='"+t+"'";
                ste =con.createStatement();
                ste.executeUpdate(requeteDelete);
                System.out.println("Specialité supprimée____  ");
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }      }
        else if(t1 instanceof Integer){
        int t=(Integer )t1;
    try {
        String requeteDelete =
                "DELETE FROM specialite where idSpecialite='"+t+"'";
                ste =con.createStatement();
                ste.executeUpdate(requeteDelete);
                System.out.println("Specialité supprimée____  ");
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }      }
        
    }
    @Override
    public void afficherList() {
        ResultSet rs;
        String requete = "select * from specialite ";
        try {
            ste =con.createStatement();
            rs= ste.executeQuery(requete);
            while(rs.next()){
                
                System.out.println("ID_SPECIALITE: "+rs.getString(1)+" , NOM_SPECIALITE: "+rs.getString(2));

               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMatiere.class.getName()).log(Level.SEVERE, null, ex);
        }       }
}
