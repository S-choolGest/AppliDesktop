/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque;

import Entite.Bibliotheque.Bibliotheque;
import Entite.Utilisateur.Bibliothecaire;
import Services.Bibliotheque.ServicesBibliotheque;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author william
 */
public class GestionBibliothequeController implements Initializable {

	@FXML
	private TableView<Bibliotheque> list_bibliotheque;
	@FXML
	private TableColumn<Bibliotheque, String> nom;
	@FXML
	private TableColumn<Bibliotheque, Integer> capacite;
	@FXML
	private TableColumn<Bibliotheque, String> adresse;
	@FXML
	private TableColumn<Bibliotheque, String> bibliothecaire;
	@FXML
	private JFXTextField field_rechercher_bibliotheque;
	@FXML
	private JFXButton add;
	@FXML
	private JFXButton delete;
	@FXML
	private JFXButton affecter;
	@FXML
	private TableColumn<Bibliotheque, Integer> id;
	@FXML
	private Label error;
	private ServicesBibliotheque ser = new ServicesBibliotheque();
	@FXML
	private TextField email;
	@FXML
	private JFXButton modifier;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			// TODO
			refresh_view_bibliotheque("");
		} catch (SQLException ex) {
			Logger.getLogger(GestionBibliothequeController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
	private void rechercher_bibliotheque(KeyEvent event) throws SQLException {
		refresh_view_bibliotheque(field_rechercher_bibliotheque.getText());
	}

	@FXML
	private void ajouter_bibliotheque(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ajouter_bibliotheque.fxml"));
		Parent n = (Parent) loader.load();
		Stage stage = new Stage();
		stage.setTitle("Ajouter bibliotheque ");
		Scene scene = new Scene(n);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private void supprimer_bibliotheque(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("supprimer_bibliotheque.fxml"));
		Parent n = (Parent) loader.load();
		Supprimer_bibliothequeController del = loader.getController();
		Stage stage = new Stage();
		stage.setTitle("Supprimer bibliotheque ");
		Scene scene = new Scene(n);
		stage.setResizable(false);
//        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private void affecter_bibliothecaire(ActionEvent event) {
		if (!email.getText().equals("")) {
			Bibliotheque b = list_bibliotheque.getSelectionModel().getSelectedItem();
			if (b != null) {
				b.setEmail(email.getText());
				try {
					ser.affecter(b);
					error.setText("Affectation Réussite");
				} catch (Exception e) {
					System.out.println(e);
					error.setText("Affectation impossible !!!(Vérifiez que le bibliothécaire n'est pas déjà affecté à une bibliothèque)");
				}
			} else {
				error.setText("veillez selectionner une bibliothèque dans la liste à gauche");
			}
		} else {
			error.setText("Champ CIN vide !!!");
		}
	}

	private void refresh_view_bibliotheque(String input_text) throws SQLException {
		ObservableList<Bibliotheque> list = getBibiothequeList(input_text);
		id.setCellValueFactory(new PropertyValueFactory<Bibliotheque, Integer>("id"));
		nom.setCellValueFactory(new PropertyValueFactory<Bibliotheque, String>("nom"));
		capacite.setCellValueFactory(new PropertyValueFactory<Bibliotheque, Integer>("capacite"));
		adresse.setCellValueFactory(new PropertyValueFactory<Bibliotheque, String>("adresse"));
		bibliothecaire.setCellValueFactory(new PropertyValueFactory<Bibliotheque, String>("email"));
//		action.setCellValueFactory();
		list_bibliotheque.setItems(list);
	}

	private ObservableList<Bibliotheque> getBibiothequeList(String input_text) throws SQLException {
		ObservableList<Bibliotheque> list = FXCollections.observableArrayList();
		for (Bibliotheque l : ser.search(input_text)) {
			list.add(l);
			System.out.println(l);
		}
		return list;
	}

	@FXML
	private void modifier_bibliotheque(ActionEvent event) throws IOException {
		error.setText("");
		Bibliotheque b = list_bibliotheque.getSelectionModel().getSelectedItem();
		if (b != null) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("ModifierBibliotheque.fxml"));
			Parent n = (Parent) loader.load();
			ModifierBibliothequeController edit = loader.getController();
			edit.getId(b);
			Stage stage = new Stage();
			stage.setTitle("Modifier bibliotheque ");
			Scene scene = new Scene(n);
			stage.setResizable(false);
//        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
			stage.setScene(scene);
			stage.show();
		}else{
			error.setText("Aucune bibliotheque sélectionnée!!!");
		}
	}
}
