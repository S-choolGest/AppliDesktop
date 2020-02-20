/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque;

import Entite.Bibliotheque.Emprunt;
import Entite.Bibliotheque.Emprunteur;
import Entite.Bibliotheque.Etat;
import Services.Bibliotheque.ServicesEmprunt;
import Services.Bibliotheque.ServicesEmprunteur;
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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author william
 */
public class EmpruntController implements Initializable {

	@FXML
	private Label nom_emprunteur;
	@FXML
	private Label prenom_emprunteur;
	@FXML
	private Label id_livre;
	@FXML
	private Label id_emprunteur;
	@FXML
	private Label id_emprunt;
	@FXML
	private Label dateemprunt;
	@FXML
	private Label dateconfirmation;
	@FXML
	private Label dateretour;
	private ServicesEmprunteur ser = new ServicesEmprunteur();
	private ServicesEmprunt ser_emp = new ServicesEmprunt();
	@FXML
	private Label tel_emprunteur;
	@FXML
	private Label email_emprunteur;
	@FXML
	private Label etat;
	@FXML
	private Button btn_accepter;
	@FXML
	private Button btn_refuser;
	@FXML
	private Button btn_retour;
	@FXML
	private ImageView etat_ok;
	@FXML
	private AnchorPane line_emprunt;
	@FXML
	private ImageView etat_ko;
	@FXML
	private ImageView img_livre;
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	
	
	public void init(int id) throws SQLException{
		id_emprunt.setText("Id : "+String.valueOf(id));
		Emprunteur e = ser.read(id);
		nom_emprunteur.setText(e.getNom());
		prenom_emprunteur.setText(e.getPrenom());
		email_emprunteur.setText(e.getEmail());
		etat.setText(e.getEtat().toString());
		id_emprunteur.setText(String.valueOf(e.getIdEmprunteur()));
		tel_emprunteur.setText(String.valueOf(e.getTel()));
		id_livre.setText(String.valueOf(e.getIdLivre()));
		dateconfirmation.setText(e.getDateConfirmation());
		dateemprunt.setText(e.getDateEmprunt());
		dateretour.setText(e.getDateRendu());
		img_livre.setImage(new Image(e.getImg()));
		if(e.getEtat() == Etat.rendu){
			btn_accepter.setVisible(false);
			btn_refuser.setVisible(false);
			btn_retour.setVisible(false);
			etat_ok.setVisible(true);
			etat_ko.setVisible(false);
		}else if(e.getEtat() == Etat.refus){
			btn_accepter.setVisible(false);
			btn_refuser.setVisible(false);
			btn_retour.setVisible(false);
			etat_ok.setVisible(false);
			etat_ko.setVisible(true);
		}
		else if(e.getEtat() != Etat.attente){
			btn_accepter.setVisible(false);
			btn_refuser.setVisible(false);
			btn_retour.setVisible(true);
			etat_ok.setVisible(false);
			etat_ko.setVisible(false);
		}else{
			btn_accepter.setVisible(true);
			btn_refuser.setVisible(true);
			btn_retour.setVisible(false);
			etat_ok.setVisible(false);
			etat_ko.setVisible(false);
		}
		btn_accepter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					ser_emp.confirmerEmprunt(id, Etat.accepte);
				} catch (SQLException ex) {
					Logger.getLogger(EmpruntController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
		btn_refuser.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					ser_emp.confirmerEmprunt(id, Etat.refus);
				} catch (SQLException ex) {
					Logger.getLogger(EmpruntController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
		btn_retour.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					ser_emp.confirmerEmprunt(id, Etat.rendu);
				} catch (SQLException ex) {
					Logger.getLogger(EmpruntController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
	}	
	
}
