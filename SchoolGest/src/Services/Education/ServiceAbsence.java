/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Education;

import Entite.Education.Absence;
import Entite.Utilisateur.Etudiant;
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
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `EduTek`.`absence` (`id`, `matiere`, `date`, `etat`,`idEtudiant`) VALUES (NULL, '" +  a.getMatiere() + "', '" + a.getDate() + "', '"+a.getetat() + "','"+a.getIdEtudiant()+"');";
        
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public boolean delete(Absence a) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "DELETE FROM `EduTek`.`absence` WHERE `matiere` = '"+a.getMatiere()+"'"+"and"+"`date` = '"+a.getDate()+"'"  ;
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
         String requeteInsert ="UPDATE `absence` SET `date`= '"+a.getDate()+"',`etat`='"+a.getetat()+"'WHERE `matiere` = '"+a.getMatiere()+"'";
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
               Timestamp date=rs.getTimestamp(3);
               boolean Etat=rs.getBoolean(4);
               int IdEtudiant = rs.getInt(5);
               Absence p = new Absence(id,matiere,date, Etat,IdEtudiant);
     arr.add(p);
     }
    return arr;
    }
    
     public ObservableList<Absence> readAllV2() throws SQLException {
    ObservableList<Absence> AbsenceData = FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from absence");
    while (rs.next()) {                
               int id=rs.getInt(1);
               String matiere=rs.getString(2);
               Timestamp date=rs.getTimestamp(3);
               boolean Etat=rs.getBoolean(4);
               int IdEtudiant = rs.getInt(5);
               Absence p = new Absence(id,matiere,date, Etat,IdEtudiant);
     AbsenceData.add(p);
     }
    return AbsenceData;
    }
    
    
    public List readAbsStudent(Etudiant e)throws SQLException{
         List<Absence> arr=new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from absence where `idEtudiant`= " +e.getIdEtudiant()); 
         while (rs.next()) {       
               String matiere=rs.getString(2);
               Timestamp date=rs.getTimestamp(3);
               boolean Etat=rs.getBoolean(4);
               Absence p = new Absence(matiere,date, Etat);
     arr.add(p);
     }
    return arr;
    }
    
}
