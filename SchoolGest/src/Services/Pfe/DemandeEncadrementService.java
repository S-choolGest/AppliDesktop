/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Pfe;

import Api.pfe.AccDemandeMaill;
import Api.pfe.JavaMailUtil;
import Api.pfe.RefDemandeMail;
import Entite.pfe.DemandeEncadrement;
import IServices.Pfe.Iservice;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author TOSHIBA
 */
    public class DemandeEncadrementService implements Iservice {
    private Connection conx;
    private Statement st;
    public DemandeEncadrementService(){
    conx = DataBase.getInstance().getConnection();
    }
    @Override
    public void ajouterDemande(DemandeEncadrement d) throws SQLException {
        st = conx.createStatement();
        String request = "INSERT INTO `encadrement` (`id`, `id_pfe`, `id_prof`, `etat`) VALUES (NULL,'"+d.getId_pfe()+"','"+d.getId_prof()+"','"+d.getEtat()+")"; 
        st.executeUpdate(request);
        try {
            JavaMailUtil.sendMail("wazkasmi@gmail.com");
        } catch (Exception ex) {
            Logger.getLogger(DemandeEncadrementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /*
        @Override
    
    public void modifierDemande(DemandeEncadrement d,String desc) throws SQLException {
st = conx.createStatement();
        if (d.getEtat().equalsIgnoreCase("en_attente")){
        String request = "UPDATE `demande` SET `description`="+desc+" WHERE id="+d.getId();
        st.executeUpdate(request);
            }
  
} */  
    @Override
    public void supprimerDemande(DemandeEncadrement d) throws SQLException{
     st = conx.createStatement();
        String request = "DELETE FROM `encadrement` WHERE id = "+d.getId();
        st.executeUpdate(request);
        
    
        
     
    }
    @Override
    public List<DemandeEncadrement> readAll() throws SQLException{  
        List<DemandeEncadrement>l =new ArrayList<>();
        st = conx.createStatement();
        ResultSet r =st.executeQuery("SELECT * FROM `demande`");
       while( r.next()){
       int id=r.getInt("id");
       int id_prof=r.getInt("id_prof");
       int id_pfe=r.getInt("id_pfe");
       String etat = r.getString("etat");
       DemandeEncadrement d = new DemandeEncadrement(id_prof, id_pfe,etat);
       l.add(d);
       }
       return l ;
    }
    public List<DemandeEncadrement> readDEmEtud() throws SQLException{  
        List<DemandeEncadrement>l =new ArrayList<>();
        st = conx.createStatement();
        ResultSet r =st.executeQuery("SELECT * FROM `demande`");/*a changer*/
       while( r.next()){
       int id=r.getInt("id");
       int id_prof=r.getInt("id_prof");
       int id_pfe=r.getInt("id_pfe");
       String etat = r.getString("etat");
       DemandeEncadrement d = new DemandeEncadrement(id_prof, id_pfe,etat);
       l.add(d);
       }
       return l ;
    }

    @Override
    public String AccepterDemande(DemandeEncadrement d) throws SQLException {
    st = conx.createStatement();
        if (d.getEtat().equalsIgnoreCase("en_attente")){
        String request = "UPDATE `demande` SET `etat`=\"acceptee\" WHERE id = "+d.getId();
        st.executeUpdate(request);    
    }
        else 
        { return "cette demande est deja "+d.getEtat();
                }
        try {
            AccDemandeMaill.sendMail("wazkasmi@gmail.com");
        } catch (Exception ex) {
            Logger.getLogger(DemandeEncadrementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "la demande est acceptee ";
    }

    @Override
    public String refuserDemande(DemandeEncadrement d) throws SQLException {
        st = conx.createStatement();
        if (d.getEtat().equalsIgnoreCase("en_attente")){
        String request = "UPDATE `encadrement` SET `etat`=\"refusee\" WHERE id = "+d.getId();
        st.executeUpdate(request);
        }   
        else 
        { return "cette demande est deja "+d.getEtat();
                }
        try {
            RefDemandeMail.sendMail("wazkasmi@gmail.com");
        } catch (Exception ex) {
            Logger.getLogger(DemandeEncadrementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "la demande est refusee ";
    }
    public ObservableList getMesDemandesEtudiant() throws SQLException
    {
        ObservableList<DemandeEncadrement> DemandeEncadrementData = FXCollections.observableArrayList(); 
        String sql ="SELECT p.titre ,p.sujet,d.etat,d.id FROM demande d,pfe p ,etudiant e WHERE d.id_pfe=p.id AND e.id=?";
        PreparedStatement p=  conx.prepareStatement(sql);
        p.setInt(1, 1);
        ResultSet r=p.executeQuery();
        while(r.next())
        {   int id =r.getInt("id");
         String titre =r.getString("titre");
            String Sujet=r.getString("sujet");
            String etat =r.getString("etat");
            DemandeEncadrement d =new DemandeEncadrement(id ,Sujet, titre, etat);

            DemandeEncadrementData.add(d);
        }
        return DemandeEncadrementData;
    }
    public ObservableList getMesDemandesProf() throws SQLException{
    ObservableList<DemandeEncadrement> DemandeEncadrementData = FXCollections.observableArrayList(); 
    String sql ="SELECT s.titre , s.sujet , d.etat ,d.id FROM demande d,pfe s, professeur p WHERE d.id_pfe=s.id AND d.id_prof=p.id AND p.id=?;";
    /*req a changer */
    PreparedStatement p =conx.prepareStatement(sql);
    p.setInt(1, 1);
    ResultSet r=p.executeQuery();
    while(r.next())
    {   
        int id =r.getInt("id");
        String titre =r.getString("titre");
        String Sujet=r.getString("sujet");
        String etat =r.getString("etat");
        DemandeEncadrement d =new DemandeEncadrement(id ,Sujet, titre, etat);

        DemandeEncadrementData.add(d);
    }   
        return DemandeEncadrementData;
    }
    public int getIdUserParMail(String s) throws SQLException
    {
        int i = -1 ;
        String sql="SELECT id FROM `professeur` WHERE email=?";
        PreparedStatement p = conx.prepareStatement(sql);
        p.setString(1, s);
    ResultSet r =p.executeQuery();
    while(r.next())
    {
        i =r.getInt(1);
    }
    return i;
        }
}
    

