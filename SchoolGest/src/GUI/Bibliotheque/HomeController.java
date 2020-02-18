/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI.Bibliotheque;

import Entite.Bibliotheque.Livre;
import Services.Bibliotheque.ServicesLivres;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javax.swing.text.DateFormatter;

/**
 *
 * @author william
 */
public class HomeController implements Initializable{

	@FXML
	private TextField editeur;
	@FXML
	private TextField auteur;
	@FXML
	private TextField titre;
	@FXML
	private Button ajouter;
	@FXML
	private TextField categorie;
	@FXML
	private DatePicker date_sortie;
	@FXML
	private TextField quantite;
	@FXML
	private TextField id_bibliotheque;
	@FXML
	private TextField taille;
	@FXML
	private Label error;
	@FXML
	private AnchorPane biblio;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		error.setText("");
	}

	@FXML
	private void ajout_livre(ActionEvent event) {
		Popup confirmation = new Popup();
		try {
			LocalDate datel = date_sortie.getValue();
			DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			String datef = dateformat.format(datel);
			int taillef = Integer.valueOf(taille.getText());
			int quantitef = Integer.valueOf(quantite.getText());
			int id_bibliothequef = Integer.valueOf(id_bibliotheque.getText());
			Livre l = new Livre(id_bibliothequef, titre.getText(), editeur.getText(), auteur.getText(), categorie.getText(), datef, taillef, quantitef);
			ServicesLivres ser = new ServicesLivres();
			ser.ajouter(l);
			Label error_msg = new Label("Ajout réussie !!!");
			error_msg.setStyle(" -fx-background-color: green;"); 
			confirmation.getContent().add(error_msg);
//			confirmation.centerOnScreen();
//			confirmation.addEventHandler(EventType.ROOT, eventHandler);
//			EventHandler eventHandler = new EventHandler() {
//				@Override
//				public void handle(Event event) {
//					
//				}
//			}
//			if (!confirmation.isShowing()) 
//                    confirmation.show(biblio, 500, 500); 
//                else
//                    confirmation.hide(); 
		} catch (Exception e) {
			error.setText(e.getMessage());
		}
	}
    
}
