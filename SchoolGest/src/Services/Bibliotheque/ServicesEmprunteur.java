/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Bibliotheque;

import Entite.Bibliotheque.Emprunt;
import Entite.Bibliotheque.Emprunteur;
import Entite.Bibliotheque.Etat;
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
public class ServicesEmprunteur implements IServiceEmprunteur<Emprunteur> {

	private Connection con;
	private Statement ste;

	public ServicesEmprunteur() {
		con = DataBase.getInstance().getConnection();
	}

	@Override
	public Emprunteur read(int id) throws SQLException {
//		List<Emprunteur> listE = new ArrayList<>();
		PreparedStatement pre = con.prepareStatement("select a.idlivre, a.etat, date_format(a.dateemprunt, '%Y-%b-%d'), date_format(a.dateconfirmation, '%Y-%b-%d'), date_format(a.daterendu, '%Y-%b-%d'), b.nom, b.prenom, b.email, b.numtel, b.id, c.img, a.datedebut, a.datefin from utilisateur b "
				+ "inner join emprunt a on a.idemprunteur = b.id "
				+ "inner join livre c on c.id = a.idlivre  where a.id = ?");
		pre.setInt(1, id);
		ResultSet rs = pre.executeQuery();
		while (rs.next()) {
			int idlivre = rs.getInt(1);
			String etat = rs.getString(2);
			String dateEmprunt = rs.getString(3);
			String dateConfirmation = rs.getString(4);
			String dateRendu = rs.getString(5);
			String nome = rs.getString(6);
			String prenome = rs.getString(7);
			String emaile = rs.getString(8);
			int numtele = rs.getInt(9);
			int ide = rs.getInt(10);
			String img = rs.getString(11);
			String datedebut = rs.getString(12);
			String datefin = rs.getString(13);
			Emprunteur b = new Emprunteur(nome, prenome, numtele, emaile, img, id, ide, idlivre, Etat.valueOf(etat), dateEmprunt, dateConfirmation, dateRendu, datedebut, datefin);
			return b;
		}
		return null;
	}
//	public List<Emprunt> filter(String t, String ordre) throws SQLException {
//		if (t == "dateSortie") {
//			if (ordre == "asc") {
//				List<Emprunt> listE = new ArrayList<>();
//				listE = readAll();
//				List<Emprunt> livres = listE.stream()
//						.sorted(Comparator.comparing(Emprunt::getDateEmprunt))
//						.collect(Collectors.toList());
//				return livres;
//			}
//			if (ordre == "desc") {
//				List<Emprunt> listE = new ArrayList<>();
//				listE = readAll();
//				List<Emprunt> livres = listE.stream()
//						.sorted(Comparator.comparing(Emprunt::getDateEmprunt)
//								.reversed())
//						.collect(Collectors.toList());
//				return livres;
//			}
//		}
//		return null;
//	}
}
