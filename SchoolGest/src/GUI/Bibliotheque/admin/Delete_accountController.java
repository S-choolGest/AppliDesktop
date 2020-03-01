/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque.admin;

import Entite.Utilisateur.Bibliothecaire;
import Entite.Utilisateur.Utilisateur;
import Services.Utilisateur.ServicesBibliothecaire;
import Services.Utilisateur.ServicesUtilisateur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author william
 */
public class Delete_accountController implements Initializable {

	@FXML
	private JFXTextField nom;
	@FXML
	private JFXButton supprimer;
	@FXML
	private JFXTextField email;
	@FXML
	private JFXTextField cin;
	@FXML
	private ImageView close;
	private boolean delete = false;
	private ServicesBibliothecaire ser = new ServicesBibliothecaire();
	private ServicesUtilisateur ser_user = new ServicesUtilisateur();
	@FXML
	private Label error;
	private AnchorPane body;
	private Stage stage;
	public Utilisateur user;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	private void supprimer_bibliothecaire(ActionEvent event) throws SQLException {
		if (delete == true) {
			if (ser.delete(cin.getText())) {
				error.setText("Suppression reussite !!!");
			} else {
				error.setText("Echec de suppression !!!");
			}
		} else {
			error.setText("Suppression impossible !!!");
		}
	}

	@FXML
	private void close_window(MouseEvent event) {
		this.stage.close();
	}

	public void getBody(AnchorPane body) {
		this.body = body;
	}

	public void getStage(Stage stage) {
		this.stage = stage;
	}

	public void getInfo(Utilisateur u) throws SQLException, SQLException {
		this.user = u;
		if (this.user != null) {
			nom.setText(u.getNom() + " " + u.getPrenom());
			email.setText(u.getEmail());
			cin.setText(u.getCin());
			delete = true;
		}
	}

	private void rechercher_bibliothecaire(KeyEvent event) throws SQLException {
		try {
			delete = false;
			error.setText("");
			Bibliothecaire b = ser.searchByCIN(cin.getText());
			if (b != null) {
				nom.setText(b.getNom() + " " + b.getPrenom());
				email.setText(b.getEmail());
				cin.setText(b.getCin());
				delete = true;
			} else {
				nom.setText("");
				email.setText("");
				delete = false;
				error.setText("CIN incorrect!!!");
			}
		} catch (Exception e) {
			error.setText("CIN incorrect!!!");
			nom.setText("");
			email.setText("");
			delete = true;
			System.out.println(e);
		}
	}

	@FXML
	private void supprimer_utilisateur(ActionEvent event) {

	}

	@FXML
	private void rechercher_utilisateur(KeyEvent event) {
		try {
			error.setText("");
			Utilisateur b = ser_user.searchByCIN(cin.getText());
			if (b != null) {
				nom.setText(b.getNom() + " " + b.getPrenom());
				email.setText(b.getEmail());
				cin.setText(b.getCin());
				delete = true;
			} else {
				nom.setText("");
				email.setText("");
				delete = false;
				error.setText("CIN incorrect!!!");
			}
		} catch (Exception e) {
			error.setText("CIN incorrect!!!");
			nom.setText("");
			email.setText("");
			delete = true;
			System.out.println(e);
		}
	}

}
