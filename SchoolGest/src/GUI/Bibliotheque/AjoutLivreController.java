/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque;

import Entite.Bibliotheque.Bibliotheque;
import Entite.Bibliotheque.Livre;
import Services.Bibliotheque.ServicesBibliotheque;
import Services.Bibliotheque.ServicesLivres;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author william
 */
public class AjoutLivreController implements Initializable {

	@FXML
	private TextField titre;
	@FXML
	private TextField auteur;
	@FXML
	private TextField editeur;
	@FXML
	private TextField categorie;
	@FXML
	private TextField taille;
	@FXML
	private DatePicker date_sortie;
	@FXML
	private TextField quantite;
	@FXML
	private Label error;
	@FXML
	private ImageView image;
	@FXML
	private JFXButton ajouter;
	private ServicesLivres ser = new ServicesLivres();
	private ServicesBibliotheque ser_bib = new ServicesBibliotheque();
	private int id_bibliotheque = 0;
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	

	@FXML
	private void ajouter_livre(ActionEvent event) {
		try {
			LocalDate datel = date_sortie.getValue();
			DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			String datef = dateformat.format(datel);
			int taillef = Integer.valueOf(taille.getText());
			int quantitef = Integer.valueOf(quantite.getText());
			int id_bibliothequef = this.id_bibliotheque;
			Livre l = new Livre(id_bibliothequef, titre.getText(), editeur.getText(), auteur.getText(), categorie.getText(), datef, taillef, quantitef, "http://localhost/mobile/book.jpg", null);
			if(ser.ajouter(l)){
				error.setText("Ajout réussi ");
			}else{
				error.setText("Ajout impossible !!!");
			}
		} catch (Exception e) {
			error.setText(e.getMessage());
		}
	}
	public void init(String email) throws SQLException{
		Bibliotheque b = ser_bib.getBibliotheque(email);
		if(b == null){
			System.out.println("Vous n'êtes pas connecté à une bibliothèque!!!");
		}else{
			this.id_bibliotheque = b.getId();
		}
	}
}
