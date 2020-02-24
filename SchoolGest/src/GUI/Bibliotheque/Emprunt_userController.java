/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque;

import Entite.Bibliotheque.Emprunt;
import Entite.Bibliotheque.Emprunteur;
import Entite.Bibliotheque.Etat;
import Entite.Bibliotheque.LivreEmprunte;
import Services.Bibliotheque.ServicesEmprunt;
import Services.Bibliotheque.ServicesEmprunteur;
import Services.Bibliotheque.ServicesLivreEmprunte;
import Services.Bibliotheque.ServicesLivres;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author william
 */
public class Emprunt_userController implements Initializable {

	private ServicesLivres ser = new ServicesLivres();
	private ServicesEmprunt ser_emp = new ServicesEmprunt();
	private ServicesEmprunteur ser_user = new ServicesEmprunteur();
	private ServicesLivreEmprunte ser_livre = new ServicesLivreEmprunte();
	@FXML
	private Label titre;
	@FXML
	private Label auteur;
	@FXML
	private Label id_livre;
	@FXML
	private Label editeur;
	@FXML
	private Label etat;
	@FXML
	private Label id_emprunt;
	@FXML
	private Label datesortie;
	@FXML
	private ImageView etat_ok;
	@FXML
	private ImageView etat_ko;
	@FXML
	private ImageView a_rendre;
	@FXML
	private ImageView etat_attente;
	@FXML
	private ImageView img_livre;
	@FXML
	private Label categorie;
	@FXML
	private Button btn_annuler;
	@FXML
	private Label day_confirmation;
	@FXML
	private Label month_confirmation;
	@FXML
	private Label year_confirmation;
	@FXML
	private Label day_retour;
	@FXML
	private Label month_retour;
	@FXML
	private Label year_retour;
	@FXML
	private Label day_emprunt;
	@FXML
	private Label month_emprunt;
	@FXML
	private Label year_emprunt;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	public void init(LivreEmprunte e) throws SQLException {
		id_emprunt.setText("Id : " + String.valueOf(e.getId_emprunt()));
		titre.setText(e.getTitre());
		auteur.setText(e.getAuteur());
		editeur.setText(e.getEditeur());
		etat.setText(e.getEtat().toString());
		id_livre.setText(String.valueOf(e.getId()));
		try {
			String[] dateC = e.getDateConfirmation().split("-");
			day_confirmation.setText(dateC[2]);
			month_confirmation.setText(dateC[1]);
			year_confirmation.setText(dateC[0]);
		} catch (Exception ex) {
			System.out.println(ex);
			day_confirmation.setText("jj");
			month_confirmation.setText("mois");
			year_confirmation.setText("année");
		}
		try {
			String[] dateE = e.getDateEmprunt().split("-");
			day_emprunt.setText(dateE[2]);
			month_emprunt.setText(dateE[1]);
			year_emprunt.setText(dateE[0]);

		} catch (Exception ex) {
			System.out.println(ex);
			day_emprunt.setText("jj");
			month_emprunt.setText("mois");
			year_emprunt.setText("année");
		}
		try {
			String[] dateR = e.getDateRendu().split("-");
			day_retour.setText(dateR[2]);
			month_retour.setText(dateR[1]);
			year_retour.setText(dateR[1]);
		} catch (Exception ex) {
			System.out.println(ex);
			day_retour.setText("jj");
			month_retour.setText("mois");
			year_retour.setText("année");
		}
		datesortie.setText(e.getDateSortie());
		categorie.setText(e.getCategorie());
		img_livre.setImage(new Image(e.getImg()));
		if (e.getEtat() == Etat.rendu) {
			etat_ok.setVisible(true);
			etat_ko.setVisible(false);
			etat_attente.setVisible(false);
			a_rendre.setVisible(false);
			btn_annuler.setVisible(false);
		} else if (e.getEtat() == Etat.refus) {
			etat_ok.setVisible(false);
			etat_ko.setVisible(true);
			etat_attente.setVisible(false);
			a_rendre.setVisible(false);
			btn_annuler.setVisible(false);
		} else if (e.getEtat() == Etat.attente) {
			etat_ok.setVisible(false);
			etat_ko.setVisible(false);
			etat_attente.setVisible(true);
			a_rendre.setVisible(false);
			btn_annuler.setVisible(true);
			btn_annuler.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						ser_emp.delete(e.getId_emprunt());
					} catch (SQLException ex) {
						Logger.getLogger(Emprunt_userController.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			});
		} else if (e.getEtat() == Etat.accepte) {
			etat_ok.setVisible(false);
			etat_ko.setVisible(false);
			etat_attente.setVisible(false);
			a_rendre.setVisible(true);
			btn_annuler.setVisible(false);
		}
	}

}
