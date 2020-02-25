/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Pfe;

import Entite.pfe.Pfe;
import IServices.Pfe.IservicePfe;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author TOSHIBA
 */
public class PfeService implements IservicePfe{
private Connection conx;
    private Statement st;
    public PfeService(){
    conx = DataBase.getInstance().getConnection();
    }
    @Override
    public void ajouterPfe(Pfe p) throws SQLException {
        st = conx.createStatement();
        String request ="INSERT INTO `pfe` (`id`, `id_etudiant`, `sujet`, `titre`) VALUES(NULL,'"+p.getId_etudiant()+"','"+p.getSujet()+"','"+p.getTitre()+"')";
        st.executeUpdate(request);

    }

    @Override
    public void modifiePfe(Pfe p,String s,String c) throws SQLException {
      st = conx.createStatement();
      String request="UPDATE `pfe` SET `sujet`="+s+",`titre`="+c+" WHERE id="+p.getId();
      st.executeUpdate(request);
        

    }

    @Override
    public void supprimerPfe(Pfe p) throws SQLException {
        st = conx.createStatement();
        String request = "DELETE FROM `pfe` WHERE id = "+p.getId();
        st.executeUpdate(request);  
    }

    @Override
   
   
    public List<Pfe> readAll() throws SQLException {
        List<Pfe>l =new ArrayList<>();
        st = conx.createStatement();
        ResultSet r =st.executeQuery("SELECT * FROM `pfe`");
       while( r.next()){
       int id=r.getInt("id");
       int id_etudiant=r.getInt("id_etudiant");
       String sujet=r.getString("sujet");
       String titre = r.getString("titre");
       Pfe p = new Pfe(id, id_etudiant, sujet, titre);
       l.add(p);
       }
       return l ;
    
    }
    public ObservableList<Pfe> readAllOb() throws SQLException{
        st =conx.createStatement();
        ObservableList<Pfe> pfeData = FXCollections.observableArrayList(); 
        ResultSet r =st.executeQuery("SELECT * FROM `pfe`");
        while(r.next())
        {
            int id = r.getInt(1);
            int ide =r.getInt(2);
            String titre =r.getString(4);
            String sujet =r.getString(3);
            Pfe p =new Pfe(id, ide, sujet, titre);
            pfeData.add(p);
        }
        return pfeData;
    }

    
    public Pfe getPfe(int id) throws SQLException{
        String sql= "SELECT * FROM `pfe` WHERE id =?";
        Pfe p =new Pfe();
        PreparedStatement st = conx.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet r = st.executeQuery();
        if(r.next()){
        p.setId(r.getInt(1));
        p.setId_etudiant(r.getInt(2));
        p.setSujet(r.getString(3));
        p.setTitre(r.getString(4));
        
        }
        return p;
    }
    public ObservableList getMesPfe() throws SQLException{
        PfeService pes = new PfeService();
        st = conx.createStatement();
        ObservableList<Pfe> pfeData = FXCollections.observableArrayList(); 
        ResultSet r =st.executeQuery("SELECT * FROM `pfe` WHERE id_etudiant=1");/*a changer avec getseesion id*/
        while (r.next())
        {
            Pfe p = new Pfe();
            p.setSujet(r.getString("sujet"));
            p.setTitre(r.getString("titre"));
            p.setId(r.getInt("id"));
            pfeData.add(p);
        }
        return pfeData;
    }
  
    
}
