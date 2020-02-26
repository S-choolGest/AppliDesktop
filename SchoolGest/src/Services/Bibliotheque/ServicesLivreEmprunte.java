/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Bibliotheque;

import Entite.Bibliotheque.Bibliotheque;
import Entite.Bibliotheque.Emprunteur;
import Entite.Bibliotheque.Etat;
import Entite.Bibliotheque.LivreEmprunte;
import IServices.Bibliotheque.IServiceEmprunteur;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author william
 */
public class ServicesLivreEmprunte implements IServiceEmprunteur {

	private Connection con;
	private Statement ste;
	private ServicesBibliotheque ser_bib = new ServicesBibliotheque();

	public ServicesLivreEmprunte() {
		con = DataBase.getInstance().getConnection();
	}

	@Override
	public LivreEmprunte read(int id) throws SQLException {
		List<LivreEmprunte> emprunts = new ArrayList<>();
		emprunts = readAll();
		LivreEmprunte emp = emprunts.stream()
				.filter(a -> a.getId_emprunt() == id)
				.findAny().orElse(null);
		return emp;

	}

	public List<LivreEmprunte> readAll() throws SQLException {
		List<LivreEmprunte> listE = new ArrayList<>();
		PreparedStatement pre = con.prepareStatement("select a.id, a.titre, a.auteur, a.editeur, a.categorie, a.dateSortie, a.img, b.etat, b.dateemprunt, b.dateconfirmation, b.daterendu,"
				+ " b.id, b.idemprunteur, b.datedebut, b.datefin, a.quantite, a.taille, a.id_bibliotheque from livre a "
				+ "inner join emprunt b on a.id = b.idlivre");
		ResultSet rs = pre.executeQuery();
		while (rs.next()) {
			int idlivre = rs.getInt(1);
			String titre = rs.getString(2);
			String auteur = rs.getString(3);
			String editeur = rs.getString(4);
			String categorie = rs.getString(5);
			String datesortie = rs.getString(6);
			String img = rs.getString(7);
			String etat = rs.getString(8);
			String dateemprunt = rs.getString(9);
			String dateconfirmation = rs.getString(10);
			String daterendu = rs.getString(11);
			int idemprunt = rs.getInt(12);
			int idemprunteur = rs.getInt(13);
			String datedebut = rs.getString(14);
			String datefin = rs.getString(15);
			int quantite = rs.getInt(16);
			int taille = rs.getInt(17);
			int id_bibliotheque = rs.getInt(18);
			LivreEmprunte b = new LivreEmprunte(idemprunt, idemprunteur, Etat.valueOf(etat), dateemprunt, dateconfirmation, daterendu, idlivre, id_bibliotheque, titre, editeur, auteur, categorie,
					datesortie, taille, quantite, img, null, datedebut, datefin);
			listE.add(b);
		}
		return listE;
	}

	public List<LivreEmprunte> search(int iduser) throws SQLException {
		List<LivreEmprunte> emprunts = new ArrayList<>();
		emprunts = readAll();
		List<LivreEmprunte> emp = emprunts.stream()
				.filter(a -> a.getId_emprunteur() == iduser)
				.collect(Collectors.toList());
		return emp;
	}

	public List<LivreEmprunte> search(int iduser, String text) throws SQLException {
		List<LivreEmprunte> livreemprunte = new ArrayList<>();
		livreemprunte = search(iduser);
		String t = text.toLowerCase();
		List<LivreEmprunte> emp = new ArrayList<>();
		try {
			emp = livreemprunte.stream()
					.filter(a -> (a.getDateConfirmation()!=null && a.getDateRendu()!=null && (a.getTitre().toLowerCase().contains(t) || a.getAuteur().toLowerCase().contains(t) || a.getEditeur().toLowerCase().contains(t) || a.getCategorie().toLowerCase().contains(t) || a.getDateSortie().contains(t) || a.getDateEmprunt().contains(t) || a.getDateConfirmation().contains(t) || a.getDateRendu().contains(t)))
					|| 
							(a.getDateConfirmation()!=null && (a.getTitre().toLowerCase().contains(t) || a.getAuteur().toLowerCase().contains(t) || a.getEditeur().toLowerCase().contains(t) || a.getCategorie().toLowerCase().contains(t) || a.getDateSortie().contains(t) || a.getDateEmprunt().contains(t) || a.getDateConfirmation().contains(t)))
							||
							(a.getDateRendu()!=null && (a.getTitre().toLowerCase().contains(t) || a.getAuteur().toLowerCase().contains(t) || a.getEditeur().toLowerCase().contains(t) || a.getCategorie().toLowerCase().contains(t) || a.getDateSortie().contains(t) || a.getDateEmprunt().contains(t) || a.getDateConfirmation().contains(t) || a.getDateRendu().contains(t)))
							||
							(a.getTitre().toLowerCase().contains(t) || a.getAuteur().toLowerCase().contains(t) || a.getEditeur().toLowerCase().contains(t) || a.getCategorie().toLowerCase().contains(t) || a.getDateSortie().contains(t) || a.getDateEmprunt().contains(t))
					)
					.collect(Collectors.toList());
		} catch (Exception e) {
			System.out.println(e);
		}
		return emp;
	}

