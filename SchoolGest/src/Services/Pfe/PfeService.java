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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println("wiiw");
        String request ="INSERT INTO `pfe` (`id`, `id_etudiant`, `sujet`, `cahier_charge`) VALUES(NULL,'"+p.getId_etudiant()+"','"+p.getSujet()+"','"+p.getCahier_charge()+"')";
        st.executeUpdate(request);

    }

    @Override
    public void modifiePfe(Pfe p,String s,String c) throws SQLException {
      st = conx.createStatement();
      String request="UPDATE `pfe` SET `sujet`="+s+",`cahier_charge`="+c+" WHERE id="+p.getId();
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
       String cahier_charge = r.getString("cahier_charge");
       Pfe p = new Pfe(id, id_etudiant, sujet, cahier_charge);
       l.add(p);
       }
       return l ;
    
    }

  
    
}
