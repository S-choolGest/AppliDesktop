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
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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
        String request = "INSERT INTO `encadrement` (`id`, `id_pfe`, `id_prof`, `etat`) VALUES (NULL,'"+d.getId_pfe()+"','"+d.getId_prof()+"','"+d.getEtat()+"')"; 
        st.executeUpdate(request);
        String mail = getMailParId(d.getId_prof());
        System.out.println(mail);
        String info = getInfoEtudiant(d.getId());
        try {
            JavaMailUtil.sendMail(mail,info);
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
        ResultSet r =st.executeQuery("SELECT * FROM `encadrement`");
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
        ResultSet r =st.executeQuery("SELECT * FROM `encadrement`");/*a changer*/
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
        String request = "UPDATE `encadrement` SET `etat` = 'acceptee' WHERE `encadrement`.`id`="+d.getId();
        st.executeUpdate(request);    
    
        
        try {
            String mail = getMailParDemande(d.getId());
            System.out.println(mail);
            AccDemandeMaill.sendMail(mail);
        } catch (Exception ex) {
            Logger.getLogger(DemandeEncadrementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        TrayNotification tray = new TrayNotification("Successfully",
                    "La demande est acceptee l'etudiant va recevoir un mail", NotificationType.SUCCESS);
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.seconds(10));
    
                    return "la demande est acceptee ";
    }

    @Override
    public String refuserDemande(DemandeEncadrement d) throws SQLException {
        st = conx.createStatement();
        String request = "UPDATE `encadrement` SET `etat` = 'refusee' WHERE `encadrement`.`id` ="+d.getId();
        st.executeUpdate(request);
         
        
        try {
            String mail = getMailParDemande(d.getId());
            System.out.println(mail);
            RefDemandeMail.sendMail(mail);
        } catch (Exception ex) {
            Logger.getLogger(DemandeEncadrementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        TrayNotification tray = new TrayNotification("Successfully",
                    "La demande est refusee l'etudiant va recevoir un mail", NotificationType.ERROR);
            tray.setAnimationType(AnimationType.SLIDE);
            tray.showAndDismiss(Duration.seconds(10));
        return "la demande est refusee ";
    }
    public ObservableList getMesDemandesEtudiant(int i) throws SQLException
    {
        ObservableList<DemandeEncadrement> DemandeEncadrementData = FXCollections.observableArrayList(); 
        String sql ="SELECT p.titre ,p.sujet,d.etat,d.id FROM utilisateur u,encadrement d,pfe p ,etudiant e WHERE d.id_pfe=p.id AND e.id=u.id AND u.id=?";
        PreparedStatement p=  conx.prepareStatement(sql);
        p.setInt(1, i);
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
    public ObservableList getMesDemandesProf(int i) throws SQLException{
    ObservableList<DemandeEncadrement> DemandeEncadrementData = FXCollections.observableArrayList(); 
    String sql ="SELECT s.titre , s.sujet , d.etat ,d.id FROM encadrement d,pfe s, professeur p,utilisateur u WHERE d.id_pfe=s.id AND d.id_prof=p.id AND p.id=u.id AND u.id=?;";;
    /*req a changer */
    PreparedStatement p =conx.prepareStatement(sql);
    p.setInt(1, i);
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
        String sql="SELECT u.id FROM utilisateur u,professeur p WHERE u.id=p.id AND u.email=?";
        PreparedStatement p = conx.prepareStatement(sql);
        p.setString(1, s);
    ResultSet r =p.executeQuery();
    while(r.next())
    {
        i =r.getInt(1);
    }
    return i;
        }
    public String getMailParId(int id) throws SQLException
    {
        String s = "";
        String ids = Integer.toString(id);
        String sql="SELECT u.email FROM utilisateur u WHERE u.id=?";
        PreparedStatement p = conx.prepareStatement(sql);
        p.setString(1, ids);
    ResultSet r =p.executeQuery();
    while(r.next())
    {
        s =r.getString(1);
    }
    return s;
        }
    public String getMailParDemande(int id) throws SQLException
    {
        String s = "";
        String ids = Integer.toString(id);
        String sql="SELECT u.email FROM encadrement d, etudiant e ,utilisateur u , pfe p WHERE d.id_pfe=p.id AND p.id_etudiant=e.id AND e.id=u.id and d.id=?";
        PreparedStatement p = conx.prepareStatement(sql);
        p.setString(1, ids);
    ResultSet r =p.executeQuery();
    while(r.next())
    {
        s =r.getString(1);
    }
    return s  ;
    }
    public String getInfoEtudiant(int id ) throws SQLException
    {
        String s = "";
        String ids = Integer.toString(id);
        String sql="SELECT u.nom,u.prenom FROM encadrement d, etudiant e ,utilisateur u , pfe p WHERE d.id_pfe=p.id AND p.id_etudiant=e.id AND e.id=u.id and d.id=?";
        PreparedStatement p = conx.prepareStatement(sql);
        p.setString(1, ids);
    ResultSet r =p.executeQuery();
    while(r.next())
    {
        String nom =r.getString(1);
        String prenom=r.getString(2);
        s=nom+prenom;

    }
    return s  ;
    }
}
    

