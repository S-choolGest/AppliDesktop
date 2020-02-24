/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Bibliotheque;

import Entite.Bibliotheque.Bibliotheque;
import Entite.Bibliotheque.Livre;
import Entite.Utilisateur.Bibliothecaire;
import IServices.Bibliotheque.IServices;
import Services.Utilisateur.ServicesBibliothecaire;
import Utils.DataBase;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author william
 */
public class ServicesBibliotheque implements IServices<Bibliotheque> {

	private Connection con;
	private Statement ste;
	private ServicesBibliothecaire ser = new ServicesBibliothecaire();

	public ServicesBibliotheque() {
		con = DataBase.getInstance().getConnection();
	}

	@Override
	public boolean ajouter(Bibliotheque b) throws SQLException {
		PreparedStatement pre = con.prepareStatement("insert INTO `edutech`.`bibliotheque` (`nom`, `capacite`, `adresse`) VALUES (?, ?, ?);");
		pre.setString(1, b.getNom());
		pre.setInt(2, b.getCapacite());
		pre.setString(3, b.getAdresse());
		return pre.executeUpdate() != 0;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		int count = -1;
		PreparedStatement ver = con.prepareStatement("select count(*) from `livre` where `id_bibliotheque` = ?");
		ver.setInt(1, id);
		ResultSet rs = ver.executeQuery();
		while (rs.next()) {
			count = rs.getInt(1);
		}
		if (count == 0) {
			PreparedStatement pre = con.prepareStatement("delete from `edutech`.`Bibliotheque` where `id` =  ?");
			pre.setInt(1, id);
			return pre.executeUpdate() != 0;
		} else {
			System.out.println("livre existant dans la bibliotheque !!!\n");
			return false;
		}
	}
	
	@Override
	public boolean update(Bibliotheque b) throws SQLException {
		PreparedStatement pre = con.prepareStatement("update `edutech`.`Bibliotheque` set `nom` = ?, `capacite` = ?, `adresse` = ? where `id` = ?;");
		pre.setString(1, b.getNom());
		pre.setInt(2, b.getCapacite());
		pre.setString(3, b.getAdresse());
		pre.setInt(4, b.getId());
		return pre.executeUpdate() != 0;
	}

	public boolean affecter(Bibliotheque b) throws SQLException {
		int id_bibliothecaire = ser.searchByEmail(b.getEmail());
		if(id_bibliothecaire == -1)
			return false;
		PreparedStatement pre = con.prepareStatement("update `edutech`.`Bibliotheque` set `id_bibliothecaire` = ? where `id` = ?;");
		pre.setInt(1, id_bibliothecaire);
		pre.setInt(2, b.getId());
		return pre.executeUpdate() != 0;
	}

	@Override
	public List<Bibliotheque> readAll() throws SQLException {
		List<Bibliotheque> listB = new ArrayList<>();
		ste = con.createStatement();
		ResultSet rs = ste.executeQuery("select * from Bibliotheque");
		while (rs.next()) {
			int id = rs.getInt(1);
			String nom = rs.getString(2);
			int capacite = rs.getInt(3);
			String adresse = rs.getString(4);
			int id_bibliothecaire = rs.getInt(5);
			String email = ser.getEmailById(id_bibliothecaire);
			Bibliotheque b = new Bibliotheque(id, nom, capacite, adresse, email);
			listB.add(b);
		}
		return listB;
	}

	@Override
	public List<Bibliotheque> search(String t) throws SQLException {
		List<Bibliotheque> bibliotheques = new ArrayList<>();
		bibliotheques = readAll();
		List<Bibliotheque> bib = bibliotheques.stream()
				.filter(a -> a.getNom().contains(t) || a.getAdresse().contains(t))
				.collect(Collectors.toList());
		return bib;
	}

	public Bibliotheque search(Bibliotheque b) throws SQLException {
		List<Bibliotheque> listB = readAll();
		Bibliotheque bib = listB.stream().filter(a -> a.getId() == b.getId()).findAny().orElse(null);
		return bib;
	}

	@Override
	public List<Bibliotheque> triAll(String t, String ordre) throws SQLException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public Bibliotheque searchByAdresse(String text) throws SQLException {
		List<Bibliotheque> listB = readAll();
		Bibliotheque bib = listB.stream().filter(a -> a.getAdresse().equals(text)).findAny().orElse(null);
		return bib;
	}
}
