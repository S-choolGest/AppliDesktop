/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque;

import Entite.Utilisateur.Bibliothecaire;
import Services.Utilisateur.ServicesBibliothecaire;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author william
 */
public class RegisterController implements Initializable {

	@FXML
	private JFXTextField nom;
	@FXML
	private JFXButton creer;
	@FXML
	private JFXTextField prenom;
	@FXML
	private JFXTextField tel;
	@FXML
	private JFXTextField email;
	@FXML
	private JFXTextField adresse;
	@FXML
	private JFXTextField cin;
	private ServicesBibliothecaire ser = new ServicesBibliothecaire();
	@FXML
	private ImageView close;
	@FXML
	private AnchorPane register;
	@FXML
	private Label error;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	private void ajouter_bibliothecaire(ActionEvent event) throws SQLException {
		error.setText("");
		String date = "2020-2-2";
		try {
			int num = Integer.valueOf(tel.getText());
			if (tel.getText().length() == 8) {
				Bibliothecaire b = new Bibliothecaire(nom.getText(), prenom.getText(), email.getText(), cin.getText(), cin.getText(), num, date, adresse.getText(), 5, "http://localhost/mobile/icons8_user_male_200px.png");
				if (ser.ajouter(b) == true) {
					System.out.println("ajout reussie");
					error.setText("ajout reussie");

				} else {
					System.out.println("echec d ajout");
					error.setText("echec d ajout");
				}
			} else {
				error.setText("Le numéro de téléphone est un nombre de 8 chiffres");
			}
		} catch (Exception e) {
			System.out.println(e);
			error.setText("Echec d'ajout!!! infos: "+e.toString());
		}
	}

	@FXML
	private void close_window(MouseEvent event) {
		register.disableProperty();
	}

}
