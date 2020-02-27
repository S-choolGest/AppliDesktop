/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Bibliotheque;

import Entite.Utilisateur.Bibliothecaire;
import Entite.Utilisateur.Utilisateur;
import Services.Utilisateur.ServicesBibliothecaire;
import Services.Utilisateur.ServicesUtilisateur;
import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author william
 */
public class Update_accountController implements Initializable {

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
	private JFXTextField cin;
	@FXML
	private JFXTextField adresse;
	@FXML
	private ImageView close;
	@FXML
	private Label error;
	@FXML
	private ImageView btn_camera;
	@FXML
	private DatePicker date_naissance;
	ServicesUtilisateur ser = new ServicesUtilisateur();
	@FXML
	private JFXTextField id;
	@FXML
	private JFXPasswordField password;
	@FXML
	private JFXPasswordField password2;
	@FXML
	private JFXButton upload_image;
	private Utilisateur user;
	private Stage stage;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	@FXML
	private void modifier_compte(ActionEvent event) {
		error.setText("");
		LocalDate datel = date_naissance.getValue();
		try {
			DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			String date = dateformat.format(datel);
			int num = Integer.valueOf(tel.getText());
			if (tel.getText().length() == 8) {
				Utilisateur b = new Utilisateur(this.user.getId(), nom.getText(), prenom.getText(), email.getText(), password.getText(), cin.getText(), num, date, adresse.getText(), this.user.getType(), "http://localhost/mobile/icons8_user_male_200px.png");
				Boolean rs = false;
				if (password.getText().length() != 0 && password2.getText().length() != 0) {
					if (password.getText().equals(password2.getText())) {
						rs = ser.updatePassword(b);
						if (ser.update(b)) {
							error.setText("Mise à jour réussite");
						} else {
							error.setText("Echec de mise à jour du compte");
						}
					} else {
						error.setText("Le mot de passe de confirmation est différent du nouveau mot de passe !!!");
					}
				}
//				ser.update(b);
				if (rs == true) {
					System.out.println("Mise à jour effectuée");
					error.setText("Mise à jour effectuée");

				} else {
					System.out.println("echec de mise à jour du compte !!!");
					error.setText("echec de mise à jour du compte !!!");
				}
			} else {
				error.setText("Le numéro de téléphone est un nombre de 8 chiffres !!!");
			}
		} catch (Exception e) {
			error.setText("echec de mise à jour du compte!!! infos: " + e.toString());
		}
	}

	@FXML
	private void close_window(MouseEvent event) {
		this.stage.close();
	}

	@FXML
	private void take_picture(MouseEvent event) throws IOException {
		Webcam webcam = Webcam.getDefault();
		webcam.open();
		try {
			ImageIO.write(webcam.getImage(), "png", new File("profil_img.png"));
		} catch (IOException ex) {
			System.out.println("error");
			Logger.getLogger(CameraController.class.getName()).log(Level.SEVERE, null, ex);
		}
		webcam.close();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("camera.fxml"));
		Parent n = (Parent) loader.load();
		CameraController del = loader.getController();
		Stage stage = new Stage();
		stage.setTitle("Edutech : Bibliotheque : Gestion : Compte : Profil picture");
		Scene scene = new Scene(n);
		stage.setResizable(false);
//        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		stage.setScene(scene);
		stage.show();
	}

	void setId(String text) {
		id.setText(text);
		try {
			// TODO
			Utilisateur u = ser.search(Integer.valueOf(id.getText()));
			nom.setText(u.getNom());
			prenom.setText(u.getPrenom());
			tel.setText(String.valueOf(u.getNumTel()));
			email.setText(u.getEmail());
			cin.setText(u.getCin());
			adresse.setText(u.getAdresse());
			if (u.getDateNaissance() != null) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate datel = LocalDate.parse(u.getDateNaissance(), formatter);
				date_naissance.setValue(datel);
			}

		} catch (SQLException ex) {
			Logger.getLogger(Update_accountController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void getInfos(Utilisateur u) {
		// TODO
		this.user = u;
		nom.setText(u.getNom());
		prenom.setText(u.getPrenom());
		tel.setText(String.valueOf(u.getNumTel()));
		email.setText(u.getEmail());
		cin.setText(u.getCin());
		adresse.setText(u.getAdresse());
		if (u.getDateNaissance() != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate datel = LocalDate.parse(u.getDateNaissance(), formatter);
			date_naissance.setValue(datel);
		}
	}

	public void getStage(Stage stage) {
		this.stage = stage;
	}
}
