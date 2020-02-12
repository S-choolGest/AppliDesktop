/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Utilisateur;

import Entite.Utilisateur.Bibliothecaire;
import Utils.DataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author william
 */
public class ServicesBibliothecaire implements IServices.IServices<Bibliothecaire> {

    private Connection con;
    private Statement ste;

    public ServicesBibliothecaire() {
        con = DataBase.getInstance().getConnection();
    }

    @Override
    public boolean ajouter(Bibliothecaire b) throws SQLException {
        PreparedStatement pre = con.prepareStatement("insert INTO `edutech`.`Bibliothecaire` (`cin`, `nom`, `prenom`, `dateNaissance`, `email`, `numTel`, `adresse`) VALUES (?, ?, ?, ?, ?, ?, ?);");
        pre.setString(1, b.getCin());
        pre.setString(2, b.getNom());
        pre.setString(3, b.getPrenom());
        pre.setString(4, b.getDateNaissance());
        //java.sql.Date date = new java.sql.Date(b.getDateNaissance());
        pre.setString(5, b.getEmail());
        pre.setInt(6, b.getTel());
        pre.setString(7, b.getAdresse());
        return pre.executeUpdate() != 0;
    }

    @Override
    public boolean delete(Bibliothecaire b) throws SQLException {
        PreparedStatement pre = con.prepareStatement("delete from `edutech`.`Bibliothecaire` where `id` =  ?");
        pre.setInt(1, b.getId());
        return pre.executeUpdate() != 0;
    }

    @Override
    public boolean update(Bibliothecaire b) throws SQLException {
        PreparedStatement pre = con.prepareStatement("update `edutech`.`Bibliothecaire` set `cin` = ?, `nom` = ?, `prenom` = ?, `dateNaissance` = ?, `email` = ?, `numTel` = ?, `adresse` = ? where `id` = ?;");
        pre.setString(1, b.getCin());
        pre.setString(2, b.getNom());
        pre.setString(3, b.getPrenom());
        pre.setString(4, b.getDateNaissance());
        pre.setString(5, b.getEmail());
        pre.setInt(6, b.getTel());
        pre.setString(7, b.getAdresse());
        pre.setInt(8, b.getId());
        return pre.executeUpdate() != 0;
    }

    @Override
    public List<Bibliothecaire> readAll() throws SQLException {
        List<Bibliothecaire> listB = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from Bibliothecaire");
        while (rs.next()) {
            int id = rs.getInt(1);
            String cin = rs.getString("cin");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String dateNaissance = rs.getString(5);
            //LocalDate date = LocalDate.parse(dateNaissance);
            String email = rs.getString(6);
            int numtel = rs.getInt(7);
            String adresse = rs.getString(8);
            Bibliothecaire b = new Bibliothecaire(id, cin, nom, prenom, dateNaissance, email, numtel, adresse);
            listB.add(b);
        }
        return listB;
    }

}
