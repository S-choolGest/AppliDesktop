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

/**
 *
 * @author william
 */
public class ServicesLivreEmprunte implements IServiceEmprunteur{

	private Connection con;
	private Statement ste;

	public ServicesLivreEmprunte() {
		con = DataBase.getInstance().getConnection();
	}
	
	@Override
	public LivreEmprunte read(int id) throws SQLException {
		PreparedStatement pre = con.prepareStatement("select a.id, a.titre, a.auteur, a.editeur, a.categorie, a.dateSortie, a.img, b.etat, b.dateemprunt, b.dateconfirmation, b.daterendu from livre a inner join emprunt b on a.id = b.idlivre where b.id = ?");
		pre.setInt(1, id);
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

			LivreEmprunte b = new LivreEmprunte(id, Etat.valueOf(etat), dateemprunt, dateconfirmation, daterendu, idlivre, 1, titre, editeur, auteur, categorie, datesortie, 0, 0, img, null);
			return b;
		}
		return null;
		
	}
	
}
