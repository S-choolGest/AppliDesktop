/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Formulaire;

import Entite.Formulaire.Formulaire;
import IServices.Formulaire.IServicesFormulaire;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ahmed
 */
public class ServicesFormulaire implements IServicesFormulaire<Formulaire> {

    private Connection con;
    private Statement ste;

    public ServicesFormulaire() {
        con = DataBase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Formulaire f) {
        try {
            ste = con.createStatement();
            String requeteInsert = "INSERT INTO formulaire VALUES (NULL, '" + f.getDescription() + "', '" + f.getObjet() + "',current_timestamp);";
            ste.executeUpdate(requeteInsert);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public void delete(int i) {
        try {
            String req = "delete from Formulaire where idFormulaire=" + i;
            PreparedStatement pt = con.prepareStatement(req);

            pt.executeUpdate(req);
            System.out.println("Formulaire supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Formulaire f) throws SQLException {
        try {
            String req = "UPDATE Formulaire SET 'descriptionFormulaire'" + f.getDescription() + ",'objet'" + f.getObjet() + ";";   //a completer   
            PreparedStatement ste = con.prepareStatement(req);
            ste.executeUpdate();
            System.out.println("Formulaire modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Formulaire> readall() throws SQLException {
        List<Formulaire> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from formulaire");
        while (rs.next()) {
            int idFormulaire = rs.getInt(1);
            String description = rs.getString("descriptionFormulaire");
            String objet = rs.getString("objet");
            Date dateEnvoi = rs.getDate(4);
            //Formulaire f = new Formulaire(idFormulaire, objet, description, true, dateEnvoi);
            //arr.add(f);
        }
        return arr;
    }
    
    /*
    public List<Formulaire> getSimpleEvenementsParMotCle(String motCle) {
        Statement st;
        List<Formulaire> ls = new ArrayList();
        try {
            st = con.createStatement();
            String req = "select * from Evenement where descriptionFormulaire like '%" + motCle + "%' or objet like '%" + motCle + "%'";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                ls.add(new Formulaire(rs.get, req));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ls;
    }
*/

}
