/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque.admin;

import Entite.Bibliotheque.Livre;
import Entite.Utilisateur.Bibliothecaire;
import Entite.Utilisateur.Utilisateur;
import Services.Utilisateur.ServicesBibliothecaire;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author william
 */
public class BibliothecairesController implements Initializable {

	@FXML
	private TableView<Bibliothecaire> list_bibliothecaire;
	private TableColumn<Bibliothecaire, Integer> id;
	@FXML
	private TableColumn<Bibliothecaire, String> nom;
	@FXML
	private TableColumn<Bibliothecaire, String> prenom;
	@FXML
	private TableColumn<Bibliothecaire, String> email;
	@FXML
	private TableColumn<Bibliothecaire, String> cin;
	@FXML
	private TableColumn<Bibliothecaire, String> adresse;
	@FXML
	private TableColumn<Bibliothecaire, String> datenaissance;
	@FXML
	private TableColumn<Bibliothecaire, Integer> tel;
	@FXML
	private JFXTextField field_rechercher_bibliothecaire;
	@FXML
	private JFXButton add;
	@FXML
	private JFXButton delete;
	@FXML
	private AnchorPane bibliothecaires;
	private ServicesBibliothecaire ser = new ServicesBibliothecaire();
	@FXML
	private TableColumn<Bibliothecaire, ImageView> img;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			// TODO
			refresh_view_bibliothecaire("");
		} catch (SQLException ex) {
			Logger.getLogger(BibliothecairesController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void rechercher_bibliothecaire(KeyEvent event) throws SQLException {
		refresh_view_bibliothecaire(field_rechercher_bibliothecaire.getText());
	}

	@FXML
	private void ajouter_bibliothecaire(ActionEvent event) throws IOException {
//		bibliothecaires.getChildren().clear();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("register.fxml"));
		Parent n = (Parent) loader.load();
		Stage stage = new Stage();
		stage.setTitle("Ajouter bibliothecaire ");
		Scene scene = new Scene(n);
		stage.setResizable(false);
//        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		stage.setScene(scene);
		stage.show();
//		bibliothecaires.getChildren().add(n);
	}

	@FXML
	private void supprimer_bibliothecaire(ActionEvent event) throws IOException {
//		bibliothecaires.getChildren().clear();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("delete_account.fxml"));
		Parent n = (Parent) loader.load();
		Delete_accountController del = loader.getController();
		Stage stage = new Stage();
		stage.setTitle("Supprimer bibliothecaire ");
		Scene scene = new Scene(n);
		stage.setResizable(false);
//        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		stage.setScene(scene);
		stage.show();
	}

	private void refresh_view_bibliothecaire(String input_text) throws SQLException {
		ObservableList<Bibliothecaire> list = getBibiothecaireList(input_text);
		nom.setCellValueFactory(new PropertyValueFactory<Bibliothecaire, String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<Bibliothecaire, String>("prenom"));
		email.setCellValueFactory(new PropertyValueFactory<Bibliothecaire, String>("email"));
		cin.setCellValueFactory(new PropertyValueFactory<Bibliothecaire, String>("cin"));
		adresse.setCellValueFactory(new PropertyValueFactory<Bibliothecaire, String>("adresse"));
		datenaissance.setCellValueFactory(new PropertyValueFactory<Bibliothecaire, String>("dateNaissance"));
		tel.setCellValueFactory(new PropertyValueFactory<Bibliothecaire, Integer>("numTel"));
		img.setCellValueFactory(new PropertyValueFactory<Bibliothecaire, ImageView>("profil"));
		list_bibliothecaire.setItems(list);
	}

	private ObservableList<Bibliothecaire> getBibiothecaireList(String input_text) throws SQLException {
		ObservableList<Bibliothecaire> list = FXCollections.observableArrayList();
		for (Bibliothecaire l : ser.search(input_text)) {
			list.add(l);
		}
		return list;
	}
}