	public List<LivreEmprunte> readAllinBibliotheque(String email, String t) throws SQLException {
		List<LivreEmprunte> emprunts = new ArrayList<>();
		emprunts = readAll();
		Bibliotheque bibliotheque = ser_bib.getBibliotheque(email);
		if (bibliotheque != null) {
			int id_bibliotheque = bibliotheque.getId();
			List<LivreEmprunte> emp = emprunts.stream()
					.filter(a -> a.getId_bibliotheque() == id_bibliotheque && (
							(a.getDateConfirmation()!=null && a.getDateRendu()!=null && (a.getTitre().toLowerCase().contains(t) || a.getAuteur().toLowerCase().contains(t) || a.getEditeur().toLowerCase().contains(t) || a.getCategorie().toLowerCase().contains(t) || a.getDateSortie().contains(t) || a.getDateEmprunt().contains(t) || a.getDateConfirmation().contains(t) || a.getDateRendu().contains(t)))
							||
							(a.getDateConfirmation()!=null && (a.getTitre().toLowerCase().contains(t) || a.getAuteur().toLowerCase().contains(t) || a.getEditeur().toLowerCase().contains(t) || a.getCategorie().toLowerCase().contains(t) || a.getDateSortie().contains(t) || a.getDateEmprunt().contains(t) || a.getDateConfirmation().contains(t)))
							||
							(a.getDateRendu()!=null && (a.getTitre().toLowerCase().contains(t) || a.getAuteur().toLowerCase().contains(t) || a.getEditeur().toLowerCase().contains(t) || a.getCategorie().toLowerCase().contains(t) || a.getDateSortie().contains(t) || a.getDateEmprunt().contains(t) || a.getDateConfirmation().contains(t) || a.getDateRendu().contains(t)))
							||
							(a.getTitre().toLowerCase().contains(t) || a.getAuteur().toLowerCase().contains(t) || a.getEditeur().toLowerCase().contains(t) || a.getCategorie().toLowerCase().contains(t) || a.getDateSortie().contains(t) || a.getDateEmprunt().contains(t))
							)
					)
					.collect(Collectors.toList());
			return emp;
		}
		return null;
	}

	public List<LivreEmprunte> filterByEtat(String email, String etat, String search) throws SQLException {
		List<LivreEmprunte> emprunts = new ArrayList<>();
		emprunts = readAllinBibliotheque(email, search);
		try {
			int id = Integer.valueOf(email);
			emprunts = search(id, search);
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			if (!etat.equals("tout")) {
				List<LivreEmprunte> emp = emprunts.stream()
						.filter(a -> a.getEtat().toString().equals(etat))
						.collect(Collectors.toList());
				return emp;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return emprunts;
	}
	public List<LivreEmprunte> tri(String email, String type, Boolean ordre, String etat, String search) throws SQLException {
		List<LivreEmprunte> emprunts = new ArrayList<>();
//		emprunts = readAllinBibliotheque(email);
		emprunts = filterByEtat(email, etat, search);
		try {
			if (!type.equals("aucun")) {
				if (type == "datesortie") {
					if (ordre == true) {
						List<LivreEmprunte> livres = emprunts.stream()
								.sorted(Comparator.comparing(LivreEmprunte::getDateSortie))
								.collect(Collectors.toList());
						return livres;
					}
					if (ordre == false) {
						List<LivreEmprunte> livres = emprunts.stream()
								.sorted(Comparator.comparing(LivreEmprunte::getDateSortie)
										.reversed())
								.collect(Collectors.toList());
						return livres;
					}
				}
				if (type == "categorie") {
					if (ordre == true) {
						List<LivreEmprunte> livres = emprunts.stream()
								.sorted(Comparator.comparing(LivreEmprunte::getCategorie))
								.collect(Collectors.toList());
						return livres;
					}
					if (ordre == false) {
						List<LivreEmprunte> livres = emprunts.stream()
								.sorted(Comparator.comparing(LivreEmprunte::getCategorie)
										.reversed())
								.collect(Collectors.toList());
						return livres;
					}
				}
				if (type == "titre") {
					if (ordre == true) {
						List<LivreEmprunte> livres = emprunts.stream()
								.sorted(Comparator.comparing(LivreEmprunte::getTitre))
								.collect(Collectors.toList());
						return livres;
					}
					if (ordre == false) {
						List<LivreEmprunte> livres = emprunts.stream()
								.sorted(Comparator.comparing(LivreEmprunte::getTitre)
										.reversed())
								.collect(Collectors.toList());
						return livres;
					}
				}
				if (type == "auteur") {
					if (ordre == true) {
						List<LivreEmprunte> livres = emprunts.stream()
								.sorted(Comparator.comparing(LivreEmprunte::getAuteur))
								.collect(Collectors.toList());
						return livres;
					}
					if (ordre == false) {
						List<LivreEmprunte> livres = emprunts.stream()
								.sorted(Comparator.comparing(LivreEmprunte::getAuteur)
										.reversed())
								.collect(Collectors.toList());
						return livres;
					}
				}
				if (type == "editeur") {
					if (ordre == true) {
						List<LivreEmprunte> livres = emprunts.stream()
								.sorted(Comparator.comparing(LivreEmprunte::getEditeur))
								.collect(Collectors.toList());
						return livres;
					}
					if (ordre == false) {
						List<LivreEmprunte> livres = emprunts.stream()
								.sorted(Comparator.comparing(LivreEmprunte::getEditeur)
										.reversed())
								.collect(Collectors.toList());
						return livres;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return emprunts;
	}
}
