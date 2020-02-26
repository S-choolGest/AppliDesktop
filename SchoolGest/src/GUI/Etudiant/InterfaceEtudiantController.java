/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Etudiant;

import GUI.Bibliotheque.Catalogue_bibliothecaireController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author william
 */
public class InterfaceEtudiantController implements Initializable {

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
	private ImageView btn_info;
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
	private JFXButton bibliotheque;
	@FXML
	private JFXButton notes;
	@FXML
	private Pane body;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
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
	private void charger_bibliotheque(ActionEvent event) throws IOException {
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
	
}
