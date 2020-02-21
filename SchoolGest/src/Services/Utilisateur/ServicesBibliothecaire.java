/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Utilisateur;

import Entite.Utilisateur.Bibliothecaire;
import Entite.Utilisateur.Utilisateur;
import Utils.DataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author william
 */
public class ServicesBibliothecaire implements IServices.Bibliotheque.IServices<Bibliothecaire> {

	private Connection con;
	private Statement ste;
	private ServicesUtilisateur ser = new ServicesUtilisateur();

	public ServicesBibliothecaire() {
		con = DataBase.getInstance().getConnection();
	}

	@Override
	public boolean ajouter(Bibliothecaire b) throws SQLException {
		if (search(b) == null) {
			ser.ajouter(b);
			PreparedStatement pre = con.prepareStatement("insert INTO `edutech`.`Bibliothecaire` (`id`) VALUES (?);");
			pre.setInt(1, b.getId());
			return pre.executeUpdate() != 0;
		}
		System.out.println("Bibliothecaire existant !!!");
		return false;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		PreparedStatement pre = con.prepareStatement("delete from `edutech`.`Bibliothecaire` where `id` =  ?");
		pre.setInt(1, id);
		ser.delete(id);
		return pre.executeUpdate() != 0;
	}

	@Override
	public boolean update(Bibliothecaire b) throws SQLException {
		ser.update(b);
		PreparedStatement pre = con.prepareStatement("update `edutech`.`Bibliothecaire` set `id_bibliotheque` = ?, `heure_debut` = ?, `heure_arret` = ? where `id` = ?;");
		pre.setInt(1, b.getId_bibliotheque());
		pre.setString(2, b.getHeure_debut());
		pre.setString(3, b.getHeure_arret());
		pre.setInt(4, b.getId());
		return pre.executeUpdate() != 0;
	}

	@Override
	public List<Bibliothecaire> readAll() throws SQLException {
		List<Bibliothecaire> listB = new ArrayList<>();
		ste = con.createStatement();
		ResultSet rs = ste.executeQuery("select u.id, u.nom, u.prenom, u.email, u.password, u.cin, u.numtel, u.datenaissance, u.adresse, u.type, b.id_bibliotheque, b.heure_debut, b.heure_arret from Bibliothecaire b inner join utilisateur u on b.id = u.id");
		while (rs.next()) {
			int id = rs.getInt(1);
			String nom = rs.getString(2);
			String prenom = rs.getString(3);
			String email = rs.getString(4);
			String password = rs.getString(5);
			String cin = rs.getString(6);
			int numtel = rs.getInt(7);
			String datenaissance = rs.getString(8);
			String adresse = rs.getString(9);
			int type = rs.getInt(10);
			int id_bibliotheque = rs.getInt(11);
			String heure_debut = rs.getString(12);
			String heure_arret = rs.getString(13);
			Bibliothecaire b = new Bibliothecaire(id_bibliotheque, heure_debut, heure_arret, id, nom, prenom, email, password, cin, numtel, datenaissance, adresse, type);
			listB.add(b);
		}
		return listB;
	}

	@Override
	public List<Bibliothecaire> search(String t) throws SQLException {
		List<Bibliothecaire> bibliothecaires = new ArrayList<>();
		bibliothecaires = readAll();
		List<Bibliothecaire> bib = bibliothecaires.stream()
				.filter(a -> a.getCin().contains(t) || a.getNom().contains(t) || a.getPrenom().contains(t) || a.getDateNaissance().contains(t) || a.getEmail().contains(t) || a.getAdresse().contains(t) || String.valueOf(a.getNumTel()).startsWith(t))
				.collect(Collectors.toList());
		return bib;
	}

	public Bibliothecaire search(Bibliothecaire b) throws SQLException {
		List<Bibliothecaire> listE = new ArrayList<>();
		listE = readAll();
		Bibliothecaire result = listE.stream().filter(a -> a.equals(b)).findAny().orElse(null);
		return result;
	}

	public Bibliothecaire search(int id) throws SQLException {
		List<Bibliothecaire> listE = new ArrayList<>();
		listE = readAll();
		Bibliothecaire result = listE.stream().filter(a -> a.getId() == id).findAny().orElse(null);
		return result;
	}

	@Override
	public List<Bibliothecaire> triAll(String t, String ordre) throws SQLException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
