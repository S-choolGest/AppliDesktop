/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque.admin;

import Entite.Utilisateur.Bibliothecaire;
import Entite.Utilisateur.Utilisateur;
import Services.Utilisateur.ServicesBibliothecaire;
import Services.Utilisateur.ServicesUtilisateur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author william
 */
public class RegisterController implements Initializable {

	@FXML
	private JFXTextField nom;
	@FXML
	private JFXButton creer;
	@FXML
	private JFXTextField prenom;
	@FXML
	private JFXTextField tel;
	@FXML
	private JFXTextField email;
	@FXML
	private JFXTextField adresse;
	@FXML
	private JFXTextField cin;
	private ServicesBibliothecaire ser = new ServicesBibliothecaire();
	@FXML
	private ImageView close;
	@FXML
	private AnchorPane register;
	@FXML
	private Label error;
    @FXML
    private ChoiceBox<String> TypeAjout;
    @FXML
    private DatePicker DateAjoutUtil;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
            ObservableList<String> Type =FXCollections.observableArrayList("Etudiant", "Enseignant","Scolarite","Admin","bibliothecaire","Parent");
            TypeAjout.setItems(Type);

	}

	private void ajouter_bibliothecaire(ActionEvent event) throws SQLException {
		error.setText("");
		String date = "2020-2-2";
		try {
			int num = Integer.valueOf(tel.getText());
			if (tel.getText().length() == 8) {
				Bibliothecaire b = new Bibliothecaire(nom.getText(), prenom.getText(), email.getText(), cin.getText(), cin.getText(), num, date, adresse.getText(), 5, "http://localhost/mobile/icons8_user_male_200px.png");
				if (ser.ajouter(b) == true) {
					System.out.println("ajout reussie");
					error.setText("ajout reussie");

				} else {
					System.out.println("echec d ajout");
					error.setText("echec d ajout");
				}
			} else {
				error.setText("Le numéro de téléphone est un nombre de 8 chiffres");
			}
		} catch (Exception e) {
			System.out.println(e);
			error.setText("Echec d'ajout!!! infos: "+e.toString());
		}
	}

	@FXML
	private void close_window(MouseEvent event) {
		register.disableProperty();
	}

    @FXML
    private void ajouter_Utilisateur(ActionEvent event) {
        String DateU = null;
        int TelephoneU = 0;
        String nomU = nom.getText();
        String  prenomU = prenom.getText();
        if(tel.getText().length()!=0){
          TelephoneU= Integer.parseInt(tel.getText());
        }
        String emailU = email.getText();
        String CINU = cin.getText();
        String AdresseU = adresse.getText();
        String TypeU = TypeAjout.getSelectionModel().getSelectedItem();
        if(DateAjoutUtil.getValue()!=null){
         DateU = DateAjoutUtil.getValue().toString();
        }
         if(nomU.length()==0||prenomU.length()==0||emailU.length()==0||CINU.length()<=0 && CINU.length()>8||AdresseU.length()==0||TypeAjout.getSelectionModel().getSelectedItem()==null||DateU.length()==0){
             
             error.setVisible(true);
        }else{
        int TypeUi = -1;
        switch (TypeU){
            case "Etudiant":
                TypeUi = 0;
                break;
            case "Enseignant":
                TypeUi = 1;
                break;
            case "Scolarite":
                TypeUi =2;
                break;
            case "Admin":
                TypeUi = 3 ;
                break;
            case "bibliothecaire":
                TypeUi = 5;
                break;
            case "Parent":
                TypeUi = 4;
                break;
            default:
                break;
        }
        
       
      
        
        ServicesUtilisateur su = new ServicesUtilisateur();
            try {
                System.out.println(nomU);
                Utilisateur U = new Utilisateur(nomU, prenomU, emailU, CINU, CINU, TelephoneU,DateU,AdresseU,TypeUi, "https://localhost/upload/uploads/icons8_user_male_200px.png");
                su.ajouter(U);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
       }   
    }

}
