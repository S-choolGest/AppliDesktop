/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.admin;

import Entite.Utilisateur.Utilisateur;
import GUI.Bibliotheque.Catalogue_bibliothecaireController;
import GUI.Bibliotheque.Update_accountController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author william
 */
public class InterfaceAdminController implements Initializable {

	@FXML
	private ImageView btn_info;
	@FXML
	private ImageView btn_close;
	@FXML
	private Pane info;
	@FXML
	private Label nom;
	@FXML
	private Label prenom;
	@FXML
	private Label email;
	@FXML
	private ImageView logout;
	@FXML
	private ImageView edit;
	@FXML
	private JFXButton utilisateurs;
	@FXML
	private JFXButton matieres;
	@FXML
	private JFXButton emplois;
	@FXML
	private JFXButton absences;
	@FXML
	private JFXButton reclamations;
	@FXML
	private JFXButton encadrement;
	@FXML
	private JFXButton evenements;
	@FXML
	private JFXButton bibliotheques;
	@FXML
	private JFXButton notes;
	@FXML
	private AnchorPane body;
	private Boolean infos_vue = false;
	private Stage stage;
	public Utilisateur user;
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	

	@FXML
	private void charger_utilisateurs(ActionEvent event) {
	}

	@FXML
	private void charger_matieres(ActionEvent event) {
	}

	@FXML
	private void charger_emplois(ActionEvent event) {
	}

	@FXML
	private void charger_absences(ActionEvent event) {
	}

	@FXML
	private void charger_reclamations(ActionEvent event) {
	}

	@FXML
	private void charger_encadrement(ActionEvent event) {
	}

	@FXML
	private void charger_evenements(ActionEvent event) {
	}

	@FXML
	private void charger_bibliotheques(ActionEvent event) throws IOException {
		body.getChildren().clear();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../Bibliotheque/Catalogue_bibliothecaire.fxml"));
		Parent n = (Parent) loader.load();
		Catalogue_bibliothecaireController emp = loader.getController();
		body.getChildren().add(n);
	}

	@FXML
	private void charger_notes(ActionEvent event) {
	}

	@FXML
	private void afficher_info(MouseEvent event) {
		infos_vue = !infos_vue;
		info.setVisible(infos_vue);
		info.toFront();
		body.toBack();
	}
	public void getInfo(Utilisateur u){
		nom.setText(u.getNom());
		prenom.setText(u.getPrenom());
		email.setText(u.getEmail());
		this.user = u;
	}

	@FXML
	private void close(MouseEvent event) {
		this.stage.close();
	}

	@FXML
	private void deconnecter(MouseEvent event) {
		this.stage.close();
	}

	@FXML
	private void charger_edit_account(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../Bibliotheque/update_account.fxml"));
		Parent n = (Parent) loader.load();
		Update_accountController del = loader.getController();
		del.getInfos(this.user);
		Stage stage = new Stage();
		stage.setTitle("Edutech : Etudiant : Edit account");
		Scene scene = new Scene(n);
		stage.setResizable(false);
//        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		stage.setScene(scene);
		stage.show();
	}

	public void getStage(Stage stage) {
		this.stage = stage;
	}
	public void close(Stage s){
		logout.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("123 close!!!");
				stage.close();
			}
		});
	}
}
