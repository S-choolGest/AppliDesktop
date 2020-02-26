/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Bibliotheque;

import Entite.Bibliotheque.Emprunt;
import Entite.Bibliotheque.Etat;
import Entite.Bibliotheque.Livre;
import Entite.Bibliotheque.LivreEmprunte;
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
public class ServicesEmprunt implements IServices<Emprunt> {

	private Connection con;
	private Statement ste;
	private ServicesLivreEmprunte ser = new ServicesLivreEmprunte();
	private ServicesLivres ser_livre = new ServicesLivres();
	private ServicesBibliotheque ser_bib = new ServicesBibliotheque();

	public ServicesEmprunt() {
		con = DataBase.getInstance().getConnection();
	}

	@Override
	public boolean ajouter(Emprunt e) throws SQLException {
		List<Emprunt> listE = readAll();
		long nbr = listE.stream().filter(a -> a.getIdEmprunteur() == e.getIdEmprunteur()).count();
		if (nbr >= 3) {
			System.out.println("max de livres empruntes!!!");
			return false;
		}
		Emprunt emp = listE.stream().filter(a -> a.getIdEmprunteur() == e.getIdEmprunteur() && a.getIdLivre() == e.getIdLivre() && a.getEtat().toString() != "rendu").findAny().orElse(null);
		if (emp != null) {
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

	public String emprunter(int id_emprunteur, int id_livre, String date_debut, String date_fin) throws SQLException {
		List<LivreEmprunte> listE = ser.readAll();
		Livre l = ser_livre.search(id_livre);
		long nbr = listE.stream().filter(a -> a.getId_emprunteur() == id_emprunteur && a.getEtat().toString() != "rendu" && a.getEtat().toString() != "refus").count();
		if (nbr >= 3) {
			System.out.println("max de livres empruntes!!!");
			return "max de livres empruntes!!!";
		}
		LivreEmprunte emp = listE.stream().filter(a -> a.getId_emprunteur() == id_emprunteur && a.getId() == id_livre && a.getEtat().toString() != "rendu" && a.getEtat().toString() != "refus").findAny().orElse(null);
		if (emp != null) {
			System.out.println("livre deja emprunte !!!");
			return "livre deja emprunte !!!";
		}
		long nbr_livre_indispo = listE.stream().filter(e -> e.getId() == id_livre && e.getEtat().toString() != "rendu" && e.getEtat().toString() != "refus"
				&& ((e.getDateFin().compareTo(date_debut) > 0) || (e.getDateDebut().compareTo(date_fin) < 0)))
				.count();
		if (l.getQuantite() - nbr_livre_indispo > 0) {
			PreparedStatement pre = con.prepareStatement("insert INTO `edutech`.`emprunt` (`idemprunteur`, `idlivre`, `etat`, `dateEmprunt`, `datedebut`, `datefin`) VALUES (?, ?, ?, current_timestamp, ?, ?);");
			pre.setInt(1, id_emprunteur);
			pre.setInt(2, id_livre);
			pre.setString(3, Etat.attente.toString());
			pre.setString(4, date_debut);
			pre.setString(5, date_fin);
			int rep = pre.executeUpdate();
			if (rep == 0) {
				return "Emprunt impossible !!!";
			} else {
				return "Emprunt réussie";
			}
		} else {
			return "Oups !!! Livre indisponible pendant cette période.";
		}
	}

	@Override
	public boolean delete(int id) throws SQLException {
		PreparedStatement pre = con.prepareStatement("delete from `edutech`.`emprunt` where `id` =  ?");
		pre.setInt(1, id);
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
		if (e.equals(Etat.accepte) || e.equals(Etat.refus)) {
			str = "update `edutech`.`emprunt` set`etat` = ?, `dateconfirmation` = current_timestamp where `id` = ?;";
		} else if (e.equals(Etat.rendu)) {
			str = "update `edutech`.`emprunt` set`etat` = ?, `daterendu` = current_timestamp where `id` = ?;";
		} else {
			return false;
		}
		PreparedStatement pre = con.prepareStatement(str);
		pre.setString(1, e.toString());
		pre.setInt(2, id);
		return pre.executeUpdate() != 0;
	}

	@Override
	public List<Emprunt> readAll() throws SQLException {
		List<Emprunt> listE = new ArrayList<>();
		ste = con.createStatement();
//		ResultSet rs = ste.executeQuery("select id, idemprunteur, idlivre, etat, date_format(dateemprunt, '%Y-%b-%d'), date_format(dateconfirmation, '%Y-%b-%d'), date_format(daterendu, '%Y-%b-%d'), date_format(datedebut, '%Y-%b-%d'), date_format(datefin, '%Y-%b-%d') from emprunt");
		ResultSet rs = ste.executeQuery("select id, idemprunteur, idlivre, etat, dateemprunt, dateconfirmation, daterendu, datedebut, datefin from emprunt");
		while (rs.next()) {
			int id = rs.getInt(1);
			int idemprunteur = rs.getInt(2);
			int idlivre = rs.getInt(3);
			String etat = rs.getString(4);
			String dateEmprunt = rs.getString(5);
			String dateConfirmation = rs.getString(6);
			String dateRendu = rs.getString(7);
			String dateDebut = rs.getString(8);
			String dateFin = rs.getString(9);
			Emprunt b = new Emprunt(id, idemprunteur, idlivre, Etat.valueOf(etat), dateEmprunt, dateConfirmation, dateRendu, dateDebut, dateFin);
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

	public List<Emprunt> search(int iduser, String t) throws SQLException {
		List<Emprunt> emprunts = new ArrayList<>();
		emprunts = search(iduser);
		List<Emprunt> emp = emprunts.stream()
				.filter(a -> a.getEtat().toString().contains(t) || a.getDateEmprunt().contains(t) || a.getDateConfirmation().contains(t) || a.getDateRendu().contains(t))
				.collect(Collectors.toList());
		return emp;
	}

	public List<Emprunt> search(int iduser) throws SQLException {
		List<Emprunt> emprunts = new ArrayList<>();
		emprunts = readAll();
		List<Emprunt> emp = emprunts.stream()
				.filter(a -> a.getIdEmprunteur() == iduser)
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

	public List<Emprunt> filterByEtat(String etat) throws SQLException {
		List<Emprunt> emprunts = new ArrayList<>();
		emprunts = readAll();
		try {
			if (!etat.equals("tout")) {
				List<Emprunt> emp = emprunts.stream()
						.filter(a -> a.getEtat().toString().equals(etat))
						.collect(Collectors.toList());
				return emp;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return emprunts;
	}

//	public List<Emprunt> readAllBibliotheque(String email) throws SQLException {
//		List<Emprunt> emprunts = new ArrayList<>();
//		emprunts = readAll();
//		int id_bibliotheque = ser_bib.getBibliotheque(email).getId();
//		
//			List<Emprunt> emp = emprunts.stream()
//					.filter(a -> ser_livre.getId_Bibliotheque(a.getIdLivre()) == id_bibliotheque)
//					.collect(Collectors.toList());
//			return emp;
//	}

}
