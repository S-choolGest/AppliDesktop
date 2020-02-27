/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque.Utilisateur;

import Entite.Utilisateur.Utilisateur;
import GUI.Bibliotheque.Catalogue_bibliothecaireController;
import GUI.Bibliotheque.Catalogue_userController;
import Services.Bibliotheque.ServicesEmprunt;
import Services.Bibliotheque.ServicesEmprunteur;
import Services.Bibliotheque.ServicesLivreEmprunte;
import Services.Bibliotheque.ServicesLivres;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author william
 */
public class Bibliotheque_user_menuController implements Initializable {

	@FXML
	private Pane catalogue;
	@FXML
	private Pane emprunt;
	@FXML
	private Pane reservation;
	private Stage stage;
	public Utilisateur user;
	private List<Boolean> ordre = new ArrayList<>();
	private String etat = "tout";
	private ServicesLivres ser = new ServicesLivres();
	private ServicesEmprunt ser_emp = new ServicesEmprunt();
	private ServicesEmprunteur ser_user = new ServicesEmprunteur();
	private ServicesLivreEmprunte ser_livre = new ServicesLivreEmprunte();
	private AnchorPane body;
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
	public void getInfo(Utilisateur u) throws SQLException, SQLException{
		this.user = u;
	}
	
	@FXML
	private void charger_catalogue(MouseEvent event) throws IOException, SQLException {
		this.body.getChildren().clear();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../catalogue_user.fxml"));
		Parent n = (Parent) loader.load();
		Catalogue_userController emp = loader.getController();
		emp.getStage(stage);
		emp.getInfo(user);
		emp.getBody(this.body);
		this.body.getChildren().add(n);
	}

	@FXML
	private void charger_emprunt(MouseEvent event) {
		
	}

	@FXML
	private void charger_reservation(MouseEvent event) {
		
	}

	public void getBody(AnchorPane body) {
		this.body = body;
	}
	
}
