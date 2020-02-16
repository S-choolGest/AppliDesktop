/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services.Bibliotheque;

import Entite.Bibliotheque.Emprunt;
import Entite.Bibliotheque.Etat;
import Entite.Bibliotheque.Livre;
import Entite.Utilisateur.Bibliothecaire;
import IServices.Bibliotheque.IServices;
import Utils.DataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Emprunt> listE = readAll();
        long nbr = listE.stream().filter(a -> a.getIdEmprunteur() == e.getIdEmprunteur()).count();
        if(nbr >= 3){
            System.out.println("max de livres empruntes!!!");
            return false;
        }
        Emprunt emp = listE.stream().filter(a -> a.getIdEmprunteur() == e.getIdEmprunteur() && a.getIdLivre() == e.getIdLivre() && a.getEtat().toString()!= "rendu").findAny().orElse(null);
        if(emp != null){
            System.out.println("livre deja emprunte !!!");
            return false;
        }
        PreparedStatement pre = con.prepareStatement("insert INTO `edutech`.`emprunt` (`idemprunteur`, `idlivre`, `etat`, `dateEmprunt`, `dateConfirmation`, `dateRendu`) VALUES (?, ?, ?, ?, ?, ?);");
        pre.setInt(1, e.getIdEmprunteur());
        pre.setInt(2, e.getIdLivre());
        pre.setString(3, e.getEtat().toString());
        pre.setString(4, e.getDateEmprunt());
        pre.setString(5, e.getDateConfirmation());
        pre.setString(6, e.getDateRendu());
        return pre.executeUpdate() != 0;
    }

    public boolean emprunter(int id_emprunteur, int id_livre) throws SQLException {
        List<Emprunt> listE = readAll();
        long nbr = listE.stream().filter(a -> a.getIdEmprunteur() == id_emprunteur).count();
        if(nbr >= 3){
            System.out.println("max de livres empruntes!!!");
            return false;
        }
        Emprunt emp = listE.stream().filter(a -> a.getIdEmprunteur() == id_emprunteur && a.getIdLivre() == id_livre && a.getEtat().toString()!= "rendu").findAny().orElse(null);
        if(emp != null){
            System.out.println("livre deja emprunte !!!");
            return false;
        }
        PreparedStatement pre = con.prepareStatement("insert INTO `edutech`.`emprunt` (`idemprunteur`, `idlivre`, `etat`, `dateEmprunt`) VALUES (?, ?, ?, current_timestamp);");
        pre.setInt(1, id_emprunteur);
        pre.setInt(2, id_livre);
        pre.setString(3, Etat.attente.toString());
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
    
    public boolean confirmerEmprunt(int id, Etat e) throws SQLException {
        String str = "";
        if(e.equals(Etat.accepte) || e.equals(Etat.refus))
            str = "update `edutech`.`emprunt` set`etat` = ?, `dateconfirmation` = current_timestamp where `id` = ?;";
        else if(e.equals(Etat.rendu))
            str = "update `edutech`.`emprunt` set`etat` = ?, `daterendu` = current_timestamp where `id` = ?;";
        else
            return false;
        PreparedStatement pre = con.prepareStatement(str);
        pre.setString(1, e.toString());
        pre.setInt(2, id);
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

    @Override
    public List<Emprunt> search(String t) throws SQLException {
        List<Emprunt> emprunts = new ArrayList<>();
        emprunts = readAll();
        List<Emprunt> emp = emprunts.stream()
                .filter(a -> a.getEtat().toString().contains(t) || a.getDateEmprunt().contains(t) || a.getDateConfirmation().contains(t) || a.getDateRendu().contains(t))
                .collect(Collectors.toList());
        return emp;
    }

    @Override
    public List<Emprunt> triAll(String t, String ordre) throws SQLException {
//        if (t == "titre") {
//            if (ordre == "asc") {
//                List<Emprunt> listE = new ArrayList<>();
//                listE = readAll();
//                List<Emprunt> emprunts = listE.stream()
//                        .sorted(Comparator.comparing(Emprunt::getEtat().toString()))
//                        .collect(Collectors.toList());
//                return emprunts;
//            }
//            if (ordre == "desc") {
//                List<Emprunt> listE = new ArrayList<>();
//                listE = readAll();
//                List<Emprunt> emprunts = listE.stream()
//                        .sorted(Comparator.comparing(Emprunt::getEtat().toString())
//                        .reversed())
//                        .collect(Collectors.toList());
//                return emprunts;
//            }
//        }
        
        if (t == "dateSortie") {
            if (ordre == "asc") {
                List<Emprunt> listE = new ArrayList<>();
                listE = readAll();
                List<Emprunt> livres = listE.stream()
                        .sorted(Comparator.comparing(Emprunt::getDateEmprunt))
                        .collect(Collectors.toList());
                return livres;
            }
            if (ordre == "desc") {
                List<Emprunt> listE = new ArrayList<>();
                listE = readAll();
                List<Emprunt> livres = listE.stream()
                        .sorted(Comparator.comparing(Emprunt::getDateEmprunt)
                        .reversed())
                        .collect(Collectors.toList());
                return livres;
            }
        }        
        return null;
    }

}
