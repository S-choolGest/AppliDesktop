/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services.Bibliotheque;

import Entite.Bibliotheque.Bibliotheque;
import IServices.IServices;
import Utils.DataBase;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author william
 */
public class ServicesBibliotheque implements IServices<Bibliotheque>{

    private Connection con;
    private Statement ste;

    public ServicesBibliotheque() {
        con = DataBase.getInstance().getConnection();
    }
    
    @Override
    public boolean ajouter(Bibliotheque b) throws SQLException {
        PreparedStatement pre = con.prepareStatement("insert INTO `edutech`.`bibliotheque` (`nom`, `capacite`) VALUES (?, ?);");
        pre.setString(1, b.getNom());
        pre.setInt(2, b.getCapacite());
        return pre.executeUpdate() != 0;
    }

    @Override
    public boolean delete(Bibliotheque b) throws SQLException {
        PreparedStatement pre = con.prepareStatement("delete from `edutech`.`Bibliotheque` where `id` =  ?");
        pre.setInt(1, b.getId());
        return pre.executeUpdate() != 0;
    }

    @Override
    public boolean update(Bibliotheque b) throws SQLException {
        PreparedStatement pre = con.prepareStatement("update `edutech`.`Bibliotheque` set `nom` = ?, `capacite` = ? where `id` = ?;");
        pre.setString(1, b.getNom());
        pre.setInt(2, b.getCapacite());
        return pre.executeUpdate() != 0;
    }

    @Override
    public List<Bibliotheque> readAll() throws SQLException {
        List<Bibliotheque> listB = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from Bibliotheque");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString("nom");
            int capacite = rs.getInt("capacite");
            Bibliotheque b = new Bibliotheque(id, nom, capacite);
            listB.add(b);
        }
        return listB;
    }
    
}
