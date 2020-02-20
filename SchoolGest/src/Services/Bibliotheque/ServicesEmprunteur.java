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
import java.util.List;

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
		PreparedStatement pre = con.prepareStatement("select a.idlivre, a.etat, a.dateemprunt, a.dateconfirmation, a.daterendu, b.nomE, b.prenome, b.emaile, b.numtele, b.idetudiant, c.img from etudiant b "
				+ "inner join emprunt a on a.idemprunteur = b.idEtudiant "
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
			Emprunteur b = new Emprunteur(nome, prenome, numtele, emaile, id, ide, idlivre, Etat.valueOf(etat), dateEmprunt, dateConfirmation, dateRendu, img);
			return b;
		}
		return null;
	}

}
