/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services.Bibliotheque;

import Entite.Bibliotheque.Emprunt;
import Entite.Bibliotheque.Etat;
import Entite.Bibliotheque.Livre;
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
public class ServicesLivres implements IServices<Livre>{

    private Connection con;
    private Statement ste;

    public ServicesLivres() {
        con = DataBase.getInstance().getConnection();
    }
    
    @Override
    public boolean ajouter(Livre l) throws SQLException {
        PreparedStatement pre = con.prepareStatement("insert INTO `edutech`.`livre` (`titre`, `auteur`, `editeur`, `categorie`, `datesortie`, `taille`, `quantite`) VALUES (?, ?, ?, ?, ?, ?, ?);");
        pre.setString(1, l.getTitre());
        pre.setString(2, l.getAuteur());
        pre.setString(3, l.getEditeur());
        pre.setString(4, l.getCategorie());
        pre.setString(5, l.getDateSortie());
        pre.setInt(6, l.getTaille());
        pre.setInt(7, l.getQuantite());
        return pre.executeUpdate() != 0;
    }

    @Override
    public boolean delete(Livre l) throws SQLException {
        PreparedStatement pre = con.prepareStatement("delete from `edutech`.`livre` where `id` =  ?");
        pre.setInt(1, l.getId());
        return pre.executeUpdate() != 0;
    }

    @Override
    public boolean update(Livre l) throws SQLException {
        PreparedStatement pre = con.prepareStatement("update `edutech`.`livre` set `titre` = ?, `auteur` = ?, `editeur` = ?, `categorie` = ?, `datesortie` = ?, `taille` = ?, `quantite` = ? where `id` = ?;");
        pre.setString(1, l.getTitre());
        pre.setString(2, l.getAuteur());
        pre.setString(3, l.getEditeur());
        pre.setString(4, l.getCategorie());
        pre.setString(5, l.getDateSortie());
        pre.setInt(6, l.getTaille());
        pre.setInt(7, l.getQuantite());
        pre.setInt(8, l.getId());
        return pre.executeUpdate() != 0;
    }

    @Override
    public List<Livre> readAll() throws SQLException {
        List<Livre> listE = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from livre");
        while (rs.next()) {
            int id = rs.getInt(1);
            String titre = rs.getString(2);
            String auteur = rs.getString(3);
            String editeur = rs.getString(4);
            String categorie = rs.getString(5);
            String datesortie = rs.getString(6);
            int taille = rs.getInt(7);
            int quantite = rs.getInt(8);
            Livre l = new Livre(id, titre, auteur, editeur, categorie, datesortie, taille, quantite);
            listE.add(l);
        }
        return listE;
    }
    
}
