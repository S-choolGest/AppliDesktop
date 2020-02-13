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
public class ServicesFormulaire implements IServicesFormulaire<Formulaire>{
    
    private Connection con;
    private Statement ste;
    
    public ServicesFormulaire(){
        con = DataBase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Formulaire f) {
        try {
            ste = con.createStatement();
            String requeteInsert = "INSERT INTO `formulaire` (`idFormulaire`, `descriptionFormulaire`, `dateEnvoi`, `objet`) VALUES (NULL, '" + f.getDescription() + "', '" + f.getObjet() + "');";
            ste.executeUpdate(requeteInsert);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public void delete(Formulaire f) {
        Statement ste;
        try {
            String req = "delete from Pole where idFormulaire=?";
            PreparedStatement pt = con.prepareStatement(req);
            ste = con.createStatement();
            ste.executeUpdate(req);
            System.out.println("utilisateur supprimé" + f.toString());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Formulaire f) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Formulaire> readall() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     
}
