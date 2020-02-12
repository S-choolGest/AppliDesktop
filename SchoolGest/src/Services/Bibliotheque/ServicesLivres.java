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
        PreparedStatement pre = con.prepareStatement("insert INTO `edutech`.`livre` (`id_bibliotheque`, `titre`, `auteur`, `editeur`, `categorie`, `datesortie`, `taille`, `quantite`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
        pre.setInt(1, l.getId_bibliotheque());
        pre.setString(2, l.getTitre());
        pre.setString(3, l.getAuteur());
        pre.setString(4, l.getEditeur());
        pre.setString(5, l.getCategorie());
        pre.setString(6, l.getDateSortie());
        pre.setInt(7, l.getTaille());
        pre.setInt(8, l.getQuantite());
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
        PreparedStatement pre = con.prepareStatement("update `edutech`.`livre` set `id_bibliotheque` = ?, `titre` = ?, `auteur` = ?, `editeur` = ?, `categorie` = ?, `datesortie` = ?, `taille` = ?, `quantite` = ? where `id` = ?;");
        pre.setInt(1, l.getId_bibliotheque());
        pre.setString(2, l.getTitre());
        pre.setString(3, l.getAuteur());
        pre.setString(4, l.getEditeur());
        pre.setString(5, l.getCategorie());
        pre.setString(6, l.getDateSortie());
        pre.setInt(7, l.getTaille());
        pre.setInt(8, l.getQuantite());
        pre.setInt(9, l.getId());
        return pre.executeUpdate() != 0;
    }

    @Override
    public List<Livre> readAll() throws SQLException {
        List<Livre> listE = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from livre");
        while (rs.next()) {
            int id = rs.getInt(1);
            int id_bibliotheque = rs.getInt(2);
            String titre = rs.getString(3);
            String auteur = rs.getString(4);
            String editeur = rs.getString(5);
            String categorie = rs.getString(6);
            String datesortie = rs.getString(7);
            int taille = rs.getInt(8);
            int quantite = rs.getInt(9);
            Livre l = new Livre(id, id_bibliotheque, titre, auteur, editeur, categorie, datesortie, taille, quantite);
            listE.add(l);
        }
        return listE;
    }
    
}
