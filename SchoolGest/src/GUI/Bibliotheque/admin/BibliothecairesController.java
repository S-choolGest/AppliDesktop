/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque.admin;

import Entite.Bibliotheque.Livre;
import Entite.Utilisateur.Bibliothecaire;
import Entite.Utilisateur.Utilisateur;
import Entite.Utilisateur.UtilisateurView;
import Services.Utilisateur.ServicesBibliothecaire;
import Services.Utilisateur.ServicesUtilisateur;
import Services.Utilisateur.ServicesUtilisateurView;
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
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author william
 */
public class BibliothecairesController implements Initializable {

	@FXML
	private TableView<UtilisateurView> list_bibliothecaire;
	private TableColumn<UtilisateurView, Integer> id;
	@FXML
	private TableColumn<UtilisateurView, String> nom;
	@FXML
	private TableColumn<UtilisateurView, String> prenom;
	@FXML
	private TableColumn<UtilisateurView, String> email;
	@FXML
	private TableColumn<UtilisateurView, String> cin;
	@FXML
	private TableColumn<UtilisateurView, String> adresse;
	@FXML
	private TableColumn<UtilisateurView, String> datenaissance;
	@FXML
	private TableColumn<UtilisateurView, Integer> tel;
	@FXML
	private JFXTextField field_rechercher_bibliothecaire;
	@FXML
	private JFXButton add;
	@FXML
	private JFXButton delete;
	@FXML
	private AnchorPane bibliothecaires;
	private ServicesBibliothecaire ser = new ServicesBibliothecaire();
	private ServicesUtilisateur ser_user = new ServicesUtilisateur();
	@FXML
	private TableColumn<UtilisateurView, ImageView> img;
	private AnchorPane body;
	private Stage stage;
	public Utilisateur user;
	@FXML
	private TableColumn<UtilisateurView, String> type;
	private ServicesUtilisateurView ser_view = new ServicesUtilisateurView();
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
	
	public void getBody(AnchorPane body) {
		this.body = body;
	}

	public void getStage(Stage stage) {
		this.stage = stage;
	}

	public void getInfo(Utilisateur u) throws SQLException, SQLException {
		this.user = u;
		refresh_view_bibliothecaire("");
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
		stage.setTitle("Ajouter Utilisateur ");
		Scene scene = new Scene(n);
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
//        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		stage.setScene(scene);
		stage.show();
//		bibliothecaires.getChildren().add(n);
	}

	@FXML
	private void supprimer_bibliothecaire(ActionEvent event) throws IOException, SQLException {
//		bibliothecaires.getChildren().clear();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("delete_account.fxml"));
		Parent n = (Parent) loader.load();
		Delete_accountController del = loader.getController();
		Stage stage = new Stage();
		stage.setTitle("Supprimer Utilisateur ");
		Scene scene = new Scene(n);
		stage.setResizable(false);
		del.getStage(stage);
		del.getBody(body);
//		del.getInfo(list_bibliothecaire.getSelectionModel().getSelectedItem());
		stage.initStyle(StageStyle.UNDECORATED);
//        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		stage.setScene(scene);
		stage.show();
	}

	private void refresh_view_bibliothecaire(String input_text) throws SQLException {
		ObservableList<UtilisateurView> list = getUtilisateurList(input_text);
		nom.setCellValueFactory(new PropertyValueFactory<UtilisateurView, String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<UtilisateurView, String>("prenom"));
		email.setCellValueFactory(new PropertyValueFactory<UtilisateurView, String>("email"));
		cin.setCellValueFactory(new PropertyValueFactory<UtilisateurView, String>("cin"));
		adresse.setCellValueFactory(new PropertyValueFactory<UtilisateurView, String>("adresse"));
		datenaissance.setCellValueFactory(new PropertyValueFactory<UtilisateurView, String>("dateNaissance"));
		tel.setCellValueFactory(new PropertyValueFactory<UtilisateurView, Integer>("numTel"));
		img.setCellValueFactory(new PropertyValueFactory<UtilisateurView, ImageView>("profil"));
		type.setCellValueFactory(new PropertyValueFactory<UtilisateurView, String>("type"));
		list_bibliothecaire.setItems(list);
	}

	private ObservableList<Bibliothecaire> getBibiothecaireList(String input_text) throws SQLException {
		ObservableList<Bibliothecaire> list = FXCollections.observableArrayList();
		for (Bibliothecaire l : ser.search(input_text)) {
			list.add(l);
		}
		return list;
	}

	private ObservableList<UtilisateurView> getUtilisateurList(String input_text) throws SQLException {
		ObservableList<UtilisateurView> list = FXCollections.observableArrayList();
		for (Utilisateur l : ser_user.search(input_text)) {
			list.add(ser_view.transform(l));
		}
		return list;
	}
}
