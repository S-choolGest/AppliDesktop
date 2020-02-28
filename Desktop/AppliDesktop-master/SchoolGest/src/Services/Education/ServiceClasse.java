/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Education;

import Entite.Education.Classe;
import IServices.Education.IServiceClasse;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MehdiS
 */
public class ServiceClasse implements IServiceClasse<Classe>{

    private Connection con;
    private Statement ste;
    
     public ServiceClasse() {
        con = DataBase.getInstance().getConnection();
    }
     
    @Override
    public ObservableList<String> readNomEtudiantClasse(String NomClasse) throws SQLException {
        ObservableList<String> arr = FXCollections.observableArrayList();
        System.out.println("classe"+NomClasse);
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("SELECT Distinct (select u.nom from utilisateur u where u.id = e.id) FROM etudiant e , classe c WHERE e.IDClasse = (SELECT Idclasse FROM classe WHERE nomClasse ='"+NomClasse+"')");
        while (rs.next()) {                
            String nom = rs.getString(1);
            arr.add(nom);
         }
        return arr;
        }
    
    public ObservableList<String> readAllS() throws SQLException {
    ObservableList<String> ClasseData = FXCollections.observableArrayList();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from classe");
    while (rs.next()) {                
               int id=rs.getInt(1);
               String nom =rs.getString(2);
               
     ClasseData.add(nom);
     }
    return ClasseData;
    }
}
