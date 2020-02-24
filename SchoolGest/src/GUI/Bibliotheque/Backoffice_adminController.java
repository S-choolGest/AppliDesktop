/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque;

import Entite.Utilisateur.Utilisateur;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author william
 */
public class Backoffice_adminController implements Initializable {

	@FXML
	private JFXButton bibliothecaires;
	@FXML
	private JFXButton bibliotheques;
	@FXML
	private AnchorPane body;
	@FXML
	private ImageView profil;
	@FXML
	private Label nom;
	@FXML
	private Label email;
	@FXML
	private Label id_user;
	@FXML
	private ImageView btn_modifier_compte;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	private void charger_pages_bibliiothecaires(ActionEvent event) throws IOException {
		body.getChildren().clear();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Bibliothecaires.fxml"));
		Parent n = (Parent) loader.load();
//		Emprunt_userController emp = loader.getController();
		body.getChildren().add(n);
	}

	@FXML
	private void charger_page_bibliotheques(ActionEvent event) throws IOException {
		body.getChildren().clear();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GestionBibliotheque.fxml"));
		Parent n = (Parent) loader.load();
//		Emprunt_userController emp = loader.getController();
		body.getChildren().add(n);
	}
	public void setProfil(Utilisateur u){
		nom.setText(u.getNom()+" "+u.getPrenom());
		email.setText(u.getEmail());
		if(u.getProfil() != null)
			profil.setImage(new Image(u.getProfil()));
		id_user.setText(String.valueOf(u.getId()));
	}

	@FXML
	private void modifier_compte(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("update_account.fxml"));
		Parent n = (Parent) loader.load();
		Update_accountController del = loader.getController();
		del.setId(id_user.getText());
		Stage stage = new Stage();
		stage.setTitle("Edutech : Bibliotheque : Backoffice : Edit account");
		Scene scene = new Scene(n);
		stage.setResizable(false);
//        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		stage.setScene(scene);
		stage.show();
	}
}
