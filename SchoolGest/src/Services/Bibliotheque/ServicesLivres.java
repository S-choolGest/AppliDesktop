/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Bibliotheque;

import Entite.Bibliotheque.Bibliotheque;
import Entite.Bibliotheque.Emprunt;
import Entite.Bibliotheque.Etat;
import Entite.Bibliotheque.Livre;
import IServices.Bibliotheque.IServices;
import Utils.DataBase;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author william
 */
public class ServicesLivres implements IServices<Livre> {

    private Connection con;
    private Statement ste;

    public ServicesLivres() {
        con = DataBase.getInstance().getConnection();
    }

    @Override
    public boolean ajouter(Livre l) throws SQLException {
//        ServicesBibliotheque ser = new ServicesBibliotheque();
//        Bibliotheque b = new Bibliotheque(l.getId_bibliotheque());
//        if (nbreLivre(b) >= ser.search(b).getCapacite()) {
//            System.out.println("bibliotheque pleine !!!");
//            return false;
//        }
        List<Livre> livres = new ArrayList<>();
        livres = readAll();
        Livre livr = livres.stream().filter(a -> a.equals(l)).findAny().orElse(null);
        if (livr == null) {
            PreparedStatement pre = con.prepareStatement("insert INTO `edutech`.`livre` (`id_bibliotheque`, `titre`, `auteur`, `editeur`, `categorie`, `datesortie`, `taille`, `quantite`, `dateajout`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, current_timestamp);");
            pre.setInt(1, l.getId_bibliotheque());
            pre.setString(2, l.getTitre());
            pre.setString(3, l.getAuteur());
            pre.setString(4, l.getEditeur());
            pre.setString(5, l.getCategorie());
            pre.setString(6, l.getDateSortie());
            pre.setInt(7, l.getTaille());
            pre.setInt(8, l.getQuantite());
            return pre.executeUpdate() != 0;
        } else {
            if (l.getCategorie().equals(livr.getCategorie())) {
                return ajouter(livr, l.getQuantite());
            } else {
                System.out.println("livre deja exitant dans categorie " + livr.getCategorie() + "\n");
            }
        }
        return false;
    }

