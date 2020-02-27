/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Utilisateur;

import Entite.Utilisateur.Bibliothecaire;
import Entite.Utilisateur.Utilisateur;
import IServices.Bibliotheque.IServicesUtilisateur;
import Utils.DataBase;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author william
 */
public class ServicesUtilisateur implements IServicesUtilisateur<Utilisateur> {

	private Connection con;
	private Statement ste;

	public ServicesUtilisateur() {
		con = DataBase.getInstance().getConnection();
	}

	@Override
	public String ajouter(Utilisateur t) throws SQLException {
		if (search(t.getId()) == null) {
			PreparedStatement pre = con.prepareStatement("insert INTO `edutech`.`utilisateur` (`nom`, `prenom`, `email`, `password`, `cin`, `numTel`, `datenaissance`, `adresse`, `type`, `profil`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			pre.setString(1, t.getNom());
			pre.setString(2, t.getPrenom());
			pre.setString(3, t.getEmail());
			pre.setString(4, t.getPassword());
			pre.setString(5, t.getCin());
			pre.setInt(6, t.getNumTel());
			pre.setString(7, t.getDateNaissance());
			pre.setString(8, t.getAdresse());
			pre.setInt(9, t.getType());
			pre.setString(10, t.getProfil());
			pre.executeUpdate();
			return "ajout reussie";
		}
		System.out.println("Bibliothecaire existant !!!");
		return "Bibliothecaire existant !!!";
	}

	@Override
	public Boolean delete(String cin) throws SQLException {
		PreparedStatement pre = con.prepareStatement("delete from `edutech`.`utilisateur` where `cin` =  ?");
		pre.setString(1, cin);
		return pre.executeUpdate() != 0;
	}

	@Override
	public Boolean update(Utilisateur t) throws SQLException {
		try {
			Utilisateur u = search(t.getId());
			if (!u.getProfil().equals("")) {
				FileUploader fu = new FileUploader("localhost/upload");
				if (fu.delete(u.getProfil())) {
					System.out.println("File " + u.getProfil() + " deleted.");
				}
			} else {
				t.setProfil(u.getProfil());
			}
		} catch (IOException ex) {
			Logger.getLogger(ServicesUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
		}
		PreparedStatement pre = con.prepareStatement("update `edutech`.`utilisateur` set `nom` = ?, `prenom` = ?, `email` = ?, `cin` = ?, `numTel` = ?, `datenaissance` = ?, `adresse` = ?, `profil` = ? where `id` = ?;");
		pre.setString(1, t.getNom());
		pre.setString(2, t.getPrenom());
		pre.setString(3, t.getEmail());
		pre.setString(4, t.getCin());
		pre.setInt(5, t.getNumTel());
		pre.setString(6, t.getDateNaissance());
		pre.setString(7, t.getAdresse());
		pre.setString(8, t.getProfil());
		pre.setInt(9, t.getId());
		return pre.executeUpdate() != 0;
	}

	public Boolean updatePassword(Utilisateur t) throws SQLException {
		PreparedStatement pre = con.prepareStatement("update `edutech`.`utilisateur` set `password` = ? where `id` = ?;");
		pre.setString(1, t.getPassword());
		pre.setInt(2, t.getId());
		return pre.executeUpdate() != 0;
	}

	@Override
	public List<Utilisateur> readAll() throws SQLException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Utilisateur> search(String t) throws SQLException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Utilisateur search(int id) throws SQLException {
		PreparedStatement pre = con.prepareStatement("select id, nom, prenom, email, password, cin, numtel, datenaissance, adresse, type, profil from utilisateur where id = ?");
		pre.setInt(1, id);
		ResultSet rs = pre.executeQuery();
		while (rs.next()) {
			String nom = rs.getString(2);
			String prenom = rs.getString(3);
			String email = rs.getString(4);
			String password = rs.getString(5);
			String cin = rs.getString(6);
			int numtel = rs.getInt(7);
			String datenaissance = rs.getString(8);
			String adresse = rs.getString(9);
			int type = rs.getInt(10);
			String profil = rs.getString(11);
			Utilisateur b = new Utilisateur(id, nom, prenom, email, password, cin, numtel, datenaissance, adresse, type, profil);
			return b;
		}
		return null;
	}

	public Utilisateur recuperer_type_compte(String email, String password) throws SQLException {
		PreparedStatement pre = con.prepareStatement("select * from utilisateur where email = ? and password = ?");
		pre.setString(1, email);
		pre.setString(2, password);
		ResultSet rs = pre.executeQuery();
		while (rs.next()) {
			int id = rs.getInt(1);
			String nom = rs.getString(2);
			String prenom = rs.getString(3);
			String email1 = rs.getString(4);
			String password1 = rs.getString(5);
			String cin = rs.getString(6);
			int numtel = rs.getInt(7);
			String datenaissance = rs.getString(8);
			String adresse = rs.getString(9);
			int type = rs.getInt(10);
			String img = rs.getString(11);
			Utilisateur b = new Utilisateur(id, nom, prenom, email, password, cin, numtel, datenaissance, adresse, type, img);
			return b;
		}
		return null;
	}

	public int recuperer_id(String email) throws SQLException {
		PreparedStatement pre = con.prepareStatement("select id from utilisateur where email = ?");
		pre.setString(1, email);
		ResultSet rs = pre.executeQuery();
		while (rs.next()) {
			int id = rs.getInt(1);
			return id;
		}
		return -1;
	}

	public int recuperer_id_by_cin(String cin) throws SQLException {
		PreparedStatement pre = con.prepareStatement("select id from utilisateur where cin = ?");
		pre.setString(1, cin);
		ResultSet rs = pre.executeQuery();
		while (rs.next()) {
			return rs.getInt(1);
		}
		return -1;
	}

	public boolean delete_profil(int id) throws SQLException, IOException {
		Utilisateur u = search(id);
		if (u.getProfil().equals("")) {
			return false;
		} else {
			FileUploader fu = new FileUploader("localhost/upload");
			if (fu.delete(u.getProfil())) {
				System.out.println("File " + u.getProfil() + " deleted.");
			}
			return true;
		}

	}
}
