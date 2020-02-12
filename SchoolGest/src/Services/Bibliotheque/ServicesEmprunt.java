/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services.Bibliotheque;

import Entite.Bibliotheque.Emprunt;
import Entite.Bibliotheque.Etat;
import Entite.Utilisateur.Bibliothecaire;
import IServices.IServices;
import Utils.DataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author william
 */
public class ServicesEmprunt implements IServices<Emprunt>{
    
    private Connection con;
    private Statement ste;

    public ServicesEmprunt() {
        con = DataBase.getInstance().getConnection();
    }

    @Override
    public boolean ajouter(Emprunt e) throws SQLException {
        PreparedStatement pre = con.prepareStatement("insert INTO `edutech`.`emprunt` (`idemprunteur`, `idlivre`, `etat`, `dateEmprunt`, `dateConfirmation`, `dateRendu`) VALUES (?, ?, ?, ?, ?, ?);");
        pre.setInt(1, e.getIdEmprunteur());
        pre.setInt(2, e.getIdLivre());
        pre.setString(3, e.getEtat().toString());
        pre.setString(4, e.getDateEmprunt());
        pre.setString(5, e.getDateConfirmation());
        pre.setString(6, e.getDateRendu());
        return pre.executeUpdate() != 0;
    }

    @Override
    public boolean delete(Emprunt e) throws SQLException {
        PreparedStatement pre = con.prepareStatement("delete from `edutech`.`emprunt` where `id` =  ?");
        pre.setInt(1, e.getId());
        return pre.executeUpdate() != 0;
    }

    @Override
    public boolean update(Emprunt e) throws SQLException {
        PreparedStatement pre = con.prepareStatement("update `edutech`.`emprunt` set `idemprunteur` = ?, `idlivre` = ?, `etat` = ?, `dateemprunt` = ?, `dateconfirmation` = ?, `daterendu` = ? where `id` = ?;");
        pre.setInt(1, e.getIdEmprunteur());
        pre.setInt(2, e.getIdLivre());
        pre.setString(3, e.getEtat().toString());
        pre.setString(4, e.getDateEmprunt());
        pre.setString(5, e.getDateConfirmation());
        pre.setString(6, e.getDateRendu());
        pre.setInt(7, e.getId());
        return pre.executeUpdate() != 0;
    }

    @Override
    public List<Emprunt> readAll() throws SQLException {
        List<Emprunt> listE = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from emprunt");
        while (rs.next()) {
            int id = rs.getInt(1);
            int idemprunteur = rs.getInt(2);
            int idlivre = rs.getInt(3);
            String etat = rs.getString(4);
            String dateEmprunt = rs.getString(5);
            String dateConfirmation = rs.getString(6);
            String dateRendu = rs.getString(7);
            Emprunt b = new Emprunt(id, idemprunteur, idlivre, Etat.valueOf(etat), dateEmprunt, dateConfirmation, dateRendu);
            listE.add(b);
        }
        return listE;
    }
    
}
