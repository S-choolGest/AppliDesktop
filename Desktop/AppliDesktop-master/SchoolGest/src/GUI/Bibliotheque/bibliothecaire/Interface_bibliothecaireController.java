/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque.bibliothecaire;

import Entite.Bibliotheque.Bibliotheque;
import Entite.Utilisateur.Utilisateur;
import GUI.Bibliotheque.Catalogue_bibliothecaireController;
import GUI.Bibliotheque.Update_accountController;
import GUI.Bibliotheque.gerer_empruntController;
import Services.Bibliotheque.ServicesBibliotheque;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author william
 */
public class Interface_bibliothecaireController implements Initializable {

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
	private JFXButton catalogue;
	@FXML
	private JFXButton reservations;
	@FXML
	private JFXButton emprunts;
	@FXML
	private AnchorPane body;
	private Boolean info_vue = false;
	private Stage stage;
	public Utilisateur user;
	private ServicesBibliotheque ser_bib = new ServicesBibliotheque();
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	

	public void getStage(Stage stage) {
		this.stage = stage;
	}

	public void getInfo(Utilisateur u) {
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
		info_vue = false;
		info.setVisible(info_vue);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../update_account.fxml"));
		Parent n = (Parent) loader.load();
		Update_accountController maj = loader.getController();
		maj.getInfos(this.user);
		Stage stage = new Stage();
		stage.setTitle("Edutech : Etudiant : Edit account");
		Scene scene = new Scene(n);
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
//        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		stage.setScene(scene);
		maj.getStage(stage);
		stage.show();
	}

	@FXML
	private void charger_catalogue(ActionEvent event) throws IOException, SQLException {
		info_vue = false;
		info.setVisible(info_vue);
		body.getChildren().clear();
		Bibliotheque b = ser_bib.getBibliotheque(user.getEmail());
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../Catalogue_bibliothecaire.fxml"));
		Parent n = (Parent) loader.load();
		Catalogue_bibliothecaireController c = loader.getController();
		c.init(b);
		c.getStage(stage);
		body.getChildren().add(n);
	}

	@FXML
	private void charger_reservations(ActionEvent event) {
		info_vue = false;
		info.setVisible(info_vue);
	}

	@FXML
	private void charger_emprunts(ActionEvent event) throws SQLException, IOException {
		info_vue = false;
		info.setVisible(info_vue);
		body.getChildren().clear();
		Bibliotheque b = ser_bib.getBibliotheque(user.getEmail());
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../gerer_emprunt.fxml"));
		Parent n = (Parent) loader.load();
		gerer_empruntController c = loader.getController();
		c.init(b);
		c.getStage(stage);
		body.getChildren().add(n);
	}

	@FXML
	private void afficher_info(MouseEvent event) {
		info_vue = !info_vue;
		info.setVisible(info_vue);
		info.toFront();
		body.toBack();
	}

	@FXML
	private void cacher_info(MouseEvent event) {
		info_vue = false;
		info.setVisible(info_vue);
	}
	
}
