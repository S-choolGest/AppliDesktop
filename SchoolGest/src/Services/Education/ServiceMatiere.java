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

/**
 *
 * @author saghir
 */
public class ServiceMatiere implements IserviceCours<Object> {
    private final Connection con;
    private Statement ste;
    PreparedStatement pst;

    public ServiceMatiere() {
        DataBase mc=DataBase.getInstance();
        con=mc.getConnection();
    }
    
    
    
    @Override
    public void ajouter(Object e1) {
        Matiere e=(Matiere)e1;
        try {
            
            
            String requeteInsert = "INSERT INTO matiere(nomMatiere,coefMatiere) VALUES(?,?)";
            
            pst =con.prepareStatement(requeteInsert);

            pst.setString(1, e.getNomMatiere());
            pst.setInt(2, e.getCoefMatiere());

            pst.executeUpdate();
            System.out.println("matiere ajouté");
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMatiere.class.getName()).log(Level.SEVERE, null, ex);
        }
	 
    }

    @Override
    public void modifier(int id, Object e1) {
        if(e1 instanceof Matiere){
        Matiere m=(Matiere)e1;
        
        try {
            String requeteUpdate =
                    "UPDATE matiere SET nomMatiere = ? , coefMatiere = ? "+"WHERE idMatiere='"+id+"'";

            pst =con.prepareStatement(requeteUpdate);

            pst.setString(1, m.getNomMatiere());
            pst.setInt(2, m.getCoefMatiere());
            

            pst.executeUpdate();
            System.out.println("matiere modifier");


        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }    
        }
        else if(e1 instanceof String){
            String m = (String)e1;
         try {
            String requeteUpdate =
                    "UPDATE matiere SET nomMatiere = ?  "+"WHERE idMatiere='"+id+"'";

            pst =con.prepareStatement(requeteUpdate);

            pst.setString(1, m);
            
            

            pst.executeUpdate();
            System.out.println("matiere modifier");


        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }       
        }
        else if(e1 instanceof Integer){
            int m = (Integer)e1;
         try {
            String requeteUpdate =
                    "UPDATE matiere SET coefMatiere = ?  "+"WHERE idMatiere='"+id+"'";

            pst =con.prepareStatement(requeteUpdate);

            pst.setInt(1, m);
            
            

            pst.executeUpdate();
            System.out.println("matiere modifier");


        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }       
        }
    }

    @Override
    public void supprimer(Object m1) {
        Matiere m =(Matiere)m1;
        try {
        String requeteDelete =
                "DELETE FROM matiere where matiere.nomMatiere='"+m.getNomMatiere()+"'";
                ste =con.createStatement();
                ste.executeUpdate(requeteDelete);
                System.out.println("user supprimée____ ");
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }     }

    @Override
    public void afficherList() {
        ResultSet rs;
        String requete = "select * from matiere ";
        try {
            ste =con.createStatement();
            rs= ste.executeQuery(requete);
            while(rs.next()){
                System.out.println("ID: "+rs.getString("idMatiere")+" , Nom: "+rs.getString("nomMatiere")+" , Coefficient: "+rs.getString("coefmatiere"));

               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMatiere.class.getName()).log(Level.SEVERE, null, ex);
        }    }
    
}
