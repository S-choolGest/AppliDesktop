/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque;

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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author william
 */
public class Ajouter_bibliothequeController implements Initializable {

	@FXML
	private JFXTextField nom;
	@FXML
	private JFXButton ajouter;
	@FXML
	private JFXTextField capacite;
	@FXML
	private JFXTextField adresse;
	@FXML
	private ImageView close;
	@FXML
	private Label error;
	private ServicesBibliotheque ser = new ServicesBibliotheque();
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	private void ajouter_bibliotheque(ActionEvent event) throws SQLException {
		Boolean add = true;
		error.setText("");
		int cap = 0;
		try {
			cap = Integer.valueOf(capacite.getText());
			if(cap < 0){
				error.setText("la capacité est un nombre positif");
				add = false;
			}
		} catch (Exception e) {
			System.out.println(e);
			error.setText("la capacité est un nombre");
			add = false;
		}
		if(adresse.getText().equals("") || nom.getText().equals("")){
			error.setText("Veillez remplir tous les champs !!!");
			add = false;
		}
		if(add == true){
			Bibliotheque b = new Bibliotheque(nom.getText(), cap, adresse.getText());
			try {
				ser.ajouter(b);
				error.setText("Ajout réussi ");
			} catch (Exception e) {
				System.out.println(e);
				error.setText("Echec d'ajout !!! (vérifiez qu'il n'existe pas une bibliothèque possédant cette adresse)");
			}
		}
	}

	@FXML
	private void close_window(MouseEvent event) {
	}

}
