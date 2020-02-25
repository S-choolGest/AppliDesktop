/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Bibliotheque;

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
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author william
 */
public class ServicesLivreEmprunte implements IServiceEmprunteur {

	private Connection con;
	private Statement ste;

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
				+ " b.id, b.idemprunteur, b.datedebut, b.datefin, a.quantite, a.taille from livre a "
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
			LivreEmprunte b = new LivreEmprunte(idemprunt, idemprunteur, Etat.valueOf(etat), dateemprunt, dateconfirmation, daterendu, idlivre, 1, titre, editeur, auteur, categorie, 
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
					.filter(a -> a.getTitre().contains(t) || a.getAuteur().contains(t) || a.getEditeur().contains(t) || a.getCategorie().contains(t) || a.getDateSortie().contains(t) || a.getDateEmprunt().contains(t) || a.getDateConfirmation().contains(t) || a.getDateRendu().contains(t))
					.collect(Collectors.toList());
		} catch (Exception e) {
			System.out.println(e);
		}
		return emp;
	}
}
