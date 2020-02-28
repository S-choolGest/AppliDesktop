/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services.Education;

import Entite.Education.*;
import Utils.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author saghir
 */

public class ServiceSpecialite implements IServices.Cours.IserviceCours<Object> {
   DataBase mc=DataBase.getInstance();
    private Connection con=mc.getConnection();
    private Statement ste;
    PreparedStatement pst=null;
    ResultSet rs;

    public ServiceSpecialite() {
        
        DataBase mc=DataBase.getInstance();
        con=mc.getConnection();
    }

    public Connection getCon() {
        return con;
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
        Statement ste =con.createStatement();
                 if(ste!=null){        
        System.out.println("l'objet ste n'est pas null");
        }
        else System.out.println("l'objet ste est null");
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
        if(ste!=null){        
        System.out.println("l'objet ste n'est pas null");
        }
        else System.out.println("l'objet ste est null");
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
        ServiceSpecialite as = new ServiceSpecialite();
                ObservableList<Specialite> list = FXCollections.observableArrayList();
        String requete = "select * from specialite ";
        try {
            ste =getCon().createStatement();
            rs= ste.executeQuery(requete);
            while(rs.next()){
                
                System.out.println("ID_SPECIALITE: "+rs.getString(1)+" , NOM_SPECIALITE: "+rs.getString(2));
                
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMatiere.class.getName()).log(Level.SEVERE, null, ex);
        }       
}
    
    public ObservableList<Specialite> getAllSpecialite() {
        ObservableList<Specialite> list = FXCollections.observableArrayList();
        String requete = "select * from specialite ";
        try {
            Statement st =con.createStatement();
            rs= st.executeQuery(requete);
            while(rs.next()){

//                    list.add(new User(rs.getString("nom"), rs.getString("prenom")));
                list.add(new Specialite(rs.getInt(1),
                        rs.getString(2)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSpecialite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list ;
    }

    

}
