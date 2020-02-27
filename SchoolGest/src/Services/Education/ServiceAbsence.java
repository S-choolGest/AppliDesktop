/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Education;

import Entite.Education.Absence;
import Entite.Utilisateur.Etudiant;
import Entite.Utilisateur.Utilisateur;
import IServices.Education.IServicesAbsences;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author MehdiS
 */
public class ServiceAbsence implements IServicesAbsences<Absence> {

     private Connection con;
     private Statement ste;
   
     public ServiceAbsence() {
        con = DataBase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Absence a) throws SQLException {
        System.out.println("im in ajouter");
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `absence` (`id`, `matiere`, `date`, `etat`,`idEtudiant`) VALUES (NULL, '" +  a.getMatiere() + "', '" + a.getDate() + "', '"+a.getetat() + "','"+a.getIdEtudiant()+"');";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public boolean delete(Absence a) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "DELETE FROM `absence` WHERE `id` = '"+a.getIdS()+"'";
        if(ste.executeUpdate(requeteInsert) == 1){
            System.out.println("Suppression effectué");
            return true;
        };
        System.out.println("Suppression non effectué");
        return false;
    }

    @Override
    public boolean update(Absence a) throws SQLException {
         ste = con.createStatement();
         System.out.println(a.getId());
         System.out.println(a.getetat());
         System.out.println(a.getDate());
         String requeteInsert ="UPDATE `absence` SET `etat`='"+a.getetat()+"' WHERE `id` = '"+a.getId()+"'";
         if(ste.executeUpdate(requeteInsert) == 1){
            System.out.println("modification effectué");
            return true;
        };
        System.out.println("modification non effectué");
        return false;
    }

    @Override
    public List readAll() throws SQLException {
        List<Absence> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from absence");
    while (rs.next()) {                
               int id=rs.getInt(1);
               String matiere=rs.getString(2);
               String Etat=rs.getString(3);
               Timestamp date=rs.getTimestamp(4);
               int IdEtudiant = rs.getInt(5);
               Absence p = new Absence(id,matiere,date, Etat,IdEtudiant);
     arr.add(p);
     }
    return arr;
    }
    
    public ObservableList<Absence> readAllV2() throws SQLException {
    ObservableList<Absence> AbsenceData = FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT a.id , a.matiere , a.etat , a.date , (SELECT u.nom WHERE a.idEtudiant = u.id) FROM absence a , utilisateur u WHERE a.idEtudiant = u.id");
    while (rs.next()) {      
               int id = rs.getInt(1);
               String matiere=rs.getString(2);
               Timestamp date=rs.getTimestamp(4);
               String Etat=rs.getString(5);
               String nomEtudiant = rs.getString(3);
               Absence p = new Absence(id,matiere,date, Etat,nomEtudiant);
     AbsenceData.add(p);
     }
    return AbsenceData;
    }
    public ObservableList<Absence> readAllVEtudiant(Utilisateur a) throws SQLException {
    ObservableList<Absence> AbsenceData = FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT a.id , a.matiere , a.etat , a.date , (SELECT u.nom WHERE a.idEtudiant = u.id) FROM absence a , utilisateur u  WHERE a.idEtudiant = u.id and a.idEtudiant= "+a.getId());
    while (rs.next()) {      
               int id = rs.getInt(1);
               String matiere=rs.getString(2);
               Timestamp date=rs.getTimestamp(4);
               String Etat=rs.getString(5);
               String nomEtudiant = rs.getString(3);
               Absence p = new Absence(id,matiere,date, Etat,nomEtudiant);
     AbsenceData.add(p);
     }
    return AbsenceData;
    }
    
    public ObservableList<Absence> readAllVEtudiantParent(Utilisateur a) throws SQLException {
    ObservableList<Absence> AbsenceData = FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT a.id , a.matiere , a.etat , a.date , (SELECT u.nom WHERE a.idEtudiant = u.id) FROM absence a , utilisateur u WHERE a.idEtudiant = u.id and a.idEtudiant= (SELECT e.id FROM etudiant e WHERE e.cin_p =" +a.getCin() +")");
    while (rs.next()) {      
               int id = rs.getInt(1);
               String matiere=rs.getString(2);
               Timestamp date=rs.getTimestamp(4);
               String Etat=rs.getString(5);
               String nomEtudiant = rs.getString(3);
               Absence p = new Absence(id,matiere,date, Etat,nomEtudiant);
     AbsenceData.add(p);
     }
    return AbsenceData;
    }
    
     
    public ObservableList<String> readAllS() throws SQLException {
    ObservableList<String> AbsenceData = FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from absence");
    while (rs.next()) {                
               int id=rs.getInt(1);
               String matiere=rs.getString(2);
               Timestamp date=rs.getTimestamp(4);
               String Etat=rs.getString(3);
               int IdEtudiant = rs.getInt(5);
               //Absence p = new Absence(id,matiere,date, Etat,IdEtudiant);
     AbsenceData.add(date.toString());
     }
    return AbsenceData;
    }
    
    
    public int readAbsStudent(String nom)throws SQLException{
        int id = -1;
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select id from utilisateur u where u.nom ='" +nom+"'"); 
        while (rs.next()) {       
               id =rs.getInt(1);
        }
        return id;
    }
    
    public ObservableList<Absence> readAllV2(Etudiant e) throws SQLException {
    ObservableList<Absence> AbsenceData = FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT a.id , a.matiere , a.etat , a.date , (SELECT e.nom WHERE"+e.getId()+"= e.id) FROM absence a , etudiant e WHERE "+e.getId()+"= e.id");
    while (rs.next()) {      
               int id = rs.getInt(1);
               String matiere=rs.getString(2);
               Timestamp date=rs.getTimestamp(4);
               String Etat=rs.getString(5);
               String nomEtudiant = rs.getString(3);
               Absence p = new Absence(id,matiere,date, Etat,nomEtudiant);
     AbsenceData.add(p);
     }
    return AbsenceData;
    }
    
    public ObservableList<Absence> readEduAbs(String cin) throws SQLException {
    ObservableList<Absence> AbsenceData = FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT a.id , a.matiere , a.etat , a.date , (SELECT u.nom WHERE a.idEtudiant = u.id) FROM absence a , utilisateur u WHERE a.idEtudiant = u.id and u.cin='"+cin+"'");
    while (rs.next()) {      
               int id = rs.getInt(1);
               String matiere=rs.getString(2);
               Timestamp date=rs.getTimestamp(4);
               String Etat=rs.getString(5);
               String nomEtudiant = rs.getString(3);
               Absence p = new Absence(id,matiere,date, Etat,nomEtudiant);
     AbsenceData.add(p);
     }
    return AbsenceData;
    }
}