    public boolean ajouter(Livre l, int qte) throws SQLException {
//        ServicesBibliotheque ser = new ServicesBibliotheque();
//        Bibliotheque b = new Bibliotheque(l.getId_bibliotheque());
//        if (nbreLivre(b) + qte > ser.search(b).getCapacite()) {
//            System.out.println("bibliotheque pleine !!!");
//            return false;
//        }
        PreparedStatement pre = con.prepareStatement("update `edutech`.`livre` set `quantite` = ? where `id` = ?;");
        pre.setInt(1, qte + l.getQuantite());
        pre.setInt(2, l.getId());
        return pre.executeUpdate() != 0;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        PreparedStatement pre = con.prepareStatement("delete from `edutech`.`livre` where `id` =  ?");
        pre.setInt(1, id);
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
        ResultSet rs = ste.executeQuery("select id, id_bibliotheque, titre, auteur, editeur, categorie, date_format(datesortie, '%Y-%b-%d'), taille, quantite, img, date_format(dateajout, '%Y-%b-%d') from livre");
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
			String img = rs.getString(10);
			String dateajout = rs.getString(11);
            Livre l = new Livre(id, id_bibliotheque, titre, editeur, auteur, categorie, datesortie, taille, quantite, img, dateajout);
            listE.add(l);
        }
        return listE;
    }

    public List<Livre> search(Livre l) throws SQLException {
        List<Livre> listE = new ArrayList<>();
        listE = readAll();
        List<Livre> result = listE.stream().filter(a -> a.equals(l)).collect(Collectors.toList());
        return result;
    }
	public Livre search(int id) throws SQLException {
        List<Livre> listE = new ArrayList<>();
        listE = readAll();
        Livre result = listE.stream().filter(a -> a.getId() == id).findAny().orElse(null);
        return result;
    }
    @Override
    public List<Livre> search(String t) throws SQLException {
        List<Livre> livres = new ArrayList<>();
        livres = readAll();
        List<Livre> livr = livres.stream()
                .filter(a -> a.getTitre().contains(t) || a.getAuteur().contains(t) || a.getCategorie().contains(t) || a.getDateSortie().contains(t) || String.valueOf(a.getTaille()).startsWith(t))
                .collect(Collectors.toList());
        return livr;
    }

    public int nbreLivre(Bibliotheque b) throws SQLException {
        List<Livre> listB = readAll();
        List<Livre> livres = listB.stream().filter(a -> a.getId_bibliotheque() == b.getId()).collect(Collectors.toList());
        return livres.stream().mapToInt(a -> a.getQuantite()).sum();
    }
	
	public List<Livre> readAllBibliotheque(int id) throws SQLException{
		List<Livre> listB = readAll();
        List<Livre> livres = listB.stream().filter(a -> a.getId_bibliotheque() == id).collect(Collectors.toList());
        return livres.stream().filter(a->a.getId_bibliotheque() == id).collect(Collectors.toList());
	}
	
    @Override
    public List<Livre> triAll(String t, String ordre) throws SQLException {
        if (t == "titre") {
            if (ordre == "asc") {
                List<Livre> listE = new ArrayList<>();
                listE = readAll();
                List<Livre> livres = listE.stream()
                        .sorted(Comparator.comparing(Livre::getTitre))
                        .collect(Collectors.toList());
                return livres;
            }
            if (ordre == "desc") {
                List<Livre> listE = new ArrayList<>();
                listE = readAll();
                List<Livre> livres = listE.stream()
                        .sorted(Comparator.comparing(Livre::getTitre)
                        .reversed())
                        .collect(Collectors.toList());
                return livres;
            }
        }
        if (t == "auteur") {
            if (ordre == "asc") {
                List<Livre> listE = new ArrayList<>();
                listE = readAll();
                List<Livre> livres = listE.stream()
                        .sorted(Comparator.comparing(Livre::getAuteur))
                        .collect(Collectors.toList());
                return livres;
            }
            if (ordre == "desc") {
                List<Livre> listE = new ArrayList<>();
                listE = readAll();
                List<Livre> livres = listE.stream()
                        .sorted(Comparator.comparing(Livre::getAuteur)
                        .reversed())
                        .collect(Collectors.toList());
                return livres;
            }
        }
        if (t == "editeur") {
            if (ordre == "asc") {
                List<Livre> listE = new ArrayList<>();
                listE = readAll();
                List<Livre> livres = listE.stream()
                        .sorted(Comparator.comparing(Livre::getEditeur))
                        .collect(Collectors.toList());
                return livres;
            }
            if (ordre == "desc") {
                List<Livre> listE = new ArrayList<>();
                listE = readAll();
                List<Livre> livres = listE.stream()
                        .sorted(Comparator.comparing(Livre::getEditeur)
                        .reversed())
                        .collect(Collectors.toList());
                return livres;
            }
        }
        if (t == "categorie") {
            if (ordre == "asc") {
                List<Livre> listE = new ArrayList<>();
                listE = readAll();
                List<Livre> livres = listE.stream()
                        .sorted(Comparator.comparing(Livre::getCategorie))
                        .collect(Collectors.toList());
                return livres;
            }
            if (ordre == "desc") {
                List<Livre> listE = new ArrayList<>();
                listE = readAll();
                List<Livre> livres = listE.stream()
                        .sorted(Comparator.comparing(Livre::getCategorie)
                        .reversed())
                        .collect(Collectors.toList());
                return livres;
            }
        }
        if (t == "dateSortie") {
            if (ordre == "asc") {
                List<Livre> listE = new ArrayList<>();
                listE = readAll();
                List<Livre> livres = listE.stream()
                        .sorted(Comparator.comparing(Livre::getDateSortie))
                        .collect(Collectors.toList());
                return livres;
            }
            if (ordre == "desc") {
                List<Livre> listE = new ArrayList<>();
                listE = readAll();
                List<Livre> livres = listE.stream()
                        .sorted(Comparator.comparing(Livre::getDateSortie)
                        .reversed())
                        .collect(Collectors.toList());
                return livres;
            }
        }
        if (t == "taille") {
            if (ordre == "asc") {
                List<Livre> listE = new ArrayList<>();
                listE = readAll();
                List<Livre> livres = listE.stream().sorted(Comparator.comparingInt(Livre::getTaille))
                        .collect(Collectors.toList());
                return livres;
            }
            if (ordre == "desc") {
                List<Livre> listE = new ArrayList<>();
                listE = readAll();
                List<Livre> livres = listE.stream()
                        .sorted(Comparator.comparingInt(Livre::getTaille)
                        .reversed())
                        .collect(Collectors.toList());
                return livres;
            }
        }
        if (t == "quantite") {
            if (ordre == "asc") {
                List<Livre> listE = new ArrayList<>();
                listE = readAll();
                List<Livre> livres = listE.stream()
                        .sorted(Comparator.comparingInt(Livre::getQuantite))
                        .collect(Collectors.toList());
                return livres;
            }
            if (ordre == "desc") {
                List<Livre> listE = new ArrayList<>();
                listE = readAll();
                List<Livre> livres = listE.stream()
                        .sorted(Comparator.comparingInt(Livre::getQuantite)
                        .reversed())
                        .collect(Collectors.toList());
                return livres;
            }
        }
        
        return null;
    }
	
	public int getId_Bibliotheque(int id_livre) throws SQLException{
		List<Livre> listE = new ArrayList<>();
        listE = readAll();
        Livre result = listE.stream().filter(a -> a.getId() == id_livre).findAny().orElse(null);
        return result.getId_bibliotheque();
	}
}
