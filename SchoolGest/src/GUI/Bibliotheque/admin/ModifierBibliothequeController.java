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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author william
 */
public class ModifierBibliothequeController implements Initializable {

	@FXML
	private JFXButton modifier_bibliotheque;
	@FXML
	private JFXTextField nom;
	@FXML
	private JFXTextField capacite;
	@FXML
	private JFXTextField adresse;
	@FXML
	private ImageView close;
	@FXML
	private Label error;
	private Bibliotheque b;
	private ServicesBibliotheque ser = new ServicesBibliotheque();
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	

	@FXML
	private void close_window(MouseEvent event) {
	}

	@FXML
	private void modifier_bibliotheque(ActionEvent event) {
		error.setText("");
		if(adresse.getText().equals("") || nom.getText().equals("") || capacite.getText().equals("")){
			error.setText("Veillez remplir tous les champs !!!");
		}else{
			int cap = 0;
			try {
				cap = Integer.valueOf(capacite.getText());
				if(cap < 0){
					error.setText("La capacité est un entier positif");
				}else{
					try {
						b.setAdresse(adresse.getText());
						b.setNom(nom.getText());
						b.setCapacite(cap);
						ser.update(b);
						error.setText("Modification effectuée avec succès");
					} catch (Exception e) {
						System.out.println(e);
						error.setText("Echec de modification !!! Vérifiez que l'adresse saisie n'est pas déjà utilisée");
					}
				}
			} catch (Exception e) {
				error.setText("La capacité est un nombre");
			}
		}
	}
	public void getId(Bibliotheque b){
		this.b = b;
		adresse.setText(b.getAdresse());
		nom.setText(b.getNom());
		capacite.setText(String.valueOf(b.getCapacite()));
		System.out.println(b);
	}
}
