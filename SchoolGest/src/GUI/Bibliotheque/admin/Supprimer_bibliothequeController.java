/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque.admin;

import Entite.Bibliotheque.Bibliotheque;
import Services.Bibliotheque.ServicesBibliotheque;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author william
 */
public class Supprimer_bibliothequeController implements Initializable {

	@FXML
	private JFXButton supprimer;
	@FXML
	private JFXTextField nom;
	@FXML
	private JFXTextField adresse;
	@FXML
	private ImageView close;
	@FXML
	private Label error;
	private ServicesBibliotheque ser = new ServicesBibliotheque();
	@FXML
	private JFXTextField capacite;
	private boolean delete = false;
	private GestionBibliothequeController g_bib = new GestionBibliothequeController();
	private Stage stage;
	private Bibliotheque b;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	private void supprimer_bibliotheque(ActionEvent event) throws SQLException {
		if (delete == true) {
			int id = ser.searchByAdresse(adresse.getText()).getId();
			if (ser.delete(id)) {
				error.setText("Suppression reussite !!!");
				this.g_bib.rechercher_bibliotheque(null);
			} else {
				error.setText("Echec de suppression !!!");
			}
		} else {
			error.setText("Suppression impossible !!!");
		}
	}

	@FXML
	private void rechercher_bibliotheque(KeyEvent event) {
		try {
			error.setText("");
			b = ser.searchByAdresse(adresse.getText());
			if (b != null) {
				nom.setText(b.getNom());
				capacite.setText(String.valueOf(b.getCapacite()));
				delete = true;
			} else {
				nom.setText("");
				capacite.setText("");
				delete = false;
				error.setText("Adresse incorrecte!!!");
			}
		} catch (Exception e) {
			error.setText("Adresse incorrecte!!!");
			nom.setText("");
			capacite.setText("");
			delete = true;
			System.out.println(e);
		}
	}

	@FXML
	private void close_window(MouseEvent event) {
		this.stage.close();
	}

	public void getController(GestionBibliothequeController g) {
		this.g_bib = g;
	}

	public void getStage(Stage stage) {
		this.stage = stage;
	}

	public void getId(Bibliotheque b) {
		if (b != null) {
			delete = true;
			this.b = b;
			adresse.setText(b.getAdresse());
			nom.setText(b.getNom());
			capacite.setText(String.valueOf(b.getCapacite()));
		}
		System.out.println(b);
	}
}
